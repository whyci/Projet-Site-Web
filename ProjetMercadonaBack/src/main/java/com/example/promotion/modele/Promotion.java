/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.modele;

import jakarta.persistence.*;

import java.util.Date;

/**
 * la classe promotion definis la table qui sera dans la base de données
 * les attributs prives correspondent aux elements de la table promotion.
 * Les fonctions seront appeles par le repertoire de la classe et sont publiques.
 */
@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date debut;

    private Date fin;

    private Float pourcentageRemise;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "produit_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "produit_fkey"))
    private Produit produit;

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Float getPourcentageRemise() {
        return pourcentageRemise;
    }

    public void setPourcentageRemise(Float pourcentageRemise) {
        this.pourcentageRemise = pourcentageRemise;
    }
}
