package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import com.thoughtworks.gauge.Step;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Component;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @InjectMocks
    private EmployeService employeService;

    // 2 appels à mocker : findByMatricule & findLastMatricule
    @Mock
    EmployeRepository employeRepository;

    // SANS RETURN
//    @Test
//    public void testEmbauchePremierEmployePleinTempsManagerIngenieur() throws Exception {
////        //Given
////        String nom = "Jean";
////        String prenom = "Aurore";
////        Poste poste = Poste.MANAGER;
////        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
////        Double tempsPartiel = 1d;
//        //Given
//        Employe employe = employeRepository.save(new Employe("Doe", "John", "M12345", LocalDate.now(),
//                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 1.0));
////        Mockito.when(vehiculeRepository.findOne(Mockito.anyLong())).thenReturn(vehicule);
////        Mockito.when(vehiculeRepository.save(Mockito.any(Vehicule.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
////        //When addProprietaire(idVehicule, idProprietaire)
////        vehiculeService.addProprietaire(1L, 2L);
//        //Then
//        ArgumentCaptor<Employe> employeCaptor = ArgumentCaptor.forClass(Employe.class);
//        Mockito.verify(employeRepository, Mockito.times(1)).save(employeCaptor.capture());
//        Assertions.assertThat(employeCaptor.getValue().equals(employe));
//
//
//////        Employe employe = employeRepository.findByMatricule("M00001");
////        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
////        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
////
////        //When
////        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
////
////        //Then
//////        Employe employe = employeRepository.findByMatricule("M00001");
////        Assertions.assertThat(employe).isNotNull();
////        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
////        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
////        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
////        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
////        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
////        //1521.22 * 1.6
////        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
//
//    }
//
//
//
//    @Test
//    public void testEmbauchePremierEmployeTempsPartielManagerIngenieur() throws Exception{
//
//        //Given
//        Employe employe = employeRepository.save(new Employe("Doe", "John", "M12345", LocalDate.now(),
//                Entreprise.SALAIRE_BASE, Entreprise.PERFORMANCE_BASE, 0.5));
////        Mockito.when(vehiculeRepository.findOne(Mockito.anyLong())).thenReturn(vehicule);
////        Mockito.when(vehiculeRepository.save(Mockito.any(Vehicule.class))).thenAnswer(AdditionalAnswers.returnsFirstArg());
////        //When addProprietaire(idVehicule, idProprietaire)
////        vehiculeService.addProprietaire(1L, 2L);
//        //Then
//        ArgumentCaptor<Employe> employeCaptor = ArgumentCaptor.forClass(Employe.class);
//        Mockito.verify(employeRepository, Mockito.times(1)).save(employeCaptor.capture());
//        Assertions.assertThat(employeCaptor.getValue().equals(employe));
//
//        //        Assertions.assertThat(employe).isNotNull();
////        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
////        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
////        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
////        Assertions.assertThat(employe.getMatricule()).isEqualTo("M12346");
////        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
////        //1521.22 * 1.6
////        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
//
//    }

    // corr

    @Test
    void testEmbauchePremierEmployePleinTempsManagerIngenieur() /*throws Exception */{
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);

        //When
//        employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);

        //Then
        ArgumentCaptor<Employe> employeArgumentCaptor = ArgumentCaptor.forClass(Employe.class);
        Mockito.verify(employeRepository, Mockito.times(1)).save(employeArgumentCaptor.capture());
        Employe employe = employeArgumentCaptor.getValue();

        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        //1521.22 * 1.6
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);

    }


    // AVEC RETURN
//    @Test
//    public void testEmbauchePremierEmployePleinTempsManagerIngenieur() throws Exception{
//        //Given
//        String nom = "Jean";
//        String prenom = "Aurore";
//        Poste poste = Poste.MANAGER;
//        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
//        Double tempsPartiel = 1d;
////        Employe employe = employeRepository.findByMatricule("M00001");
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
//        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
//
//        //When
//        Employe employe = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//
//        //Then
////        Employe employe = employeRepository.findByMatricule("M00001");
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
//
    @Test
    void testEmbaucheDeuxiemeEmployeTempsPartielManagerIngenieur() throws Exception {
        //Given
        String nom = "Dupont";
        String prenom = "Emilie";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("12345");
        Mockito.when(employeRepository.findByMatricule("M12346")).thenReturn(null);
        //When
        Employe employe = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
        //Then

//        Assertions.assertThat(employe).isNotNull();
        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
        Assertions.assertThat(employe.getMatricule()).isEqualTo("M12346");
        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
        //1521.22 * 1.6
        Assertions.assertThat(employe.getSalaire()).isEqualTo(2433.95d);
    }

