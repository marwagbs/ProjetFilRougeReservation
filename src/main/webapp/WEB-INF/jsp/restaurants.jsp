<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nos restaurants</title>
<link href="././css/style.css" rel="stylesheet">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../fragments/linksfont.jspf"%>
</head>
<body>
	<%@include file="../fragments/header.jspf"%>
	<main>
		<div class="intro-resto">
			<h1>Nos restaurants</h1>
			<p>Trouvez le restaurant le plus proche de chez vous !</p>
		</div>
		<section class="resto-section">
			<c:forEach var="restaurant" items="${restaurantsHoraire }">
				<div class="resto-card">
					<div class="resto-flex">
						<h3>${restaurant.nom }</h3>
						<div class="icons-container">
							<div class="resto-icons">
								<span> <img src="././assets/icons/emporter.svg"
									alt="Icône de sac d'achat">
								</span>
								<p>à emporter</p>
							</div>
							<div class="resto-icons">
								<span id="icon-moto"> <img
									src="././assets/icons/livraison.svg" alt="Icône de moto">
								</span>
								<p>en livraison</p>
							</div>
							<div class="resto-icons">
								<span> <img src="././assets/icons/surplace.svg"
									alt="Icône de couverts">
								</span>
								<p>sur place</p>
							</div>
						</div>
					</div>
					<div class="resto-flex">
						<div class="resto-flex-two">
							<div class="resto-info">
								<p>Adresse : ${restaurant.adresse }, ${restaurant.cpo }
									${restaurant.ville }</p>
								<p>Téléphone : 04 87 22 00 70</p>
								 <p>Nombre des tables : <span>${restaurant.tableRes.id }</span></p>
							</div>
							<div class="resto-btn">
								<a href="cartes?id=${restaurant.id }">
									<button type="submit" name="consulter">Voir la carte</button>
								</a>

								<c:if test="${not empty identifiant }">
									<a href="reservation?id=${restaurant.id}"><button
											type="button">Réserver</button></a>
								</c:if>
								<c:if test="${empty identifiant }">
									<a href="connexion"><button type="button">Réserver</button></a>
								</c:if>
							</div>
						</div>
	
						<div class="resto-horaires">
							<h3>Horaires d'ouverture</h3>
							<c:forEach var="entry"
								items="${horairesParRestaurant[restaurant.id] }">
								<c:set var="jour" value="${entry.key}" />
								<c:set var="horaires" value="${entry.value}" />
								<div>${jour}: &nbsp
									<c:forEach var="horaire" items="${horaires}" varStatus="loop">
                   					 ${horaire.heureOuverture} - ${horaire.heureFermeture}
                   
                					</c:forEach>
								</div>
							</c:forEach>

						</div>
					</div>
					</div>
			</c:forEach>
			
		</section>
	</main>
	<%@include file="../fragments/footer.jspf"%>
</body>
</html>