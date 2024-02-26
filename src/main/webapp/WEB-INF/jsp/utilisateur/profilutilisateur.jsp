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
     <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<%@include file="../../fragments/header.jspf" %>
	<main>
		<section class="user-profil">
			<div class="user-profil-text">
				<h2>Hello ${utilisateur.prenom} !</h2>
				<p>Voici les détails de votre compte :</p>
			</div>
			<div class="user-profil-error">
		 		<c:forEach var="current" items="${erreurs }">
		 			<p>${current }</p>
		 		</c:forEach>
			</div>
			<form action="profil" method="POST" class="user-profil-form" >
				<input type="hidden" name="id" value="${utilisateur.id}" /> 
				<div class="form-group">			
					<label for="nom" class="form-label">Nom :</label>
					<div class="form-input">
						<input id="nom" name="nom" value="${utilisateur.nom}"  placeholder="Nom"  class="form-control" /> 
					</div>
				</div>

				<div class="form-group">			
					<label for="prenom" class="form-label">Prenom :</label>
					<div class="form-input">
						<input id="prenom" name="prenom" value="${utilisateur.prenom}"  placeholder="Prenom"  class="form-control" /> 
					</div>
				</div>
				
				<div class="form-group">			
					<label for="email" class="form-label">Email :</label>
					<div class="form-input">
						<input id="email" name="email" value="${utilisateur.email}" readOnly placeholder="Email"  class="form-control" /> 
					</div>
				</div>

				<div class="form-group">			
					<label for="motdepasse" class="form-label">Mot de passe :</label>
					<div class="form-input">
						<input id="motdepasse" name="motdepasse" value="***********" readOnly placeholder="Mot de passe"  class="form-control" /> 
					</div>
				</div>
				
				<div class="form-group">			
					<label for="telephone" class="form-label">Telephone :</label>
					<div class="form-input">
						<input id="telephone" name="telephone" value="${utilisateur.telephone}"  placeholder="Telephone"  class="form-control" /> 
					</div>
				</div>

				<div class="form-group" >
					<div class="form-btn">
						<button type="submit">Modifier</button>
					</div>
				</div>
			
			</form>
			<div class="user-profil-delete">Pour supprimer votre compte cliquez sur <span onclick="document.getElementById('id01').style.display='block'">ce lien</span></div>
		</section>
		<div id="id01" class="modal">
		  <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
		  <form class="modal-content" action="/action_page.php">
		    <div class="modal-container">
		      <p>Êtes-vous sûr de vouloir supprimer votre compte ?</p>
		      <div class="clearfix">
		        <a href="profil" class="cancelbtn">Non</a>
		        <a href="ServletSupprimerProfil?id=${utilisateur.id}" class="deletebtn">Oui</a>
		      </div>
		    </div>
		  </form>
		</div>
	</main>
	<%@include file="../../fragments/footer.jspf" %>
</body>
</html>