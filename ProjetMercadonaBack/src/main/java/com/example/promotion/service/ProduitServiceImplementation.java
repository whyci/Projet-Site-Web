package com.example.promotion.service;

import com.example.promotion.modele.Produit;
import com.example.promotion.repertoire.ProduitRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImplementation implements ProduitService {

    @Autowired
    private ProduitRepertoire produitRepertoire;

    @Override
    public void enregistrerProduit(Produit produit) {
        produitRepertoire.save(produit);
    }

    @Override
    public void supprimerProduit(Integer id) {
        produitRepertoire.deleteById(id);
    }

    @Override
    public List<Produit> afficherProduits() {
        return produitRepertoire.findAll();
    }
}
