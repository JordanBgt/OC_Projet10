<%@ page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <%@ include file="head.jsp"%>
    </head>
    <body>
        <%@ include file="header.jsp"%>

        <form:form method="post" modelAttribute="documentForm" action="documents/search">
            <div class="row">
                <div class="col-6 col-md-4 form-group">
                    <form:input path="title" id="title" class="form-control" placeholder="Titre" />
                </div>
                <div class="col-6 col-md-4 form-group">
                    <form:input path="isbn" id="isbn" class="form-control" placeholder="Isbn" />
                </div>
                <div class="col-6 col-md-4 form-group">
                    <form:input path="authorName" id="authorName" class="form-control" placeholder="Auteur" />
                </div>
            </div>
            <div class="row">
                <div class="col-6 col-lg-4 form-group">
                    <form:input path="publisherName" id="publisherName" class="form-control" placeholder="Éditeur" />
                </div>
                <div class="col-6 col-lg-2 form-group">
                    <form:select path="type" id="documentType" class="form-control">
                        <form:option value="">Type</form:option>
                        <form:option value="Livre" />
                        <form:option value="Presse" />
                        <form:option value="Bande dessinée" />
                    </form:select>
                </div>
                <div class="col-6 col-lg-2 form-group">
                    <form:select path="category" id="category" class="form-control">
                        <form:option value="">Catégorie</form:option>
                        <form:option value="Roman" />
                        <form:option value="Science-Fiction" />
                        <form:option value="Thriller" />
                        <form:option value="Informatique" />
                        <form:option value="Littérature" />
                    </form:select>
                </div>
                <div class="col-4 offset-1 col-lg-2 offset-lg-1 text-center">
                    <button class="btn btn-primary" type="submit">Rechercher</button>
                </div>
            </div>
        </form:form>


        <div class="card-container">
            <c:forEach items="${pageDocuments.content}" var="document">
               <div class="card">
                   <img src="${document.photo.fileToBase64String}">
                   <div class="card-body">
                       <h5>${document.title}</h5>
                       <p>${document.authorLastName} ${document.authorFirstName}</p>
                       <a class="btn btn-primary" href="<c:url value="/documents/${document.id}" />">Detail</a>
                   </div>
               </div>
            </c:forEach>
        </div>

        <c:if test="${pageDocuments.totalPages > 1}">
            <nav class="pagination">
                <ul class="pagination">
                    <li class="page-item  ${pageDocuments.first ? 'disabled' : ''}">
                        <a class="page-link" href="<c:url value="documents"><c:param name="page" value="${pageDocuments.number - 1}"/></c:url>" >
                            <span>&laquo;</span>
                        </a>
                    </li>
                    <c:forEach var="i" begin="1" end="${pageDocuments.totalPages}" step="1">
                        <li class="page-item ${pageDocuments.number +1  == i ? 'active' : ''}">
                            <a href="<c:url value="documents"><c:param name="page" value="${i-1}" /></c:url>" class="page-link">${i}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item  ${pageDocuments.last ? 'disabled' : ''}">
                        <a href="<c:url value="documents"><c:param name="page" value="${pageDocuments.number + 1}"/></c:url>" class="page-link">
                            <span>&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </c:if>

        <%@ include file="footer.jsp"%>
    </body>
</html>
