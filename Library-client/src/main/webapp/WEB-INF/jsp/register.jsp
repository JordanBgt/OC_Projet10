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

<div class="login">
    <form:form method="post" modelAttribute="registerForm" action="register">
        <img src="<c:url value="/img/logo.png" />" class="logo">
        <div class="form-group">
            <form:input path="email" class="form-control" placeholder="Email" />
        </div>
        <div class="form-group">
            <form:input path="username" class="form-control" placeholder="Username" />
        </div>
        <div class="form-grou">
            <form:input type="password" path="password" class="form-control" placeholder="Password" />
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-primary btn-block login-form-btn">S'enregistrer</button>
        </div>
    </form:form>
</div>

<%@ include file="footer.jsp"%>
</body>
</html>
