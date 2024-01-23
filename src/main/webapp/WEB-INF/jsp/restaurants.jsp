<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nos restaurants</title>
<link href="././css/style.css" rel="stylesheet" >
<%@include file="../fragments/linksfont.jspf" %>
</head>
<body>
	<%@include file="../fragments/header.jspf" %> 
	<main>
		<%@include file="../fragments/searchbar.jspf" %>
		<section class="resto-section">
			<c:forEach var="restaurant" items="${restaurants }">
			<div class="resto-card">
				<h3>${restaurant.nom }</h3>
				<div class="resto-flex">
					<div class="resto-icons">
						<span>
							<img src="././assets/icons/emporter.svg" alt="Icône de sac d'achat" >
						</span>
						<p>à emporter</p>
					</div>
					<div class="resto-icons">
						<span id="icon-moto">
							<img src="././assets/icons/livraison.svg" alt="Icône de moto" >
						</span>
						<p>en livraison</p>
					</div>
					<div class="resto-icons">
						<span>
							<img src="././assets/icons/surplace.svg" alt="Icône de couverts" >
						</span>
						<p>sur place</p>
					</div>
				</div>
				<p>${restaurant.adresse }, ${restaurant.cpo } ${restaurant.ville }</p>
				<div class="resto-btn">
					<a href="">
						<button type="submit" name="consulter">Voir la carte</button>
					</a>
					<a href="">
						<button type="submit" name="reserver">Réserver</button>
					</a>
				</div>
			</div>
			</c:forEach>
		</section>
	</main>
	<%@include file="../fragments/footer.jspf" %>
</body>
</html>