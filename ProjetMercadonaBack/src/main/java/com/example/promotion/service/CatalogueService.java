package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service du catalogue de produits. Lien avec la base de donnée pour y effectuer des opérations.
 */
@Service
public interface CatalogueService {

    /**
     * Retourne la liste des produits qui sont stockés dans la base de données.
     * @return Liste des produits.
     */
    List<Produit> afficherCatalogueComplet();
}
