/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import Service from "../services/index.js";
import router from "../router/index.js";
import store from "./index.js";

/**
 * Demande au back la validité du token que l'on possède.
 * Si le token est valide, retourne true, sinon retourne faux.
 */
export function accesEspaceAdmin() {

  // Vérifie si l'existence d'un token dans le stockage local
  if (localStorage.getItem("token") === "null") {
    router.push("/connexion-admin").then(r => {});
    return;
  }

  // Demande vérification de la validité au back
  Service.serviceAccesEspaceAdmin()
    .then(response => {
      // Si connexion accepté
      if (response.data === "OK") return;

      // Sinon, on désenregitre le token et on redirige sur la page de connexion
      deconnexionAdmin();
      router.push("/connexion-admin").then(r => {});
    })
    .catch(e => {
      console.log("Erreur détecté à la demande d'accès pour l'espace admin ...");
      console.log(e);
    })
}

/**
 * Déconnexion d'un administrateur, qui consiste à supprimer le token enregistré et redirigé vers la page de connexion.
 */
export function deconnexionAdmin() {
  localStorage.setItem("token", null);
  location.reload();
  router.push("/connexion-admin").then(r => {});
}

/**
 * Retourne le nouveau prix associé
 * @param idPromotion Id de la promotion que l'on souhaite avoir
 */
export function getPromotionAssocie(idPromotion) {
  let listePromotions = store.state.promotions;
  for (let indexPromotion = 0; indexPromotion < listePromotions.length; indexPromotion ++) {
    // Si l'id de la promotion correspond à l'id que l'on cherche
    if (listePromotions[indexPromotion].id === idPromotion) {
      return JSON.parse(JSON.stringify(listePromotions[indexPromotion])).pourcentageRemise;
    }
  }
}

/**
 * Calcul du nouveau prix :
 * 1. On le divise par 100 pour avoir un nombre entre 0.99 et 0.01
 * 2. On le soustrait à 1 pour l'inverser "symétriquement par 0.5"
 * 3. On le multiplie par l'ancien prix pour avoir le nouveau prix
 * 4. On le parse en float de 2 chiffres après la virgule pour avoir un prix qui se limite à des centimes : X€XX
 */
export function calculNouveauPrix(ancienPrix, pourcentageRemise) {
  return parseFloat( ( ancienPrix * ( 1 - (pourcentageRemise / 100) ) )
    .toFixed(2)
  );
}
