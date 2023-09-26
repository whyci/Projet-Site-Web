package com.example.promotion.repertoire;

import com.example.promotion.modele.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepertoire extends JpaRepository<Produit, Integer> {

}
