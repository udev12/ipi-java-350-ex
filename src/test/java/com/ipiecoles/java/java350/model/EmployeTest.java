//package com.ipiecoles.java.java350.model;
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//
//import java.time.LocalDate;
//
//public class EmployeTest {
//
//    // Scenarii de test
//
//    // corr
//
//
//    //Scénarios de test, 1 scénario = 1 test
//    @Test
//    public void testGetNbAnneesAncienneteDateEmbaucheToday() {
//        //Given
//        LocalDate dateEmbaucheToday = LocalDate.now();
//        Employe employe = new Employe();
//        employe.setDateEmbauche(dateEmbaucheToday);
//        //When
//        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
//        //Then
//        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(0);
//    }
//
//    @Test
//    public void testGetNbAnneesAncienneteDateEmbaucheFuture() {
//        //Given
//        LocalDate dateEmbaucheFuture = LocalDate.now().plusYears(5);
//        Employe employe = new Employe();
//        employe.setDateEmbauche(dateEmbaucheFuture);
//        //When
//        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
//        //Then
//        Assertions.assertThat(nbAnneesAnciennete).isNull();
//    }
//
//    @Test
//    public void testGetNbAnneesAncienneteDateEmbaucheNull() {
//        //Given
//        LocalDate dateEmbauche = null;
//        Employe employe = new Employe();
//        employe.setDateEmbauche(dateEmbauche);
//        //When
//        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
//        //Then
//        Assertions.assertThat(nbAnneesAnciennete).isNull();
//    }
//
//    // Date d'embauche 5 ans dans le passé => nb année ancienneté : 5
//    @Test
//    public void testGetNbAnneesAncienneteDateEmbauchePast() {
//        //Given
//        LocalDate dateEmbaucheFuture = LocalDate.now().minusYears(5);
//        Employe employe = new Employe();
//        employe.setDateEmbauche(dateEmbaucheFuture);
//        //When
//        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
//        //Then
//        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(5);
//    }
//
//
//
//    // Date d'embauche 5 ans dans le passé => nb année ancienneté : 5
//    @Test
//    public void testGetPrimeAnnuelleMatriculeValide() {
//        //Given
////        LocalDate dateEmbaucheFuture = LocalDate.now().minusYears(5);
//        Employe employe = new Employe();
////        String matricule = "M235566";
//        String matricule2 = "M235566";
//        String matricule3 = "M235566";
//        employe.setMatricule(matricule2);
//        employe.setMatricule(matricule3);
//        //When
//
////        Double primeEmploye = employe.getPrimeAnnuelle();
//        Boolean firstLetterMatricule2 = matricule2.startsWith("T");
//        Boolean firstLetterMatricule3 = matricule3.startsWith("C");
////        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
//
//        //Then
//
//        Assertions.assertThat(firstLetterMatricule2).isEqualTo("T");
//        Assertions.assertThat(firstLetterMatricule2).isEqualTo("C");
//    }
//
//
//    @Test
//    public void testGetPrimeAnnuelleManagerSansAnciennetePleinTemps(){
//        //Given
//        // 4 données d'entrée
//        LocalDate dateEmbauche = LocalDate.now();
//        Integer performance = null;
//        String matricule = "M12345";
//        Double tempsPartiel = 1.0;
//        //Initialise l'employé à partir des données d'entrée
//        Employe employe = new Employe("Doe", "John", matricule,
//                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
//        //When
//        Double primeCalculee = employe.getPrimeAnnuelle();
//
//        //Then
//        //1000 * 1.7 = 1700
//        Assertions.assertThat(primeCalculee).isEqualTo(1700.0);
//    }
//
//
//    // scenarii :
//    //
//
////    public static final Double SALAIRE_BASE = 1521.22;
////    public static final Integer NB_CONGES_BASE = 25;
////    public static final Double INDICE_PRIME_BASE = 0.3;
////    public static final Double INDICE_PRIME_MANAGER = 1.7;
////    public static final Double PRIME_ANCIENNETE = 100d;
////    public static final Integer PERFORMANCE_BASE = 1;
////    public static final Integer NB_JOURS_MAX_FORFAIT = 218;
////    private static final double PRIME_BASE = 1000d;
//
//    // poste employe : manager (matricule commence par "M") ou technicien (T) ou commercial (C)
//    // d'où matricule valide : commence par une lettre
//
//    // Entrées manquantes: dateEmbauche (=>null), performance (=>null), matricule (=>null), tempsPartiel (=>null)
//    // Format entrées : format date (dateEmbauche) => null, integer(perf) => null,
//    // string / commence par "M", "T" ou "C" / nombre caractères (matricule) => null,
//    // double(temps partiel) => null
//    // date embauche past, future (=>null), today
//    // perf > 1 : null
//    // matricule
//    // temps partiel > 1 : on garde 1 / null
//
////    @Test //Changer l'annotation
////    //Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
////    public void testGetPrimeAnnuelle(/*Les données d'entrées et les données de sortie*/){
////        //Given
////        // 4 données d'entrée => remplacer par les paramètres
////        LocalDate dateEmbauche = LocalDate.now();
////        Integer performance = null;
////        String matricule = "M12345";
////        Double tempsPartiel = 1.0;
////        //Initialise l'employé à partir des données d'entrée
////        Employe employe = new Employe("Doe", "John", matricule,
////                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
////        //When
////        Double primeCalculee = employe.getPrimeAnnuelle();
////        //Then
////        //1000 * 1.7 = 1700
////        //Remplace la valeur de sortie en dur par le paramètre de sortie
////        Assertions.assertThat(primeCalculee).isEqualTo(1700.0);
////    }
//
////    @ParameterizedTest(name = "Employé ancienneté {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}")
////    @CsvSource({
//////            "0, null, 'M12345', 1.0, 1700.0"
////            "0,, 'M12345', 1.0, 1700.0", 'Manager sans ancienneté à plein temps'" // ne pas mettre 'null'
////    })
////    public void testGetPrimeAnnuelle(Integer nbAnneesAnciennete, Integer performance, String matricule, Double tempsPartiel,
////                                     Double primeObtenue){
////        //Given
////        // 4 données d'entrée => remplacer par les paramètres
////        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);
//////        Integer performance = null;
//////        String matricule = "M12345";
//////        Double tempsPartiel = 1.0;
////        //Initialise l'employé à partir des données d'entrée
////        Employe employe = new Employe("Doe", "John", matricule,
////                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
////        //When
////        Double primeCalculee = employe.getPrimeAnnuelle();
////        //Then
////        //1000 * 1.7 = 1700
////        //Remplace la valeur de sortie en dur par le paramètre de sortie
////        Assertions.assertThat(primeCalculee).isEqualTo(1700.0);
////    }
//
////    @ParameterizedTest(name = "Employé anciennete {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}") //Change l'annotation
//////Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
////    @CsvSource({
////            "0,,'M12345',1.0,1700.0",
////            "0,,'M12345',5.0,0"
////    })
////    public void testGetPrimeAnnuelle(Integer nbAnneesAnciennete, Integer performance, String matricule, Double tempsPartiel,
////                                     Double primeObtenue){
////        //Given
////        // 4 données d'entrée => remplacer par les paramètres
////        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);
////        //Initialise l'employé à partir des données d'entrée
////        Employe employe = new Employe("Doe", "John", matricule,
////                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
////        //When
////        Double primeCalculee = employe.getPrimeAnnuelle();
////        //Then
////        //Remplace la valeur de sortie en dur par le paramètre de sortie
////        Assertions.assertThat(primeCalculee).isEqualTo(primeObtenue);
////    }
//
//
//    @ParameterizedTest(name = "Employé anciennete {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}") //Change l'annotation
////Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
//    @CsvSource({
//            "0,,'M12345',1.0,1700.0", //Manager à plein temps sans ancienneté
//            "0,,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté
//            "0,1,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté avec performance de base
////            "0,,'M12345',0.5,850.0", //Manager à mi-temps sans ancienneté
////            "5,,'M12345',1.0,2200.0", //Manager à plein temps avec 5 années d'ancienneté
////            "0,3,'T12345',1.0,3300.0", //Technicien à plein temps sans ancienneté avec performance 3
//    })
//    public void testGetPrimeAnnuelle(Integer nbAnneesAnciennete, Integer performance, String matricule, Double tempsPartiel,
//                                     Double primeObtenue){
//        //Given
//        // 4 données d'entrée => remplacer par les paramètres
//        LocalDate dateEmbauche = LocalDate.now().minusYears(nbAnneesAnciennete);
//        //Initialise l'employé à partir des données d'entrée
//        Employe employe = new Employe("Doe", "John", matricule,
//                dateEmbauche, Entreprise.SALAIRE_BASE, performance, tempsPartiel);
//        //When
//        Double primeCalculee = employe.getPrimeAnnuelle();
//        //Then
//        //Remplace la valeur de sortie en dur par le paramètre de sortie
//        Assertions.assertThat(primeCalculee).isEqualTo(primeObtenue);
//    }
//
//
////    public class VehiculeTest {
////
////        @Test //Junit 4 : org.junit.Test, Junit 5 : org.junit.jupiter.api.Test
////        public void testCheckBadImmatriculation(){
////            //Given = Initialisation des données d'entrée
////            String immatriculation = "XXXXXX";
////
////            //When = Exécution de la méthode à tester
////            Boolean immatOk = Vehicule.checkImmatriculation(immatriculation);
////
////            //Then = Vérifications de ce qu'a fait la méthode
////            Assertions.assertThat(immatOk).isFalse();
////        }
//
//    // Date d'embauche à aujourd'hui
////    @Test
////    public void testGetNbAnneesAncienneteDateEmbaucheToday(){
////        // Given
////        LocalDate dateEmbaucheToday = LocalDate.now();
////        Employe employe = new Employe();
////        employe.setDateEmbauche((dateEmbaucheToday));
////
////        // When
////        Integer nbAnneesAnciennete = employe.getNombreAnneeAnciennete();
////
////        // Then
////        Assertions.assertThat(nbAnneesAnciennete).isEqualTo(5);
////    }
//
//    // Date d'embauche dans le futur => nb années ancienneté : null
//
//    @Test
//    public void testGetNombreAnneeAncienneteDateEmbaucheInTheFuture() {
//        //return LocalDate.now().getYear() - dateEmbauche.getYear()
//        //Given
////        LocalDate dateEmbaucheToday = LocalDate.now();
//        LocalDate dateEmbaucheFutur = LocalDate.now().plusYears(5);
//        Employe employe = new Employe();
//        employe.setDateEmbauche((dateEmbaucheFutur));
//        //When
//        Integer anciennete = employe.getNombreAnneeAnciennete();
//
//        //Then
////        Assertions.assertThat(anciennete).isEqualTo(null);
//        Assertions.assertThat(anciennete).isNull();
//    }
//
//
//    // Date d'embauche null => nb années ancienneté : null
//    @Test
//    public void testGetNombreAnneeAncienneteDateEmbaucheNull() {
//        //return LocalDate.now().getYear() - dateEmbauche.getYear()
//        //Given
////        LocalDate dateEmbaucheToday = LocalDate.now();
//        LocalDate dateEmbauche = null;
//        Employe employe = new Employe();
//
//        employe.setDateEmbauche(dateEmbauche);
//
//        //When
//        Integer anciennete = employe.getNombreAnneeAnciennete();
//
//        //Then
////        Assertions.assertThat(anciennete).isPositive(); //isNegative
//        Assertions.assertThat(anciennete).isEqualTo(null);
//
//    }
//
//    // Date d'embauche 5 ans dans le passé => nb années ancienneté : 5
//    @Test
//    public void testGetNombreAnneeAncienneteDateEmbaucheInteger() {
//        //return LocalDate.now().getYear() - dateEmbauche.getYear()
//        //Given
////        LocalDate dateEmbaucheToday = LocalDate.now();
//        Employe employe = new Employe();
//        LocalDate dateEmbauche = LocalDate.now().minusYears(5);
//        employe.setDateEmbauche(dateEmbauche);
//        //When
//        Integer anciennete = employe.getNombreAnneeAnciennete();
//
//        //Then
////        Assertions.assertThat(anciennete).isPositive(); //isNegative
//        Assertions.assertThat(anciennete).isEqualTo(5);
//    }
//
//
//}
