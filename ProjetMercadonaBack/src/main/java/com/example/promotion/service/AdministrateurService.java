package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.modele.Produit;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministrateurService {

    Administrateur connecter(Administrateur administrateur);

    Administrateur inscription(Administrateur administrateur);
}
