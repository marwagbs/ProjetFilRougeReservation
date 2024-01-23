<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>accueil</title>
</head>
<body>

<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div>
    <%@ include file="/WEB-INF/fragments/searchbar.jspf" %>
</div>

<div>
    <h2>A Propos de nous</h2>
    <p></p>
</div>

<div>
    <h2> Nos coups de coeur</h2>
</div>

<div>
    <h2>Nous decouvrir</h2>
    <c:forEach var="restaurant" items="${restaurants}">
        <div class="card">
            <div class="container">
                <td><c:out value="${restaurant.nom}" /></td>
            </div>
        </div>
    </c:forEach>
</div>

<div>
    <h2> Temoignages </h2>
</div>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>

