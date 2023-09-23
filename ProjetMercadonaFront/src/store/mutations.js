import state from "./state.js";

export function setConnexionAdmin(connexionAdmin) {
  state.connexionAdmin = connexionAdmin || null;
}

export function setConnexionBack(connexionBack) {
  state.connexionBack = connexionBack || null;
}
