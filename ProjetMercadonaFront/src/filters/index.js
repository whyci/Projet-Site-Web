import store from "../store/index.js";

export function truncateWords(str, count) {
  if (!str) return str;
  return str.split(" ").slice(0, count).join(" ")
}

export function filtreCategorie(ancienFiltre, categorie) {
  // Récupère le catalogue dans le store
  let catalogue = store.state.catalogue;

  if (categorie === "") {
    return [];
  }
  let filtre = [];

  // Si l'on souhaite appliquer plusieurs filtres en même temps.
  if (ancienFiltre.length !== 0) {
    for (let indexProduit = 0; indexProduit < ancienFiltre.length; indexProduit++) {
      let produitCourant = ancienFiltre[indexProduit];
      if ( produitCourant.categorie.toLowerCase().includes(categorie.toLowerCase()) ) {
        filtre.push(produitCourant);
      }
    }
  } else {
    for (let indexProduit = 0; indexProduit < catalogue.length; indexProduit++) {
      let produitCourant = JSON.parse(JSON.stringify(catalogue[indexProduit]));
      if ( produitCourant.categorie.toLowerCase().includes(categorie.toLowerCase()) ) {
        filtre.push(produitCourant);
      }
    }
  }
  return filtre;
}

/**
 * Filtre par libelle.
 * @param ancienFiltre Liste correspondant à l'ancien filtre (Dans le cas où il y aurait plusieurs filtres disponibles à appliquer en même temps).
 * @param libelle Libelle que l'on souhaite retrouver dans le produits que l'on retourne.
 * @returns {*[]} Liste des produits possédant la valeur de libelle donné en paramètre dans leur libelle.
 */
export function filtreLibelle(ancienFiltre, libelle) {
  // Récupère le catalogue dans le store
  let catalogue = store.state.catalogue;

  if (libelle === "") {
    return [];
  }
  let filtre = [];
  if (ancienFiltre.length !== 0) {
    for (let indexProduit = 0; indexProduit < ancienFiltre.length; indexProduit++) {
      let produitCourant = ancienFiltre[indexProduit];
      if ( produitCourant.libelle.toLowerCase().includes(libelle.toLowerCase()) ) {
        filtre.push(produitCourant);
      }
    }
  } else {
    for (let indexProduit = 0; indexProduit < catalogue.length; indexProduit++) {
      let produitCourant = JSON.parse(JSON.stringify(catalogue[indexProduit]));
      if ( produitCourant.libelle.toLowerCase().includes(libelle.toLowerCase()) ) {
        filtre.push(produitCourant);
      }
    }
  }
  return filtre;
}

