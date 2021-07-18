package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

/**
 * On teste ici la méthode "findLastMatricule" de l'interface "EmployeRepository"
 * Scénarios:
 * M12345
 * pour un employé donné (ex : M12345), vérifier que son matricule se termine bien "12345"
 * compter le nombre de caractères après le "M", qui doit être égal à 5
 * vérifier qu'on a bien des chiffres après le "M"
 * si on n'a que des zéros (ex : M00000")
 */
@ExtendWith(SpringExtension.class) //Junit 5
@SpringBootTest
public class EmployeRepositoryTest {

    @Autowired
    EmployeRepository employeRepository;

    /**
     * On vide le repository avant et après chaque test, afin de ne pas influer sur les tests suivants
     */
    @AfterEach
    @BeforeEach
    public void cleanUp() {
        employeRepository.deleteAll();
    }

    /**
     * On vérifie que le matricule "M12345" se termine bien par "12345"
     */
    @Test
    public void testFindLastMatriculeEmployeM12345() {
        //Given
        Employe employe = employeRepository.save(new Employe("Doe", "John", "M12345", LocalDate.now(),
                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    /**
     * On teste le cas de trois employés avec des matricules différents
     */
    @Test
    void testFindLastMatricule3Employes() {
        //Given
        employeRepository.save(new Employe("Doe", "John", "C11032",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeRepository.save(new Employe("Doe", "Jane", "M12345",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
        employeRepository.save(new Employe("Doe", "Jim", "T12000",
                LocalDate.now(), Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));

        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isEqualTo("12345");
    }

    /**
     * On teste le cas de figure où on n'a aucun employé
     */
    @Test
    public void testFindLastMatricule0Employe() {
        //Given
        //When
        String lastMatricule = employeRepository.findLastMatricule();

        //Then
        Assertions.assertThat(lastMatricule).isNull();
    }

}
