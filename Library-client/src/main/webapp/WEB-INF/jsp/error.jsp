<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">

    <head>
        <%@ include file="head.jsp"%>
    </head>

    <body>
        <%@ include file="header.jsp"%>

        <div class="jumbotron">
            <div class="text-center">
                <h1>Une erreur est survenue lors des traitements</h1>
            </div>
            <hr class="my-4">
            <div class="jumbotron-body">
                <p>Oops, une erreur est survenue lors du traitement de votre requête, veuillez vérifier que vous êtes bien connecté puis essayer de nouveau</p>
            </div>
        </div>
        <%@ include file="footer.jsp"%>
    </body>
</html>
