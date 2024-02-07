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
					<div class="resto-flex">
						<h3>${restaurant.nom }</h3>
						<div class="icons-container">
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
					</div>
					<div class="resto-flex">
						<div class="resto-info">
							<p>${restaurant.adresse }, ${restaurant.cpo } ${restaurant.ville }</p>
							<p>Téléphone : 04 87 22 00 70</p>
						</div>
						<div class="resto-horaires">
							<h3>Horaires d'ouverture</h3>
							<c:forEach var="jour" items="${horaires.jours }" >
								<div>
									<h4>${jour } : &nbsp</h4>
									<c:forEach var="heures" items="${horaires.horaires[jour] }">
										<p> ${heures.ouverture } - ${heures.fermeture }</p>
									</c:forEach>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="resto-btn">
						<a href="cartes?id=${restaurant.id }">
							<button type="submit" name="consulter">Voir la carte</button>
						</a>
						<a href="">
							<button type="submit" name="reserver">Réserver</button>
						</a>
					</div>
				</div>
				</c:forEach>
				
				<%-- <c:forEach var="current" items="${restaurants }">
					<img src="img/${current.id }.png" />
				</c:forEach> --%>
				
		</section>
	</main>
	<%@include file="../fragments/footer.jspf" %>
</body>
</html>