# Openclassrooms projet 10 : Améliorez le système d'information de la bibliothèque

## Contexte 

Lors d'une missions précédente, [OC_Projet7](https://github.com/JordanBgt/OPC_Projet7), nous avons travaillé sur un système d'information pour les bibliothèques d'une grande ville. Fort du succès rencontré par cette première version, le directeur du service culturel reprend contact avec nous pour apporter des évolutions au système ainsi que pour corriger quelques dysfonctionnement. 

## Référentiel d'évaluation

* Gestion par tickets
* Organisation du versionning

## Exécution de l'application en local 

- Exécution de [Library-api](https://github.com/JordanBgt/OPC_Projet7/blob/master/Library-api/README.md)
- Exécution de [Library-client](https://github.com/JordanBgt/OPC_Projet7/blob/master/Library-client/README.md)
- Exécution de [Library-batch](https://github.com/JordanBgt/OPC_Projet7/blob/master/Library-batch/README.md)

Pour que l'application soit fontionnelle, il faut au moins avoir lancé `Library-api` et `Library-client`.
L'application est ensuite accessible à l'adresse suivante : `http://localhost:8081` (sauf si vous avez modifié les ports dans les propriétés)

### Utilisateurs enregistrés :

| Username | Password | Rôle  |
|----------|----------|-------|
| admin    | admin    | admin |
| user1    | user1    | user  |
| user2    | user2    | user  |
| user3    | user3    | user  |
| user4    | user4    | user  |
| user5    | user5    | user  |
| user6    | user6    | user  |
| user7    | user7    | user  |
| user8    | user8    | user  |

## Déploiement sur un serveur Tomcat

- Dans les projets Library-batch et Library-client : modifier la propriété `app.apiUrl` du fichier `application.properties` pour que l'url corresponde à celle du serveur
- Exécuter la commande `mvn clean package` dans les projets `Library-batch` et `Library-client`, ainsi que dans le module `webapp` du projet `Library-api`
- Copier les différents `war` générés dans le dossier `target` de ces projets/modules puis coller les dans le dossier `webapp` de tomcat
- Démarrer Tomcat et l'application sera disponible à l'url configurée dans Tomcat + le nom du client. Par exemple : `localhost:8080/library-client-1.0.0`

## Exécution des tests d'intégration avec postman

Une collection de test Postman a été créer pour tester l'api. Elle se trouve dans le dossier : `tests-postman` à la racine du repo. Afin d'exécuter ces tests, il convient de lancer l'application avec le profil spring `test` qui va créer une base de données H2 avec toutes les données nécessaires à l'exécution de ces tests. 

Pour exécuter `Library-api` avec le profil `test` : 
```shell
cd Library-api
cd webapp
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

Vous pouvez ensuite importer la collection dans Postman puis exécuter les tests à l'aide du `Collection Runner` de Postman.