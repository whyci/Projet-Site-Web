package com.example.promotion.modele;

import jakarta.persistence.*;

@Entity
@Table(name = "produit")
public class Produit {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer produit_id;

    private String libelle;

    private String description;

    private String categorie;

    private Integer prix;

    private Integer promotion_id_cle;

    public Integer getPromotion_id_cle() {
        return promotion_id_cle;
    }

    public void setPromotion_id_cle(Integer promotion_id_cle) {
        this.promotion_id_cle = promotion_id_cle;
    }

    private Integer getProduit_id() {
        return produit_id;
    }

    private void setProduit_id(Integer id) {
        this.produit_id = id;
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

    public Integer getPrix() {
        return prix;
    }

    public void setPrix(Integer prix) {
        this.prix = prix;
    }
}
