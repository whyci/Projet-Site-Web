package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CatalogueService {

    List<Produit> afficherCatalogueComplet();

    Optional<Produit> afficherCatalogueFiltre(Long id);

    List<Produit> afficherCatalogueFiltreCategorie(String categorie);
}
