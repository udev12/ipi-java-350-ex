package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

public class EvalEmployeTest {


    // nombre de RTT =
    //  Nombre de jours dans l'année
    //      - Nombre de jours travaillés dans l'année en plein temps : a
    //      - Nombre de samedi et dimanche dans l'année : b
    //      - Nombre de jours fériés ne tombant pas le week-end  : c
    //      - Nombre de congés payés : d
    //  - cas nominal : on a a, b, c, et d, et ils sont corrects/cohérents
    //  - cas où il manque des paramètres :
    //      - on a seulement a
    //	    - on a seulement b
    //	    - on a seulement c
    //	    - on a seulement d
    //	    - on a seulement a et b
    //	    - on a seulement a et c
    //	    - on a seulement a et d
    //	    - on a seulement b et c
    //	    - on a seulement b et d
    //	    - on a seulement c et d
    //	    - on a seulement a, b et c
    //	    - on a seulement a, b et d
    //	    - on a seulement a, c et d
    //	    - on a seulement b, c et d
    //  - cas avec année bissextile :
    //	    - test avec une date du jour qui tombe sur un jeudi
    //	    - test avec une date du jour qui tombe sur un vendredi
    //	    - test avec une date du jour qui tombe sur un samedi
    //	    - test avec une date du jour qui tombe su un jour autre qu'un jeudi, vendredi ou samedi
    //      - cas avec année non bissextile : une date du jour au hasard
    //  - tous les cas ci-dessus en temps partiel et en temps complet


    // Scénarii de test méthode augmenterSalaire (pourcentage d'augmentation en paramètre) :
    //  - pourcentage > 0 et salaire > 0
    //  - pourcentage nul
    //  - pourcentage = 100%
    //  - pourcentage > 100%
    //  - salaire nul

    // Calcul avec pourcentage > 0 (2%) et salaire > 0
    @Test
    public void testAugmenterSalairePourcentagePositifSalairePositif() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 0.2;
        double salaireAugmente = 0d;

        //When
        salaireAugmente = salaire * (1 + pourcentage);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(1825.464);
    }

    // Pourcentage nul
    @Test
    public void testAugmenterSalairePourcentageNul() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 0d;
        double salaireAugmente = 0d;

        //When
        salaireAugmente = salaire * (1 + pourcentage);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(1521.22);
    }

    // Pourcentage = 100%
    @Test
    public void testAugmenterSalairePourcentageEgalA100() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 1d;
        double salaireAugmente = 0d;

        //When
        salaireAugmente = salaire * (1 + pourcentage);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(3042.44);
    }

    // Pourcentage > 100% (ex : 120%)
    @Test
    public void testAugmenterSalairePourcentageEgalA150() {
        //Given
        double salaire = 1521.22;
        double pourcentage = 1.2;
        double salaireAugmente = 0d;

        //When
        salaireAugmente = salaire * (1 + pourcentage);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(3346.684);
    }

    // Salaire nul, pourcentage = 3%
    @Test
    public void testAugmenterSalaireNul() {
        //Given
        double salaire = 0d;
        double pourcentage = 0.3;
        double salaireAugmente = 0d;

        //When
        salaireAugmente = salaire * (1 + pourcentage);

        // Then
        Assertions.assertThat(salaireAugmente).isEqualTo(0);
    }

    // Scénarii de test getNbRtt : date du jour en paramètre

//    Infos :
//            - 2019 : l'année est non bissextile, a débuté un mardi et il y a 10 jours fériés ne tombant pas le week-end.
//            - 2021 : l'année est non bissextile, a débuté un vendredi et il y a 7 jours fériés ne tombant pas le week-end.
//            - 2022 : l'année est non bissextile, a débuté un samedi et il y a 7 jours fériés ne tombant pas le week-end.
//            - 2032 : l'année est bissextile, a débuté un jeudi et il y a 7 jours fériés ne tombant pas le week-end.

//    NbRtt = Nombre de jours dans l'année - Nombre de jours travaillés dans l'année en plein temps
//      - Nombre de samedi et dimanche dans l'année - Nombre de jours fériés ne tombant pas le week-end
//      - Nombre de congés payés

//    2019 : 365 - 218 - 104 - 10 - 25 = 8
//    2021 : 365 - 218 - 106 - 7 - 25 = 9
//    2022 : 365 - 218 - 105 - 7 - 25 = 10
//    2032 : 366 - 218 - 105 - 7 - 25 = 11

    @ParameterizedTest(name = "d {0} => resultat {1}") //Change l'annotation
//Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
    @CsvSource({
            "2019-11-15, 8", // 2019
            "2021-06-15, 9", // 2021
            "2022-02-17, 10", // 2022
            "2032-01-01, 11", // 2032
    })
    public void testGetNbRtt(String d, String resultat) {
        //Given
        Employe employe = new Employe();

        //When
        Integer nbJoursRttCalcules = employe.getNbRtt(LocalDate.parse(d));

        //Then
        //Remplace la valeur de sortie en dur par le paramètre de sortie
        Assertions.assertThat(nbJoursRttCalcules).isEqualTo(Integer.parseInt(resultat));
    }

}
