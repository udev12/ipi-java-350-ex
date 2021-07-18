package com.ipiecoles.java.java350.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

/**
 * Classe qui permet de tester unitairement les méthodes de la classe "Employe"
 * Scénarii de test pour la méthode "getNombreAnneeAnciennete" :
 * date d'embauche = aujourd'hui
 * date d'embauche = aujourd'hui + 5 ans
 * date d'embauche nulle
 * date d'embauche = aujourd'hui - 5 ans
 * Scenarii de test pour la méthode "getPrimeAnnuelle" :
 * manager sans ancienneté travaillant à temps plein
 * test paramétré
 */
public class EmployeTest {

    //Scénarios de test, 1 scénario = 1 test

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec comme date d'embauche, la date du jour
     */
    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheToday() {
        //Given
        LocalDate dateEmbaucheToday = LocalDate.now();
        Employe employe = new Employe();
        employe.setDateEmbauche(dateEmbaucheToday);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(0);
    }

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec comme date d'embauche, une date future (ici, la date du jour + 5 ans)
     */
    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheFuture() {
        //Given
        LocalDate dateEmbaucheFuture = LocalDate.now().plusYears(5);
        Employe employe = new Employe();
        employe.setDateEmbauche(dateEmbaucheFuture);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isNull();
    }

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec comme date d'embauche, une date dans le futur (c'est-à-dire, la date du jour + 5 ans)
     * Date d'embauche dans le futur => nb années ancienneté : null
     */
    @Test
    public void testGetNombreAnneeAncienneteDateEmbaucheInTheFuture() {
        //Given
        LocalDate dateEmbaucheFutur = LocalDate.now().plusYears(5);
        Employe employe = new Employe();
        employe.setDateEmbauche((dateEmbaucheFutur));
        //When
        Integer anciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anciennete).isNull();
    }

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec une date d'embauche nulle
     */
    @Test
    public void testGetNbAnneesAncienneteDateEmbaucheNull() {
        //Given
        LocalDate dateEmbauche = null;
        Employe employe = new Employe();
        employe.setDateEmbauche(dateEmbauche);
        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
        //Then
        Assertions.assertThat(nbAnneesAnciennete).isNull();
    }

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec une  date d'embauche nulle
     * Date d'embauche null => nb années ancienneté : null
     */
    @Test
    public void testGetNombreAnneesAncienneteDateEmbaucheNull1() {
        //Given
        LocalDate dateEmbauche = null;
        Employe employe = new Employe();

        employe.setDateEmbauche(dateEmbauche);

        //When
        Integer anciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anciennete).isEqualTo(null);
    }

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec comme date d'embauche, une date dans le passé (date du jour - 5 ans)
     */
    @Test
    public void testGetNbAnneesAncienneteDateEmbauchePast() {
        //Given
        LocalDate dateEmbauchePasse = LocalDate.now().minusYears(5);
        Employe employe = new Employe();
        employe.setDateEmbauche(dateEmbauchePasse);

        //When
        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(5);
    }

    /**
     * Méthode qui teste la méthode "getNombreAnneeAnciennete" de la classe "Employe"
     * avec comme date d'embauche, une date dans le passé (c'est-à-dire, la date du jour - 5 ans)
     * Date d'embauche 5 ans dans le passé => nb années ancienneté : 5
     */
    @Test
    public void testGetNombreAnneeAncienneteDateEmbauchePast2() {
        //Given
        Employe employe = new Employe();
        LocalDate dateEmbauche = LocalDate.now().minusYears(5);
        employe.setDateEmbauche(dateEmbauche);

        //When
        Integer anciennete = employe.getNombreAnneeAnciennete();

        //Then
        Assertions.assertThat(anciennete).isEqualTo(5);
    }

    /**
     * Méthode qui teste la méthode "getPrimeAnnuelle" de la classe "Employe"
     * On calcule la prime annuelle d'un manager à temps plein qui vient d'être embauché (l'embauche a lieu la date du jour)
     */
    @Test
    public void testGetPrimeAnnuelleManagerSansAnciennetePleinTemps() {
        //Given
        // 4 données d'entrée
        LocalDate dateEmbauche = LocalDate.now();
        Integer performance = null;
        String matricule = "M12345";
        Double tempsPartiel = 1.0;
        //Initialise l'employé à partir des données d'entrée
        Employe employe = new Employe("Doe", "John", matricule,
                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double primeCalculee = employe.getPrimeAnnuelle();

        //Then
        //1000 * 1.7 = 1700
        Assertions.assertThat(primeCalculee).isEqualTo(1700.0);
    }

    /**
     * Ici, on a un test paramétrè pour la méthode "getPrimeAnnuelle" de la classe "Employe"
     *
     * @param nbAnneesAnciennete
     * @param performance
     * @param matricule
     * @param tempsPartiel
     * @param primeObtenue
     */
    @ParameterizedTest(name = "Employé anciennete {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}")
    //Change l'annotation
    //Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
    @CsvSource({
            "0,,'M12345',1.0,1700.0", //Manager à plein temps sans ancienneté
            "0,,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté
            "0,1,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté avec performance de base
            "0,,'M12345',0.5,850.0", //Manager à mi-temps sans ancienneté
            "5,,'M12345',1.0,2200.0", //Manager à plein temps avec 5 années d'ancienneté
            "0,3,'T12345',1.0,3300.0", //Technicien à plein temps sans ancienneté avec performance 3
    })
    public void testGetPrimeAnnuelle(Integer nbAnneesAnciennete, Integer performance, String matricule, Double tempsPartiel,
                                     Double primeObtenue) {
        //Given
        // 4 données d'entrée => remplacer par les paramètres
        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);

        Employe employe = new Employe("Doe", "John", matricule,
                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
        //When
        Double primeCalculee = employe.getPrimeAnnuelle();
        //Then
        Assertions.assertThat(primeCalculee).isEqualTo(primeObtenue);
    }

}
