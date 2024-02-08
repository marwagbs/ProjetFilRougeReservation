<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title> ACCUEIL</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="./css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="icon" href="data:;base64,iVBORw0KGgo=">
</head>

<body>

<%@ include file="/WEB-INF/fragments/header.jspf" %>

<main>

    <section class="carousel_accueil" class="carousel-container full-screen">
        <div class="container">

            <div id="myCarousel" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                    <li data-target="#myCarousel" data-slide-to="1"></li>
                    <li data-target="#myCarousel" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner">

                    <div class="item active">
                        <img src="./assets/img/carousel1.jpg" alt="Los Angeles" style="width:100%;">
                        <div class="carousel-caption">
                            <h3>Los Angeles</h3>
                            <p>LA is always so much fun!</p>
                        </div>
                    </div>

                    <div class="item">
                        <img src="./assets/img/carousel2.jpg" alt="Chicago" style="width:100%;">
                        <div class="carousel-caption">
                            <h3>Chicago</h3>
                            <p>Thank you, Chicago!</p>
                        </div>
                    </div>

                    <div class="item">
                        <img src="./assets/img/carousel3.jpg" alt="New York" style="width:100%;">
                        <div class="carousel-caption">
                            <h3>New York</h3>
                            <p>We love the Big Apple!</p>
                        </div>
                    </div>

                </div>

                <!-- Left and right controls -->
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

<section class="accueil_nous">
    <div class="container">
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h2>LA PATE D'OR</h2>
                        <p>Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                            when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting,
                            remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software
                            like Aldus PageMaker including versions of Lorem Ipsum.</p>
                    </div>
                </div>
            </div>
            
           
        </div>
    </div>
</section>

    <section class="nos_coups_coeur">
            <h2 class="title">Nos Coups de Cœur à Découvrir </h2>
            <p class="intro-p">Lorem ipsum dolor sit amet consectetur, adipisicing elit. Hic fuga sit illo modi aut aspernatur tempore laboriosam saepe dolores eveniet.</p>
            <div class="menu-flex">
               
                <div>
                    <img src="./assets/img/b1.jpg" alt="slider1">
                    <h3 class="h3-menu">Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                <div>
                    <img src="./assets/img/b2.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                <div>
                    <img src="./assets/img/b3.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                <div>
                    <img src="./assets/img/b4.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                 <div>
                    <img src="./assets/img/b5.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                 <div>
                    <img src="./assets/img/b6.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                  <div>
                    <img src="./assets/img/b7.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
                 <div>
                    <img src="./assets/img/b7.jpg" alt="slider1">
                    <h3>Lorem ipsum dolor sit amet consectetur</h3>
                    <p class="p-menu">Lorem ipsum dolor sit amet consectetur adipisicing elit. Eveniet itaque labor.</p>
                </div>
            </div>
           
    </section>


    <section class="nos_restaurants">
        <h2>Decouvrir nos restaurants</h2>
        <div class="nous-decouvrir">
            <c:forEach var="restaurant" items="${restaurants}">
                <div class="nous-decouvrir-card">

                    <h3><c:out value="${restaurant.nom}" /></h3>

                    <img src="./assets/img/restaurant.jpg" alt="Description of the image" class="decouvrir-img">

                    <form action="ServletReservation" method="get">
                        <input type="hidden" name="idRestaurant" value="${restaurant.id}">
                        <input type="hidden" name="idUtilisateur" value="${idUtilisateur}">
                        <button type="submit">Reserver</button>
                    </form>

                </div>
            </c:forEach>
        </div>
    </section>

    <div class="temoignages">
        <h2> Temoignages </h2>
    </div>

</main>
<%@ include file="/WEB-INF/fragments/footer.jspf" %>
</body>
</html>
