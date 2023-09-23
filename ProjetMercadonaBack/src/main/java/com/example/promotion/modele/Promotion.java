package com.example.promotion.modele;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @PrimaryKeyJoinColumn
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date debut;

    private Date fin;

    private Integer pourcentage_remise;

    private Integer produit_id_cle;

    public Integer getProduit_id_cle() {
        return produit_id_cle;
    }

    public void setProduit_id_cle(Integer produit_id_cle) {
        this.produit_id_cle = produit_id_cle;
    }

    private Integer getId() {
        return id;
    }

    private void setId(Integer id) {
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

    public Integer getPourcentage_remise() {
        return pourcentage_remise;
    }

    public void setPourcentage_remise(Integer pourcentage_remise) {
        this.pourcentage_remise = pourcentage_remise;
    }
}
