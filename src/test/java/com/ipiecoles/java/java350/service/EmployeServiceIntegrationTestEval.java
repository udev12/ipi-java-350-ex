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
        String nom = "MARTIN";
        String prenom = "Chloé";
        Poste poste = Poste.COMMERCIAL;
        NiveauEtude niveauEtude = NiveauEtude.BTS_IUT;
        Double tempsPartiel = 1d;
        Employe employe = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //When
        Integer perfCalculee = employeService.calculPerformanceCommercial(employe.getMatricule(), caTraite, objCA);

        //When/Then
        Employe employe1 = employeRepository.findByMatricule("T00001");
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfDeBase);
        Assertions.assertThat(perfCalculee).isEqualTo(perfObtenue);


    }
}
