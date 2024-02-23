<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>connexion</title>
<link href="css/style.css" rel="stylesheet" >
</head>
<body>
 <%@include file="../../fragments/header.jspf" %> 
	<section class="bradcrumb">
		<h1>Connexion</h1>
	</section>
	<div class="flex-form">
	<form action="connexion" method="POST" class="form">
	<div class="form-div">
		<div class="mb-3">
			<label class="label">Email*</label>
			<input type="email" placeholder="Votre email" name="identifiant" class="form-control" required="true"/>
		</div>
		<div class="mt-5">
			<label>Mot de passe*</label>
			<input type="password" placeholder="Votre mot de passe" name="motDePasse" class="form-control" required="true"/>
		</div>
		<div class="div-complement">
			<a href="">Mot de passe oublié </a>
		</div>
		 <div class="div-complement">
			  	<button type="submit" class="color-button">Connexion</button>
	  	</div>
	  	<div class="div-complement">
	  		<a href="creationDuCompte"> Pas de compte  ? Créez-en un ici</a>
	  	</div>
  	</div>	
	</form>
	</div>
	
	<div>
	 			<c:forEach var="current" items="${erreurs }">
	 				<p class="color-error">${current }</p>
	 			</c:forEach>
	</div>
	 
	 <%@include file="../../fragments/footer.jspf" %>
</body>
</html>