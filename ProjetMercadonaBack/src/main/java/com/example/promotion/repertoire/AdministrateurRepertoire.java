package com.example.promotion.repertoire;

import com.example.promotion.modele.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdministrateurRepertoire extends JpaRepository<Administrateur, Integer> {

    List<Administrateur> findAdministrateursByAdresseMailAndMotDePasse(String adresseMail, String motDePasse);
}
