import { createRouter, createWebHistory } from "vue-router";
import DefaultLayout from '../components/DefaultLayout.vue';
import Accueil from '../views/Accueil.vue'
import Catalogue from '../views/Catalogue.vue'
import EspaceAdministrateur from '../views/EspaceAdministrateur.vue'
import ConnexionUtilisateur from "../views/ConnexionUtilisateur.vue"
import ConnexionAdmin from "../views/ConnexionAdmin.vue";
import CreationAdmin from "../views/CreationAdmin.vue";
import CreationUtilisateur from "../views/CreationUtilisateur.vue";

const routes = [
  {
    path: '/',
    component: DefaultLayout,
    children: [
      {
        path: "/",
        name: "accueil",
        component: Accueil,
      },
      {
        path: "/catalogue",
        name: "catalogue",
        component: Catalogue,
      },
      {
        path: "/connexion-utilisateur",
        name: "connexionUtilisateur",
        component: ConnexionUtilisateur,
        props : name ,
      },
      {
        path: "/espace-admin",
        name: "espaceAdmin",
        component: EspaceAdministrateur,
      },

      {
        path: "/creation-utilisateur",
        name: "creationUtilisateur",
        component: CreationUtilisateur
      },

      {
        path: "/connexion-admin",
        name: "connexionAdmin",
        component: ConnexionAdmin
      },

      {
        path: "/creation-admin",
        name: "creationAdmin",
        component: CreationAdmin
      },
    ]
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
