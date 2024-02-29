<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> ACCUEIL</title>
    <link rel="stylesheet" type="text/css" href="css/slick.css" />
	<link rel="stylesheet" type="text/css" href="css/slick-theme.css" />
       <link href="././css/style.css" rel="stylesheet" >

	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	<script type="text/javascript" src="././js/slick.min.js" defer></script>
    <script type="text/javascript" src="././js/slick.js" defer></script>
	   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="icon" href="data:;base64,iVBORw0KGgo="> 
	<%@include file="../fragments/linksfont.jspf" %>
</head>

<body>

<%@ include file="/WEB-INF/fragments/header.jspf" %>

<main class="main-accueil">
 
    <section class="carousel_accueil" class="carousel-container full-screen">
        <div class="container">

            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                Indicators
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                Wrapper for slides
                <div class="carousel-inner">

                    <div class="item active">
                        <img src="./assets/img/carousel1.jpg" alt="pate d'or 1" style="width:100%;">
                        <div class="carousel-caption">
                           
                        </div>
                    </div>

                    <div class="item">
                        <img src="./assets/img/carousel2.jpg" alt="pate d'or 2" style="width:100%;">
                        <div class="carousel-caption">
                           
                        </div>
                    </div>

                    <div class="item">
                        <img src="./assets/img/carousel3.jpg" alt="pate d'or 3" style="width:100%;">
                        <div class="carousel-caption">
                           
                        </div>
                    </div>

                </div>

                Left and right controls
                <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#myCarousel" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </section> 
    
 <!--    <section class="container">
    		<div class="slider">
        <div> <img src="./assets/img/slider-01.jpg" alt="pate d'or 1" ></div>
        <div> <img src="./assets/img/slider-02.jpg" alt="pate d'or 1" ></div>
        <div> <img src="./assets/img/slider-03.jpg" alt="pate d'or 3" ></div>
    </div> 
    </section> -->

<section class="accueil_nous">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        
                        <p>
                        <b id="pate-or-accueil">LA PATE D'OR </b>
                         , c'est avant tout une histoire de partage.  Dans un décor moderne, lumineux et chaleureux, Vous pourrez,
                          découvrir l'identité et la créativité de notre <b>chef Etienne Cassin</b> et son équipe à travers des réalisations à base de produits <b>raffinés</b> 
                          , de <b>saison</b> et de <b>producteurs locaux</b>.
                         </p>
                    </div>
                </div>
            </div>
            
           
        </div>
    </div>
</section>

    <section class="nos_coups_coeur">
            <h2 id="nos_coups_coeur-titre">Nos Coups de Cœur à Découvrir </h2>
            <p class="intro-p-accueil">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Hic fuga sit illo modi aut aspernatur tempore laboriosam saepe dolores eveniet.</p>
            
            <div class="menu-flex">
               
                <div>
                    <img src="./assets/img/1.jpg" alt="slider1">
                    <h3 class="h3-menu">Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                <div>
                    <img src="./assets/img/2.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                <div>
                    <img src="./assets/img/3.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                <div>
                    <img src="./assets/img/4.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
   
    </section>


    <section class="nos_restaurants">
        <h2 id="nos-restaurants-titre-accueil">Decouvrir nos restaurants</h2>
        <div class="nous-decouvrir">
            <c:forEach var="restaurant" items="${restaurants}">
                <div class="nous-decouvrir-card">

                    <h3><c:out value="${restaurant.nom}" /></h3>

                    <img src="./assets/img/restaurant.jpg" alt="Description of the image" class="decouvrir-img">

                    <form action="ServletReservation" method="get">
                        <input type="hidden" name="idRestaurant" value="${restaurant.id}">
                        <input type="hidden" name="idUtilisateur" value="${idUtilisateur}">
                         <c:if test="${not empty identifiant }">
									<a href="reservation?id=${restaurant.id}"><button type="button" id="btn-reserver-accueil" >Réserver</button></a>
								</c:if>
						<c:if test="${empty identifiant }">
								      <a href="connexion"><button type="button" id="btn-reserver-accueil" >Réserver</button></a>
						</c:if> 
                        
                    </form>

                </div>
            </c:forEach>
        </div>
    </section>

   <br>

</main>
	<%@include file="../fragments/footer.jspf" %>
		
</body>
</html>
