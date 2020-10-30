# Projet Library-client

## Description

Client du projet Library : affichage des différentes pages web. Il fait appel au projet `Library-api` via des proxy OpenFeign.

## Exécution de l'application en local

- A la racine du projet : exécuter la commande `mvn clean install` puis `mvn spring-boot:run`
- L'application est ensuite disponible à l'adresse suivante : `http://localhost:8081` (port modifiable dans le fichier `application.properties`)
