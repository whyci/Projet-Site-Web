import state from "./state.js";

export function setConnexionAdmin(connexionAdmin) {
  state.connexionAdmin = connexionAdmin || null;
}

export function setConnexionBack(connexionBack) {
  state.connexionBack = connexionBack || null;
}

export function setCatalogue(catalogue) {
  state.catalogue = catalogue || null;
}

export function ajoutProduit(produit) {
  state.catalogue.push(produit);
}
