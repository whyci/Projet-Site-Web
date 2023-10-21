package com.example.promotion.service;

import io.jsonwebtoken.*;

import java.util.Date;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import com.example.promotion.modele.Administrateur;

import javax.crypto.SecretKey;

@Service
public class JwtTokenImplementation implements JwtTokenService {

    /**
     * Clé secrète générée aléatoirement selon l'algorithme HS256.
     * Elle est utilisée pour générer le token de l'administrateur courant et vérifier la validité de chaque token reçu.
     */
    private static final SecretKey CLE_SECRETE = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * En millisecondes : temps de validité du token / temps de session de connexion administrateur
     * 3 600 000 millisecondes = 60 min
     */
    private static final int TEMPS_EXPIRATION_TOKEN = 3600000;

    @Override
    public String generateToken(Administrateur administrateur) {

        // Crée le token par la fonciton builder de la librairie Jwts.
        String token = Jwts.builder()
                .setSubject(administrateur.getAdresseMail())
                .setIssuedAt(new Date())
                .setExpiration(new Date( System.currentTimeMillis() + TEMPS_EXPIRATION_TOKEN ))
                .signWith(CLE_SECRETE, SignatureAlgorithm.HS256)
                .compact();

        System.out.println("Token généré : " + token);

        return token;
    }

    /**
     * Vérifie la validité du token renseigné
     * @param token Token que l'on souhaite vérifier.
     * @return OK si le token est valide, KO si le token n'est pas valide.
     */
    @Override
    public String validatingToken(String token) {

        System.out.println("Token reçu en validation : "+token);

        // Lancement du décodage du token pour vérifier sa validité. Les exceptions sont levées avec un message d'erreur
        // correspondant.
        try {
            Jwts.parserBuilder()
                    .setSigningKey(CLE_SECRETE)
                    .build()
                    .parseClaimsJws(token);
            return "OK";
        } catch (SignatureException e) {
            System.out.println("Signature token invalide. " + e);
        } catch (MalformedJwtException e) {
            System.out.println("Format token invalide. " + e);
        } catch (ExpiredJwtException e) {
            System.out.println("Token expiré. " + e);
        } catch (UnsupportedJwtException e) {
            System.out.println("Token non supporté. " + e);
        } catch (IllegalArgumentException e) {
            System.out.println("Token illégal argument. " + e);
        }
        return "KO";
    }
}


