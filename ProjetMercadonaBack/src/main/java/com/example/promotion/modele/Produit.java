/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.modele;

import jakarta.persistence.*;

/**
 * la classe produit definis la table qui sera dans la base de données
 * les attributs prives correspondent aux elements de la table produit.
 * Les fonctions seront appeles par le repertoire de la classe et sont publiques.
 */
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "promotion_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "promotion_fkey"))
    private Promotion promotion;

    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Promotion getPromotionIdCle() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

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
