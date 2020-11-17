<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <%@ include file="head.jsp"%>
</head>
<body>
<%@ include file="header.jsp"%>

    <div class="container-fluid detail">
        <div class="row detail-header">
            <div class="col-5 detail-img">
                <img src="${document.photo.fileToBase64String}">
            </div>
            <div class="col-7">
                <div class="detail-info">
                    <h2>${document.title}</h2>
                    <hr>
                    <p>Auteur : ${document.author.firstName} ${document.author.lastName}</p>
                    <p>Éditeur : ${document.publisher.name}</p>
                    <p>Isbn : ${document.isbn}</p>
                    <p>Date de publication : ${document.publicationDate}</p>
                </div>
            </div>
        </div>

        <div class="card detail-description">
            <div class="card-body">
                <h3 class="card-title">Description</h3>
                <hr>
                <p class="card-text">${document.description}</p>
            </div>
        </div>

        <div class="card detail-exemplars">
            <div class="card-body">
                <h3 class="card-title">
                    Exemplaires disponibles
                    <c:choose >
                        <c:when test="${sumExemplarsAvailable.isPresent()}"><span class="badge badge-secondary">${sumExemplarsAvailable.get()}</span></c:when>
                        <c:otherwise><span class="badge badge-danger">0</span></c:otherwise>
                    </c:choose>
                </h3>
                <hr>
                <ul class="list-group">
                    <c:choose>
                        <c:when test="${exemplars.size() > 0}">
                            <c:forEach items="${exemplars}" var="exemplar">
                                <li class="list-group-item">
                                    <strong>${exemplar.libraryName}</strong> - <span class="badge badge-success">${exemplar.number}</span>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div class="reservation-content">
                                <div>
                                <p>Tous les exemplaires sont actuellement prêtés</p>
                                <p>Prochaine date de retour prévue : ${document.waitingList.endDateNextLoanReturn}</p>
                                <p>Nombre de personnes ayant reservé l'ouvrage : ${document.waitingList.numberUsersInWaitingList}</p>
                                <p>Nombre de places disponibles dans la file d'attente : ${document.waitingList.size - document.waitingList.numberUsersInWaitingList}</p>
                                </div>
                                <c:if test="${document.canBeReserved}">
                                    <div class="reservation-button">
                                        <a href="<c:url value="/documents/${document.id}/reserve">
                                            <c:param name="waitingListId" value="${document.waitingList.id}"/>
                                            </c:url>" class="btn btn-primary" >Réserver</a>
                                    </div>
                                </c:if>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </div>

<%@ include file="footer.jsp"%>
</body>
</html>
