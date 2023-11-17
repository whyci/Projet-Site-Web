/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.CodeAdmin;
import com.example.promotion.repertoire.CodeAdminRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implémentation des fonctions de AdministrateurService.
 */
@Service
public class CodeAdminServiceImplementation implements CodeAdminService {

    @Autowired
    private CodeAdminRepertoire codeAdminRepertoire;

    @Override
    public List<CodeAdmin> verifierDisponibitilite(String code) {
        return codeAdminRepertoire.findCodeAdminByCode(code);
    }

    @Override
    public void associationAdmin(Long idCodeAdmin, Administrateur administrateur) {
        // Vérifie si le codeAdmin existe bien dans la base de donnée. Sinon, ne fait rien.
        if (codeAdminRepertoire.findById(Math.toIntExact(idCodeAdmin)).isPresent()) {

            // Récupère le codeAdmin à modifier
            CodeAdmin codeAdmin = codeAdminRepertoire.findById(Math.toIntExact(idCodeAdmin)).get();

            // Affecte le nouvel identifiant de l'admin dans le codeAdmin
            codeAdmin.setAdministrateur(administrateur);

            // Enregistre le changement du codeAdmin dans la base de donnée.
            codeAdminRepertoire.save(codeAdmin);
        }
    }

    @Override
    public void ajoutCodeAdmin(String code) {
        CodeAdmin codeAdmin = new CodeAdmin();
        codeAdmin.setCode(code);
        codeAdminRepertoire.save(codeAdmin);
    }
}
