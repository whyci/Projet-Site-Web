package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AdministrateurService {

    List<Administrateur> connecterAdministrateur(String adresseMail, String motDePasse);

    Administrateur inscrireAdministrateur(Administrateur administrateur);
}
