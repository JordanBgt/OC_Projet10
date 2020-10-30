<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <%@ include file="head.jsp" %>
    </head>
    <body>
        <%@ include file="header.jsp" %>

        <div class="jumbotron">
            <div class="text-center">
                <h1>Bienvenue sur le site des bibliothèques de Lyon</h1>
                <p class="lead">Site web destiné aux usagers des bibliothèques de la ville</p>
            </div>
            <hr class="my-4">
            <div class="jumbotron-body">
                <p>Vous pouvez : </p>
                <ul>
                    <li>Consulter le catalogue des bibliothèques et effectuer des recherches de documents</li>
                    <li>Voir le nombre d'exemplaires disponibles</li>
                    <li>Consulter vos prêts en cours</li>
                    <li>Prolonger vos prêts (dans la limite d'une prolongation par document emprunté)</li>
                </ul>
            </div>
        </div>

        <%@ include file="footer.jsp" %>
    </body>
</html>
