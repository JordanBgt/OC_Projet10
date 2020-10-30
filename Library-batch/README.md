# Projet Library-batch

## Description

Batch qui, quotidiennement, récupère tous les prêts échus et envoie un mail de relance aux utilisateurs n'ayant pas rendu leurs prêts à l'échéance de ceux-ci.
Utilisation d'une tâche CRON pour paramétrer la périodicité de l'envoie de mail. Par défaut : tous les jours à 8h.

## Exécution de l'application en local

- Modifier le fichier `src/main/resources/application.properties` pour configurer les informations nécessaires à Spring-Mail pour l'envoie des mails
- Modifier éventuellement dans ce fichier, la propriété `app.mail.periodicity` pour modifier la périodicité de l'envoie de mail
- A la racine du projet, exécuter la commande `mvn clean install` puis la commande `mvn spring-boot:run` pour lancer l'application
- L'application tourne ensuite sur le port 8082 (modifiable dans le fichier `application.properties`
