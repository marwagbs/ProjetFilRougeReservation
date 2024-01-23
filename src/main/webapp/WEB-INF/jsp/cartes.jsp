<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="jakarta.tags.core" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>la carte de notre restaurant</title>
</head>
<body>

		 
	 <%@include file="../fragments/header.jspf" %> 
	 <main>
	 
	 	<section class="menu"> 
	 		<div class="menu_img">
                <div class="image-wrapper">
                    <img src="./assets/images/restaurants/palette-du-gout.jpg" alt="picture restaurant palette du goutr">
                </div>
                 <h1 class="cursive-title">${nomCarte }   </h1>
            </div>
 			<div class="container">
 			<div>
 				<div class="section--menu__header">
                <h3 class="section-title section-title--small">Entrée</h3>
            </div>
 			<c:forEach var="current" items="${listeEntrees }"> 
 				
 				<p>${current.nom }</p>
 				<p>${current.description }</p>
 				<p>${current.prix }</p>
 				
 			</c:forEach>
 			
 			</div>
 			
 			<div>
 				<div class="section--menu__header">
                <h3 class="section-title section-title--small">Plats</h3>
            </div>
 			<c:forEach var="current" items="${listePlats }"> 
 				
 				<p>${current.nom }</p>
 				<p>${current.description }</p>
 				<p>${current.prix }</p>
 				
 			</c:forEach>
 			
 			</div>
 			
 			<div>
 				<div class="section--menu__header">
                <h3 class="section-title section-title--small">Boissons</h3>
            </div>
 			<c:forEach var="current" items="${listeBoissons }"> 
 				
 				<p>${current.nom }</p>
 				<p>${current.description }</p>
 				<p>${current.prix }</p>
 				
 			</c:forEach>
 			
 			</div>
 		<div>
 				<div class="section--menu__header">
                	<h3 class="section-title section-title--small">Dessert</h3>
            </div>
 			<c:forEach var="current" items="${listeDesserts }"> 
 				
 				<p>${current.nom }</p>
 				<p>${current.description }</p>
 				<p>${current.prix }</p>
 				
 			</c:forEach>
 			
 			</div>
 			</div>
	 	</section>

	 
	</main>
	 
	
	<%@include file="../fragments/footer.jspf" %> 	
			 

	<h1>Notre carte</h1>

</body>
</html>