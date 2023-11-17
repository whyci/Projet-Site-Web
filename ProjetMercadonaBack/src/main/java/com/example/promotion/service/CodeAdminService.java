/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.CodeAdmin;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service de CodeAdmin. Lien avec la base de donnée pour y effectuer des opérations.
 */
@Service
public interface CodeAdminService {

    /**
     * Retourne une liste de CodeAdmin qui comporte le code donné en paramètre.
     * @param code Code que l'on cherche dans tous les codeAdmin existant dans la base de donnée.
     * @return Liste de CodeAdmin.
     */
    List<CodeAdmin> verifierDisponibitilite(String code);

    /**
     * Associe un nouvel administrateur avec un codeAdmin que l'on cible par son identifiant.
     * @param idCodeAdmin Identifiant du codeAdmin.
     * @param administrateur Administrateur que l'on associe au codeAdmin.
     */
    void associationAdmin(Long idCodeAdmin, Administrateur administrateur);

    /**
     * Ajoute un CodeAdmin dans la base de donnée en lui attribuant directement un code.
     * @param code Code du codeAdmin qu'on attribut au codeAdmin.
     */
    void ajoutCodeAdmin(String code);
}
