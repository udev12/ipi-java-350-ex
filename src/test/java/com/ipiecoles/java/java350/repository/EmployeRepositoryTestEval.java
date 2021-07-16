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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class) //Junit 4
@ExtendWith(SpringExtension.class) //Junit 5
@SpringBootTest
public class EmployeRepositoryTestEval {

    @Autowired
    EmployeRepository employeRepository;

    @Autowired
    EmployeService employeService;

    @AfterEach
    @BeforeEach
    public void cleanUp() {
        employeRepository.deleteAll();
    }

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

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        employeRepository.deleteAll();
    }

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

        // Cas nominal 1 : perf = 1
        Long caTraite1 = 7000L;
        Long objCA1 = 10000L;
//        Integer perfCalculee1 = employeService.calculPerformanceCommercial(employe1.getMatricule(), caTraite1, objCA1);
        employeService.calculPerformanceCommercial(employe1.getMatricule(), caTraite1, objCA1);

        // Cas nominal 2 : perf = 1
        Long caTraite2 = 9000L;
        Long objCA2 = 10000L;
//        Integer perfCalculee2 = employeService.calculPerformanceCommercial(employe2.getMatricule(), caTraite2, objCA2);
        employeService.calculPerformanceCommercial(employe2.getMatricule(), caTraite2, objCA2);

        // Cas nominal 3 : perf = 1
        Long caTraite3 = 9800L;
        Long objCA3 = 10000L;
//        Integer perfCalculee3 = employeService.calculPerformanceCommercial(employe3.getMatricule(), caTraite3, objCA3);
        employeService.calculPerformanceCommercial(employe3.getMatricule(), caTraite3, objCA3);

        // Cas nominal 4 : perf = 3
        Long caTraite4 = 10000L;
        Long objCA4 = 9000L;
//        Integer perfCalculee4 = employeService.calculPerformanceCommercial(employe4.getMatricule(), caTraite4, objCA4);
        employeService.calculPerformanceCommercial(employe4.getMatricule(), caTraite4, objCA4);

        // Cas nominal 5 : perf = 6
        Long caTraite5 = 10000L;
        Long objCA5 = 7000L;
//        Integer perfCalculee5 = employeService.calculPerformanceCommercial(employe5.getMatricule(), caTraite5, objCA5);
        employeService.calculPerformanceCommercial(employe5.getMatricule(), caTraite5, objCA5);
        Double perfMoyennObtenue = 2.4d; // (1 + 1 + 1 + 3 + 6) / 5 -> 12 / 5

        //When

        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        //Then

        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean

        employeRepository.deleteAll();
    }

    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithCLetterPerfNulles() {

        // Given
        Employe employe1 = employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, 0, 1.0));
        Employe employe2 = employeRepository.save(new Employe("Dupont", "Lucile", "C12346", LocalDate.now(),
                Entreprise.SALAIRE_BASE, 0, 1.0));
        Employe employe3 = employeRepository.save(new Employe("Jean", "Aurore", "C12347", LocalDate.now(),
                Entreprise.SALAIRE_BASE, 0, 1.0));
        Double perfMoyennObtenue = 0d;

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        employeRepository.deleteAll();
    }

    @Test
    public void testAvgPerformanceWhereMatriculeStartsWithCLetterPerfNegatives() {

        // Given
        Employe employe1 = employeRepository.save(new Employe("Doe", "John", "C12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, -1, 1.0));
        Employe employe2 = employeRepository.save(new Employe("Dupont", "Lucile", "C12346", LocalDate.now(),
                Entreprise.SALAIRE_BASE, -1, 1.0));
        Employe employe3 = employeRepository.save(new Employe("Jean", "Aurore", "C12347", LocalDate.now(),
                Entreprise.SALAIRE_BASE, -1, 1.0));
        Double perfMoyennObtenue = -1d;

        //When
        Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");

        //Then
        Assertions.assertThat(performanceMoyenne).isEqualTo(perfMoyennObtenue);

        //Clean
        employeRepository.deleteAll();
    }

}
