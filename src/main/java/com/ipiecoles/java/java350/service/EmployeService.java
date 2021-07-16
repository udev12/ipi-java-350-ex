package com.ipiecoles.java.java350.service;

import com.ipiecoles.java.java350.exception.EmployeException;
import com.ipiecoles.java.java350.model.Employe;
import com.ipiecoles.java.java350.model.Entreprise;
import com.ipiecoles.java.java350.model.NiveauEtude;
import com.ipiecoles.java.java350.model.Poste;
import com.ipiecoles.java.java350.repository.EmployeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.time.LocalDate;

@Service
public class EmployeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeRepository employeRepository;


    /**
     * Méthode enregistrant un nouvel employé dans l'entreprise
     *
     * @param nom          Le nom de l'employé
     * @param prenom       Le prénom de l'employé
     * @param poste        Le poste de l'employé
     * @param niveauEtude  Le niveau d'étude de l'employé
     * @param tempsPartiel Le pourcentage d'activité en cas de temps partiel
     * @throws EmployeException      Si on arrive au bout des matricules possibles
     * @throws EntityExistsException Si le matricule correspond à un employé existant
     */
//    public void embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
////    public Employe embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
//
//        logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
//        logger.info("Ceci est une information");
//        logger.warn("Attention !");
//        logger.error("Problème détecté !");
//
//        //Récupération du type d'employé à partir du poste
//        String typeEmploye = poste.name().substring(0,1);
//
//        //Récupération du dernier matricule...
//        String lastMatricule = employeRepository.findLastMatricule();
//        if(lastMatricule == null){
//            lastMatricule = Entreprise.MATRICULE_INITIAL;
//        }
//        //... et incrémentation
//        Integer numeroMatricule = Integer.parseInt(lastMatricule) + 1;
//        if(numeroMatricule >= 100000){
//            throw new EmployeException("Limite des 100000 matricules atteinte !");
//        }
//        //On complète le numéro avec des 0 à gauche
//        String matricule = "00000" + numeroMatricule;
//        matricule = typeEmploye + matricule.substring(matricule.length() - 5);
//
//        //On vérifie l'existence d'un employé avec ce matricule
//        if(employeRepository.findByMatricule(matricule) != null){
//            throw new EntityExistsException("L'employé de matricule " + matricule + " existe déjà en BDD");
//        }
//
//        //Calcul du salaire
//        Double salaire = Entreprise.COEFF_SALAIRE_ETUDES.get(niveauEtude) * Entreprise.SALAIRE_BASE;
//        if(tempsPartiel != null){
//            salaire = salaire * tempsPartiel;
//        }
//
//        // Arrondi au centime
//        salaire = Math.round(salaire * 100) / 100d;
//
//        //Création et sauvegarde en BDD de l'employé.
//        Employe employe = new Employe(nom, prenom, matricule, LocalDate.now(), salaire, Entreprise.PERFORMANCE_BASE, tempsPartiel);
//
//        employeRepository.save(employe);
//
////        return employe;
//
//    }


    // avec logs
//    public void embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
////    public Employe embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
//
////        logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
////        logger.info("Ceci est une information");
////        logger.warn("Attention !");
////        logger.error("Problème détecté !");
//        logger.info("Embauche de l'employé {} {} diplômé de {} en tant que {} avec un taux d'activité de {}",
//                nom, prenom, niveauEtude, poste, tempsPartiel);
//
//        //Récupération du type d'employé à partir du poste
//        String typeEmploye = poste.name().substring(0,1);
//        logger.debug("Type employé : {}", typeEmploye);
//
//        //Récupération du dernier matricule...
//        String lastMatricule = employeRepository.findLastMatricule();
//        if(lastMatricule == null){
//            logger.info("Matricule intitial affecté")
//            lastMatricule = Entreprise.MATRICULE_INITIAL;
//        }
//        logger.debug("Dernier matricule : {}", lastMatricule);
//
//        //... et incrémentation
//        Integer numeroMatricule = Integer.parseInt(lastMatricule) + 1;
//        if(numeroMatricule >= 100000){
//            logger.warn("Attention limite matricule!");
//            logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
//            throw new EmployeException("Limite des 100000 matricules atteinte !");
//        }
//
//        //On complète le numéro avec des 0 à gauche
//        String matricule = "00000" + numeroMatricule;
//        matricule = typeEmploye + matricule.substring(matricule.length() - 5);
//        logger.info("Matricule employé : {}", matricule);
//
//        //On vérifie l'existence d'un employé avec ce matricule
//        if(employeRepository.findByMatricule(matricule) != null){
//            logger.warn("Attention le matricule {} existe déjà!", matricule);
//            logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
//            throw new EntityExistsException("L'employé de matricule " + matricule + " existe déjà en BDD");
//        }
//
//        //Calcul du salaire
//        Double salaire = Entreprise.COEFF_SALAIRE_ETUDES.get(niveauEtude) * Entreprise.SALAIRE_BASE;
//        if(tempsPartiel != null){
//            salaire = salaire * tempsPartiel;
//        }
//
//        // Arrondi au centime
//        salaire = Math.round(salaire * 100) / 100d;
//        logger.info("Salaire employé : {}", salaire);
//
//        //Création et sauvegarde en BDD de l'employé.
//        Employe employe = new Employe(nom, prenom, matricule, LocalDate.now(), salaire, Entreprise.PERFORMANCE_BASE, tempsPartiel);
//
//        employeRepository.save(employe);
//        logger.info("Employé {} {} sauvegardé avec succès!", employe.getNom(), employe.getPrenom());
////        return employe;
//
//    }

    //corr
    public Employe embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
