/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
import Navbar from './Navbar.vue'
import ConnexionAdmin from "../views/ConnexionAdmin.vue";
import CreationAdmin from "../views/CreationAdmin.vue";
import Accueil from "../views/Accueil.vue";
import Catalogue from "../views/Catalogue.vue";
import EspaceAdministrateur from "../views/EspaceAdministrateur.vue";

/**
 * Test composant de Navbar.
 */
describe('<Navbar />', () => {
  it('Renvoie la page Navbar', () => {
    cy.mount(Navbar)
  })
})

/**
 * Test composant de Accueil.
 */
describe('<Accueil />', () => {
  it('Test le titre de la page Accueil', () => {

    // Test de connexion admin
    cy.mount(Accueil)
    cy.contains('Voici les produits en promotions !')
  })
})

/**
 * Test composant de Catalogue.
 */
describe('<Catalogue />', () => {
  it('Test le titre de la page Catalogue', () => {

    // Test de connexion admin
    cy.mount(Catalogue)
    cy.contains('Catalogue')
  })
})

/**
 * Test composant de CreationAdmin.
 */
describe('<CreationAdmin />', () => {
  it('Test le titre de la page CreationAdmin', () => {

    // Test de connexion admin
    cy.mount(CreationAdmin)
    cy.contains('Se créer un compte Administrateur')
  })
})

/**
 * Test composant de ConnexionAdmin.
 */
describe('<ConnexionAdmin />', () => {
  it('Test le titre de la page ConnexionAdmin', () => {

    // Test de connexion admin
    cy.mount(ConnexionAdmin)
    cy.contains('Se connecter Administrateur')
  })
})

/**
 * Test composant de ConnexionAdmin changement de page vers inscription.
 */
describe('<ConnexionAdmin />', () => {
  it('Test le click et le focus du bouton "Pas encore de compte ? Clique ici"', () => {

    // Test de connexion admin
    cy.mount(ConnexionAdmin)

    // Vérifie que dans l'input il y a bien écrit " Adresse mail * "
    cy.get('input') .should('have.attr', 'placeholder', 'Adresse mail *')
    // Cliquer sur le bouton
    cy.get('.btn').click()
    // Cliquer dessus avec le focus
    cy.focused().click()
  })
})

/**
 * Test composant de EspaceAdministrateur.
 */
describe('<EspaceAdministrateur />', () => {
  it('Test le titre de la page EspaceAdministrateur', () => {

    // Test de connexion admin
    cy.mount(EspaceAdministrateur)
    cy.contains('Espace administrateur')
  })
})





