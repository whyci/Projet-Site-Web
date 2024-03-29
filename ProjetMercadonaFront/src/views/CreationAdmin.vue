<!--
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
-->
<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Se créer un compte Administrateur</h1>
    <br>
    <p>Veuillez remplir le formulaire ci-dessous afin de vous créer un compte administrateur, les informations comportants un signe * sont obligatoires.</p>
    <!-- br permet de sauter une ligne -->
    <br> <br>
    <!-- permet de centrer toutes les informations de la page -->
    <div class="text-center">
      <div class="submit-form">
        <div class="form-group">
          <!-- permet de pouvoir sélectionner sa civilité selon les 3 options définies, et le class de design, et le v-model permet de choisir celui de sélection -->

          <!-- rounded border-2 permet d'arrondir légérement les angles, bg-white de mettre le fond en blanc
             border-gray-200 de griser la bordure de base du carré
             focus:border-green-800 de rendre le bordure verte quand le carré est sélectionné
             mb-4 de créer des marges autour de la barre et w-full de générer la barre sur toute la longueur de la fenêtre-->
          <!-- placeholder permet d'écrire dans la barre ce que l'utilisateur devra remplir -->
          <datepicker
            style=""
            class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 "
            v-model="administrateurInscription.dateNaissance"
            input-format="dd/MM/yyyy *"
          />
        </div>
        <br>
        <select v-model="administrateurInscription.civilite" class="border-gray-200 rounded border-2 focus:border-green-800 mb-4 w-25">
          <option disabled value=""> Choisissez votre civilité </option>
          <option>Madame</option>
          <option>Monsieur</option>
          <option>Autre</option>
        </select>
        <input
          type="text"
          v-model="administrateurInscription.nom"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20 "
          placeholder="Nom *"
        />
        <br>
        <input
          type="text"
          v-model="administrateurInscription.prenom"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          placeholder="Prénom *"
        />

        <input
          type="text"
          v-model="administrateurInscription.adresseMail"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
          placeholder="Adresse mail *"
        />
        <br>
        <input
          type="text"
          v-model="administrateurInscription.numeroTelephone"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          placeholder="Téléphone *"
        />
        <input
          type="text"
          v-model="codeAdmin"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-3 w-25 ml-20"
          placeholder="Code admin *"
        />
        <br>
        <template v-if="!afficherMdp">
          <input
            type="password"
            v-model="administrateurInscription.motDePasse"
            class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-3 w-25 ml-20"
            placeholder="Mot de passe *"
          />
        </template>
        <template v-else>
          <input
            type="text"
            v-model="administrateurInscription.motDePasse"
            class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-3 w-25 ml-20"
            placeholder="Mot de passe *"
          />
        </template>
        <br>
        <button class="button" @click="afficherMdp = !afficherMdp">Voir mdp</button>
        <br><br>
      </div>

      <div class="p-2 text-center">
        <br>
        <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
          btn, et btn-success permettent de créer un carré et le mettre en couleur
          les autres informations permettent de rechercher un produit selon sa catégorie
          quand l'on click sur la bouton ça valide le formulaire -->
        <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
                class="btn btn-success" @click="inscrireAdministrateur">Valider</button>
      </div>
    </div>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">

  </footer>
  <div class="text-center p-2">
    <br>
    <!-- le router-link permet de faire le lien avec la page de création de connexion admin -->
    <router-link
      :to="{ name: 'connexionAdmin' }"
    >
      <!-- ici on crée le bouton et défini son style - et lorsque l'on clique sur le bouton ça nous amène sur la page de connexion admin -->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              @click="navigate"
              role="link"
      >
        <!-- le texte du bouton -->
        Déjà un compte ? Clique ici
      </button>
    </router-link>
    <br><br>
  </div>
</template>
<script setup>
import {onMounted, ref} from "vue";
import Service from "../services/index.js";
import Datepicker from 'vue3-datepicker';
import router from "../router/index.js";

import {
  validerDateNaissance,
  validerMailCreationAdmin,
  validerMotDePasse,
  validerNom,
  validerNumeroTelephone
} from "../control/index.js";

const administrateurInscription = ref({
  civilite: "",
  adresseMail: "",
  dateNaissance: new Date(),
  motDePasse: "",
  nom: "",
  numeroTelephone: "",
  prenom: ""
});

const afficherMdp = ref(false);

const codeAdmin = ref("");

onMounted(async () => {
  administrateurInscription.value.dateNaissance.setFullYear(new Date().getFullYear() - 18);
});

/**
 * Vérification de la validité des champs remplis.
 * @return {boolean} vrai si tous les champs sont correctement remplis, sinon faux.
 */
function verificationChampsObligatoires() {
  return validerNom(administrateurInscription.value.nom) && validerNom(administrateurInscription.value.prenom) &&
    validerDateNaissance(administrateurInscription.value.dateNaissance) && validerMailCreationAdmin(administrateurInscription.value.adresseMail) &&
    validerNumeroTelephone(administrateurInscription.value.numeroTelephone) && validerMotDePasse(administrateurInscription.value.motDePasse) &&
    codeAdmin.value !== "";
}

/**
 * Inscription de l'administrateur en validant les champs et envoyant une requête au back.
 */
function inscrireAdministrateur() {
  if (!verificationChampsObligatoires())
    return
  Service.serviceInscrireAdministrateur(administrateurInscription.value, codeAdmin.value)
    .then(response => {
      if (response.data.message === "OK") {
        router.push("/connexion-admin");
        alert("Création administrateur terminée !");
      } else if (response.data.message === "KO_code_incorrect") {
        alert("Code admin erroné.");
      } else if (response.data.message === "KO_dispo_code") {
        alert("Code admin déjà pris.");
      }
    })
    .catch(e => {
      console.log("Erreur détectée à la création d'un administrateur, malheureusement ...");
      console.log(e);
    })
}

</script>
