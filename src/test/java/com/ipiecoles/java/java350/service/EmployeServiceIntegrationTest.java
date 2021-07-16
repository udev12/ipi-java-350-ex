//package com.ipiecoles.java.java350.service;
//
//import com.ipiecoles.java.java350.model.Employe;
//import com.ipiecoles.java.java350.model.NiveauEtude;
//import com.ipiecoles.java.java350.model.Poste;
//import com.ipiecoles.java.java350.repository.EmployeRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.time.LocalDate;
//
//@SpringBootTest
//class EmployeServiceIntegrationTest {
//
//    @Autowired
//    private EmployeService employeService;
//
//    @Autowired
//    private EmployeRepository employeRepository;
//
//    @AfterEach
//    @BeforeEach
//    public void cleanUp() {
//        employeRepository.deleteAll();
//    }
//
//    @Test
//    void testEmbauchePremierEmployePleinTempsManagerIngenieur() throws Exception {
//        //Given
//        String nom = "Jean";
//        String prenom = "Aurore";
//        Poste poste = Poste.MANAGER;
//        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
//        Double tempsPartiel = 1d;
//
//        //When
//        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//
//        //Then
//        Employe employe = employeRepository.findByMatricule("M00001");
//        Assertions.assertThat(employe).isNotNull();
//        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
//        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
//        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
//        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
//        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
//        //1521.22 * 1.6
//        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
//
//    }
//}
//
//
////package com.ipiecoles.java.java350.service;
////
////import com.ipiecoles.java.java350.exception.EmployeException;
////import com.ipiecoles.java.java350.model.Employe;
////import com.ipiecoles.java.java350.model.Entreprise;
////import com.ipiecoles.java.java350.model.NiveauEtude;
////import com.ipiecoles.java.java350.model.Poste;
////import com.ipiecoles.java.java350.repository.EmployeRepository;
////import org.assertj.core.api.Assertions;
////import org.junit.Assert;
////import org.junit.jupiter.api.Test;
////import org.junit.jupiter.params.ParameterizedTest;
////import org.junit.jupiter.params.provider.CsvSource;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
////import org.springframework.boot.test.context.SpringBootTest;
////
////import java.time.LocalDate;
////
////@SpringBootTest
////@DataJpaTest
////public class EmployeServiceTest {
////
////    //Tester de façon intégrée un cas nominal de la méthode `embaucheEmploye`
////    // de la classe `EmployeService`
////
//////             * @param nom Le nom de l'employé
//////                * @param prenom Le prénom de l'employé
//////                * @param poste Le poste de l'employé
//////                * @param niveauEtude Le niveau d'étude de l'employé
//////                * @param tempsPartiel Le pourcentage d'activité en cas de temps partiel
////
//////        TECHNICIEN,
//////                MANAGER,
//////                COMMERCIAL
////
//////        CAP,
//////                BAC,
//////                BTS_IUT,
//////                LICENCE,
//////                MASTER,
//////                INGENIEUR,
//////                DOCTORAT
////
////    @Autowired
////    EmployeService employeService;
////
////    @Autowired
////    private EmployeRepository employeRepository;
////
////    @Test
////    public void testEmbauchePremierEmployePleinTempsManagerIngenieur () throws Exception {
////        // Given
////        Employe employe = new Employe();
//////        Employe employe = new Employe(nom, prenom, matricule, LocalDate.now(), salaire, Entreprise.PERFORMANCE_BASE, tempsPartiel);
////
//////        Employe nom = employe.setNom("Jean");
////        String nom = "Jean";
////        String prenom = "Aurore";
////        Poste poste = Poste.MANAGER;
////        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
////        Double tempsPartiel = 1d;
////        employe.setNom(nom);
//////        EmployeService employe = new EmployeService();
//////        Employe employeATeste = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempspartiel);
////
////        //Employe employe = new Employe(
////        // nom,
////        // prenom,
////        // matricule,
////        // LocalDate.now(),
////        // salaire,
////        // Entreprise.PERFORMANCE_BASE,
////        // tempsPartiel);
////
////        // When
//////        Employe employeEmbauche = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//////        String lastMatricule = employeRepository.findLastMatricule();
//////        try {
////            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//////        } catch (EmployeException e) {
//////            e.printStackTrace();
//////        }
//////        String matricule = employe.getMatricule();
//////        employe.getNom();
////        // Then
////        Employe employe = employeRepository.findByMatricule("M00001");
////        Assertions.
////        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
////        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
////        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
////        Assertions.assertThat(employe.getSalaire()).isEqualTo(1700);
////        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
////
////    }
////
////    @ParameterizedTest(name = "Employé anciennete {0}, performance {1}, matricule {2}, temps partiel {3} => Prime {4}") //Change l'annotation
//////Rajoute l'annotation contenant les scénarios de test  (réflechir aux dfférents scénarios possibles)
////    @CsvSource({
////            "0,,'M12345',1.0,1700.0", //Manager à plein temps sans ancienneté
////            "0,,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté
////            "0,1,'T12345',1.0,1000.0", //Technicien à plein temps sans ancienneté avec performance de base
//////            "0,,'M12345',0.5,850.0", //Manager à mi-temps sans ancienneté
//////            "5,,'M12345',1.0,2200.0", //Manager à plein temps avec 5 années d'ancienneté
//////            "0,3,'T12345',1.0,3300.0", //Technicien à plein temps sans ancienneté avec performance 3
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
////
////
////}
