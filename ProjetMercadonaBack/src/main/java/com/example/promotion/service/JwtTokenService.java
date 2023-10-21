package com.example.promotion.service;

import com.example.promotion.modele.Administrateur;

public interface JwtTokenService {
    String generateToken(Administrateur administrateur);

    String validatingToken(String token);
}
