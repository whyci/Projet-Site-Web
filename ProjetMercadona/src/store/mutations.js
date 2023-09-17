import state from "./state.js";

export function setConnexion(connexion) {
  state.connexion = connexion || null;
}
