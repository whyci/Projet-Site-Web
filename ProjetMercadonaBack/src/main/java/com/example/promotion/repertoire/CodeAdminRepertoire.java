/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.repertoire;

import com.example.promotion.modele.CodeAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Répertoire de CodeAdmin, lien avec la base de donnée et géré par Spring par la technologie JPA. Les injections
 * SQL sont par conséquent déjà sécurisé.
 */
@Repository
public interface CodeAdminRepertoire extends JpaRepository<CodeAdmin, Integer> {
    List<CodeAdmin> findCodeAdminByCode(String code);
}
