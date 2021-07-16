package com.ipiecoles.java.java350.repository;

import com.ipiecoles.java.java350.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    @Query("select max(substring(matricule,2)) from Employe")
    String findLastMatricule();

    Employe findByMatricule(String matricule);

    Employe findByNom(String nom);

    @Query("select avg(performance) from Employe where SUBSTRING(matricule,0,1) = ?1 ")
    Double avgPerformanceWhereMatriculeStartsWith(String premiereLettreMatricule);
}
