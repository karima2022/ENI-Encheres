<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Accueil</title>
</head>
<body>
	<%@ include file="Entete.html"%>

	
		<c:if test="${utilisateurActuel == null }">
		<a href="seConnecter">S'inscrire-Se connecter</a>
		</c:if>
	
	<c:if test="${utilisateurActuel != null }">
		<a href="">Enchères</a>
		<a href="">Vendre un article</a>
		<a href="">Mon profil</a>
		<a href="">Déconnexion</a>
</c:if>

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

<c:if test="${utilisateurActuel != null }">
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
<section>
<c:forEach var="a" items="${ListeArticle}"> 
<article>
<ul >
<c:if test="${ListeArticle==null}">
<p>liste non vide</p>
</c:if>
<li><c:choose >
		<c:when test="${utilisateurActuel != null }">
		Article:<a href="${a.getNom()}"><c:out value= "${a.getNom()}"/> </a></c:when> 
		<c:otherwise>Article : ${a.getNom()} </c:otherwise>
	</c:choose>
	<li>prix :<c:out value="${a.getPrix() }"/> points</li>

	<li>
	<c:choose >
		<c:when test="${utilisateurActuel != null }">
		vendeur:<a href="${pseudo}"> ${pseudo} </a></c:when> 
		<c:otherwise>Vendeur : ${pseudo} </c:otherwise>
	</c:choose>
</li>
</ul>
</article>
	

</c:forEach>
</section>









</body>
</html>