//    @Test
//    public void testEmbauchePremierEmployeTempsPartielManagerIngenieur() throws Exception{
//        //Given
//        String nom = "Jean";
//        String prenom = "Aurore";
//        Poste poste = Poste.MANAGER;
//        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
//        Double tempsPartiel = 0.5d;
////        Employe employe = employeRepository.findByMatricule("M00001");
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn(null);
//        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
//
//        //When
//        Employe employe = employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//
//        //Then
////        Employe employe = employeRepository.findByMatricule("M00001");
//        Assertions.assertThat(employe).isNotNull();
//        Assertions.assertThat(employe.getNom()).isEqualTo(nom);
//        Assertions.assertThat(employe.getPrenom()).isEqualTo(prenom);
//        Assertions.assertThat(employe.getDateEmbauche()).isEqualTo(LocalDate.now());
//        Assertions.assertThat(employe.getMatricule()).isEqualTo("M00001");
//        Assertions.assertThat(employe.getTempsPartiel()).isEqualTo(tempsPartiel);
//        //1521.22 * 1.6
//        Assertions.assertThat(employe.getSalaire()).isEqualTo(1216.98d);
//
//    }

//    @Test
//    public void testEmbaucheEmployeLimiteMatricule() throws Exception{
//        //Given
//        String nom = "Jean";
//        String prenom = "Aurore";
//        Poste poste = Poste.MANAGER;
//        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
//        Double tempsPartiel = 1d;
////        Employe employe = employeRepository.findByMatricule("M00001");
//        Mockito.when(employeRepository.findLastMatricule()).thenReturn('99999');
////        Mockito.when(employeRepository.findByMatricule("M00001")).thenReturn(null);
//
//        //When
//        try{
//            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//            Assertions.fail("Aurait dû planter");
//        } catch(Exception e){
//            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
//            Assertions.assertThat(e.getMessage()).isEqualTo("Limite des 100000 matricules atteinte");
//        }
//
//    }

     // Autre syntaxe (erreur, comprendre pourquoi)
    @Test
    void testEmbaucheEmployeLimiteMatricule() {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99999");

        //When
        try {
            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("Limite des 100000 matricules atteinte !");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Limite des 100000 matricules atteinte !");


    }

    // Autre syntaxe
    @Test
    void testEmbaucheEmployeLimiteMatricule1() {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99999");

//        //When
//        try {
//            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
//            Assertions.fail("Aurait du planter");
//        } catch (Exception e) {
//            //Then
//            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
//            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
//            Assertions.assertThat(e.getMessage()).isEqualTo("Limite des 100000 matricules atteinte !");
//        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Limite des 100000 matricules atteinte !");

    }


    @Test
    public void testEmbaucheEmployeEmployeExistantFailed() throws Exception{
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
        String matricule = "12346";
        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn(matricule);


        //When
        try {
            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
            Assertions.fail("Aurait du planter");
        } catch (EntityExistsException e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EmployeException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'employé de matricule " + matricule + " existe déjà en BDD");
        }

//        //When/Then
//        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel))
//                .isInstanceOf(EmployeException.class)
//                .hasMessage("Limite des 100000 matricules atteinte !");

    }


    @Test
    public void testEmbaucheEmployeExistantPassed() {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
        //Employe employe = new Employe("Doe", "John", "M99999".../*Les paramètres pour créer un employé*/);
        //Ajouter les mocks...
        Mockito.when(employeRepository.findLastMatricule()).thenReturn("99998");
        Mockito.when(employeRepository.findByMatricule("M99999")).thenReturn(new Employe());

        //When
        try {
            employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel);
            Assertions.fail("Aurait du planter");
        } catch (Exception e) {
            //Then
            //Vérifie qu'une exception est bien levée, et que c'est la bonne exception
            Assertions.assertThat(e).isInstanceOf(EntityExistsException.class);
            Assertions.assertThat(e.getMessage()).isEqualTo("L'employé de matricule M99999 existe déjà en BDD");
        }

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel))
                .isInstanceOf(EntityExistsException.class)
                .hasMessage("L'employé de matricule M99999 existe déjà en BDD");


    }


}
