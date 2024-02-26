<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Création du compte</title>
<link href="css/style.css" rel="stylesheet" >
 <meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../../fragments/linksfont.jspf" %>
</head>
<body>
	 <%@include file="../../fragments/header.jspf" %> 
	 
	 <main>
	 <section class="bradcrumb">
	 	<h1>Création du compte</h1>
	 </section>
	 	<section>
	 	<div>
	 			<c:forEach var="current" items="${erreurs }">
	 				<p class="color-error">${current }</p>
	 			</c:forEach>
	 		</div>
	 		<form action="creationDuCompte" method="POST" class="form">
				<div class="form-div">
				<div class="mb-3">
				<label>Nom*</label>
				<input type="text" placeholder="Votre nom" name="nom" class="form-control" required="true"/>
				</div>
				<div class="mb-3">
				<label>Prénom*</label>
				<input type="text" placeholder="Votre prenom" name="prenom" class="form-control" required="true"/>
				</div>
				<div class="mb-3">
				<label>Numéro de téléphone*</label>
				<input type="tel" placeholder="Votre numéro de téléphone" name="numTel" class="form-control" required="true"/>
				
				</div>
				<div class="mb-3" >
				<label>Email*</label>
				<input type="email" placeholder="Votre email" name="email" class="form-control" required="true"/>
				</div>
				<div class="mb-3">
				<label>Mot de passe*</label>
				<input type="password" placeholder="Votre mot de passe" name="motDePasse" class="form-control" required="true"/>
				</div>
				 <div class="mb-3">
			    <input type="checkbox" id="scales" name="rgbd"  required="true"/>
			    <label for="checkbox" class="label-check">En soumettant ce formulaire, j’accepte que les informations saisies soient exploitées dans le cadre de la relation commerciale ou communicationnelle qui peut en découler. *</label>
			  </div>
			  <div class="div-complement">
			  	<button type="submit" class="color-button">Enregistrer</button>
			  </div>
			   </div>	 		
	 		</form>
	 			<p class="textConnexion">Vous avez déjà un compte ? <a href="connexion">Identifiez-vous</a></p>
	 		
	 			
	 	</section>
	 	
	 	
	 </main>
	 
	 <%@include file="../../fragments/footer.jspf" %>
</body>
</html>