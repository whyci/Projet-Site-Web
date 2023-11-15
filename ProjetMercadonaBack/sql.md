
# Base de donnée promotion

### Creation des tables

```sql
Administrateur, Produit, Promotion : 
CREATE TABLE administrateur (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    age INT,
    adresse_mail VARCHAR(100),
    mot_de_passe VARCHAR(100),
    numero_telephone VARCHAR(100)
);  
CREATE TABLE produit (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    libelle VARCHAR(100),
    description VARCHAR(200),
    categorie VARCHAR(100),
    prix INT
);  
CREATE TABLE promotion (
     id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
     debut DATE,
     fin DATE,
     pourcentage_remise INT
);  
```

### Insertion des valeurs

Administrateur
```sql
INSERT INTO administrateur (nom, prenom, age, adresse_mail, mot_de_passe,  numero_telephone)
VALUES ('Tural', 'Patrick', 45, 'p.tural@gmail.com', '12345patrick', '0677889944');
INSERT INTO administrateur (nom, prenom, age, adresse_mail, mot_de_passe,  numero_telephone)
VALUES ('Gourez', 'Mathias', 30, 'm.gourez@gmail.com', '12345mathias', '0677889955');
INSERT INTO administrateur (nom, prenom, age, adresse_mail, mot_de_passe,  numero_telephone) 
VALUES ('plomel', 'iture', 24, 'i.plomel@gmail.com', '12345plomel', '0677889966');
```

Produit
```sql
INSERT INTO produit (libelle, categorie, description, prix) 
VALUES ('pomme', 'fruit', 'fruit de nos regions', 4);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('poire', 'fruit', 'fruit de nos regions', 5);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('peche', 'fruit', 'fruit de nos regions', 3);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('kiwi', 'fruit', 'fruit exotique', 3);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('lichi', 'fruit', 'fruit exotique', 4);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('ananas', 'fruit', 'fruit exotique', 5);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('pamplemousse', 'fruit', 'fruit de nos regions', 5);

INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('tablette ipadde air', 'electronique', 'tablette pom', 799);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('tele panasonik', 'electronique', 'television 20"40', 1499);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('pot-peint 16L blanc', 'peinture', 'acrylique blanc', 10);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('pain-so 18cm', 'peinture', 'pinceau brosse 18cm', 19);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('pain-so 20cm', 'peinture', 'pinceau brosse 20cm', 19);
INSERT INTO produit (libelle, categorie, description, prix)
VALUES ('pomme', 'fruit', 'fruit de nos regions', 4);
```

Promotion
```sql
INSERT INTO promotion (debut, fin, pourcentage_remise) VALUES ('2023-09-01', '2023-09-21', 50);
INSERT INTO promotion (debut, fin, pourcentage_remise) VALUES ('2022-01-01', '2025-01-01', 15);
INSERT INTO promotion (debut, fin, pourcentage_remise) VALUES ('2023-08-01', '2023-08-31', 75);
INSERT INTO promotion (debut, fin, pourcentage_remise) VALUES ('2023-05-01', '2023-11-01', 30);
```

CodeAdministrateur
```sql
INSERT INTO code_administrateur (code) VALUES ('Ceci est un coooooode');
INSERT INTO code_administrateur (code) VALUES ('Noooooooope');
INSERT INTO code_administrateur (code) VALUES ('vybisufognvufsdhbjk');
INSERT INTO code_administrateur (code) VALUES ('v16b4984bb98ez4bw8b');
```

### Suppression des contenus des tables
```sql
TRUNCATE TABLE promotion, produit, administrateur, code_administrateur
```

### Suppression des tables
```sql
drop table promotion, produit, administrateur, code_administrateur
```

### Suppression de la base de donnée
```sql
drop database [database]
```
