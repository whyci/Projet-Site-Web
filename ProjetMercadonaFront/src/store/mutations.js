
import Service from "../services/service.js";
import router from "../router/index.js";
import store from "./index.js";

/**
 * Demande au back la validité du token que l'on possède.
 * Si le token est valide, retourne true, sinon retourne faux.
 */
export function accesEspaceAdmin() {
  if (!store.state.connexionBack) {return;}

  // Vérifie si l'existence d'un token dans le stockage local
  console.log("Acces : "+localStorage.getItem("token"));
  if (localStorage.getItem("token") === "null") {
    console.log("Accès Espace Admin - Aucun token enregistré");
    router.push("/connexion-admin").then(r => {});
    return;
  }

  // Demande vérification de la validité au back
  Service.serviceAccesEspaceAdmin()
    .then(response => {
      // Si connexion accepté
      if (response.data === "OK") { console.log("Acces Admin OK"); return; }

      // Sinon, on désenregitre le token et on redirige sur la page de connexion
      deconnexionAdmin();
      router.push("/connexion-admin").then(r => {});
    })
    .catch(e => {
      console.log("Erreur détecté à la demande d'accès pour l'espace admin ...");
      console.log(e);
    })
}

export function deconnexionAdmin() {
  localStorage.setItem("token", null);
  router.push("/connexion-admin").then(r => {});
}

export function getEtatConnexion() {
  return localStorage.getItem("token") !== "null";
}
