<!--
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
-->
<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Espace administrateur</h1>
    <br>
    <div class="text-center">
    <div class="form-group">
      <p className="text-test">Veuillez remplir les champs ci-dessous pour créer un code administrateur :</p>
      <br>
      <template v-if="!voirCodes">
        <input
          type="password"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="codeAdmin"
          required_
          v-model="ajoutCodeAdmin.code"
          name="codeAdmin"
          placeholder="Ecrire le code administrateur*"
        />
        <br>
        <input
          type="password"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="codeMaster"
          required_
          v-model="ajoutCodeAdmin.codeMaster"
          name="codeMaster"
          placeholder="Ecrire le code master *"
        />
      </template>
      <template v-else>
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="codeAdmin"
          required_
          v-model="ajoutCodeAdmin.code"
          name="codeAdmin"
          placeholder="Ecrire le code administrateur*"
        />
        <br>
        <input
          type="text"
          class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
          id="codeMaster"
          required_
          v-model="ajoutCodeAdmin.codeMaster"
          name="codeMaster"
          placeholder="Ecrire le code master *"
        />
      </template>
      <br>
      <button class="button" @click="voirCodes = !voirCodes">Voir les codes</button>
    </div>
    <div>
      <br>
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="ajouterCodeAdmin">Ajouter le code administrateur</button>
      <br><br>
    </div>
    </div>
    <br>

  <div class="text-center">
    <div class="form-group">
      <br>
      <p className="text-test">Veuillez remplir le formulaire ci-dessous pour créer un produit :</p>
      <br>
      <input
        type="text"
        class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
        id="title"
        required_
        v-model="ajoutProduit.libelle"
        name="libelle"
        placeholder="Ecrire le libelle du produit *"
      />

      <input
        type="text"
        class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
        id="description"
        required
        v-model="ajoutProduit.categorie"
        name="categorie"
        placeholder="Ecrire la catégorie du produit *"
      />
      <br>
      <input
        type="text"
        class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
        id="prix"
        required
        v-model="ajoutProduit.prix"
        name="prix"
        placeholder="Ecrire le prix du produit *"
      />

      <input
        type="text"
        class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25 ml-20"
        id="description"
        required
        v-model="ajoutProduit.description"
        name="description"
        placeholder="Ecrire la description du produit *"
      />
      <br>
      <input
        type="file"
        class="rounded border-2 bg-white border-gray-200 focus:border-green-800 mb-4 w-25"
        id="image"
        accept=".jpg, .jpeg, .png"
        @change="enregistrerImage"
        required>
    </div>
    <div v-if="imageApercu !== null">
      <img
           class="rounded-xl h-52 ml-auto mr-auto "
           :src="imageApercu"
      >
    </div>
    <div>
      <br>
        <!-- permet de gérer la bordure et l'espace du carré pour soumettre la demande
        btn, et btn-success permettent de créer un carré et le mettre en couleur
        les autres informations permettent de rechercher un produit selon sa catégorie
        quand l'on click sur la bouton ça valide le formulaire -->
      <button style="border: 1px; padding : 12px; background-color : lightgray; color :black"
              class="btn btn-success" @click="ajouterProduit">Créer le produit</button>
      <br><br>
    </div>
    <br>

  </div>
    <!-- permet d'importer le fichier produits et d'afficher les données -->
    <Produits :page-parent="'EspaceAdministrateur'" :resultat-recherche-categorie="null"/>
  </div>

  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
  <footer class="footer-bg">
  </footer>
</template>

<script setup>

import { onMounted, ref } from "vue";
import Produits from "../components/Produits.vue";
import EspaceAdministrateur from "./EspaceAdministrateur.vue";
import Service from "../services/index.js";
import store from "../store/index.js";
import {accesEspaceAdmin} from "../store/mutations.js";
import {MASTER_CODE} from "../main.js";

const idProduitPromotionString = ref("");
const idProduitPromotionInt = ref(0);
const askedPromo = ref(false);
const ancienPrixPromo = ref(0);

const catalogue = ref(store.state.catalogue);

// Produit que l'on souhaite ajouter
const ajoutProduit = ref({
  id: 0,
  libelle: ref(),
  description: ref(),
  categorie: ref(),
  prix: ref()
});

// CodeAdmin que l'on souhaite ajouter
const ajoutCodeAdmin = ref({
  code: ref(),
  codeMaster: ref()
})

const voirCodes = ref(false);

// Image du produit que l'on souhaite ajouter
const ajoutProduitImage = ref(null);
// Aperçu de l'image que l'on affiche quand l'utilisateur choisi une image.
const imageApercu = ref(null);

/**
 * S'exécute à l'initialisation du composant ; vérifie la validité d'accès à l'espace administrateur par le token.
 */
onMounted(() => {
  // Vérifie l'accès aux ressources de l'espace admin.
  accesEspaceAdmin();
})

/**
 * Vérifie si les champs d'ajout d'un codeAdmin sont bien remplis.
 * @returns {boolean} Retourne vrai si les champs sont tous remplis, faux sinon.
 */
function ajouterCodeAdminValide() {
  return ( (ajoutCodeAdmin.value.code !== "") && (ajoutCodeAdmin.value.codeMaster === MASTER_CODE()) );
}

/**
 * Ajoute un codeAdmin dans la base de donnée.
 */
