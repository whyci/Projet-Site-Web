/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */
describe('Premier test exemple', () => {
  it('Does not do much!', () => {
    expect(true).to.equal(true)
  })
})

//test exemple pour s'aider de la page cypress
describe('Test exemple', () => {
  it('Gets, types and asserts', () => {
    cy.visit('https://example.cypress.io')

    cy.contains('type').click()

    // Should be on a new URL which
    // includes '/commands/actions'
    cy.url().should('include', '/commands/actions')

    // Get an input, type into it
    cy.get('.action-email').type('fake@email.com')

    //  Verify that the value has been updated
    cy.get('.action-email').should('have.value', 'fake@email.com')
  })
})
