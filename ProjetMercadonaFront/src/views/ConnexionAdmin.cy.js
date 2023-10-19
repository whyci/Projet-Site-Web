import ConnexionAdmin from './ConnexionAdmin.vue'
import CreationUtilisateur from "./CreationUtilisateur.vue";

import router from './../router/index.js';
import {createMemoryHistory, createRouter} from "vue-router";
import EspaceAdministrateur from "./EspaceAdministrateur.vue";

describe('<ConnexionAdmin />', () => {
  it('renvoie la page', () => {
    // see: https://on.cypress.io/mounting-vue
    cy.mount(ConnexionAdmin);
  })
})

describe('<ConnexionAdmin />', () => {
  it("test l'affichage du 1er champ", () => {
    cy.mount(ConnexionAdmin);

    cy.get('input') .should('have.attr', 'placeholder', 'Adresse mail *');
  })
});

describe('<ConnexionAdmin />', () => {
  it("test du click du champ", () => {
    cy.mount(ConnexionAdmin);

    cy.get('.btn').click() // Click on button
    cy.focused().click() // Click on el with focus

    cy.get('router-link > button').click() // Click on button
  })
});


describe('Vue Router - Connexion Admin To Création', () => {
  it('Redirection Création Admin par router', () => {
    cy.mount(ConnexionAdmin);

    cy.get('[cy-testing="CreationAdmin"]').should('exist').click();
    //cy.url().should('match', /\/creation-utilisateur/)
  })
});

/*
describe('Vue Router - Connexion Admin To Création', () => {

  it('Redirection Création Admin par router', () => {
    // cy.get('button');
    // cy.wrap(router.push('/creation-admin'))  // essaie la commande suivante avec un timer
    cy.mount(CreationUtilisateur, { router })
    cy.contains('Se créer un compte')
  })
});*/

/*
describe('Vue Router - Création Admin to Connexion', () => {

  it('Redirection Connexion Admin par router', () => {
    cy.wrap(router.push('/connexion-admin'))
    cy.mount(ConnexionAdmin, { router })

    cy.wrap(router.push('/espace-admin'))
    cy.mount(EspaceAdministrateur, { router })
  })
})
*/
