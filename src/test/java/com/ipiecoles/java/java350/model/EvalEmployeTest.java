package com.ipiecoles.java.java350.model;

import com.ipiecoles.java.java350.service.EmployeService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

/**
 * Classe qui permet de tester unitairement les méthodes de la classe "Employe"
 * Scénarii de test pour la méthode "augmenterSalaire" :
 * pourcentage = 2% et salaire = 1521.22 euros
 * pourcentage = 0% et salaire = 1521.22 euros
 * pourcentage = 100% et salaire = 1521.22 euros
 * pourcentage = 120% et salaire = 1521.22 euros
 * pourcentage = 30% et salaire nul
 * Scenarii de test pour la méthode "getNbRtt" :
 * test paramétré
 */
public class EvalEmployeTest {

    static final Logger logger = LoggerFactory.getLogger(EvalEmployeTest.class);

    /**
     * Méthode qui teste la méthode "augmenterSalaire" de la classe "Employe"
     * Calcul avec un pourcentage supérieur à zéro (c'est-à-dire 2%) et un salaire supérieur à zéro (c'est-à-dire 1521.22 euros)
     */
    @Test
    public void testAugmenterSalairePourcentagePositifSalairePositif() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 0.2;
        double salaireAugmente = 0d;

        logger.info("Le salaire avant augmentation est de {}!", salaire);
        logger.info("Le pourcentage d'augmentation est de {}!", pourcentage);

        //When
        salaireAugmente = salaire * (1 + pourcentage);
        logger.info("Le salaire augmenté est de {}!", salaireAugmente);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(1825.464);
    }

    /**
     * Méthode qui teste la méthode "augmenterSalaire" de la classe "Employe"
     * Calcul avec un pourcentage nul et un salaire supérieur à zéro (c'est-à-dire 1521.22 euros)
     */
    @Test
    public void testAugmenterSalairePourcentageNul() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 0d;
        double salaireAugmente = 0d;

        logger.info("Le salaire avant augmentation est de {}!", salaire);
        logger.info("Le pourcentage d'augmentation est de {}!", pourcentage);

        //When
        salaireAugmente = salaire * (1 + pourcentage);
        logger.info("Le salaire augmenté est de {}!", salaireAugmente);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(1521.22);
    }

    /**
     * Méthode qui teste la méthode "augmenterSalaire" de la classe "Employe"
     * Calcul avec un pourcentage de 100% et un salaire supérieur à zéro (c'est-à-dire 1521.22 euros)
     */
    @Test
    public void testAugmenterSalairePourcentageEgalA100() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 1d;
        double salaireAugmente = 0d;

        logger.info("Le salaire avant augmentation est de {}!", salaire);
        logger.info("Le pourcentage d'augmentation est de {}!", pourcentage);

        //When
        salaireAugmente = salaire * (1 + pourcentage);
        logger.info("Le salaire augmenté est de {}!", salaireAugmente);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(3042.44);
    }

    /**
     * Méthode qui teste la méthode "augmenterSalaire" de la classe "Employe"
     * Calcul avec un pourcentage supérieur à 100% (soit 120%) et un salaire supérieur à zéro (c'est-à-dire 1521.22 euros)
     */
    @Test
    public void testAugmenterSalairePourcentageEgalA120() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 1.2;
        double salaireAugmente = 0d;

        logger.info("Le salaire avant augmentation est de {}!", salaire);
        logger.info("Le pourcentage d'augmentation est de {}!", pourcentage);

        //When
        salaireAugmente = salaire * (1 + pourcentage);
        logger.info("Le salaire augmenté est de {}!", salaireAugmente);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(3346.684);
    }

    /**
     * Méthode qui teste la méthode "augmenterSalaire" de la classe "Employe"
     * Calcul avec un pourcentage de 30% et un salaire nul
     */
    @Test
    public void testAugmenterSalaireNul() {
        //Given
        double salaire = 0d;
        double pourcentage = 0.3;
        double salaireAugmente = 0d;

        logger.info("Le salaire avant augmentation est de {}!", salaire);
        logger.info("Le pourcentage d'augmentation est de {}!", pourcentage);

        //When
        salaireAugmente = salaire * (1 + pourcentage);
        logger.info("Le salaire augmenté est de {}!", salaireAugmente);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(0);
    }

    /**
     * Test paramétré pour la méthode "getNbRtt" de la classe "Employe"
     * 2019 : l'année est non bissextile, a débuté un mardi et il y a 10 jours fériés ne tombant pas le week-end
     * 2021 : l'année est non bissextile, a débuté un vendredi et il y a 7 jours fériés ne tombant pas le week-end
     * 2022 : l'année est non bissextile, a débuté un samedi et il y a 7 jours fériés ne tombant pas le week-end
     * 2032 : l'année est bissextile, a débuté un jeudi et il y a 7 jours fériés ne tombant pas le week-end
     * NbRtt = Nombre de jours dans l'année
     * - Nombre de jours travaillés dans l'année en plein temps
     * - Nombre de samedi et dimanche dans l'année
     * - Nombre de jours fériés ne tombant pas le week-end
     * - Nombre de congés payés
     *
     * @param d        : date
     * @param resultat : nombre de jours de RTT
     */
    @ParameterizedTest(name = "d {0} => resultat {1}") //Change l'annotation
    @CsvSource({
            "2019-11-15, 8", // 2019 : 365 - 218 - 104 - 10 - 25 = 8
            "2021-06-15, 9", // 2021 : 365 - 218 - 106 - 7 - 25 = 9
            "2022-02-17, 10", // 2022 : 365 - 218 - 105 - 7 - 25 = 10
            "2032-01-01, 11" // 2032 : 366 - 218 - 105 - 7 - 25 = 11
    })
    public void testGetNbRtt(String d, String resultat) {
        //Given
        Employe employe = new Employe();

        //When
        Integer nbJoursRttCalcules = employe.getNbRtt(LocalDate.parse(d));
        logger.info("Le nombre de jours de RTT calculé est de {}!", nbJoursRttCalcules);

        //Then
        Assertions.assertThat(nbJoursRttCalcules).isEqualTo(Integer.parseInt(resultat));
    }

}
