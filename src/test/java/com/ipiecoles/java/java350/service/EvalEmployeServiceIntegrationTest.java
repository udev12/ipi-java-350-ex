package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.repository.EvalEmployeRepositoryTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Ici, on teste de manière intégrée, la méthode "calculPerformanceCommercial" de la classe "EmployeService"
 * On fait appel aux classes "EmployeService" et "EmployeRepository"
 * NB : on ne teste que des commerciaux
 */
@SpringBootTest // indispensable pour faire un test intégré
public class EvalEmployeServiceIntegrationTest {

    static final Logger logger = LoggerFactory.getLogger(EvalEmployeServiceIntegrationTest.class);

    @Autowired
    EmployeService employeService;

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
     * Cas nominal 5 : le chiffre d'affaires est supérieur de plus de 20%, il gagne 4 de performance
     *
     * @throws Exception : si le matricule est null ou ne commence pas par un C
     */
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
        logger.info("L'employé(e) {} {} titulaire d'un {} a été créé(e) avec succès!", prenom, nom, niveauEtude);

        //When
        employeService.calculPerformanceCommercial(employe.getMatricule(), caTraite, objCA);
        logger.info("La performance moyenne de {} {} est de {}", prenom, nom, employe.getPerformance());

        //When/Then
        employeRepository.findByMatricule(employe.getMatricule());
        Assertions.assertThat(employe.getPerformance()).isEqualTo(perfDeBase);
    }
}
