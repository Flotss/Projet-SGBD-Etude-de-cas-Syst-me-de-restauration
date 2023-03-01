# GoodFoodApplication
## Etudiant : Florian MANGIN

## Prérequis

Avant de pouvoir exécuter ce projet, vous devez avoir installé les éléments suivants :

- Java JDK 1.8 plus récent
- Apache Maven 3.0 ou plus récent

De plus la base de données est oracle, vous devez utilisez 

## Configuration
### Base de données

Pour configurer la base de données, vous devez modifier le fichier `db_config.ini` dans le package `resources` et modifier les paramètres suivants :
- `username` : votre username oracle
- `password` : le mot de passe oracle

Voire même - `url` : l'url de votre base de données

De plus vous devez importer le fichier `DonnéesSQL.sql` dans votre base de données.

## Utilisation
Clone ce répertoire sur votre machine locale en utilisant
```
git clone https://github.com/Flotss/Projet-SGBD-Etude-de-cas-Syst-me-de-restauration.git
```

Ouvrez ce projet dans IntelliJ IDEA et exécutez la classe `GoodFoodApplication.java` dans le package `com.goodfood.app`.

## Liste de fonctionnalité : 
### Service
- ✅ Connexion d'un serveur
- ✅ Consulter les tables disponibles pour une date et heure donnée
- ✅ Réserver une table pour une date et heure donnée
- ✅ Consulter les plats disponibles
- ✅ Commander un plat

### Gestion

- ✅ Création et mise à jour des informations des serveurs
- ✅ Affectation des serveurs à des tables
- ✅ Création et mise à jour des plats
- ✅ Encaissement d'une commande
- ✅ Affichage d'un classement des serveurs entre deux dates
- ✅ Affichage des serveurs qui n'ont fait aucun chiffre d'affaires entre deux dates

## License

GNU General Public License v3.0