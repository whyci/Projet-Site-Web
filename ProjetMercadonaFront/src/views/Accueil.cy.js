import Accueil from './Accueil.vue';
import 'bootstrap/dist/css/bootstrap.min.css';
import './../style.css';

describe('<Accueil />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-vue
    cy.mount(Accueil)
  })
})

