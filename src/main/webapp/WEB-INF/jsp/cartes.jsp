<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>la carte de notre restaurant</title>
    <link href="././css/style.css" rel="stylesheet" />
    <%@include file="../fragments/linksfont.jspf" %>
 
  </head>

  <body>
    <%@include file="../fragments/header.jspf" %>
    <main>
    	<div class="intro">
				<h1>Notre carte</h1>
				<p class="intro-p">Passionnément, à la folie… </p>
		</div>
  
      <section class="menu container">
        <div class="menu_header">
          <h2 class="cursive-title">${nomRestaurant }</h2>
          <h3 class="cursive-carte">${nomCarte }</h3>
        </div>
		<section class="submenus">
       
          <section class="submenu">
            <h4 class="submenu_title">Entrées</h4>
            <c:forEach var="current" items="${listeEntrees }">
             <article class="menuItem">
              <div class="menuItem_header">
                <h4 class="menuItem_title">${current.nom }</h4>
                
             <p class="menuItem_description">${current.description }</p>
              </div>
              <p class="menuItem_price"> ${current.prix }€
              </p>
             </article>
            </c:forEach>
        
          </section>
          <section class="submenu">
            <h4 class="submenu_title">Plats</h4>
          
          
            <c:forEach var="current" items="${listePlats }">
            <article class="menuItem">
              <div class="menuItem_header">
                <h4 class="menuItem_title">${current.nom }</h4>
                <p class="menuItem_description">${current.description }</p>
              </div>
              <div class="menuItem_price">${current.prix }€</div>
              </article>
            </c:forEach>
        
          </section>

          <section class="submenu">
            <h4 class="submenu_title">Desserts</h4>
          
        
            <c:forEach var="current" items="${listeDesserts }">
              <article class="menuItem">
              <div class="menuItem_header">
                <h4 class="menuItem_title">${current.nom }</h4>
                <p class="menuItem_description">${current.description }</p>
              </div>
              <div class="menuItem_price">${current.prix }€</div>
               </article>
            </c:forEach>
         
          </section>
          <section class="submenu">
            <h4 class="submenu_title">Boissons</h4>
          
        
            <c:forEach var="current" items="${listeBoissons }">
              <article class="menuItem">
              <div class="menuItem_header">
                <h4 class="menuItem_title">${current.nom }</h4>
                <p class="menuItem_description">${current.description }</p>
              </div>
              <div class="menuItem_price">
              		<p>${current.prix }€</p></div>
               </article>
            </c:forEach>
         
          </section>
    	</section>
         </section>
       
    </main>
    <%@include file="../fragments/footer.jspf" %>
  </body>
</html>
