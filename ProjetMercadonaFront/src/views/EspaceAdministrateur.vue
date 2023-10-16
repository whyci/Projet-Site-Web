<template>
  <div class="p-10">
    <!-- text-4xl permet d'agrandir le texte; font-bold permet de mettre en gras;
      text-green-900 permet de mettre en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Espace administrateur</h1>
    <br>
    <div>
      <p className="text-test">Veuillez remplir le formulaire ci-dessous pour créer un produit.</p>
    <br>
  </div>
  <div class="text-center">
    <div class="form-group">
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
      <img v-if="imageApercu !== null" :src="imageApercu" alt="Aucune image sélectionné">
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
import { computed, onMounted, ref } from "vue";
import Produits from "../components/Produits.vue";
import Produit from "../components/ProduitElement.vue";
import Catalogue from "./Catalogue.vue";
import EspaceAdministrateur from "./EspaceAdministrateur.vue";
import Service from "../services/service.js";
import store from "../store/index.js";

const idProduitPromotionString = ref("");
const idProduitPromotionInt = ref(0);
const askedPromo = ref(false);
const ancienPrixPromo = ref(0);

const catalogue = ref(store.state.catalogue);

// Produit que l'on souhaite ajouter
const ajoutProduit = ref({
  id: 0,
  libelle: "",
  description: "",
  categorie: "",
  prix: 0
});

// Image du produit que l'on souhaite ajouter
const ajoutProduitImage = ref(null);
// Aperçu de l'image que l'on affiche quand l'utilisateur choisi une image.
const imageApercu = ref();

// Encodage de l'image
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

function ajoutProduitValide() {
  return !( (ajoutProduit.value.libelle === "") || (ajoutProduit.value.categorie === "") ||
    (ajoutProduit.value.prix <=0) || (ajoutProduit.value.description === ""));
}

function ajouterProduit() {
  if (!ajoutProduitImage.value) {
    return null;
  }

  var idProduit;
  const formulaireDonneeImage = new FormData();
  if (ajoutProduitImage.value) {
    formulaireDonneeImage.append('image', ajoutProduitImage.value);
  } else {
    // Ajouter l'image par defaut si il n'y a pas d'image.
  }
  console.log("fichier image: " + ajoutProduitImage.value);

  // Création d'un produit - Etape 1: Ajout d'un produit dans la base de donnée avec une image.
  Service.serviceAjouterProduitImage(formulaireDonneeImage)
    .then(response => {
      console.log(response.data);
      idProduit = parseInt(ajoutProduitTraitementIntermediaire(response.data.message), 10);
      console.log("Création produit - Etape 1 terminée");
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
      console.log(response.data);
      console.log("Création produit - Etape 2 terminée");
      alert("Création produit terminée !");
    })
    .catch(e => {
      console.log("Erreur détectée, malheureusement ...");
      console.log(e);
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
  console.log("Id du produit : " + idProduit);
  return idProduit;
}

function nouveauProduit() {
  submitted.value = false;
  console.log("nouveau produit. submitted: " + submitted.value);
  ajoutProduit.value = {
    libelle: "",
    description: "",
    categorie: "",
    prix: 0
  };
}

/**
 On récupère l'ancien prix du produit, que l'on souhaite appliquer une promotion, dans le catalogue que l'on possède.
 L'objectif est d'éviter les requêtes inutiles à la base de donnée.
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

function demanderPromo() {
  idProduitPromotionInt.value = parseInt(idProduitPromotionString.value);
  askedPromo.value = true;
  recupereAncienPrix();
}

function terminerPromo() {
  askedPromo.value = false;
}
</script>

<style>
text-test {
  color: black;
}

</style>
