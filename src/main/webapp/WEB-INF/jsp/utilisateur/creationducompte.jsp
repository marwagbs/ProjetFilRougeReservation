<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="../../../../css/style.css" rel="stylesheet" >
</head>
<body>
	 <%@include file="../../fragments/header.jspf" %> 
	 
	 <main>
	 	<h1>Création du compte</h1>
	 	<section>
	 		<form action="/creationducompte" method="POST">
				<div>
				<label>Nom</label>
				<input type="text" placeholder="Votre nom" name="nom"/>
				</div>
				<div>
				<label>Prénom</label>
				<input type="text" placeholder="Votre prenom" name="prenom"/>
				</div>
				<div>
				<label>Numéro de téléphone</label>
				<input type="tel" placeholder="Votre numéro de téléphone" name="numTel"/>
				
				</div>
				<div>
				<label>Email</label>
				<input type="email" placeholder="Votre email" name="email"/>
				</div>
				<div>
				<label>Mot de passe</label>
				<input type="password" placeholder="Votre mot de passe" name="motDePasse"/>
				</div>
				 <div>
			    <input type="checkbox" id="scales" name="rgbd"  />
			    <label for="checkbox">En soumettant ce formulaire, j’accepte que les informations saisies soient exploitées dans le cadre de la relation commerciale ou communicationnelle qui peut en découler. *</label>
			  </div>
			  <div>
			  	<input type="submit" value="Enregistrer"/>
			  </div>	 		
	 		</form>
	 	
	 	</section>
	 	
	 	
	 </main>
	 
	 <%@include file="../../fragments/footer.jspf" %>
</body>
</html>