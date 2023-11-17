/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.repertoire;

import com.example.promotion.modele.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Répertoire de Administrateur, lien avec la base de donnée et géré par Spring par la technologie JPA. Les injections
 * SQL sont par conséquent déjà sécurisé.
 */
@Repository
public interface AdministrateurRepertoire extends JpaRepository<Administrateur, Integer> {

    List<Administrateur> findAdministrateursByAdresseMailAndMotDePasse(String adresseMail, String motDePasse);
}
