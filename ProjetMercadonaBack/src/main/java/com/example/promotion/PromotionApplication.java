/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
package com.example.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Projet Spring de Mercadona Back qui fait le lien avec la base de données.
 * Utilise la technologie JPA pour récupérer et modifier les informations de la base de donnée.
 */
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class PromotionApplication {

	/**
	 * Lancement de la partie back du projet.
	 * @param args Arguments de l'exécution.
	 */
	public static void main(String[] args) {
		SpringApplication.run(PromotionApplication.class, args);
	}

	/**
	 * Configure les sécurités CORS (Cross-Origin Resource Sharing).
	 * @return Configuration Web MVC (Model-View-Controller).
	 */
	@Bean
	public WebMvcConfigurer configurationCORS() {
		// Retourne la configuration renseignée.
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// Attribution des configurations lié à la sécurité CORS.
				registry.addMapping("/**")
						.allowedOrigins("http://localhost:3000")
						.allowCredentials(false)
						.allowedMethods("GET", "POST");
			}
		};
	}
}
