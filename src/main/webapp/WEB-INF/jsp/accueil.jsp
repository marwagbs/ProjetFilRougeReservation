<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="styles.css">
<title>Accueil</title>
</head>
<body>

<%@ include file="/WEB-INF/fragments/header.jspf" %>

<div>
    <%@ include file="/WEB-INF/fragments/searchbar.jspf" %>
</div>

<div class="apropos">
    <h2>A Propos de nous</h2>
    <p></p>
</div>

<div class=coups-coeur>
    <h2> Nos coups de coeur</h2>
</div>

<div class="nous-decouvrir">
    <h2>Nous decouvrir</h2>
    
    <c:forEach var="restaurant" items="${restaurants}">
        <div class="card">
            <div class="container">
                <td><c:out value="${restaurant.nom}" /></td>
                <td> <img src="images/restaurant.jpg" alt="Description of the image"></td>
                 <!-- Reservation form -->
                 <td>
			    <form action="ServletReservation" method="post">
			        <button type="submit">Reserver</button>
			    </form>
			    </td>
            </div>
        </div>
    </c:forEach>
    
    
</div>

<div class ="temoignages">
    <h2> Temoignages </h2>
</div>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>