//        logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
//        logger.info("Ceci est une information");
//        logger.warn("Attention !");
//        logger.error("Problème détecté !");
        logger.info("Embauche de l'employé {} {} diplômé de {} en tant que {} avec un taux d'activité de {}",
                nom, prenom, niveauEtude, poste, tempsPartiel);
        //Récupération du type d'employé à partir du poste
        String typeEmploye = poste.name().substring(0, 1);
        logger.debug("Type d'employé {}", typeEmploye);
        //Récupération du dernier matricule...
        String lastMatricule = employeRepository.findLastMatricule();
        if (lastMatricule == null) {
            logger.info("Aucun matricule trouvé, attribution du matricule par défaut 00001");
            lastMatricule = Entreprise.MATRICULE_INITIAL;
        }
        //... et incrémentation
        Integer numeroMatricule = Integer.parseInt(lastMatricule) + 1;
        if (numeroMatricule >= 100000) {
            logger.error("Limite des 100000 matricules atteinte !");
            throw new EmployeException("Limite des 100000 matricules atteinte !");
        }
        if (numeroMatricule >= 90000) {
            logger.warn("Attention, seuil des 90000 matricules atteints !");
        }
        //On complète le numéro avec des 0 à gauche
        String matricule = "00000" + numeroMatricule;
        matricule = typeEmploye + matricule.substring(matricule.length() - 5);
        logger.debug("Matricule calculé {}", matricule);
        //On vérifie l'existence d'un employé avec ce matricule
        if (employeRepository.findByMatricule(matricule) != null) {
            logger.error("L'employé de matricule {} existe déjà en BDD", matricule);
            throw new EntityExistsException("L'employé de matricule " + matricule + " existe déjà en BDD");
        }
        //Calcul du salaire
        Double salaire = Entreprise.COEFF_SALAIRE_ETUDES.get(niveauEtude) * Entreprise.SALAIRE_BASE;
        if (tempsPartiel != null) {
            salaire = salaire * tempsPartiel;
        }
        //Arrondi au centime
        salaire = Math.round(salaire * 100) / 100d;
        logger.debug("Salaire calculé {}", salaire);
        //Création et sauvegarde en BDD de l'employé.
        Employe employe = new Employe(nom, prenom, matricule, LocalDate.now(), salaire, Entreprise.PERFORMANCE_BASE, tempsPartiel);
        logger.info("Employé créé : {}", employe);
        employe = employeRepository.save(employe);
        //System.out.println(employe.getMatricule());
        return employe;
    }


    // Pour tester les mocks
