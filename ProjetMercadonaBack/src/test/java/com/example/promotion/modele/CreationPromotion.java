package com.example.promotion.modele;

import org.springframework.security.core.parameters.P;

import java.util.Date;

public class CreationPromotion {

    /**
     * Crée une instance de Promotion avec tous les attributs. Utilisée pour les tests de PromotionControleur.
     * @param id Identifiant de la promotion
     * @param debut Date de début de la promotion
     * @param fin Date de fin de la promotion
     * @param pourcentageRemise Pourcentage de remise de la promotion
     * @param idProduit Identifiant du produit associé à la promotion
     * @return Instance de la promotion créée avec les attributs données en paramètre
     */
    public static Promotion creationPromotion(Long id, Date debut, Date fin, Float pourcentageRemise, Long idProduit) {

        Promotion promotion = new Promotion();
        promotion.setId(id);
        promotion.setDebut(debut);
        promotion.setFin(fin);
        promotion.setPourcentageRemise(pourcentageRemise);
        promotion.setProduitIdCle(idProduit);

        return promotion;
    }

    /**
     * Méthode de création des produits utilisées pour les tests, n'utilisant pas toutes les propriétés d'un Produit
     * @param libelle Nom du produit
     * @param prix Prix du produit
     * @param categorie Categorie du produit
     * @param description Description du produit
     * @return Le produit créé avec les paramètres
     */
    public static Produit creationPartieDTProduit(String libelle, Float prix, String categorie,
                                                  String description) {

        Produit produit = new Produit();
        produit.setLibelle(libelle);
        produit.setPrix(prix);
        produit.setCategorie(categorie);
        produit.setDescription(description);

        return produit;
    }
}
