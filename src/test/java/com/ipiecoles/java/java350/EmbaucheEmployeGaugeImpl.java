package com.ipiecoles.java.java350;

import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.ipiecoles.java.java350.service.EmployeService;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Classe d'implémentation pour "AcceptationTestt.spec"
 */
@SpringBootTest
@Component
public class EmbaucheEmployeGaugeImpl {

    static final Logger logger = LoggerFactory.getLogger(EmployeService.class);

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
     * Scénario du test : embauche d'une ingénieure (première employée) à temps plein
     * La Step est exécutée dans "AcceptationTestt.spec"
     * @throws Exception : si on arrive au bout des matricules possibles / si le matricule correspond à un employé existant
     */
    @Step("Employe embauche passed")
    public void testEmbauchePremierEmployePleinTempsManagerIngenieur() throws Exception {
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
        logger.info("L'employé(e) {} {} de matricule {} a été ajouté(e) avec succès à la liste des employés!", prenom, nom, employe.getMatricule());
        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);

    }

}