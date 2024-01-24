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
	<h1>Connexion</h1>
	<form action="connexion" method="POST">
		<div>
			<label>Email</label>
			<input type="email" placeholder="Votre email" name="identifiant"/>
		</div>
		<div>
			<label>Mot de passe</label>
			<input type="password" placeholder="Votre mot de passe" name="motDePasse"/>
		</div>
		 <div>
			  	<input type="submit" value="Enregistrer"/>
	  	</div>	
	</form>
	<div>
	 			<c:forEach var="current" items="${erreurs }">
	 				<p>${current }</p>
	 			</c:forEach>
	</div>
	 
	 <%@include file="../../fragments/footer.jspf" %>
</body>
</html>