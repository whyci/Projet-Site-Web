package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.repertoire.ProduitRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CatalogueServiceImplementation implements CatalogueService {

    @Autowired
    private ProduitRepertoire produitRepertoire;

    @Override
    public List<Produit> afficherCatalogueComplet() {
        return produitRepertoire.findAll();
    }

    @Override
    public Optional<Produit> afficherCatalogueFiltre(Long id) {
        return produitRepertoire.findById(Math.toIntExact(id));
    }

    @Override
    public List<Produit> afficherCatalogueFiltreCategorie(String categorie) {
        return produitRepertoire.findProduitByCategorie(categorie);
    }
}
