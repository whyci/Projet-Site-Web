package com.example.promotion.repertoire;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.Produit;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrateurRepertoire extends JpaRepository<Administrateur, Integer> {

    /*@Query("SELECT EXISTS ( SELECT 1 FROM Administrateur WHERE adresseMail = :adresse AND motDePasse = :mdp ) AS administrateur")
    boolean administrateurExiste(@Param("adresse") String adresse, @Param("mdp") String mdp);*/

    List<Administrateur> findAdministrateursByAdresseMailAndMotDePasse(String adresseMail, String motDePasse);
}
