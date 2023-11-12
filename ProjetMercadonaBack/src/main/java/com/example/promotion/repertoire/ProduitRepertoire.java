package com.example.promotion.repertoire;

import com.example.promotion.modele.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Répertoire de Produit, lien avec la base de donnée et géré par Spring par la technologie JPA. Les injections SQL sont
 * par conséquent déjà sécurisé.
 */
@Repository
public interface ProduitRepertoire extends JpaRepository<Produit, Integer> {
}
