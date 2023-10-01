package com.example.promotion.repertoire;

import com.example.promotion.modele.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepertoire extends JpaRepository<Promotion, Integer> {

}
