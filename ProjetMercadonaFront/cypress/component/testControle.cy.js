import {
  validerNom,
  validerMailCreationAdmin,
  validerMotDePasse,
  validerNumeroTelephone,
  validerDateNaissance
} from "../../src/control/index.js";


/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier que des chiffres n'est pas autorisé etc.
 */
describe('validerNom KO', () => {
  it('vérifie que les espaces vides, les chiffres et les caractères spéciaux ne sont pas autorisés sauf le "-"', () => {
    expect(validerNom("")).to.eq(false);
    expect(validerNom("123")).to.eq(false);
    expect(validerNom("!?")).to.eq(false);
    expect(validerNom("-ab")).to.eq(false);
    expect(validerNom("ab-")).to.eq(false);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier que des lettres, avec ou sans tiret, au milieu sont autorisés.
 */
describe('validerNom OK', () => {
  it('vérifie que la forme du nom/prenom correspond aux formats suivants : abc ou ab-c', () => {
    expect(validerNom("a-b")).to.eq(true);
    expect(validerNom("abcd")).to.eq(true);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier qu'avoir pas de nom de mail, de domaine, etc. n'est pas autorisé.
 */
describe('validerMailCreationAdmin KO', () => {
  it('vérifie que les espaces vides, chiffres, lettres et caractères spéciaux ne sont pas autorisés', () => {
    expect(validerMailCreationAdmin("")).to.eq(false);
    expect(validerMailCreationAdmin("123")).to.eq(false);
    expect(validerMailCreationAdmin("!?")).to.eq(false);
    expect(validerMailCreationAdmin("abcd")).to.eq(false);
    expect(validerMailCreationAdmin("@dom.co")).to.eq(false);
    expect(validerMailCreationAdmin("nom@.co")).to.eq(false);
    expect(validerMailCreationAdmin("nom@domco")).to.eq(false);
    expect(validerMailCreationAdmin("nom@dom.")).to.eq(false);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier qu'avoir un nom de mail, nom de domaine, etc. est validé.
 */
describe('validerMailCreationAdmin OK', () => {
  it("vérifie que la forme d'une adresse mail correspond bien aux formats suivants : Mb@c142d.cv ou ab@cd.cv" , () => {
    expect(validerMailCreationAdmin("ab@cd.cv")).to.eq(true);
    expect(validerMailCreationAdmin("Mb@c142d.cv")).to.eq(true);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier que de mettre moins 8 caractères, sans chiffre, etc. n'est pas autorisé.
 */
describe('validerMotDePasse KO', () => {
  it("Vérifie qu'un mot de passe  ne peut pas contenir un espace vide et qu'il doit obligatoirement " +
    "etre composé de plus de 7 caractères avec majuscule, mininuscule, chiffres, lettres et spéciaux" , () => {
    expect(validerMotDePasse("")).to.eq(false);
    expect(validerMotDePasse("123")).to.eq(false);
    expect(validerMotDePasse("Abcde565 !?ght")).to.eq(false);
    expect(validerNumeroTelephone("dayvuhbk+06848")).to.eq(false);
    expect(validerNumeroTelephone("VAUFBIJV+06848")).to.eq(false);
    expect(validerNumeroTelephone("VAUFBIJVsav06848")).to.eq(false);
    expect(validerNumeroTelephone("VAUFBIJVsa+v")).to.eq(false);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier qu'avoir au moins 8 caractères, des chiffres, etc. est validé.
 */
describe('validerMotDePasse OK', () => {
  it("vérifie que la forme du mot de passe correspond bien aux formats suivants : Abcde565!?gh", () => {
    expect(validerMotDePasse("Abcde565!?ght")).to.eq(true);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier qu'utiliser des lettres, etc. n'est pas autorisé.
 */
describe('validerNumeroTelephone KO', () => {
  it('vérifie que les espaces vides, lettres et caractères spéciaux ne sont pas autorisés' +
    "'De plus un numéro doit etre composé d'au moins 7 chiffres", () => {
    expect(validerNumeroTelephone("")).to.eq(false);
    expect(validerNumeroTelephone("!?")).to.eq(false);
    expect(validerNumeroTelephone("abcd")).to.eq(false);
    expect(validerNumeroTelephone("06848")).to.eq(false);
    expect(validerNumeroTelephone("+06848")).to.eq(false);
    expect(validerNumeroTelephone("+3368484856a2")).to.eq(false);
    expect(validerNumeroTelephone("+33684L848562")).to.eq(false);
    expect(validerNumeroTelephone("33684L848562+")).to.eq(false);
    expect(validerNumeroTelephone("3+3684L848562")).to.eq(false);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier qu'utiliser des chiffres, des espaces, etc. est validé.
 */
describe('validerNumeroTelephone OK', () => {
  it("Vérifie que le numéro correspond bien aux formats suivants : +6810457260 ou 06810457260", () => {
    expect(validerNumeroTelephone("+681045726")).to.eq(true);
    expect(validerNumeroTelephone("0681045726")).to.eq(true);
    expect(validerNumeroTelephone("+33 6 81 04 57 26")).to.eq(true);
    expect(validerNumeroTelephone("06 81 04 57 26")).to.eq(true);
  })
})

/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier qu'une date de naissance - 18 ans ou + 120 ans n'est pas autorisé.
 */
describe('validerDateNaissance KO', () => {
  it("Vérifie invalide : Admin a moins de 18 ans ou plus de 120 ans", () => {
    // Création de la date de naissance de l'admin incorrecte
    let dateNaissanceAdminIncorrect = new Date();
    // Attribution d'une date de naissance de -18 ans
    dateNaissanceAdminIncorrect.setFullYear(2006, 8, 23);
    expect(validerDateNaissance(dateNaissanceAdminIncorrect)).to.eq(false);
    // Attribution d'une date de naissance de +120 ans
    dateNaissanceAdminIncorrect.setFullYear(1900, 11, 17);
    expect(validerDateNaissance(dateNaissanceAdminIncorrect)).to.eq(false);
  })
})


/**
 * describe des fonctions à tester, it nous informe des tests que l'on va passer, expect nous permet de tester les attendus
 * ici de vérifier que la date de naissance + de 18 ans et - de 120 ans est validé.
 */
describe('validerDateNaissance OK', () => {
  it("Vérifie valide : Admin a plus de 18 ans et moins de 120 ans", () => {
    // Création de la date de naissance de l'admin correcte
    let dateNaissanceAdminMajeur = new Date();
    // Attribution d'une date de naissance de +18 ans et -120 ans
    dateNaissanceAdminMajeur.setFullYear(2002, 5, 2);
    expect(validerDateNaissance(dateNaissanceAdminMajeur)).to.eq(true);
  })
})
