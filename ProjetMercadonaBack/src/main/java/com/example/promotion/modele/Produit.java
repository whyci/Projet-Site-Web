package com.example.promotion.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String libelle;

    private String description;

    private String categorie;

    private Float prix;

    private Long promotionIdCle;

    public Long getPromotionIdCle() {
        return promotionIdCle;
    }

    public void setPromotionIdCle(Long promotionIdCle) {
        this.promotionIdCle = promotionIdCle;
    }

    /**
     * Getter : Récupère l'identifiant du produit.
     * @return Identifiant du produit.
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String name) {
        this.libelle = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }
}
