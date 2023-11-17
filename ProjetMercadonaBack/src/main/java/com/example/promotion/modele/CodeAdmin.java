/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion.modele;

import jakarta.persistence.*;

/**
 * la classe codeAdmin definis la table qui sera dans la base de données
 * les attributs prives correspondent aux elements de la table codeAdmin.
 * Les fonctions seront appeles par le repertoire de la classe et sont publiques.
 */
@Entity
@Table(name = "codeAdministrateur")
public class CodeAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "admin_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "administrateur_fkey"))
    private Administrateur administrateur;

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Administrateur getAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(Administrateur administrateur) {
        this.administrateur = administrateur;
    }
}
