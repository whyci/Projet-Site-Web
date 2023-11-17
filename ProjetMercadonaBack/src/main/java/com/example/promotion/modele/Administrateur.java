/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.modele;

import jakarta.persistence.*;

import java.util.Date;

/**
 * la classe administrateur definis la table qui sera dans la base de données
 * les attributs prives correspondent aux elements de la table administrateur.
 * Les fonctions seront appeles par le repertoire de la classe et sont publiques.
 */
@Entity
@Table(name = "administrateur")
public class Administrateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String civilite;

    private String nom;

    private String prenom;

    private Date dateNaissance;

    private String adresseMail;

    private String motDePasse;

    private String numeroTelephone;

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getCivilite() { return civilite;}

    public void setCivilite(String civilite) {this.civilite = civilite;}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTelephone) {
        this.numeroTelephone = numeroTelephone;
    }
}
