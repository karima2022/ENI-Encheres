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
		<a href="seConnecter">Se connecter</a>
			<a href="CreerUtilisateur">S'inscrire</a>
		</c:if>
	
	<c:if test="${utilisateurActuel != null }">
		<a href="">Enchères</a>
		<a href="nouvelle_vente">Vendre un article</a>
		<a href="">Mon profil</a>
		<a href="${pageContext.request.contextPath }/deconnexion" title="deconnexion">Déconnexion</a>
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
		<input type ="radio" name="achatVente" id="achats-RB" checked>Achats
	<div>
		<input type="checkbox" name="achat" checked>enchères ouvertes
	</div>
	<div>
		<input type="checkbox" name="achat">mes enchères en cours
	</div>
	<div>
		<input type="checkbox" name="achat">mes enchères remportées
	</div>
</a>
	
	<a>
		<input type="radio" name="achatVente" id="ventes-RB">Mes ventes
	<div>
		<input type="checkbox" name="vente" disabled>mes ventes en cours
	</div>
	<div>
		<input type="checkbox" name="vente" disabled>ventes non débutées
	</div>
	<div>
		<input type="checkbox" name="vente" disabled>ventes terminées
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
					<li><c:choose>
							<c:when test="${utilisateurActuel != null }">
		Article:<a href="${a.getNom()}"><c:out value="${a.getNom()}" /> </a>
							</c:when>
							<c:otherwise>Article : ${a.getNom()} </c:otherwise>
						</c:choose>
					<li>prix :<c:out value="${a.getPrix() }" /> points
					</li>
					<li>Fin de l'enchere : <fmt:parseDate
							value="${a.getFinEnchere()}" pattern="yyyy-MM-dd'T'HH:mm"
							var="parsedDateTime" type="both" /> <fmt:formatDate
							pattern="dd.MM.yyyy HH:mm" value="${parsedDateTime}" /></li>
					<li><c:choose>
							<c:when test="${utilisateurActuel != null }">
		vendeur:<a href="${pseudo}"> ${a.getPseudoVendeur()} </a>
							</c:when>
							<c:otherwise>Vendeur : ${a.getPseudoVendeur()} </c:otherwise>
						</c:choose></li>
				</ul>
</article>
	

</c:forEach>
</section>

<script>
	const achatsRB = document.getElementById("achats-RB");
	const ventesRB = document.getElementById("ventes-RB");
	const encheres = document.getElementsByName("achat");
	const ventes = document.getElementsByName("vente");
	
	achatsRB.addEventListener("change", (event) => {
	  ventesRB.disabled = !event.target.checked;
	  ventes.forEach(element => {
	    element.checked = false;
	    element.disabled = true;
	  });
	  encheres[0].checked = true;
	  encheres.forEach(element => element.disabled = false);
	}, false);
	
	ventesRB.addEventListener("change", (event) => {
	  achatsRB.disabled = !event.target.checked;
	  encheres.forEach(element => {
	    element.checked = false;
	    element.disabled = true;
	  });
	  ventes[0].checked = true;
	  ventes.forEach(element => element.disabled = false);
	}, false);
</script>

</body>
</html>