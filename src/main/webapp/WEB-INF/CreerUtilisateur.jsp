<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="fr.eni.encheres.messages.LecteurMessage"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
		<h1>Eni-Encheres</h1>
	</header>
	<form method="post">
		<c:if test="${!empty listeCodesErreur}">
			<div class="alert alert-danger" role="alert">
				<strong>Erreur!</strong>
				<ul>
					<c:forEach var="code" items="${listeCodesErreur}">
						<li>${LecteurMessage.getMessageErreur(code)}</li>
					</c:forEach>
				</ul>
			</div>
		</c:if>
		<c:if test="${empty utilisateurActuel}">
			<label>Pseudo :</label>
			<input type="text" id="pseudo" name="pseudo" value="${pseudo}"
				required>
			<br>
			<label>Prénom :</label>
			<input type="text" id="prenom" name="prenom" required>
			<br>
			<label>Nom :</label>
			<input type="text" id="nom" name="nom" required>
			<br>
			<label>Téléphone :</label>
			<input type="text" id="telephone" name="telephone">
			<br>
			<label>Email :</label>
			<input type="text" id="email" name="email" required>
			<br>
			<label>Mot de passe :</label>
			<input type="password" id="motDePasse" name="motDePasse" required>
			<br>
			<label>Confirmation :</label>
			<input type="password" id="confirmationMdp" name="confirmationMdp"
				required>
			<br>
			<label>Rue :</label>
			<input type="text" id="rue" name="rue" required>
			<br>
			<label>Ville :</label>
			<input type="text" id="ville" name="ville" required>
			<br>
			<label>Code Postal :</label>
			<input type="text" id="codePostale" name="codePostal" required>
			<br>
		</c:if>



		<c:if test="${!empty utilisateurActuel}">
			<label>Pseudo:</label>
			<input type="text" name="pseudo" maxlength="50"
				value="${utilisateurActuel.getPseudo()}" required><<br>
			<label>Prénom :</label>
			<input type="text" id="prenom" name="prenom" maxlength="50"
				value="${utilisateurActuel.getPrenom()}" required>
			<br>
			<label>Nom :</label>
			<input type="text" name="nom" maxlength="50"
				value="${utilisateurActuel.getNom()}" required>
			<br>
			<label>Téléphone :</label>
			<input type="text" name="telephone" maxlength="50"
				value="${utilisateurActuel.getTelephone()}">
			<br>
			<label>Email :</label>
			<input type="text" id="email" name="email" maxlength="50"
				value="${utilisateurActuel.getEmail()}" required>
			<br>
			<label>Rue :</label>
			<input type="text" id="rue" name="rue"
				value="${utilisateurActuel.getRue()}" required>
			<br>
			<label>Ville :</label>
			<input type="text" id="ville" name="ville"
				value="${utilisateurActuel.getVille()}" required>
			<br>
			<label>Code Postal :</label>
			<input type="text" id="codePostale" name="codePostal"
				value="${utilisateurActuel.getCodePostal()}" required>
			<br>
			<label>Mot de passe actuel :</label>
			<input type="password" id="motDePasse" name="motDePasse"
				value="${utilisateurActuel.getMotDePasse()}" required>
			<br>
			<label>Nouveau mot de passe actuel :</label>
			<input type="password" id="motDePasse" name="newMotDePasse">
			<br>
			<label>Confirmation :</label>
			<input type="password" id="confirmationMdp" name="confirmationMdp">
			<br>
			<label>Credit :</label>
			<c:out value="${utilisateurActuel.credit}" /><br>
		</c:if>


		<c:choose>
			<c:when test="${utilisateurActuel == null }">
				<button type="submit" name="profil" value="creer">Créer</button>
				<button type="submit" name="profil" value="annuler">
					<a href="${pageContext.request.contextPath}/accueil">Annuler</a>
				</button>
			</c:when>
		</c:choose>

		<c:choose>
			<c:when test="${utilisateurActuel != null }">
				<button type="submit" name="profil" value="enregistrer">
					<a href="${pageContext.request.contextPath}/accueil">Enregistrer
				</button>
				<button type="submit" name="profil" value="supprimer">Supprimer
					mon compte</button>
			</c:when>
		</c:choose>

	</form>
</body>
</html>