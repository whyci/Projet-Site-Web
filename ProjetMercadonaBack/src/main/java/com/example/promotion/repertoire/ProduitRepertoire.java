package com.example.promotion.repertoire;

import com.example.promotion.modele.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepertoire extends JpaRepository<Produit, Integer> {

}