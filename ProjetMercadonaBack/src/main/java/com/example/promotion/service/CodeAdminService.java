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

    public List<CodeAdmin> verifierDisponibitilite(String code);

    public void associationAdmin(Long idCodeAdmin, Administrateur administrateur);
}
