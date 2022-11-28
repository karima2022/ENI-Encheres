<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acceuil</title>
</head>
<body>
	<%@ include file="Entete.html"%>

	<a href="seConnecter">S'inscrire-Se connecter</a>

	<h1>Liste des enchères</h1>

	<h3>Filtres</h3>
	

	<input type="search" placeholder="Le nom de l'article contient">

	<p>
		<label for="Categorie"> Catégorie :</label> <select name="Categorie">
			<option value="toutes">Toutes</option>
			<option value="info">Informatique</option>
			<option value="meuble">Ameublement</option>
			<option value="vetement">Vêtement</option>
			<option value="sport">Sport&Loisirs</option>
		</select>
	</p>
	<button name="rechercher">Rechercher</button><br>

<c:if test="${login != null }">
	<a>
		<input type ="radio" name="achatVente">Achats
	<div>
		<input type="checkbox">enchères ouvertes
	</div>
	<div>
		<input type="checkbox">mes enchères en cours
	</div>
	<div>
		<input type="checkbox">mes enchères remportées
	</div>
</a>
	
	<a>
		<input type="radio" name="achatVente">Mes ventes
	<div>
		<input type="checkbox">mes ventes en cours
	</div>
	<div>
		<input type="checkbox">ventes non débutées
	</div>
	<div>
		<input type="checkbox">ventes terminées
	</div>
</a>
</c:if>

<ul >
	<li><a href=""><c:out value="${nomArticle}"/></a></li>
	<li>prix :<c:out value="${prix }"/> points</li>
	<li>Fin de l'enchere : <c:out value="${finEnchere}"/></li>
	<li>
	<c:choose >
		<c:when test="${login != null }">
		<a href="${idVendeur}">vendeur: ${idVendeur} </a></c:when>
		<c:otherwise>Vendeur : ${idVendeur} </c:otherwise>
	</c:choose>
</li>
</ul>








</body>
</html>