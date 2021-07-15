package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
//@RunWith(MockitoJunitRunner.class)
public class EmployeServiceTestEval {

    @InjectMocks
    public EmployeService employeService;

    @Mock
    public EmployeRepository employeRepository;

// Scénarii de test méthode calculPerformanceCommercial
//  - cas nominal avec 1 matricule valide (commercial), 1 CA traité valide et 1 objectif CA valide
//  - cas avec 1 matricule valide et 1 CA traité valide
//  - cas avec 1 matricule valide et 1 objectif CA valide
//  - cas avec seulement 1 matricule valide
//  - cas avec seulement 1 CA traité valide
//  - cas avec seulement 1 objectif CA valide
//  - CA traité < de plus de 20%
//  - CA traité < de 5% à 20%
//  - CA traité compris entre -5% et 5%
//  - CA traité > de 5% à 20%
//  - Ca traité > de plus de 20%
//  - perf > moy des perf
//  - CA traité < 0
//  - matricule nul
//  - matricule invalide (manager & technicien)
//  - obj CA < 0
//  - obj CA = CA traité
//  - obj CA = CA traité
//  - obj CA > CA traité
//  - obj CA < CA traité
//  - obj CA > CA traité
//  - obj CA < CA traité
//
// Cas nominaux :
//  1 : Si le chiffre d'affaire est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
//  2 : Si le chiffre d'affaire est inférieur entre 20% et 5% par rapport à l'ojectif fixé, il perd 2 de performance (dans la limite de la performance de base)
//  3 : Si le chiffre d'affaire est entre -5% et +5% de l'objectif fixé, la performance reste la même.
//  4 : Si le chiffre d'affaire est supérieur entre 5 et 20%, il gagne 1 de performance
//  5 : Si le chiffre d'affaire est supérieur de plus de 20%, il gagne 4 de performance
//
//  Si la performance ainsi calculée est supérieure à la moyenne des performances des commerciaux, il reçoit + 1 de performance.

//    calculPerformanceCommercial(String matricule, Long caTraite, Long objectifCa)

    @Test
    void testCalculPerformanceCommercialCatraiteNull() {

        //Given
        String matricule = "C00001";
        Long caTraite = null;
        Long objCA = 7000L;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaires traité ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le chiffre d'affaires traité ne peut être négatif ou null !");

    }

    @Test
    void testCalculPerformanceCommercialCatraiteNegatif() {

        //Given
        String matricule = "C00001";
        Long caTraite = -5000L;
        Long objCA = 7000L;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaires traité ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le chiffre d'affaires traité ne peut être négatif ou null !");

    }

    @Test
    void testCalculPerformanceCommercialObjectifcaNull() {

        //Given
        String matricule = "C00001";
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = null;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("L'objectif de chiffre d'affaires ne peut être négatif ou null !");

    }

    @Test
    void testCalculPerformanceCommercialObjectifcaNegatif() {

        //Given
        String matricule = "C00001";
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = -5000L;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("L'objectif de chiffre d'affaires ne peut être négatif ou null !");

    }

    @Test
    void testCalculPerformanceCommercialMatriculeNull() {

        //Given
        String matricule = null;
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = 5000L;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule ne peut être null et doit commencer par un C !");

    }

    @Test
    void testCalculPerformanceCommercialMatriculeNonCommercial() {

        //Given
        String matricule = "M00001";
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = 5000L;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule ne peut être null et doit commencer par un C !");

    }

    @Test
    void testCalculPerformanceCommercialMatriculeIntrouvable() {

        //Given
        String matricule = "C00001";
        Long caTraite = 0L;
        Long objCA = 7000L;

        //When
        try {
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule C00001 n'existe pas !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule C00001 n'existe pas !");

    }

    // Cas nominal 1
    @Test
    void testCalculPerformanceCommercialCatraiteInferieurDePlusDe20Pourcent() throws EmployeException {

        //Given
        String matricule = "C00001";
        Long caTraite = 7000L;
        Long objCA = 10000L;
        Integer perfDeBase = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perfObtenue = perfDeBase + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

    // Cas nominal 2
    @Test
    void testCalculPerformanceCommercialCatraiteInferieurDe20A5Pourcent() throws EmployeException {

        //Given
        String matricule = "C00001";
        Long caTraite = 9000L;
        Long objCA = 10000L;
        Integer perfDeBase = 1;
        Integer perfEmploye = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perf = Math.max(perfDeBase, perfEmploye - 2);
        Integer perfObtenue = perf + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

    // Cas nominal 3
    @Test
    void testCalculPerformanceCommercialCatraiteEntreMoins5EtPlus5DeObjectifca() throws EmployeException {

        //Given
        String matricule = "C00001";
        Long caTraite = 9800L;
        Long objCA = 10000L;
        Integer perfDeBase = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perfObtenue = perfDeBase + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

    // Cas nominal 4
    @Test
    void testCalculPerformanceCommercialcaTraiteSuperieurEntre5Et20Pourcent() throws EmployeException {

        //Given
        String matricule = "C00001";
        Long caTraite = 10000L;
        Long objCA = 9000L;
        Integer perfDeBase = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perfObtenue = perfDeBase + 1 + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

    // Cas nominal 5
    @Test
    void testCalculPerformanceCommercialCatraiteSuperieurDePlusDe20Pourcent() throws EmployeException {

        //Given
        String matricule = "C00001";
        Long caTraite = 10000L;
        Long objCA = 7000L;
        Integer perfDeBase = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perfObtenue = perfDeBase + 4 + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

}