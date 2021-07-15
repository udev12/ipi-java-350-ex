package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

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

//    // Cas nominal 5
//    @Test
//    void testCalculPerformanceCommercialCAtraiteNul() /*throws EmployeException*/ {
//////        //Given
////        String matricule = "C00001";
////        String nom = "MARTIN";
////        String prenom = "Chloé";
////        Employe employe = new Employe();
////        employe.setMatricule(matricule);
////        employe.setNom(nom);
////        employe.setPrenom(prenom);
//////        employe.setDateEmbauche();
//////        employe.set
////////
////////        String nom = "MARTIN";
////////        String prenom = "Chloé";
////        Poste poste = Poste.COMMERCIAL;
////        NiveauEtude niveauEtude = NiveauEtude.LICENCE;
////        Double tempsPartiel = 1d;
////        Long caTraite = 10000L;
////        Long objCA = 7000L;
////        Integer nvllePerf = 5;
////
////        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
////        Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(null);
//
//
//        //Given
//        String nom = "MARTIN";
//        String prenom = "Chloé";
//        Poste poste = Poste.COMMERCIAL;
//        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
//        Double tempsPartiel = 1d;
////        Long caTraite = 10000L;
//        Long caTraite = 0L;
//        Long objCA = 7000L;
//        String matricule = "C00001";
////        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());
//
//
//
//
//
////
////        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
////        Mockito.when(employeRepository.findByMatricule("C00001")).thenReturn(null);
////
////        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
////        Mockito.verify(employeRepository/*, Mockito.times(1)*/).save(employeArgumentCaptor.capture());
////        Employe employe1 = employeArgumentCaptor.getValue();
//
//
//
////        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
////
//////        Poste poste = Poste.MANAGER;
//////        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
//////        Double tempsPartiel = 1d;
////        //Ajouter les mocks...
//////        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
//////        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
////
//        //When
//////        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
////        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
//
//
//        //When
//        try {
//            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
//            Assertions.fail("Aurait du planter");
//        } catch (Exception e) {
//            //Then
//            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
//            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
//            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaires traité ne peut être négatif ou null !");
//        }
//
//        //When/Then
//        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
//                .isInstanceOf(EmployeException.class)
//                .hasMessage("Le chiffre d'affaires traité ne peut être négatif ou null !");
//
//
////        //Then
//
////
////        Assertions.assertThat(employe1).isNotNull();
////        Assertions.assertThat(employe1).isNull();
////        Assertions.assertThat(employe1.getPerformance().compareTo(nvllePerf));
//
////        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
////        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
////        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
////        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
//        //1521.22 * 1.6
////        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
//
//    }


    @Test
    void testCalculPerformanceCommercialCatraiteNull() {

        //Given
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
//        Long caTraite = 10000L;
        Long caTraite = null;
        Long objCA = 7000L;
        String matricule = "C00001";
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
//        Long caTraite = 10000L;
        Long caTraite = -5000L;
        Long objCA = 7000L;
        String matricule = "C00001";
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = null;
        String matricule = "C00001";
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = -5000L;
        String matricule = "C00001";
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = 5000L;
        String matricule = null;
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 10000L;
//        Long caTraite = null;
        Long objCA = 5000L;
        String matricule = "M00001";
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
//        Long caTraite = 10000L;
        Long caTraite = 0L;
        Long objCA = 7000L;
        String matricule = "C00001";
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("00000");
//        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 7000L;
        Long objCA = 10000L;
        String matricule = "C00001";
        Integer perfEmploye = 1;
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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 9000L;
        Long objCA = 10000L;
        String matricule = "C00001";
        Integer perfDeBase = 1;
        Integer perfEmploye = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perf = Math.max(perfDeBase, perfEmploye-2);
        Integer perfObtenue = perf + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

//        Mockito.when(employeRepository.deleteAll());
//        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

    // Cas nominal 3
    @Test
    void testCalculPerformanceCommercialCatraiteEntreMoins5EtPlus5DeObjectifca() throws EmployeException {

        //Given
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 9800L;
        Long objCA = 10000L;
        String matricule = "C00001";
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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 10000L;
        Long objCA = 9000L;
        String matricule = "C00001";
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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Long caTraite = 10000L;
        Long objCA = 7000L;
        String matricule = "C00001";
        Integer perfDeBase = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perfObtenue = perfDeBase + 4 + bonusPerf;
        Mockito.when(employeRepository.findByMatricule(matricule)).thenReturn(new Employe());

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);

    }

//    @Test
//    void testEmbaucheEmployeExistant() {
//        //Given
//        String nom = "Jean";
//        String prenom = "Aurore";
//        Poste poste = Poste.MANAGER;
//        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
//        Double tempsPartiel = 1d;
//        //Employe employe = new Employe("Doe", "John", "M99999".../*Les paramètres pour créer un employé*/);
//        //Ajouter les mocks...
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99998");
//        Mockito.when(employeRepository.findByMatricule("M99999")).thenReturn(new Employe());
//
//        //When
//        try {
//            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//            Assertions.fail("Aurait du planter");
//        } catch (Exception e) {
//            //Then
//            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
//            Assertions.assertThat(e).isInstanceOf(EntityExistsException.class);
//            Assertions.assertThat(e.getMessage()).isEqualTo("L'employé de matricule M99999 existe déjà en BDD");
//        }
//
//        //When/Then
//        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel))
//                .isInstanceOf(EntityExistsException.class)
//                .hasMessage("L'employé de matricule M99999 existe déjà en BDD");
//
//    }


}
