import ConnexionAdmin from './ConnexionAdmin.vue'

describe('<ConnexionAdmin />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-vue
    cy.mount(ConnexionAdmin)
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
  })
});



/*
describe('<ConnexionAdmin />', () => {
  it("test l'affichage du champ", () => {
    cy.mount(ConnexionAdmin);
    cy.get('input').should('have.attr', 'placeholder', 'Mot de passe *');
  })
});
*/

/*
    // asserts that when the 'add' button is clicked, an event is emitted
    // with the payload containing the value of the text input
    cy.get('input[type="text"]').type('Adresse mail *');
    cy.get('input[type=submit]').click().then(() => {
      // Cypress.vueWrapper provides access to the Vue Test Utils.
      // With this wrapper you can access any Vue Test Utils API.
      // Learn more about Vue Test Utils here: https://vue-test-utils.vuejs.org/
      // e.g. cy.vueWrapper().emitted() returns all the events emitted by the BaseTextInput component
      cy.wrap(Cypress.vueWrapper.emitted()).should('have.property', 'Adresse mail *');
      expect(Cypress.vueWrapper.emitted().newTodo[0]).to.deep.equal(['Adresse mail *']);
    });*/
