<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
         <%@ taglib prefix="c" uri="jakarta.tags.core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>about</title>
<link href="css/style.css" rel="stylesheet" >
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@include file="../../fragments/linksfont.jspf" %>

</head>
<body>
 <%@include file="../../fragments/header.jspf" %> 
<main>
		<div class="intro-about">
				<h1 >On fait connaisance ?</h1>
		</div>
<article class="equipe container">
	<div>
		<h2 class="title-about">Notre équipe</h2>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, facere, voluptate, unde aliquid delectus ratione consectetur omnis quam sapiente aspernatur modi ipsam veniam recusandae est a tempore velit inventore veritatis?</p>
		<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Illum, facere, voluptate, unde aliquid delectus ratione consectetur omnis quam sapiente aspernatur modi ipsam veniam recusandae est a tempore velit inventore veritatis?</p>
	</div>
	<div class="imgEquipe">
		<img src="././assets/img/chef.jpg" alt="equipe" class="height"></img>
		<img src="././assets/img/chef2.jpg" alt="equipe"></img>
	</div>
</article>

<section class="container">
	<h2 class="title-about">Notre concept</h2>
	<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Provident, voluptate maiores  </p>
<div class="concept">
					<ul>
						<li>
							<i class="fa-regular fa-face-smile"></i>
							<h3 class="h3-about">Service rapide</h3>
							<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur tempore sed doloribus animi consectetur at ducimus molestias fuga eum molestiae.</p>
						</li>
							<li>
							<i class="fa-solid fa-spoon"></i>
							<h3 class="h3-about">Dejeuner</h3>
							<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur tempore sed doloribus animi consectetur at ducimus molestias fuga eum molestiae.</p>
						</li>
						<li>
							<i class="fa-regular fa-gem"></i>
							<h3  class="h3-about">Nourriture délicieuse</h3>
							<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur tempore sed doloribus animi consectetur at ducimus molestias fuga eum molestiae. </p>
						</li>
						<li>
							<i class="fa-solid fa-bread-slice"></i>
								<h3  class="h3-about">Petit déjeuner</h3>
							<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur tempore sed doloribus animi consectetur at ducimus molestias fuga eum molestiae. </p>
						</li>
						<li>
								<i class="fa-sharp fa-regular fa-thumbs-up"></i>
								<h3  class="h3-about">Dessert</h3>
							<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur tempore sed doloribus animi consectetur at ducimus molestias fuga eum molestiae. </p>
						</li>
						
						
						<li>
							<i class="fa-solid fa-utensils"></i>
							<h3  class="h3-about">Dinner</h3>
							<p class="p-about">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Pariatur tempore sed doloribus animi consectetur at ducimus molestias fuga eum molestiae. </p>
						</li>
						


					</ul>
				</div>
</section>

<section>
	<h2 class="title-about">Lorem ipsum dolor </h2>
	<article>
		<p class="p-about-video">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Suscipit, totam.</p>
		<video controls poster="././assets/media/poster.png">
			<source src="././assets/media/cooking.mp4" type="video/mp4">
		</video>
	</article>
</section>
</main>
 <%@include file="../../fragments/footer.jspf" %>
</body>
</html>