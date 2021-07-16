package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
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

@SpringBootTest
public class EmployeServiceIntegrationTestEval {

    @Autowired
    EmployeService employeService;

    @Autowired
    EmployeRepository employeRepository;

    @AfterEach
    @BeforeEach
    public void cleanUp() {
        employeRepository.deleteAll();
    }

    // Cas nominal 5
    @Test
    void testCalculPerformanceCommercialCatraiteSuperieurDePlusDe20Pourcent() throws Exception {

        //Given
        Long caTraite = 10000L;
        Long objCA = 7000L;
        Integer perfDeBase = 1;
        Integer bonusPerf = 1; // si performance commercial > performance moyenne
        Integer perfObtenue = perfDeBase + 4 + bonusPerf;
        String matricule = "C00001";
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double salaire = Entreprise.SALAIRE_BASE;
        Double tempsPartiel = 1d;
//        Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer performance, Double tempsPartiel)
        Employe employe = new Employe(nom, prenom, matricule, LocalDate.now(), salaire, perfDeBase, tempsPartiel);
        employeRepository.save(employe);
//        Employe employe = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //When
//        employeService.calculPerformanceCommercial(employe.getMatricule(), caTraite, objCA);
        employeService.calculPerformanceCommercial(matricule, caTraite, objCA);

        //When/Then
        Employe employe1 = employeRepository.findByMatricule(matricule);
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfDeBase);
//        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfObtenue);



//        Assertions.assertThat(employe).isNotNull();
//        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
//        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
//        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
//        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
//        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
//        //1521.22 * 1.6
//        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);


    }
}
