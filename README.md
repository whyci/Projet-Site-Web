# Projet-Site-Web

## Logiciel utilisé
  J’ai utilisé l’IDE IntelliJ  avec Java avec la version 20 SDK  

## Prérequis back
  Installer la base de données PostgreSQL : https://www.postgresql.org/  
  Le port 8080 doit aussi être libre pour l’utilisation de la base de données  
  Installer Java avec la version 20 SDK : https://www.java.com/fr/    

## Prérequis front
  npm install
 
## Utilisation de Vue.Js.3 en local sur le port 3000
  npm run dev

  Pour lancer le site avec la base de données il faut changer la variable de connexionBack sur true sinon il y a une liste de produits fixe dans le front  
  store.state.connexionBack = false; -> true;   

## Lancer des test
  Au besoin, installer Cypress : https://www.cypress.io/  
  npx cypress open    
 
## Autres informations
  Toutes informations supplémentaires sont à retrouver dans le document rendu pour mon examen.  
