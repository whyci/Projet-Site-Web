/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import store from "../store/index.js";

export function truncateWords(str, count) {
  if (!str) return str;
  return str.split(" ").slice(0, count).join(" ")
}

export function filtreCategorie(categorie) {
  // Récupère le catalogue dans le store
  let catalogue = store.state.catalogue;

  if (categorie === "") {
    return [];
  }
  let filtre = [];

  for (let indexProduit = 0; indexProduit < catalogue.length; indexProduit++) {
    let produitCourant = JSON.parse(JSON.stringify(catalogue[indexProduit]));
    if ( produitCourant.categorie.toLowerCase().includes(categorie.toLowerCase()) ) {
      filtre.push(produitCourant);
    }
  }
  return filtre;
}
