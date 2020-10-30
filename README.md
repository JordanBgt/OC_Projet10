# Openclassrooms projet 7 : Développez le nouveau système d'information des bibliothèques d'une grande ville

## Contexte 

Le client souhaite la création d'un ensemble d'outils numériques pour les différents acteurs des bibliothèques de la ville : 

* Un site web et une appliaction mobile pour les usagers des bibliothèques
* Un logiciel pour le personnel des bibliothèques
* Un logiciel pour des traitements automatisés (mails de relance)

Le site web permettra aux usagers de suivre les prêts de leurs ouvrages. Ils pourront : 

* Rechercher des ouvrages et voir le nombre d’exemplaires disponibles
* Consulter leurs prêts en cours. Les prêts sont pour une période de 4 semaines
* Prolonger un prêt en cours. Le prêt d’un ouvrage n’est prolongeable qu’une seule fois. La prolongation ajoute une nouvelle période de prêt (4 semaines) à la période initiale.

L'application mobile (IOS et Android) devra fournir les mêmes services que le site web.

Le logiciel pour le personnel des bibliothèques permettra notamment de gérer les emprunts et les livres rendus.

Le logiciel pour les traitements automatisés permettra d'envoyer des mails de relance aux usagers n'ayant pas rendu les livres en fin de période de prêt. L'envoi est automatique à la fréquence d'un par jour.

### Plan de travail

* Release 1.0 : API Web, Application web, Batch
* Release 2.0 : Logiciel pour le personnel
* Release 3.0 : Application mobile

Nous sommes chargés uniquement de la release 1.0. Mais dès cette release, les actions de création d'un prêt et retour d'un prêt seront implémentées même si elles ne seront utilisées par le logiciel pour le personnel uniquement à la release 2.0.

## Contraintes techniques 

* API Web en REST ou SOAP : les clients (site web, batch, logiciel pour les personnels, application mobile) communiquerons à travers une API Web.
* Application web avec un framework MVC (Spring MVC, Struts, ...)
* Packaging avec Maven

## Référentiel d'évaluation

Concevoir une application web avec une approche par composants

* L’application web est consommatrice d’une API web.
* Un framework MVC est utilisé.
* Le scope fonctionnel est respecté.

Créer une API web avec un web service SOAP ou un microservice REST

* La logique métier est implémentée au sein d’un composant qui expose une API web.

Interagir avec des composants externes

* L’API web communique avec la base de données.

Sélectionner les langages de programmation adaptés pour le développement de l’application

* Différents frameworks sont mis en oeuvre en fonction des choix techniques.
* Les choix techniques sont justifiés à l’oral.

Respecter les bonnes pratiques de développement en vigueur

* Le packaging est géré avec Apache Maven.
* Chaque composant a un cycle de développement qui lui est propre.
* Une architecture n-tiers est mise en oeuvre.
* Le patron de conception ‘Inversion de contrôle’ est mis en oeuvre.

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

## Déploiement sur un serveur Tomcat

- Dans les projets Library-batch et Library-client : modifier la propriété `app.apiUrl` du fichier `application.properties` pour que l'url corresponde à celle du serveur
- Exécuter la commande `mvn clean package` dans les projets `Library-batch` et `Library-client`, ainsi que dans le module `webapp` du projet `Library-api`
- Copier les différents `war` générés dans le dossier `target` de ces projets/modules puis coller les dans le dossier `webapp` de tomcat
- Démarrer Tomcat et l'application sera disponible à l'url configurée dans Tomcat + le nom du client. Par exemple : `localhost:8080/library-client-1.0.0`
