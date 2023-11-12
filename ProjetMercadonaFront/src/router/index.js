/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import { createRouter, createWebHistory } from "vue-router";
import DefaultLayout from '../components/DefaultLayout.vue';
import Accueil from '../views/Accueil.vue'
import Catalogue from '../views/Catalogue.vue'
import EspaceAdministrateur from '../views/EspaceAdministrateur.vue'
import ConnexionAdmin from "../views/ConnexionAdmin.vue";
import CreationAdmin from "../views/CreationAdmin.vue";

/**
 * Défini toutes les routes URL que l'application aura accès.
 * @type {[{path: string, component: {}, children: [{path: string, component: {}, name: string},{path: string, component: {}, name: string},{path: string, component: {}, name: string},{path: string, component: {}, name: string},{path: string, component: {}, name: string}]}]}
 */
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
        path: "/espace-admin",
        name: "espaceAdmin",
        component: EspaceAdministrateur,
      },
      {
        path: "/connexion-admin",
        name: "connexionAdmin",
        component: ConnexionAdmin,
      },

      {
        path: "/creation-admin",
        name: "creationAdmin",
        component: CreationAdmin,
      },
    ]
  }
];

/**
 * Crée le router qui permet de changer de page
 * @type {Router}
 */
const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