////    public void embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
//    public Employe embaucheEmploye(String nom, String prenom, Poste poste, NiveauEtude niveauEtude, Double tempsPartiel) throws EmployeException, EntityExistsException {
//
//        //Récupération du type d'employé à partir du poste
//        String typeEmploye = poste.name().substring(0,1);
//
//        //Récupération du dernier matricule...
//        String lastMatricule = employeRepository.findLastMatricule();
//        if(lastMatricule == null){
//            lastMatricule = Entreprise.MATRICULE_INITIAL;
//        }
//        //... et incrémentation
//        Integer numeroMatricule = Integer.parseInt(lastMatricule) + 1;
//        if(numeroMatricule >= 100000){
//            throw new EmployeException("Limite des 100000 matricules atteinte !");
//        }
//        //On complète le numéro avec des 0 à gauche
//        String matricule = "00000" + numeroMatricule;
//        matricule = typeEmploye + matricule.substring(matricule.length() - 5);
//
//        //On vérifie l'existence d'un employé avec ce matricule
//        if(employeRepository.findByMatricule(matricule) != null){
//            throw new EntityExistsException("L'employé de matricule " + matricule + " existe déjà en BDD");
//        }
//
//        //Calcul du salaire
//        Double salaire = Entreprise.COEFF_SALAIRE_ETUDES.get(niveauEtude) * Entreprise.SALAIRE_BASE;
//        if(tempsPartiel != null){
//            salaire = salaire * tempsPartiel;
//        }
//
//        // Arrondi au centime
//        salaire = Math.round(salaire * 100) / 100d;
//
//        //Création et sauvegarde en BDD de l'employé.
//        Employe employe = new Employe(nom, prenom, matricule, LocalDate.now(), salaire, Entreprise.PERFORMANCE_BASE, tempsPartiel);
//
//        employeRepository.save(employe);
//
//        return employe;
//
//    }

    /**
     * Méthode calculant la performance d'un commercial en fonction de ses objectifs et du chiffre d'affaire traité dans l'année.
     * Cette performance lui est affectée et sauvegardée en BDD
     * <p>
     * 1 : Si le chiffre d'affaire est inférieur de plus de 20% à l'objectif fixé, le commercial retombe à la performance de base
     * 2 : Si le chiffre d'affaire est inférieur entre 20% et 5% par rapport à l'ojectif fixé, il perd 2 de performance (dans la limite de la performance de base)
     * 3 : Si le chiffre d'affaire est entre -5% et +5% de l'objectif fixé, la performance reste la même.
     * 4 : Si le chiffre d'affaire est supérieur entre 5 et 20%, il gagne 1 de performance
     * 5 : Si le chiffre d'affaire est supérieur de plus de 20%, il gagne 4 de performance
     * <p>
     * Si la performance ainsi calculée est supérieure à la moyenne des performances des commerciaux, il reçoit + 1 de performance.
     *
     * @param matricule  le matricule du commercial
     * @param caTraite   le chiffre d'affaire traité par le commercial pendant l'année
     * @param objectifCa l'object de chiffre d'affaire qui lui a été fixé
     * @throws EmployeException Si le matricule est null ou ne commence pas par un C
     */
    public Employe calculPerformanceCommercial(String matricule, Long caTraite, Long objectifCa) throws EmployeException {

//        logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
//        logger.info("Ceci est une information");
//        logger.warn("Attention !");
//        logger.error("Problème détecté !");

        // Vérification des paramètres d'entrée
        if (caTraite == null || caTraite < 0) {
            logger.error("Chiffre d'affaires traité {} n'est pas valide !", caTraite);
            logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
            throw new EmployeException("Le chiffre d'affaires traité ne peut être négatif ou null !");
        }
        if (objectifCa == null || objectifCa < 0) {
            logger.error("L'objectif de chiffre d'affaires {} n'est pas valide !", objectifCa);
            logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
            throw new EmployeException("L'objectif de chiffre d'affaires ne peut être négatif ou null !");
        }
        if (matricule == null || !matricule.startsWith("C")) {
            logger.error("Le matricule {} n'est pas valide !", matricule);
            logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
            throw new EmployeException("Le matricule ne peut être null et doit commencer par un C !");
        }

//        //Recherche de l'employé dans la base
        Employe employe = employeRepository.findByMatricule(matricule);
        if (employe == null) {
            logger.warn("Le matricule {} est introuvable dans la bdd!", matricule);
            logger.debug("Ceci est un élément purement technique, à des fins de debuggage");
            throw new EmployeException("Le matricule " + matricule + " n'existe pas !");
        }

//        Employe employe1


        if (employe != null && caTraite != null && objectifCa != null) {
            Integer performance = Entreprise.PERFORMANCE_BASE;
            //Cas 2
            if (caTraite >= objectifCa * 0.8 && caTraite < objectifCa * 0.95) {
                performance = Math.max(Entreprise.PERFORMANCE_BASE, employe.getPerformance() - 2);
            }
            //Cas 3
            else if (caTraite >= objectifCa * 0.95 && caTraite <= objectifCa * 1.05) {
                performance = Math.max(Entreprise.PERFORMANCE_BASE, employe.getPerformance());
            }
            //Cas 4
            else if (caTraite <= objectifCa * 1.2 && caTraite > objectifCa * 1.05) {
                performance = employe.getPerformance() + 1;
            }
            //Cas 5
            else if (caTraite > objectifCa * 1.2) {
                performance = employe.getPerformance() + 4;
            }
            //Si autre cas, on reste à la performance de base.

            //Calcul de la performance moyenne
            Double performanceMoyenne = employeRepository.avgPerformanceWhereMatriculeStartsWith("C");
//        if(performanceMoyenne > 0 && performance > performanceMoyenne){
            if (performanceMoyenne != null && performance > performanceMoyenne) {
                performance++;
            }
            logger.info("Performance employé {} : ", performance);
            logger.info("Performance moyenne employé {} : ", performanceMoyenne);

            //Affectation et sauvegarde
            employe.setPerformance(performance);
            employeRepository.save(employe);
        }
        return employe;
    }
}
