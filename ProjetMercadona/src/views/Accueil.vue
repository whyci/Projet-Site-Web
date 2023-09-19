<template>
  <div class="p-10 ">
    <!-- text-4xl permet d'agrandir le texte et font-bold de le mettre en gras;
   text-green-900 permet de mettre le texte en vert foncée -->
    <h1 class="text-4xl font-bold text-green-900">Voici les produits en promotions !</h1>
    <br>
  <p>Voici les produits en promotion de notre magasin toutes catégories confondus, l'alimentaire, traiteur, hygiènes, loisirs ...</p>
    <br>
    <div>
      <!-- permet d'importer le fichier produits et d'afficher les données -->
   <Produits :page-parent="'Accueil'"/>
    </div>
  </div>
  <!-- footer-bg permet de mettre l'image en bas de page et de gérer la taille -->
      <footer class="footer-bg">
      </footer>
</template>

<script setup>
import { computed, onMounted, ref } from "vue";

// Importe la framework Bootstrap 5 qui permet d'améliorer le front du document
import 'bootstrap/dist/css/bootstrap.min.css';
import Produits from "../components/Produits.vue";

const naturesChemin = "natures.png";

// Index du produit que l'on souhaite appliquer une promotion & condition de demande de promo
// (false=non demandé, true=demandé)
const idProduitPromotionString = ref("");
const idProduitPromotionInt = ref(0);
const askedPromo = ref(false);
const ancienPrixPromo = ref(0);

const nomProduitRecherche = ref("");

const produits = ref([]);
const catalogue = ref([
  {id: 1, libelle: "Ratatouille", categorie: "Alimentaire", prix: 6, image: "src/assets/Banque_images/amigurimi tuto.jpg"},
  {id: 2, libelle: "Thon fumé", categorie: "Alimentaire", prix: 3, image: "src/assets/Banque_images/basilic.jpg"},
  {id: 3, libelle: "Poisson", categorie: "Alimentaire", prix: 7, image: "src/assets/Banque_images/café.jpg"},
  {id: 4, libelle: "Pomme", categorie: "Alimentaire", prix: 2, image: "../assets/Banque_images/cd.jpg"},
  {id: 5, libelle: "Télévision HD", categorie: "Electronique", prix: 1899, image: "../assets/Banque_images/chocolat noir.jpg"},
  {id: 6, libelle: "Telecommande", categorie: "Electronique", prix: 9, image: "../assets/Banque_images/cd.jpg"},
  {id: 7, libelle: "Reveil", categorie: "Electronique", prix: 11, image: "../assets/Banque_images/cd.jpg"},
  {id: 8, libelle: "Soda pomme", categorie: "Alimentaire", prix: 12, image: "../assets/Banque_images/cd.jpg"},
  {id: 9, libelle: "Lampe", categorie: "Mobilier", prix: 5, image: "../assets/Banque_images/cd.jpg"},
  {id: 10, libelle: "Canapé", categorie: "Mobilier", prix: 70, image: "../assets/Banque_images/cd.jpg"},
  {id: 11, libelle: "Chaise", categorie: "Mobilier", prix: 15, image: "../assets/Banque_images/cd.jpg"},
  {id: 12, libelle: "Tabouret", categorie: "Mobilier", prix: 11, image: "../assets/Banque_images/cd.jpg"}
]);

function nouveauProduit() {
  submitted.value = false;
}

function recupereAncienPrix() {
  let i;
  for (i = 0; i < catalogue.value.length; i ++) {
    let idProduitCourant = catalogue.value[i].id;

    if (idProduitCourant === idProduitPromotionInt.value) {
      ancienPrixPromo.value = catalogue.value[i].prix;
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

function chercherProduit(nomProduitRecherche) {
  if (nomProduitRecherche !== "") {
    let i;
    for (i = 0; i < catalogue.value.length; i ++) {
      let catProduit = catalogue.value[i].categorie;
      if (catProduit === nomProduitRecherche) {
        produits.value.push(catalogue.value[i]);
      }
    }
  } else {
    produits.value = [];
  }
}

onMounted(async () => {
});
</script>


<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}

.footer-bg {
  background-image: url('/nat.png'); /* Adjust the path accordingly */
  background-position: center;
  background-repeat: repeat;
  min-height:80px; /* Adjust this height as needed */
  min-width:1400px; /* Adjust this height as needed */
  margin-left: 0;

}
</style>