package com.ipiecoles.java.java350.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Classe qui représente l'entité "Employe"
 */
@Entity
public class Employe {

    static final Logger logger = LoggerFactory.getLogger(Employe.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    private String prenom;

    private String matricule;

    private LocalDate dateEmbauche;

    private Double salaire = Entreprise.SALAIRE_BASE;

    private Integer performance = Entreprise.PERFORMANCE_BASE;

    private Double tempsPartiel = 1.0;

    /**
     * Constructeur par défaut
     */
    public Employe() {
    }

    /**
     * Surcharge du constructeur avec tous les attributs de la classe
     *
     * @param nom          : le nom de l'employé
     * @param prenom       : le prénom de l'employé
     * @param matricule    : le matricule de l'employé
     * @param dateEmbauche : la date d'embauche de l'employé
     * @param salaire      : le salaire de l'employé
     * @param performance  : la performance de l'employé
     * @param tempsPartiel : le temps de travail de l'employé
     */
    public Employe(String nom, String prenom, String matricule, LocalDate dateEmbauche, Double salaire, Integer performance, Double tempsPartiel) {
        this.nom = nom;
        this.prenom = prenom;
        this.matricule = matricule;
        this.dateEmbauche = dateEmbauche;
        this.salaire = salaire;
        this.performance = performance;
        this.tempsPartiel = tempsPartiel;
    }

    /**
     * Méthode calculant le nombre d'années d'ancienneté à partir de la date d'embauche
     *
     * @return null si date d'embauche est nule ou dans le futur ou la différence
     * entre l'année courante et l'année de la date d'embauche
     */
    public Integer getNombreAnneeAnciennete() {
        if (dateEmbauche == null || dateEmbauche.isAfter(LocalDate.now())) {
            logger.info("L'ancienneté n'a pu être calculée, soit parce qu'il n'y a pas de date d'embauche, " +
                    "soit parce que la date d'embauche est dans le futur!");
            return null;
        }
        logger.info("La date d'embauche a été calculée avec succès!");
        return LocalDate.now().getYear() - dateEmbauche.getYear();
    }

    public Integer getNbConges() {
        return Entreprise.NB_CONGES_BASE + this.getNombreAnneeAnciennete();
    }

    public Integer getNbRtt() {
        return getNbRtt(LocalDate.now());
    }

    /**
     * Méthode qui calcule le nombre de jours de RTT de l'employé à partir d'une date
     *
     * @param d : date entrée en paramètre
     * @return le nombre de jours de RTT
     */
    public Integer getNbRtt(LocalDate d) {
        // Si l'année est bissextile, alors nbJoursAnnee = 366, sinon nbJoursAnnee = 365
        int nbJoursAnnee = d.isLeapYear() ? 366 : 365;

        // Nombre de samedis et de dimanches dans l'année
        int nbSamedisEtDimanchesAnnee = 104;
        switch (LocalDate.of(d.getYear(), 1, 1).getDayOfWeek()) {
            case THURSDAY:
                if (d.isLeapYear()) nbSamedisEtDimanchesAnnee = nbSamedisEtDimanchesAnnee + 1;
                break;
            case FRIDAY:
                if (d.isLeapYear()) nbSamedisEtDimanchesAnnee = nbSamedisEtDimanchesAnnee + 2;
                else nbSamedisEtDimanchesAnnee = nbSamedisEtDimanchesAnnee + 1;
            case SATURDAY:
                nbSamedisEtDimanchesAnnee = nbSamedisEtDimanchesAnnee + 1;
                break;
        }

        logger.info("Il y a {} samedis et dimanches dans l'année!", nbSamedisEtDimanchesAnnee);

        // On calcule ici, le nombre de jours fériés ne tombant pas un week-end
        int nbJoursFeriesNeTombantPasUnWeekend = (int) Entreprise.joursFeries(d).stream().filter(localDate ->
                localDate.getDayOfWeek().getValue() <= DayOfWeek.FRIDAY.getValue()).count();

        logger.info("Il y a {} jours fériés qui ne tombent pas un week-end!", nbJoursFeriesNeTombantPasUnWeekend);

        // On renvoie le nombre de jours de RTT
        logger.info("Le nombre de jours de RTT a été calculé avec succès");
        return (int) Math.ceil((nbJoursAnnee - Entreprise.NB_JOURS_MAX_FORFAIT - nbSamedisEtDimanchesAnnee - Entreprise.NB_CONGES_BASE - nbJoursFeriesNeTombantPasUnWeekend) * tempsPartiel);
    }

    /**
     * Calcul de la prime annuelle selon la règle :
     * Pour les managers : Prime annuelle de base bonnifiée par l'indice prime manager
     * Pour les autres employés, la prime de base plus éventuellement la prime de performance calculée si l'employé
     * n'a pas la performance de base, en multipliant la prime de base par un l'indice de performance
     * (égal à la performance à laquelle on ajoute l'indice de prime de base)
     * <p>
     * Pour tous les employés, une prime supplémentaire d'ancienneté est ajoutée en multipliant le nombre d'année
     * d'ancienneté avec la prime d'ancienneté. La prime est calculée au pro rata du temps de travail de l'employé
     *
     * @return la prime annuelle de l'employé en Euros et cents
     */
    //Matricule, performance, date d'embauche, temps partiel, prime
    public Double getPrimeAnnuelle() {
        //Calcule de la prime d'ancienneté
        Double primeAnciennete = Entreprise.PRIME_ANCIENNETE * this.getNombreAnneeAnciennete();

        logger.info("La prime d'ancienneté de l'employé est de {}!", primeAnciennete);

        Double prime;
        //Prime du manager (matricule commençant par M) : Prime annuelle de base multipliée par l'indice prime manager
        //plus la prime d'anciennté.
        if (matricule != null && matricule.startsWith("M")) {
            prime = Entreprise.primeAnnuelleBase() * Entreprise.INDICE_PRIME_MANAGER + primeAnciennete;
        }
        //Pour les autres employés en performance de base, uniquement la prime annuelle plus la prime d'ancienneté.
        else if (this.performance == null || Entreprise.PERFORMANCE_BASE.equals(this.performance)) {
            prime = Entreprise.primeAnnuelleBase() + primeAnciennete;
        }
        //Pour les employés plus performants, on bonnifie la prime de base en multipliant par la performance de l'employé
        // et l'indice de prime de base.
        else {
            prime = Entreprise.primeAnnuelleBase() * (this.performance + Entreprise.INDICE_PRIME_BASE) + primeAnciennete;
        }
        //Au pro rata du temps partiel.
        logger.info("La prime annuelle de l'employé est de {}!", prime * this.tempsPartiel);
        return prime * this.tempsPartiel;
    }

    /**
     * Méthode qui calcule l'augmentation de salaire de l'employé
     *
     * @param pourcentage : pourcentage d'augmentation du salaire de l'employé
     */
    public void augmenterSalaire(Double pourcentage) {
        this.salaire = this.getSalaire() * (1 + pourcentage);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     * @return un objet de type "Employe"
     */
    public Employe setNom(String nom) {
        this.nom = nom;
        return this;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the matricule
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * @param matricule the matricule to set
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * @return the dateEmbauche
     */
    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    /**
     * @param dateEmbauche the dateEmbauche to set
     */
    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    /**
     * @return the salaire
     */
    public Double getSalaire() {
        return salaire;
    }

    /**
     * @param salaire the salaire to set
     */
    public void setSalaire(Double salaire) {
        this.salaire = salaire;
    }

    public Integer getPerformance() {
        return performance;
    }

    public void setPerformance(Integer performance) {
        this.performance = performance;
    }

    public Double getTempsPartiel() {
        return tempsPartiel;
    }

    public void setTempsPartiel(Double tempsPartiel) {
        this.tempsPartiel = tempsPartiel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employe)) return false;
        Employe employe = (Employe) o;
        return Objects.equals(id, employe.id) &&
                Objects.equals(nom, employe.nom) &&
                Objects.equals(prenom, employe.prenom) &&
                Objects.equals(matricule, employe.matricule) &&
                Objects.equals(dateEmbauche, employe.dateEmbauche) &&
                Objects.equals(salaire, employe.salaire) &&
                Objects.equals(performance, employe.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom, prenom, matricule, dateEmbauche, salaire, performance);
    }
}
