import {validerNom, validerMailCreationAdmin, validerMotDePasse, validerNumeroTelephone} from "../../src/control/index.js";

describe('validerNom', () => {
  it('vérifie que les espaces vides, les chiffres et les caractères spéciaux ne sont pas autorisés sauf le "-"', () => {
    expect(validerNom("")).to.eq(false);
    expect(validerNom("123")).to.eq(false);
    expect(validerNom("!?")).to.eq(false);
  })
})

describe('validerNom', () => {
  it('vérifie que la forme du nom/prenom correspond aux formats suivants : abc ou ab-c', () => {
    expect(validerNom("a-b")).to.eq(true);
    expect(validerNom("abcd")).to.eq(true);
  })
})

describe('validerMailCreationAdmin', () => {
  it('vérifie que les espaces vides, chiffres, lettres et caractères spéciaux ne sont pas autorisés', () => {
    expect(validerMailCreationAdmin("")).to.eq(false);
    expect(validerMailCreationAdmin("123")).to.eq(false);
    expect(validerMailCreationAdmin("!?")).to.eq(false);
    expect(validerMailCreationAdmin("abcd")).to.eq(false);
  })
})

describe('validerMailCreationAdmin', () => {
  it("vérifie que la forme d'une adresse mail correspond bien aux formats suivants : Mb@c142d.cv ou ab@cd.cv" , () => {
    expect(validerMailCreationAdmin("ab@cd.cv")).to.eq(true);
    expect(validerMailCreationAdmin("Mb@c142d.cv")).to.eq(true);
  })
})

describe('validerMotDePasse', () => {
  it("Vérifie qu'un mot de passe  ne peut pas contenir un espace vide et qu'il doit obligatoirement " +
    "etre composé de plus de 7 caractères avec majuscule, mininuscule, chiffres, lettres et spéciaux" , () => {
    expect(validerMotDePasse("")).to.eq(false);
    expect(validerMotDePasse("123")).to.eq(false);
    expect(validerMotDePasse("Abcde565 !?ght")).to.eq(false);
  })
})

describe('validerMotDePasse', () => {
  it("vérifie que la forme du mot de passe correspond bien aux formats suivants : Abcde565!?gh", () => {
    expect(validerMotDePasse("Abcde565!?ght")).to.eq(true);
  })
})

describe('validerNumeroTelephone', () => {
  it('vérifie que les espaces vides, lettres et caractères spéciaux ne sont pas autorisés' +
    "'De plus un numéro doit etre composé d'au moins 7 chiffres", () => {
    expect(validerNumeroTelephone("")).to.eq(false);
    expect(validerNumeroTelephone("!?")).to.eq(false);
    expect(validerNumeroTelephone("abcd")).to.eq(false);
    expect(validerNumeroTelephone("06848")).to.eq(false);
    expect(validerNumeroTelephone("+06848")).to.eq(false);
  })
})

describe('validerNumeroTelephone', () => {
  it("Vérifie que le numéro correspond bien aux formats suivants : +6810457260 ou 06810457260", () => {
    expect(validerNumeroTelephone("+6810457260")).to.eq(true);
    expect(validerNumeroTelephone("06810457260")).to.eq(true);
  })
})
