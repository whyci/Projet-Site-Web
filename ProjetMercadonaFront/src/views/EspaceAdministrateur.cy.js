import EspaceAdministrateur from './EspaceAdministrateur.vue'
import 'bootstrap/dist/css/bootstrap.min.css';
import './../style.css';

describe('<EspaceAdministrateur />', () => {
  it('renders', () => {
    // see: https://on.cypress.io/mounting-vue
    cy.mount(EspaceAdministrateur)
  })
});
