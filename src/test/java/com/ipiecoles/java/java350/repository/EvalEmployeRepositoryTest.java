package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.service.EmployeService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

/**
 * Ici, on teste la méthode "avgPerformanceWhereMatriculeStartsWith" de "EmployeRepository"
 * Scénarii de test :
 * poste employe = "commercial" et performance égale à 1
 * poste employe = "commercial" et performance supérieure à 1
 * poste employe = "commercial" et performance égale à 0
 * poste employe = "commercial" et performance inférieure à 1
 */
@RunWith(SpringRunner.class) //Junit 4
@ExtendWith(SpringExtension.class) //Junit 5
@SpringBootTest
public class EvalEmployeRepositoryTest {

    static final Logger logger = LoggerFactory.getLogger(EvalEmployeRepositoryTest.class);

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    EmployeService employeService;

    /**
     * On vide le repository avant et après chaque test, afin de ne pas influer sur les tests suivants
     */
    @AfterEach
    @BeforeEach
    public void cleanUp() {
        employeRepository.deleteAll();
    }

    /**
     * Employé occupant un poste de commercial, dont la performance est égale à 1
     */
    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithCLetterPerfEgaleA1() {
        // Given
        Employe employe1 = employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Employe employe2 = employeRepository.save(new Employe("Dupont", "Lucile", "C12346", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Employe employe3 = employeRepository.save(new Employe("Jean", "Aurore", "C12347", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Double perfMoyennObtenue = 1d;
        logger.info("3 employés ont été créés avec succès!");

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
        logger.info("La performance moyenne des commerciaux est de {}!", performanceMoyenne);

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        logger.info("Base de données réinitialisée");
        employeRepository.deleteAll();
    }

    /**
     * Employé occupant un poste de commercial, dont la performance est supérieure à 1
     *
     * @throws EmployeException : si le matricule est null ou ne commence pas par un C
     */
    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithCLetlerPerfSuperieureA1() throws EmployeException {
        // Given

        Employe employe1 = employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Employe employe2 = employeRepository.save(new Employe("Dupont", "Lucile", "C12346", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Employe employe3 = employeRepository.save(new Employe("Jean", "Aurore", "C12347", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Employe employe4 = employeRepository.save(new Employe("Mazet", "Elodie", "C12348", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        Employe employe5 = employeRepository.save(new Employe("Grand", "Dominique", "C12349", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));

        logger.info("5 employés ont été créés avec succès!");

        // Cas nominal 1 : perf = 1
        Long caTraite1 = 7000L;
        Long objCA1 = 10000L;
        employeService.calculPerformanceCommercial(employe1.getMatricule(), caTraite1, objCA1);
        logger.info("Calcul de la performance du commercial 1!");

        // Cas nominal 2 : perf = 1
        Long caTraite2 = 9000L;
        Long objCA2 = 10000L;
        employeService.calculPerformanceCommercial(employe2.getMatricule(), caTraite2, objCA2);
        logger.info("Calcul de la performance du commercial 2!");

        // Cas nominal 3 : perf = 1
        Long caTraite3 = 9800L;
        Long objCA3 = 10000L;
        employeService.calculPerformanceCommercial(employe3.getMatricule(), caTraite3, objCA3);
        logger.info("Calcul de la performance du commercial 3!");

        // Cas nominal 4 : perf = 3
        Long caTraite4 = 10000L;
        Long objCA4 = 9000L;
        employeService.calculPerformanceCommercial(employe4.getMatricule(), caTraite4, objCA4);
        logger.info("Calcul de la performance du commercial 4!");

        // Cas nominal 5 : perf = 6
        Long caTraite5 = 10000L;
        Long objCA5 = 7000L;
        employeService.calculPerformanceCommercial(employe5.getMatricule(), caTraite5, objCA5);
        Double perfMoyennObtenue = 2.4d; // (1 + 1 + 1 + 3 + 6) / 5 -> 12 / 5
        logger.info("Calcul de la performance du commercial 5!");

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
        logger.info("La performance moyenne des commerciaux est de {}!", performanceMoyenne);

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        logger.info("Base de données réinitialisée");
        employeRepository.deleteAll();
    }

    /**
     * Employé occupant un poste de commercial, dont la performance est nulle
     */
    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithCLetterPerfNulle() {
        // Given
        Employe employe1 = employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, 0, 1.0));
        Employe employe2 = employeRepository.save(new Employe("Dupont", "Lucile", "C12346", LocalDate.now(),
                Entreprise.SALAIRE_BASE, 0, 1.0));
        Employe employe3 = employeRepository.save(new Employe("Jean", "Aurore", "C12347", LocalDate.now(),
                Entreprise.SALAIRE_BASE, 0, 1.0));
        Double perfMoyennObtenue = 0d;
        logger.info("3 employés ont été créés avec succès!!");

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
        logger.info("La performance moyenne des commerciaux est de {}!", performanceMoyenne);

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        logger.info("Base de données réinitialisée");
        employeRepository.deleteAll();
    }

    /**
     * Employé occupant un poste de commercial, dont la performance est négative
     */
    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithCLetterPerfNegative() {
        // Given
        Employe employe1 = employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, -1, 1.0));
        Employe employe2 = employeRepository.save(new Employe("Dupont", "Lucile", "C12346", LocalDate.now(),
                Entreprise.SALAIRE_BASE, -1, 1.0));
        Employe employe3 = employeRepository.save(new Employe("Jean", "Aurore", "C12347", LocalDate.now(),
                Entreprise.SALAIRE_BASE, -1, 1.0));
        Double perfMoyennObtenue = -1d;
        logger.info("3 employés ont été créés avec succès!!");

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
        logger.info("La performance moyenne des commerciaux est de {}!", performanceMoyenne);

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        logger.info("Base de données réinitialisée");
        employeRepository.deleteAll();
    }

}
