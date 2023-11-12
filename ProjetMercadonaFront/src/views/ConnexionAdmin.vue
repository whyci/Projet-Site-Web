<template>

  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
     text-green-900 permet de mettre en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Se connecter Administrateur</h1>
    <br>

    <div>
      <p>Veuillez rentrer vos informations pour vous connecter.</p>
      <br> <br>
    </div>

    <div class="text-center">
      <div class="submit-form">
        <div class="form-group">
          <!-- rounded border-2 permet d'arrondir légérement les angles, bg-white de mettre le fond en blanc
            border-gray-200 de griser la bordure de base du carré
            focus:border-green-800 de rendre le bordure verte quand le carré est sélectionné
            mb-4 de créer des marges autour de la barre et w-full de générer la barre sur toute la longueur de la fenêtre-->

          <!-- placeholder permet d'écrire dans la barre ce que l'utilisateur devra remplir -->
          <input
            type="text"
            v-model="administrateurDemandeConnexion.adresseMail"
            class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-50"
            placeholder="Adresse mail *"
          />

          <br>
          <template v-if="!afficherMdp">
            <input
              type="password"
              v-model="administrateurDemandeConnexion.motDePasse"
              class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-50"
              placeholder="Mot de passe *"
            />
          </template>
          <template v-else>
            <input
              type="text"
              v-model="administrateurDemandeConnexion.motDePasse"
              class="rounded border-2 bg-white border-gray-200 focus:bg-green-800 focus:border-green-800 mb-4 w-50"
              placeholder="Mot de passe *"
            />
          </template>
          <br>
          <button class="button" @click="afficherMdp = !afficherMdp">Voir mdp</button>
          <br><br>
        </div>
        <div class="text-center p-2">
          <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
           btn, et btn-success permettent de créer un carré et le mettre en couleur
           les autres informations permettent de rechercher un produit selon sa catégorie
           quand l'on click sur la bouton ça valide le formulaire -->
          <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
                  class="btn btn-success" @click="connecterAdministrateur">Valider</button>
          <br><br>
        </div>
      </div>
    </div>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->

  <footer class="footer-bg">
  </footer>
  <br>
  <div class="text-center p-2">
    <!-- le router-link permet de faire le lien avec la page de création de compte administrateur -->
    <router-link
      :to="{ name: 'creationAdmin' }"
    >
      <!-- ici on crée le bouton et défini son style - et lorsque l'on clique sur le bouton ça nous amène sur la page de création de compte admin -->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              @click="navigate"
              role="link"
              cy-testing="CreationAdmin"
      >
        <!-- le texte du bouton -->
        Pas encore de compte ? Clique ici
      </button>
    </router-link>
    <br><br>
  </div>
</template>

<script setup>

import { ref } from "vue";
import Service from "../services/index.js";
import router from "../router/index.js";

const administrateurDemandeConnexion = ref({
  adresseMail: "",
  motDePasse: ""
});

const afficherMdp = ref(false);

function connecterAdministrateur() {
  if ( (administrateurDemandeConnexion.value.adresseMail === "")
    || ( administrateurDemandeConnexion.value.motDePasse === "") ) {
    return;
  }

  Service.serviceConnecterAdministrateur(administrateurDemandeConnexion.value.adresseMail, administrateurDemandeConnexion.value.motDePasse)
    .then(response => {

      // On reçoit KO, soit un echec de connexion.
      if (response.data.token === "KO") {
        console.log("Echec de connexion");


      }
      // Connexion réussie/acceptée
      else {

        // Sauvegarde le token dans la variable dans le store et le local storage.
        localStorage.setItem("token", response.data.token);
        // Popup de connexion acceptée
        alert("Connexion acceptée !");
        location.reload();
      }
    })
    .catch(e => {
      console.log("Erreur détectée à la création d'un administrateur, malheureusement ...");
      console.log(e);
    })
}

</script>
