<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mon compte</title>
<link href="././css/style.css" rel="stylesheet" >
<%@include file="../../fragments/linksfont.jspf" %>
</head>
<body>
	<%@include file="../../fragments/header.jspf" %>
	<main>
		<section class="user-profil">
			<form action="profil" method="POST" class="user-profil-form" >
				<input type="hidden" name="id" value="${utilisateur.id}" /> 
				<div class="form-group">			
					<label for="nom" class="form-label">Nom :</label>
					<div class="form-input">
						<input id="nom" name="nom" value="${utilisateur.nom}"  placeholder="..."  class="form-control" /> 
					</div>
				</div>

				<div class="form-group">			
					<label for="prenom" class="form-label">Prenom :</label>
					<div class="form-input">
						<input id="prenom" name="prenom" value="${utilisateur.prenom}"  placeholder=" ..."  class="form-control" /> 
					</div>
				</div>
				
				<div class="form-group">			
					<label for="email" class="form-label">Email :</label>
					<div class="form-input">
						<input id="email" name="email" value="${utilisateur.email}"  placeholder=" ..."  class="form-control" /> 
					</div>
				</div>

				<div class="form-group">			
					<label for="motDePasse" class="form-label">Mot de passe :</label>
					<div class="form-input">
						<input id="motdepasse" name="motdepasse" value="***********"  placeholder=" ..."  class="form-control" /> 
					</div>
				</div>
				
				<div class="form-group">			
					<label for="telephone" class="form-label">Telephone :</label>
					<div class="form-input">
						<input id="telephone" name="telephone" value="${utilisateur.telephone}"  placeholder=" ..."  class="form-control" /> 
					</div>
				</div>

				<div class="form-group" >
					<div class="form-btn">
						<button type="submit">Enregistrer</button>
						<button type="submit">Supprimer</button>
					</div>
				</div>
			
	</form>
		</section>
	</main>
	<%@include file="../../fragments/footer.jspf" %>
</body>
</html>