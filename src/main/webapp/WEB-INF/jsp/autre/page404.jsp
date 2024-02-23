<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>404 - Not found</title>
<link href="css/style.css" rel="stylesheet" >
<%@include file="../../fragments/linksfont.jspf" %>
</head>
<body>
		<section class="error-page">
		  <i class="fa-solid fa-triangle-exclamation"></i>
	      <h1 class="error-number">404</h1>
	      <h2 class="error-text">
	        Oups! La page que vous demandez n'existe pas.
	      </h2>
	      <a href="accueil" class="error-link">
	        Retourner sur la page d’accueil
	      </a>
	    </section>
</body>
</html>