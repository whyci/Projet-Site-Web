import {createApp, ref} from "vue";
import router from "./router";
import "./style.css";
import App from "./App.vue";
import * as filters from './filters'
import store from "./store/index.js";
import Service from "./services/service.js";

const app = createApp(App);
app.use(router).use(store).mount("#app");

app.config.globalProperties.$filters = filters

/* - - - - - Ci-dessous s'enchainent les actions de l'application au démarrage du site web. - - - - - */

// Paramètre la valeur connexionBack dans toute l'application. Elle permet de savoir si on veut connecter le front
// avec le back. true = connecté au back, false = non connecté au back.
store.state.connexionBack = false;

// Si connexion avec le back; récupère le catalogue et les promotions provenant du back. Sinon, il stocke en dur les
// valeurs dans le code.
// Cas de connexion avec le back.
if (store.state.connexionBack) {
  // Demande du catalogue
  if (store.state.catalogue.length === 0) {
    Service.serviceDemanderCatalogue()
      .then(response => {
        console.log("Catalogue:");
        console.log(response.data);
        store.state.catalogue = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  }
  // Demande des promotions
  if (store.state.promotions.length === 0) {
    Service.serviceDemanderPromotions()
      .then(response => {
        console.log("Promotions:");
        console.log(response.data);
        store.state.promotions = response.data;
      })
      .catch(e => {
        console.log(e);
      });
  }

// Cas de non connexion avec le back.
} else {
  // Stocke en dur le catalogue.
  store.state.catalogue = ref([
    {id: 1, libelle: "Artichaut", categorie: "Alimentaire", prix: 6, nouveauprix: 0, description: "légume de saison", image: "./src/assets/Banque_images/artichaut.jpg"},
    {id: 2, libelle: "Dentifrice", categorie: "Hygiène", prix: 4, nouveauprix: 2, description: "Hygiène bucco dentaire", image: "./src/assets/Banque_images/dentifrice.jpg"},
    {id: 3, libelle: "Pains aux fruits secs", categorie: "Alimentaire", prix: 6, description: "Pains idéal pour l'apéro", nouveauprix: 4, image: "./src/assets/Banque_images/pains aux fruits secs.jpg"},
    {id: 4, libelle: "Serviettes", categorie: "Hygiène", prix: 14, nouveauprix: 0, description: "Linges de maison", image: "./src/assets/Banque_images/Serviettes.jpg"},
    {id: 5, libelle: "Teléphone", categorie: "Electronique", prix: 999, nouveauprix:0, description: "8 Giga RAM, 128 GO", image: "./src/assets/Banque_images/Tell.jpg"},
    {id: 6, libelle: "Peinture", categorie: "Loisirs", prix: 35, nouveauprix: 30, description: "Loisirs créatif", image: "./src/assets/Banque_images/Peinture.jpg"},
    {id: 7, libelle: "Savon", categorie: "Hygiène", prix: 6, nouveauprix: 0, description: "Hygiène corporel", image: "./src/assets/Banque_images/savon.jpg"},
    {id: 8, libelle: "Ecran", categorie: "Electronique", prix: 600, nouveauprix: 500, description: "Ecran 12 pouces", image: "./src/assets/Banque_images/ecran.jpg"},
    {id: 9, libelle: "Jeux de carte", categorie: "Loisirs", prix: 8,nouveauprix: 0, description: "Jeux de société", image: "./src/assets/Banque_images/jeux de carte.jpg"},
    {id: 10, libelle: "Citrouille", categorie: "Mobilier", prix: 10, nouveauprix: 8, description: "légume de saison", image: "./src/assets/Banque_images/citrouille.jpg"},
    {id: 11, libelle: "CD", categorie: "Musique", prix: 25, nouveauprix: 15, description: "Musique pop, rock, métal", image: "./src/assets/Banque_images/cd.jpg"},
    {id: 12, libelle: "Café", categorie: "Alimentaire", prix: 6, nouveauprix: 0, description: "Café goût intense", image: "./src/assets/Banque_images/café.jpg"}
  ]);
  // Stocke en dur les promotions.
  store.state.promotions = ref([
  ]);
}
