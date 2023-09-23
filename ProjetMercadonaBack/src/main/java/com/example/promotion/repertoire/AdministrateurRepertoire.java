package com.example.promotion.repertoire;

import com.example.promotion.modele.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministrateurRepertoire extends JpaRepository<Administrateur, Integer> {

}