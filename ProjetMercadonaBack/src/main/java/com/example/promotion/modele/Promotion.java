package com.example.promotion.modele;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "promotion")
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date debut;

    private Date fin;

    private Float pourcentageRemise;

    private Long produitIdCle;

    public Long getProduitIdCle() {
        return produitIdCle;
    }

    public void setProduitIdCle(Long produitIdCle) {
        this.produitIdCle = produitIdCle;
    }

    private Long getId() {
        return id;
    }

    private void setId(Long id) {
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
