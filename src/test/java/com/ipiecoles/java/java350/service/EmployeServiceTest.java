package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityExistsException;

/**
 * Dans cette classe, on recourt aux mocks, afin de rester dans des tests unitaires de la méthode "embaucheEmploye" (classe "EmployeService")
 * Scénérii de test :
 * limite matricule des 100000 matricules atteinte
 * employé existant
 */
@ExtendWith(MockitoExtension.class)
public class EmployeServiceTest {

    @InjectMocks
    private EmployeService employeService;

    // 2 appels à mocker : findByMatricule & findLastMatricule
    @Mock
    EmployeRepository employeRepository;

    /**
     * On est dans le cas d'une ingénieure embauchée à temps plein, et dont le matricule se termine par 99999
     */
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

    /**
     * Autre syntaxe test précédent
     */
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

        //When/Then
        Assertions.assertThatThrownBy(() -> employeService.embaucheEmploye(nom, prenom, poste, niveauEtude, tempsPartiel))
                .isInstanceOf(EmployeException.class)
                .hasMessage("Limite des 100000 matricules atteinte !");
    }

    /**
     * Idem tests précédents, mais on vérifie en plus, que l'employé n'existe déjà pas
     */
    @Test
    public void testEmbaucheEmployeExistantPassed() {
        //Given
        String nom = "Jean";
        String prenom = "Aurore";
        Poste poste = Poste.MANAGER;
        NiveauEtude niveauEtude = NiveauEtude.INGENIEUR;
        Double tempsPartiel = 1d;
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
