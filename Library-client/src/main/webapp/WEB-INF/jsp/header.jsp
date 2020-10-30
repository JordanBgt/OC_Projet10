<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<nav class="navbar navbar-dark navbar-expand-lg bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="<c:url value="/" />"><img src="<c:url value="/img/logo.png" />" class="logo"></a>
        <button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1">
            <span class="sr-only">Toggle navigation</span>
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse navbar-menu" id="navcol-1">
            <ul class="nav navbar-nav">
                <li class="nav-item" role="presentation"><a class="nav-link" href="<c:url value="/documents" />">Catalogue</a></li>
                <li class="nav-item" role="presentation"><a class="nav-link" href="#"></a></li>
                <li class="nav-item dropdown"><a data-toggle="dropdown" aria-expanded="false" class="dropdown-toggle nav-link" href="#">Profil </a>
                    <div role="menu" class="dropdown-menu">
                        <c:if test="${empty sessionScope.user}">
                            <a role="presentation" class="dropdown-item" href="<c:url value="/login" />">Se connecter</a>
                            <a role="presentation" class="dropdown-item" href="<c:url value="/register" />">Créer un compte</a>
                        </c:if>
                        <c:if test="${!empty sessionScope.user}">
                            <a role="presentation" class="dropdown-item" href="<c:url value="/profil" />">Consulter profil</a>
                            <a role="presentation" class="dropdown-item" href="<c:url value="/logout" />">Se déconnecter</a>
                        </c:if>
                    </div>
                </li>
            </ul><span class="navbar-text actions"></span></div>
    </div>
</nav>
