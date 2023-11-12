/*
Copyright (c) 2023 to Present,
Author: Camille VERON.
All rights reserved.
 */

/**
 * Vérifie que l'adresse mail correspond au format suivant : exemple@domaine.com.
 * Autrement dit, il devra contenir un nom de mail, un seul caractère '@', et enfin un domaine (contenant un seul '.').
 * Chaque exigence est définie sous forme de condition. Si une des conditions n'est pas validée, l'adresse mail est
 * considérée invalide.
 * Retourne vrai si l'adresse mail est valide. Sinon, retourne faux.
 * @param mail Adresse mail que l'on souhaite vérifier.
 * @return boolean
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
 * Retourne vrai si le nom (ou prénom) est valide. Sinon, retourne faux.
 * @param nom Nom (ou prénom) que l'on souhaite valider.
 * @return boolean
 */
export function validerNom(nom) {
  // Si aucun caractère dans le nom
  if (nom === "") {
    alert("Les champs obligatoires sont à remplir");
    return false;
  }

  // On prend chaque caractère contenu dans le nom (ou prénom)
  for (let lettre in nom) {
    // Si le caractère n'est pas dans l'alphabet
    if (!(nom[lettre] >= 'A' && nom[lettre] <= 'Z') && !(nom[lettre] >= 'a' && nom[lettre] <= 'z'))
      // S'il n'est pas un tiret, on invalide le nom (ou prénom)
      if (nom[lettre] !== '-') {
        alert("Aucun caractère spécial autorisé, uniquement alphabet et le tiret au milieu du nom/prénom");
        return false;
      }
      // Si c'est un tiret et qu'il est placé en première ou dernière position dans le nom (ou prénom), on l'invalide
      else if (Number.parseInt(lettre) === 0 || Number.parseFloat(lettre) === nom.length-1) {
        return false;
      }
  }
  // Si rien à signaler, on valide le nom (ou prénom)
  return true;
}

/**
 * Vérifie que le mot de passe valide les conditions suffisantes. Il doit contenir au moins :
 * 8 caractères ; une lettre en majuscule ; une lettre en minuscule ; un chiffre ; un caractère spécial ;
 * Retourne vrai si le mdp est valide. Sinon, retourne faux.
 * @param mdp Mot de passe que l'on souhaite valider.
 * @return boolean
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
    }
    // Le caractère n'est pas un nombre
    else {


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
        conditionCaractereSpecial = true;
      }
    }
  }

  // Si toutes les conditions sont respectées, on valide le mdp
  return conditionChiffre && conditionLettreMajuscule && conditionLettreMinuscule && conditionCaractereSpecial &&
    mdp.length >= 8;
}

/**
 * Vérifie que le numéro de téléphone comporte uniquement des nombres (au moins 9), des espaces ou un signe '+' au début.
 * Retourne vrai si le numéro de téléphone est valide. Sinon, retourne faux.
 * @param numeroTel Numéro de téléphone à valider.
 * @return boolean
 */
export function validerNumeroTelephone(numeroTel) {
  // Vérifie que le numéro n'est pas vide
  if (numeroTel === "") {
    return false;
  }

  // Compteur du nombre de chiffres qui doit être au minimum supérieur à 9.
  let cptNbChiffres = 0;

  // Boucle pour analyser chaque élément du numéro de téléphone. Si un élément ne rentre pas dans les conditions, on
  // invalide le numéro.
  for (let caractere in numeroTel) {
    // On vérifie si le caractère est un chiffre.
    if (Number.isInteger(Number.parseInt(numeroTel[caractere]))) {
      // Le caractère est un chiffre, on incrément le compteur du nombre de chiffre pour valider les 9 chiffres min.
      cptNbChiffres ++;
    } else {
      // Si le caractère n'est pas un chiffre, on vérifie qu'il ne s'agit pas d'un espace ou bien qu'un '+' en
      // première position. Sinon, on considère que le numéro est invalide et l'on renvoie faux.
      if (numeroTel[caractere] !== " ") {
        if (!(numeroTel[caractere] === "+" && Number.parseInt(caractere) === 0)) return false;
      }
    }
  }
  return cptNbChiffres >= 9;
}

/**
 * Vérifie que la date de naissance équivaut à ce que l'administrateur ait au moins 18 ans, et moins de 120 ans.
 * @param dateNaissance Date de naissance que l'on vérifie.
 */
export function validerDateNaissance(dateNaissance) {

  // Récupère le jour actuel
  let instantActuel = new Date();

  // Année moins de 18 ans ou plus de 120 ans, invalide la date de naissance
  if ( (dateNaissance.getFullYear() > instantActuel.getFullYear() - 18) ||
    (dateNaissance.getFullYear() < instantActuel.getFullYear() - 120) ) {
    return false;
  }
  // Si égal à l'année d'il y a 18 ans, on regarde le mois
  else if (dateNaissance.getFullYear() === instantActuel.getFullYear() - 18) {
    // Mois supérieur à l'actuel donc plus jeune que 18 ans, invalide la date de naissance
    if (dateNaissance.getMonth() > instantActuel.getMonth()) {
      return false;
    }
    // Si égal, on regarde le jour
    else if (dateNaissance.getMonth() === instantActuel.getMonth()) {
      // Jour supérieur à l'actuel donc plus jeune que 18 ans, invalide la date de naissance
      if (dateNaissance.getDate() > instantActuel.getDate()) {
        return false;
      }
    }
  }
  // Si égal à l'année d'il y a 120 ans, on regarde le mois
  else if (dateNaissance.getFullYear() === instantActuel.getFullYear() - 120) {
    // Mois inférieur à l'actuel donc plus vieux que 120 ans, invalide la date de naissance
    if (dateNaissance.getMonth() < instantActuel.getMonth()) {
      return false;
    }
    // Si égal, on regarde le jour
    else if (dateNaissance.getMonth() === instantActuel.getMonth()) {
      // Jour inférieur à l'actuel donc plus vieux que 120 ans, invalide la date de naissance
      if (dateNaissance.getDate() <= instantActuel.getDate()) {
        return false;
      }
    }
  }

  // La date de naissance est valide
  return true;
}
