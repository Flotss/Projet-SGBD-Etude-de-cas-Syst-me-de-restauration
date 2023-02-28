# Projet-SGBD-Etude-de-cas-Syst-me-de-restauration

## Etudiant : Florian MANGIN

Sure, here’s a sample README.md file that you can use to guide users on how to execute your Maven project:

# GoodFoodApplication

## Prérequis

Avant de pouvoir exécuter ce projet, vous devez avoir installé les éléments suivants :

- Java JDK 1.8 plus récent
- Apache Maven 3.0 ou plus récent

## Configuration
### Base de données
Pour configurer la base de données, vous devez modifier le fichier `db_config.ini` dans le package `resources` et modifier les paramètres suivants :
- `username` : l'adresse du serveur de base de données
- `password` : le mot de passe de l'utilisateur de la base de données

De plus vous devez importer le fichier `DonnéesSQL.sql` dans votre base de données.

## Utilisation
Clone ce répertoire sur votre machine locale en utilisant
```
git clone https://github.com/Flotss/Projet-SGBD-Etude-de-cas-Syst-me-de-restauration.git
```

Ouvrez ce projet dans IntelliJ IDEA et exécutez la classe `GoodFoodApplication.java` dans le package `com.goodfood.app`.


## License

GNU General Public License v3.0