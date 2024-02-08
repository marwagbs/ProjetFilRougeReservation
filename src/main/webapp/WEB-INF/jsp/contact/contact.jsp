
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contacter nous</title>
<link href="././css/style.css" rel="stylesheet" />
</head>
<body>
	<%@include file="../../fragments/header.jspf"%>
	<main>
		<section class="main-container">
		<div class="main-banner">

			<div class="double-title ">
				<span class="contact-title ">Nous contacter</span> <span
					class="double-title__main-title-wrapper">
					<h1 class="contact-subtitle">
						Une question ? On vous répond au plus vite
					</h1>

				</span>
			</div>
		</div>

		<div class="flex-form ">
		
			<c:forEach var="current" items="${erreurs }">
	 				<p class="color-error">${current }</p>
	 			</c:forEach>
			<form action="contact" method="POST" class="form form-contact ">
				<div class="form-div">
					<div class="mb-3">
						<label class="label-contact">Sujet*</label> 
						<input type="text" placeholder="Sujet*" name="sujet" class="form-control input-contact"  required="true"/>
					</div>
					<div class="mb-3">
						<label class="label-contact">Restaurant* </label>
						<select name="idRestaurant" id="restaurant" class="form-control input-contact select-contact" >
							<option value="choix">Choix du restaurant*  </option>
							 <c:forEach var="current" items="${listeRestaurant }">
							 <option value="${current.id }">${current.nom } ${current.id }</option>
							 </c:forEach>
						</select>
					</div>
					<div class="mb-3">
					<label class="label-contact">Message*</label>
						<textarea cols="40" rows="10"
							class="form-control input-contact"
							id="message" 
							placeholder="Message*" name="contenu" required="true"></textarea>
					</div>
					<div class="div-complement">
						<button type="submit" class="color-button">Envoyer</button>
					</div>

				</div>
			</form>
			<p class="color-messge">${message }</p>
		</div>
	</section>
	</main>
    <%@include file="../../fragments/footer.jspf" %>
</body>
</html>