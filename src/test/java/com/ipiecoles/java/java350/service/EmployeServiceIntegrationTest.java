package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

/**
 * Ici, on teste de manière intégrée la méthode "embaucheEmploye" de la classe "EmployeService"
 * On fait appel aux classes "EmployeService" et "EmployeRepository"
 */
@SpringBootTest
class EmployeServiceIntegrationTest {

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EmployeRepository employeRepository;

    /**
     * On vide le repository avant et après chaque test, afin de ne pas influer sur les tests suivants
     */
    @AfterEach
    @BeforeEach
    public void cleanUp() {
        employeRepository.deleteAll();
    }

    /**
     * Scenario de test : on a une ingénieure embauchée à temps plein
     *
     * @throws Exception : si on arrive au bout des matricules possibles / si le matricule correspond à un employé existant
     */
    @Test
    void testEmbauchePremierEmployePleinTempsManagerIngenieur() throws Exception {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;

        //When
        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        Employe employe = employeRepository.findByMatricule("M00001");
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d); //1521.22 * 1.6
    }
}


