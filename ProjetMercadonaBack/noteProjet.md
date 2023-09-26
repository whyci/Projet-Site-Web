# Note du projet

### Commande SQL 

#### Quelques conseils : 
* A la fin des requetes, on peut ajouter un point-virgule ';' pour permettre d'en executer plusieurs d'affiler.

#### Créer une table avec ses colonnes :  
```sql
CREATE TABLE [nom_table] (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    [element1] [typeElement1],
    [element2] [typeElement2],
    ...
)
```
Type d element possible : [Types de données SQL](https://www.w3schools.com/sql/sql_datatypes.asp).  

#### Visualiser toutes les données :  

```sql
SELECT * FROM [nom_table]  
ex: SELECT * FROM produits  
```
#### Ajouter des données dans les tables :  
```sql
INSERT INTO [nom_table] ([nom_table].[element1], [nom_table].[element2], ...) 
VALUES ([valeur1], [valeur2], ...)
ex: Vouloir ajouter un produit : libelle='pomme', categorie='fruit', description='aucune', prix=2 
INSERT INTO produit (produit.libelle, produit.categorie, produit.description, produit.prix) VALUES 
('pomme', 'fruit', 'aucune', 2)
NB: Il n est pas obligatoire d ajouter le nom de la table avant les elements
```

#### Filtrer des données :  
```sql
SELECT [affichage] FROM [nom_table] WHERE [condition]
ex: vouloir afficher les libelles des produits de la categorie boisson :
SELECT produits.libelle FROM produits WHERE produits.categorie='boisson'
```

#### Supprimer TOUTES les données d'une table (et non la structure) :  
```sql
DELETE FROM [nom_table]  
ex: DELETE FROM produits  
```

#### Supprimer une colonne :  
```sql
ALTER TABLE [nom_table] DROP [nom_colonne]  
ex: ALTER TABLE produit DROP image  
```

#### Ajouter une colonne :
```sql
ALTER TABLE [nom_table] ADD [nom_colonne]  
ex: ALTER TABLE produit ADD image  
```

#### Ajouter une contrainte (cle primaire) :  
```sql
ALTER TABLE [nom_table] ADD PRIMARY KEY ([id_table])  
ex: ALTER TABLE promotion ADD PRIMARY KEY (promotion_id)  
```

#### Ajouter une contrainte (cle etrangere) :  
```sql
ALTER TABLE [nom_table_accueil] ADD FOREIGN KEY ([id_table_accueil])
    REFERENCES [nom_table_cible]([id_table_cible])  
ex: Vouloir connecter (colonne cle etrangere) promotion_id_cle de (table accueil) produit  
avec (colonne cle primaire) promotion_id de (table cible) promotion.
ALTER TABLE produit ADD FOREIGN KEY (promotion_id_cle) REFERENCES promotion(promotion_id)  
```

#### Connecter deux tables entre elles (relation un a un) :  
```sql
UPDATE [nom_table_accueil] SET [id_cle_etrangere_accueil] = [id_table_cible] FROM [nom_table_cible]
WHERE [condition]
ex: Vouloir connecter (cle etrangere) promotion_id_cle de (table accueil) produit 
avec (identifiant table cible) promotion_id de (table cible) promotion 
selon la condition : les produits de la categorie 'fruit' avec les promotions avec un pourcentage
de remise de 30.
UPDATE produit SET promotion_id_cle = promotion_id FROM promotion 
               WHERE categorie = 'fruit' AND promotion.pourcentage_remise = 30
```

Ajouter une clé étrangère dans une table: 
NB: INNER JOIN: Returns records that have matching values in both tables  

### Bases de donnees relationnelles :  

Comprendre l'utilisation des bases de données relationnelles en Javascript :  
https://medium.com/huawei-developers/database-relationships-in-spring-data-jpa-8d7181f50f60

### Boite à outils :  

Modifier/Réinitialiser le compteur auto-incrément de l'identifiant d'une table :  
Database concernée > table concernée > onglet 'Opérations' :  
'Options pour cette table' > 'AUTO_INCREMENT' > Mettre la valeur désirée  

### Boîte à idées :

#### Appliquer une promotion sur un ensemble de produits  
  
L'administrateur peut filtrer sa vue du catalogue de l'espace 
admin, il sélectionne le nombre de produits qu'il veut et même 
sélectionner tous les produits visibles en même temps.
Cela pour pouvoir appliquer un pourcentage de remise sur un ensemble.

