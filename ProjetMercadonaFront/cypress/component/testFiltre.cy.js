import {filtreCategorie} from "../../src/filters/index.js";

/*describe de la fonction filtreCategorie, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
ici de vérifier qu'un espace n'est pas autorisé etc */
describe('filtreCategorie', () => {
  it('vérifie que les espaces vides, les chiffres et les caractères spéciaux ne sont pas autorisés sauf le "-"', () => {
    expect(filtreCategorie("")).to.eq(false);
    expect(filtreCategorie("123")).to.eq(false);
    expect(filtreCategorie("!?")).to.eq(false);
  })
})
