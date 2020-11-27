<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="head.jsp"%>
</head>
<body>
<%@ include file="header.jsp"%>

<div class="profil-container">
    <div class="card">
        <div class="card-header">
            <div class="rounded-circle">
                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-person-circle" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path d="M13.468 12.37C12.758 11.226 11.195 10 8 10s-4.757 1.225-5.468 2.37A6.987 6.987 0 0 0 8 15a6.987 6.987 0 0 0 5.468-2.63z"/>
                    <path fill-rule="evenodd" d="M8 9a3 3 0 1 0 0-6 3 3 0 0 0 0 6z"/>
                    <path fill-rule="evenodd" d="M8 1a7 7 0 1 0 0 14A7 7 0 0 0 8 1zM0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8z"/>
                </svg>
            </div>
            <h3>Utilisateur</h3>
        </div>
        <div class="card-body">
            <p>Username : ${user.username}</p>
            <p>Email : ${user.email}</p>
        </div>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="rounded-circle">
                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-book" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M1 2.828v9.923c.918-.35 2.107-.692 3.287-.81 1.094-.111 2.278-.039 3.213.492V2.687c-.654-.689-1.782-.886-3.112-.752-1.234.124-2.503.523-3.388.893zm7.5-.141v9.746c.935-.53 2.12-.603 3.213-.493 1.18.12 2.37.461 3.287.811V2.828c-.885-.37-2.154-.769-3.388-.893-1.33-.134-2.458.063-3.112.752zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
                </svg>
            </div>
                <h3>Prêts en cours</h3>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${loans.size() > 0}">
                    <ul class="list-group">
                        <c:forEach items="${loans}" var="loan">
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-12 col-lg-9">
                                            ${loan.exemplar.documentTitle} - ${loan.exemplar.library.name} - <span class="badge badge-${loan.cssClass}">${loan.endDate}</span>
                                    </div>
                                    <div class="col-12 col-lg-3 col-renewal-button">
                                        <c:if test="${loan.canBeRenewed()}">
                                            <a href="<c:url value="/profil/${loan.id}/renewal" />" class="btn btn-primary renewal-button" >Renouveler</a>
                                        </c:if>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    Aucun prêt en cours
                </c:otherwise>
            </c:choose>
        </div>
    </div>

    <div class="card">
        <div class="card-header">
            <div class="rounded-circle">
                <svg width="3em" height="3em" viewBox="0 0 16 16" class="bi bi-book" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
                    <path fill-rule="evenodd" d="M1 2.828v9.923c.918-.35 2.107-.692 3.287-.81 1.094-.111 2.278-.039 3.213.492V2.687c-.654-.689-1.782-.886-3.112-.752-1.234.124-2.503.523-3.388.893zm7.5-.141v9.746c.935-.53 2.12-.603 3.213-.493 1.18.12 2.37.461 3.287.811V2.828c-.885-.37-2.154-.769-3.388-.893-1.33-.134-2.458.063-3.112.752zM8 1.783C7.015.936 5.587.81 4.287.94c-1.514.153-3.042.672-3.994 1.105A.5.5 0 0 0 0 2.5v11a.5.5 0 0 0 .707.455c.882-.4 2.303-.881 3.68-1.02 1.409-.142 2.59.087 3.223.877a.5.5 0 0 0 .78 0c.633-.79 1.814-1.019 3.222-.877 1.378.139 2.8.62 3.681 1.02A.5.5 0 0 0 16 13.5v-11a.5.5 0 0 0-.293-.455c-.952-.433-2.48-.952-3.994-1.105C10.413.809 8.985.936 8 1.783z"/>
                </svg>
            </div>
            <h3>Réservation en cours</h3>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${userWaitingLists.size() > 0}">
                    <ul class="list-group reservation-profil-list">
                        <c:forEach items="${userWaitingLists}" var="userWaitingList">
                            <li class="list-group-item">
                                <div class="row">
                                    <div class="col-12 col-lg-9">
                                        <ul class="reservation-profil-content">
                                            <li>
                                                <span>${userWaitingList.waitingList.documentTitle}</span>
                                            </li>
                                            <li>
                                                <span>Position dans la file d'attente :</span> ${userWaitingList.userPosition}
                                            </li>
                                            <li>
                                                <span>Date prochain retour de prêt :</span> ${userWaitingList.waitingList.endDateNextLoanReturn}
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="col-12 col-lg-3 reservation-button-div">
                                        <a href="<c:url value="/profil/userWaitingList/${userWaitingList.id}" />" class="btn btn-primary reservation-button" >Supprimer</a>
                                    </div>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </c:when>
                <c:otherwise>
                    Aucune réservation en cours
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>


<%@ include file="footer.jsp"%>
</body>
</html>
