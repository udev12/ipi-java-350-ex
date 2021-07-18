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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dans cette classe, on recourt aux mocks, afin de rester dans des tests unitaires
 * de la méthode "calculPerformanceCommercial" (classe "EmployeService")
 * Cas nominaux :
 * 1 : Si le chiffre d'affaires est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
 * 2 : Si le chiffre d'affaires est inférieur entre 20% et 5% par rapport à l'ojectif fixé, il perd 2 de performance (dans la limite de la performance de base)
 * 3 : Si le chiffre d'affaires est entre -5% et +5% de l'objectif fixé, la performance reste la même
 * 4 : Si le chiffre d'affaires est supérieur entre 5 et 20%, il gagne 1 de performance
 * 5 : Si le chiffre d'affaires est supérieur de plus de 20%, il gagne 4 de performance
 * Cas aux limites :
 * objectifCa = 7000 euros et caTraite nul
 * objectifCa = 7000 euros et caTraite = -5000 euros
 * objectifCa nul et caTraite = 10000 euros
 * objectifCa = -5000 euros et caTraite = 10000 euros
 * objectifCa = 5000 euros, caTraite = 10000 euros et matricule nul
 * objectifCa = 5000 euros, caTraite = 10000 euros et matricule manager
 * objectifCa = 7000 euros, caTraite nul et matricule inexistant
 * NB : on ne teste que des commerciaux
 */
@ExtendWith(MockitoExtension.class)
public class EvalEmployeServiceTest {

    static final Logger logger = LoggerFactory.getLogger(EvalEmployeServiceTest.class);

    @InjectMocks
    public EmployeService employeService;

    @Mock
    public EmployeRepository employeRepository;

    /**
     * Cas nominal 1 : le chiffre d'affaires est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
     *
     * @throws EmployeException : si le matricule est null ou ne commence pas par un C
     */
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

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        logger.info("Calcul de la performance du commercial de matricule {}", matricule);
        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Employe employe = employeRepository.findByMatricule(matricule);
        logger.info("La performance du commercial de matricule {} est de {}", matricule, employe.getPerformance());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfObtenue);
    }

    /**
     * Cas nominal 2 : le chiffre d'affaires est inférieur entre 20% et 5% par rapport à l'ojectif fixé, il perd 2 de performance (dans la limite de la performance de base)
     *
     * @throws EmployeException : si le matricule est null ou ne commence pas par un C
     */
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

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        logger.info("Calcul de la performance du commercial de matricule {}", matricule);
        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Employe employe = employeRepository.findByMatricule(matricule);
        logger.info("La performance du commercial de matricule {} est de {}", matricule, employe.getPerformance());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfObtenue);
    }

    /**
     * Cas nominal 3 : le chiffre d'affaires est entre -5% et +5% de l'objectif fixé, la performance reste la même
     *
     * @throws EmployeException : si le matricule est null ou ne commence pas par un C
     */
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

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        logger.info("Calcul de la performance du commercial de matricule {}", matricule);
        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Employe employe = employeRepository.findByMatricule(matricule);
        logger.info("La performance du commercial de matricule {} est de {}", matricule, employe.getPerformance());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfObtenue);
    }

    /**
     * Cas nominal 4 : si le chiffre d'affaires est supérieur entre 5 et 20%, il gagne 1 de performance
     *
     * @throws EmployeException : si le matricule est null ou ne commence pas par un C
     */
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

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        logger.info("Calcul de la performance du commercial de matricule {}", matricule);
        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Employe employe = employeRepository.findByMatricule(matricule);
        logger.info("La performance du commercial de matricule {} est de {}", matricule, employe.getPerformance());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfObtenue);
    }

    /**
     * Cas nominal 5 : le chiffre d'affaires est supérieur de plus de 20%, il gagne 4 de performance
     *
     * @throws EmployeException : si le matricule est null ou ne commence pas par un C
     */
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

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        logger.info("Calcul de la performance du commercial de matricule {}", matricule);
        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Employe employe = employeRepository.findByMatricule(matricule);
        logger.info("La performance du commercial de matricule {} est de {}", matricule, employe.getPerformance());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfObtenue);
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa = 7000 euros et un caTraite nul
     */
    @Test
    void testCalculPerformanceCommercialCatraiteNull() {
        //Given
        String matricule = "C00001";
        Long caTraite = null;
        Long objCA = 7000L;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");

        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaires traité ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le chiffre d'affaires traité ne peut être négatif ou null !");
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa = 7000 euros et un caTraite = -5000 euros
     */
    @Test
    void testCalculPerformanceCommercialCatraiteNegatif() {
        //Given
        String matricule = "C00001";
        Long caTraite = -5000L;
        Long objCA = 7000L;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le chiffre d'affaires traité ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le chiffre d'affaires traité ne peut être négatif ou null !");
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa nul et un caTraite = 10000 euros
     */
    @Test
    void testCalculPerformanceCommercialObjectifcaNull() {
        //Given
        String matricule = "C00001";
        Long caTraite = 10000L;
        Long objCA = null;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa = -5000 euros et un caTraite = 10000 euros
     */
    @Test
    void testCalculPerformanceCommercialObjectifcaNegatif() {
        //Given
        String matricule = "C00001";
        Long caTraite = 10000L;
        Long objCA = -5000L;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa = 5000 euros, un caTraite = 10000 euros et un matricule nul
     */
    @Test
    void testCalculPerformanceCommercialMatriculeNull() {
        //Given
        String matricule = null;
        Long caTraite = 10000L;
        Long objCA = 5000L;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule ne peut être null et doit commencer par un C !");
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa = 5000 euros, un caTraite = 10000 euros et un matricule de manager
     */
    @Test
    void testCalculPerformanceCommercialMatriculeNonCommercial() {
        //Given
        String matricule = "M00001";
        Long caTraite = 10000L;
        Long objCA = 5000L;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule ne peut être null et doit commencer par un C !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule ne peut être null et doit commencer par un C !");
    }

    /**
     * Calcul de la performance du commercial avec un objectifCa = 7000 euros, un caTraite nul et un matricule inexistant
     */
    @Test
    void testCalculPerformanceCommercialMatriculeInexistant() {
        //Given
        String matricule = "C00001";
        Long caTraite = 0L;
        Long objCA = 7000L;
        Integer performance = 0;

        logger.info("Objectif de chiffre d'affaires : {} euros", objCA);
        logger.info("Chiffre d'affaires traité : {} euros", caTraite);

        //When
        try {
            logger.info("Calcul de la performance du commercial de matricule {}", matricule);
            employeService.calculPerformanceCommercial(matricule, caTraite, objCA);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            logger.warn("Le chiffre d'affaires traité ne peut être négatif ou null !");
            logger.debug("L'exception 'EmployeException' a été levée");
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Le matricule C00001 n'existe pas !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.calculPerformanceCommercial(matricule, caTraite, objCA))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Le matricule C00001 n'existe pas !");
    }

}