function ajouterCodeAdmin() {
  if (!ajouterCodeAdminValide())
    return;

  // Ajout d'un codeAdmin dans la base de donnée
  Service.serviceAjouterCodeAdmin(ajoutCodeAdmin.value.code)
    .then(response => {
      if (response.data.message === "OK") {
        alert("Ajout du code administrateur est terminé !");
        location.reload();
      }
    })
    .catch(e => {
      console.log("Erreur détectée, malheureusement ...");
      console.log(e);
    })
}

/**
 * Etape d'encodage de l'image pour pouvoir la stocker dans la base de donnée.
 * @param event
 */
function enregistrerImage(event) {
  // Stockage de l'image prêt pour l'envoi à la base de donnée.
  ajoutProduitImage.value = event.target.files[0];

  // Préparation de l'aperçu de l'image. Vérifier si elle existe, puis la lie pour pouvoir l'afficher.
  if (ajoutProduitImage.value) {
    // Prépare le convertisseur pour pouvoir afficher l'image.
    const reader = new FileReader();
    reader.readAsDataURL(ajoutProduitImage.value);
    reader.onload = () => {
      // Enregistrement de l'image convertie et prête pour affichage.
      imageApercu.value = reader.result;
    };
  }
}

/**
 * Vérifie si les champs sont bien remplis
 * @returns {boolean} Retourne vrai si les champs sont tous remplis, faux sinon.
 */
function ajoutProduitValide() {
  return ( (ajoutProduit.value.libelle !== "") && (ajoutProduit.value.categorie !== "") &&
    (ajoutProduit.value.prix > 0) && (ajoutProduit.value.prix <= 1000000) && (ajoutProduit.value.description !== ""));
}

/**
 * Première étape d'ajout d'un produit. Elle consiste de vérifier l'accès à l'espace administrateur, de vérifier si
 * les champs sont bien remplis, et d'envoyer l'image à la base de donnée.
 */
function ajouterProduit() {
  console.log("Ajout produit");
  accesEspaceAdmin();
  if ( !ajoutProduitValide() || !ajoutProduitImage.value ) {
    return;
  }

  var idProduit;
  const formulaireDonneeImage = new FormData();
  if (ajoutProduitImage.value) {
    formulaireDonneeImage.append('image', ajoutProduitImage.value);
  } else {
    // Ajouter l'image par defaut si il n'y a pas d'image.
  }

  // Création d'un produit - Etape 1: Ajout d'un produit dans la base de donnée avec une image.
  Service.serviceAjouterProduitImage(formulaireDonneeImage)
    .then(response => {
      idProduit = parseInt(ajoutProduitTraitementIntermediaire(response.data.message), 10);
      deuxiemeEtapeAjoutProduit(idProduit);
    })
    .catch(e => {
      console.log("Erreur détectée, malheureusement ...");
      console.log(e);
    })
}

/**
 * Deuxième étape d'ajout d'un produit dans la BDD. Envoie cette fois-ci les données du produit (libellé, prix, etc.).
 * @param idProduit Id du produit que l'on a récupéré par la première requête d'ajout d'un produit.
 */
function deuxiemeEtapeAjoutProduit(idProduit) {

  // Création d'un produit - Etape 2: Association des paramètres du produit que l'on souahite ajouter.
  Service.serviceAjouterProduitParametres(ajoutProduit.value, idProduit)
    .then(response => {
      alert("Création produit terminée !");
      location.reload();
    })
    .catch(e => {
      console.log("Erreur détectée, malheureusement ...");
      console.log(e);
      location.reload();
    })
}

/**
 * Récupère l'id du produit donné par la réponse du back de la première requête d'ajout d'un produit.
 * @param responseData Les données de réponse de la requête auprès du back.
 * @returns {string} Retourne l'id du produit.
 */
function ajoutProduitTraitementIntermediaire(responseData) {
  let idProduit = "";
  let indexString;
  for (indexString = responseData.length - 1; indexString > 0; indexString--) {
    if (responseData[indexString] === "=") {
      break;
    }
    idProduit = responseData[indexString] + idProduit;
  }
  return idProduit;
}

/**
 * On récupère l'ancien prix du produit, que l'on souhaite appliquer une promotion, dans le catalogue que l'on possède.
 *  L'objectif est d'éviter les requêtes inutiles à la base de donnée.
 */

function recupereAncienPrix() {
  // On parcours les index du catalogue pour recuperer les id des produits.
  let i;
  for (i = 0; i < catalogue.value.length; i ++) {

    // On cree une variable locale qui stocke l'id courant du produit.
    let idProduitCourant = catalogue.value[i].id;

    // On verifie ensuite s'il est egal a l'id du produit que l'on souhaite appliquer une promotion.
    if (idProduitCourant === idProduitPromotionInt.value) {
      // On récupère la valeur de l'ancien prix.
      ancienPrixPromo.value = catalogue.value[i].prix;
      break;
    }
  }
}

// N'est plus utilisé pour le moment
function demanderPromo() {
  idProduitPromotionInt.value = parseInt(idProduitPromotionString.value);
  askedPromo.value = true;
  recupereAncienPrix();
}

// N'est plus utilisé pour le moment
function terminerPromo() {
  askedPromo.value = false;
}

</script>

<style>
text-test {
  color: black;
}

</style>
