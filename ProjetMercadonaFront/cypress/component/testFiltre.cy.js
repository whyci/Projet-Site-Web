import {filtreCategorie} from "../../src/filters/index.js";

describe('filtreCategorie', () => {
  it('vérifie que les espaces vides, les chiffres et les caractères spéciaux ne sont pas autorisés sauf le "-"', () => {
    expect(filtreCategorie("")).to.eq(false);
    expect(filtreCategorie("123")).to.eq(false);
    expect(filtreCategorie("!?")).to.eq(false);
  })
})
