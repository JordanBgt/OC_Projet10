# Projet Library-api

## Description 

API REST mutli-modules pour exposer les données.

- Module `business` : contient la logique métier c'est à dire les différents services, mappers et Dto de notre API
- Module `consumer` : contient la couche accès aux données avec les repositories et les entités
- Module `webapp`: contient la couche présentation et donc les différents contrôleurs à exposer

## Exécution de l'application en local

- Importer la base de données via le script SQL dans le dossier `dump` à la racine du projet
- A la racine du projet `Library-api` : exécuter la commande `mvn clean install`
- Dans le module `webapp` : 
    * Modifier si besoin les propriétés relatives à la base de données (fichier `application.yml`)
    * Exécuter la commande `mvn spring-boot:run` pour lancer l'application
- L'application tourne sur le port 8081 (modifiable dans le fichier `application.yml`)
