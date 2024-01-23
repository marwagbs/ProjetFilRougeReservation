<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
    <form action="#" method="post">
    
     <input type="hidden" name="idUtilisateur" value="${utilisateur.id}">
        <label for="date">Date :</label>
        <input type="date" id="date" name="dateres" required>

        <label for="heure">Heure :</label>
        <input type="time" id="heure" name="heure" required>

        <label for="nbrPersonnes">Nombre de personnes :</label>
        <input type="number" id="nbrPersonnes" name="nbrPersonnes" required>


        <label for="idRestaurant">Choisir un restaurant :</label>
        <select id="idRestaurant" name="idRestaurant" required>
            <c:forEach var="restaurant" items="${restaurants}">
                <option value="${restaurant.id}">${restaurant.nom}</option>
            </c:forEach>
        </select>

        <label for="commentaire">Commentaire :</label>
        <textarea id="commentaire" name="commentaire"></textarea>

        <button type="submit">Soumettre la réservation</button>
    </form>
</body>
</html>
