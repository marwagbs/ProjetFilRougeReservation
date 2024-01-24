<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> ACCUEIL</title>
    <link rel="stylesheet" href="styles.css">

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
    <h2> Nos coups de coeur </h2>
</div>

<section>


    <h2>Nous decouvrir</h2>
    <div class="nous-decouvrir">
    <c:forEach var="restaurant" items="${restaurants}">
    <div class="nous-decouvrir-card">
                <h3><c:out value="${restaurant.nom}" /></h3>
                <img src="images/restaurant.jpg" alt="Description of the image" class="decouvrir-img">
                <a href="ServletReservation"><button id="add-contact-btn">Reserver</button></a>        
    </div>
    </c:forEach>
    </div>


</section>


<div class ="temoignages">
    <h2> Temoignages </h2>
</div>


<%@ include file="/WEB-INF/fragments/footer.jspf" %>

