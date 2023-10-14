import CreationAdmin from './CreationAdmin.vue'

describe('<CreationAdmin />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-vue
    cy.mount(CreationAdmin)
  })
})