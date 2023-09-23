package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;
import com.example.promotion.repertoire.AdministrateurRepertoire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministrateurServiceImplementation implements AdministrateurService {

    @Autowired
    private AdministrateurRepertoire administrateurRepertoire;

    @Override
    public Administrateur connecter(Administrateur administrateur) {
        return administrateurRepertoire.save(administrateur);
    }

    @Override
    public Administrateur inscription(Administrateur administrateur) {
        return administrateurRepertoire.save(administrateur);
    }
}
