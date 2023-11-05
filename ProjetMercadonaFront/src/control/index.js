
/**
 * Vérifie que l'adresse mail correspond au format suivant : exemple@domaine.com.
 * Autrement dit, il devra contenir un nom de mail, un seul caractère '@', et enfin un domaine (contenant un seul '.').
 * Chaque exigence est définie sous forme de condition. Si une des conditions n'est pas validée, l'adresse mail est
 * considérée invalide.
 * @param mail Adresse mail que l'on souhaite vérifier.
 */
export function validerMailCreationAdmin(mail) {

  // Définition des conditions à valider (cpt pour compteur, pt pour point). Ce sont des compteurs servant à valider
  // les conditions.

  // Compteur pour le nom du mail
  let cptNomMail = 0;
  // Compteur pour l'arobase
  let cptArobase = 0;
  // Compteur pour le domaine avant le '.'
  let cptDomaineAvantPt = 0;
  // Compteur pour le '.' du domaine
  let cptDomainePt = 0;
  // Compteur pour le domaine après le '.'
  let cptDomaineApresPt = 0;

  // Adresse mail vide
  if (mail === "") {
    alert("Adresse mail à remplir sous le format : exemple@domaine.com")
    return false;
  }

  // On regarde chaque caractère contenu dans l'adresse mail
  for (let lettre in mail) {
    //console.log(mail[lettre]);

    // Le caractère est avant l'arobase
    if (cptArobase === 0) {
      // Le caractère n'est pas l'arobase, alors on le comprend comme faisant partie du nom du mail
      if (mail[lettre] !== '@') {
        cptNomMail ++;
        continue;
      }
      // Le caractère est l'arobase
      else {
        // On obtient un arobase alors qu'on a de nom de mail, on invalide le mail
        if (cptNomMail === 0) {
          alert("Aucun nom de mail\n");
          return false;
        }

        // Le caractère est l'arobase, la condition de l'arobase est valide (tant qu'il n'y en a qu'un)
        cptArobase ++;
        continue;
      }
    }
    // La condition du nom du mail et de l'arobase est remplie, on passe au domaine
    else {
      // On obtient un deuxième arobase, on invalide le mail
      if (mail[lettre] === '@') {
        alert("Trop @\n");
        return false;
      }

      // Avant la validation du point du domaine
      if (cptDomainePt === 0) {
        // Pour le domaine, le caractère n'est pas '.', alors on le comprend comme faisant partie de la première partie du domaine
        if (mail[lettre] !== ".") {

          // On identifie le caractère comme le domaine avant le '.'
          cptDomaineAvantPt ++;
        }
        // Le caractère est '.'
        else {
          // On obtient un '.' alors qu'on a pas enregistré de première partie du domaine, on invalide l'adresse mail
          if (cptDomaineAvantPt === 0) {
            alert("Aucun première partie de domaine`\n");
            return false;
          }

          // On identifie le caractère comme '.'
          cptDomainePt ++;
        }
      }
      // Après la validation du '.'
      else {
        // On obtient un deuxième '.' pour le domaine, on invalide l'adresse mail
        if (mail[lettre] === ".") {
          alert("Trop . dans domaine\n");
          return false;
        }

        // On identifie le caractère comme le domaine après le '.'
        cptDomaineApresPt ++;
      }
    }
  }

  if (cptArobase === 0) {
    alert("Aucun @");
    return false;
  }
  if (cptDomainePt === 0) {
    alert("Aucun . dans domaine\n");
    return false;
  }
  if (cptDomaineApresPt === 0) {
    alert("Aucun deuxième partie domaine\n");
    return false;
  }

  return cptNomMail > 0 && cptArobase === 1 && cptDomaineAvantPt > 0 &&
    cptDomainePt === 1 && cptDomaineApresPt > 0;
}

/**
 * Vérifie que le nom ou le prénom est valide, soit ne possèdant pas de chiffre.
 * @param nom Nom ou prénom que l'on souhaite valider.
 */
export function validerNom(nom) {
  // Si aucun caractère dans le nom
  if (nom.length === "") {
    alert("Les champs obligatoires sont à remplir");
    return false;
  }

  // On prend chaque caractère contenu dans le nom (ou prénom)
  for (let lettre in nom) {
    // Si le caractère n'est pas dans l'alphabet
    if (!(nom[lettre] >= 'A' && nom[lettre] <= 'Z') && !(nom[lettre] >= 'a' && nom[lettre] <= 'z'))
      // S'il n'est pas un tiret, on invalide le nom (ou prénom)
      if (nom[lettre] !== "-") {
        alert("Aucun caractère spécial autorisé, uniquement alphabet et le tiret");
        return false;
      }
      // Si c'est un tiret et qu'il est placé en première ou dernière position dans le nom (ou prénom), on l'invalide
      else if (lettre === 0 || Number.parseFloat(lettre) === nom.length-1) {
        return false;
      }
  }
  // Si rien à signaler, on valide le nom (ou prénom)
  return true;
}

/**
 * Vérifie que le mot de passe valide les conditions suffisantes. Il doit contenir au moins :
 * 8 caractères ; une lettre en majuscule ; une lettre en minuscule ; un chiffre ; un caractère spécial ;
 * @param mdp Mot de passe que l'on souhaite valider.
 */
export function validerMotDePasse(mdp) {
  // Si aucun caractère dans le mdp
  if (mdp === "") {
    return false;
  }

  // Conditions à remplir pour valider le mdp
  // Condition du chiffre
  let conditionChiffre = false;
  // Condition de la lettre en majuscule
  let conditionLettreMajuscule = false;
  // Condition de la lettre en minuscule
  let conditionLettreMinuscule = false;
  // Condition du caractère spécial
  let conditionCaractereSpecial = false;

  // On prend chaque caractère contenu dans le mdp
  for (let caractere in mdp) {
    // Si le caractère est un espace, on invalide le mdp
    if (mdp[caractere] === " ") {
      alert("Espace interdit dans le mot de passe");
      return false;
    }

    // Vérifie si le caractère est un chiffre

    let number = Number.parseFloat(mdp[caractere]);
    // Le caractère est un nombre, on valide la condition correspondante si elle ne l'était pas déjà
    if (Number.isInteger(number)) {
      if (!conditionChiffre) conditionChiffre = true;
    } else {
      // Le caractère n'est pas un nombre
      //console.log("Not number : " + mdp[caractere]);

      // Vérifie si le caractère est une lettre en majuscule, et valide la condition correspondante si elle ne l'était pas
      if (mdp[caractere] >= 'A' && mdp[caractere] <= 'Z') {
        if (!conditionLettreMajuscule) conditionLettreMajuscule = true;
      }
      // Vérifie si le caractère est une lettre en minuscule, et valide la condition correspondante si elle ne l'était pas
      else if (mdp[caractere] >= 'a' && mdp[caractere] <= 'z') {
        if (!conditionLettreMinuscule) conditionLettreMinuscule = true;
      }
      // On déduit que le caractère est un caractère spécial, on valide la condition correspondante si elle ne l'était pas
      else if (!conditionCaractereSpecial) {
        //console.log("Spe : " + mdp[caractere]);
        conditionCaractereSpecial = true;
      }
    }
  }

  //console.log(mdp.length);
  //console.log("chiffre : " + conditionChiffre + " maj " + conditionLettreMajuscule + " min " + conditionLettreMinuscule
  // + " spe " + conditionCaractereSpecial);
  // Si toutes les conditions sont respectées, on valide le mdp
  return conditionChiffre && conditionLettreMajuscule && conditionLettreMinuscule && conditionCaractereSpecial &&
    mdp.length >= 8;
}
