package com.example.promotion.modele;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "administrateur")
public class Administrateur {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer administrateur_id;

    private String nom;

    private String prenom;

    private Integer age;

    private String adresse_mail;

    private String mot_de_passe;

    private String numero_telephone;

    private Integer getAdministrateur_id() {
        return administrateur_id;
    }

    private void setAdministrateur_id(Integer id) {
        this.administrateur_id = id;
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getNumero_telephone() {
        return numero_telephone;
    }

    public void setNumero_telephone(String numero_telephone) {
        this.numero_telephone = numero_telephone;
    }
}
