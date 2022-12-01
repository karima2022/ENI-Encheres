<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="fr.eni.encheres.messages.LecteurMessage" %>
<%@ page import="java.util.GregorianCalendar" %>    
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Nouvelle vente - ENI-Enchères</title>
	</head>
	
	<body>
		<h1>ENI-Enchères</h1>
		
		<h2>Nouvelle vente</h2>
		
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
		
		<form action="" method="post">
			<c:if test="${!empty utilisateurActuel}">
				<input type="hidden" name="idVendeur" value="${utilisateurActuel.getNoUtilisateur()}"/>
			</c:if>
			<label for="nom-input">Article : </label>
			
			<c:if test="${!empty objArticle}">
				<input type="text" name="nom" id="nom-input" maxlength="50" value="${objArticle.getNom()}" required>
			</c:if>
			
			<c:if test="${empty objArticle}">
				<input type="text" name="nom" id="nom-input" maxlength="50" required>
			</c:if>
			<br>
			<br>
			
			<label for="categorie-select">Catégorie : </label>
			<select name="categorie" id="categorie-select">
				<c:if test="${!empty objArticle}">
					<c:if test="${objArticle.getCategorie() == 1}">
						<option value="1" selected>Informatique</option>
					</c:if>	
					<c:if test="${objArticle.getCategorie() != 1}">
						<option value="1" >Informatique</option>
					</c:if>	
						
					<c:if test="${objArticle.getCategorie() == 2}">	
						<option value="2" selected>Ameublement</option>
					</c:if>	
					<c:if test="${objArticle.getCategorie() != 2}">	
						<option value="2">Ameublement</option>
					</c:if>	
					
					<c:if test="${objArticle.getCategorie() == 3}">	
						<option value="3" selected>Vêtement</option>
					</c:if>	
					<c:if test="${objArticle.getCategorie() != 3}">	
						<option value="3">Vêtement</option>
					</c:if>	
					
					<c:if test="${objArticle.getCategorie() == 4}">	
						<option value="4" selected>Sport&Loisirs</option>
					</c:if>	
					<c:if test="${objArticle.getCategorie() != 4}">	
						<option value="4">Sport&Loisirs</option>
					</c:if>	
				</c:if>
				<c:if test="${empty objArticle}">
					<option value="1" selected>Informatique</option>
					<option value="2">Ameublement</option>
					<option value="3">Vêtement</option>
					<option value="4">Sport&Loisirs</option>
				</c:if>
			</select>
			<br>
			<br>
			
			<label for="description-textarea">Description : </label>
			
			<c:if test="${!empty objArticle}">
				<textarea name="description" id="description-textarea" maxlength="300" required>${objArticle.getDescription()}</textarea>
			</c:if>
			<c:if test="${empty objArticle}">
				<textarea name="description" id="description-textarea" maxlength="300" required></textarea>
			</c:if>
			<br>
			<br>
			
			<label>Photo de l'article : </label>
			<label for="upload-input" style="cursor: pointer; border: 1px solid black">UPLOADER</label>
			<input type="file" id="upload-input" hidden>
			<br>
			<img alt="" src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXerdzqdNqu4QNvGsjqgNrbfHNMdEfaAMSFA&usqp=CAU"
				name="image"
				width="100"
				height="100"
				style="border: 1px solid black"
			>
			<br>
			<br>
			
			<label for="prix-input">Mise à prix : </label>
			<c:if test="${!empty objArticle}">
				<input type="number" name="prix" id="prix-input" value="${objArticle.getPrix()}" required>
			</c:if>
			<c:if test="${empty objArticle}">
				<input type="number" name="prix" id="prix-input" value="0" required>
			</c:if>
			<br>
			<br>
			
			<label for="debutenchere-datetime">Début de l'enchère : </label>
			<c:if test="${!empty objArticle}">	
				<input type="datetime-local" name="debutEnchere" id="debutenchere-datetime" 
						min=""
						max=""
						value="${objArticle.getDebutEnchere()}"
						required
				>
			</c:if>
			<c:if test="${empty objArticle}">	
				<input type="datetime-local" name="debutEnchere" id="debutenchere-datetime" 
						min='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
						max=""
						value='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
						required
				>
			</c:if>
			<br>
			<br>
			
			<label for="finenchere-datetime">Fin de l'enchère : </label>
			<c:if test="${!empty objArticle}">
				<input type="datetime-local" name="finEnchere" id="finenchere-datetime"
						min=""
						max=""
						value="${objArticle.getFinEnchere()}"
						required
				>
			</c:if>
			<c:if test="${empty objArticle}">
				<input type="datetime-local" name="finEnchere" id="finenchere-datetime"
						min='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
						max=""
						value='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
						required
				>
			</c:if>
			<br>
			<br>
			
			<fieldset>
				<legend>Retrait</legend>
				
				<label for="rue-input">Rue : </label>
				<c:if test="${!empty utilisateurActuel}">
					<c:if test="${!empty objArticle}">
						<input type="text" name="rue" id="rue-input" maxlength="100" value="${objArticle.getRetrait().getRue()}" required>
					</c:if>
					<c:if test="${empty objArticle}">
						<input type="text" name="rue" id="rue-input" maxlength="100" value="${utilisateurActuel.getRue()}" required>
					</c:if>
				</c:if>
				<c:if test="${empty utilisateurActuel}">
					<input type="text" name="rue" id="rue-input" maxlength="100" required>
				</c:if>
				<br>
				
				<label for="codepostal-input">Code postal : </label>
				<c:if test="${!empty utilisateurActuel}">
					<c:if test="${!empty objArticle}">
						<input type="text" name="codePostal" id="codepostal-input" maxlength="10" value="${objArticle.getRetrait().getCodePostal()}" required>
					</c:if>
					<c:if test="${empty objArticle}">
						<input type="text" name="codePostal" id="codepostal-input" maxlength="10" value="${utilisateurActuel.getCodePostal()}" required>
					</c:if>
				</c:if>
				<c:if test="${empty utilisateurActuel}">
					<input type="text" name="codePostal" id="codepostal-input" maxlength="10" required>
				</c:if>
				<br>
				
				<label for="ville-input">Ville : </label>
				<c:if test="${!empty utilisateurActuel}">
					<c:if test="${!empty objArticle}">
						<input type="text" name="ville" id="ville-input" maxlength="50" value="${objArticle.getRetrait().getVille()}" required>
					</c:if>
					<c:if test="${empty objArticle}">
						<input type="text" name="ville" id="ville-input" maxlength="50" value="${utilisateurActuel.getVille()}" required>
					</c:if>
				</c:if>
				<c:if test="${empty utilisateurActuel}">
					<input type="text" name="ville" id="ville-input" maxlength="50" required>
				</c:if>
			</fieldset>
			<br>
			
			<button type="submit" value="enregistrer" name="btnSubmit">Enregistrer</button>
			<button type="submit" value="annuler" name="btnSubmit" formnovalidate="formnovalidate" >Annuler</button>
			
			<c:if test="${!empty objArticle}">
				<button type="submit" value="supprimer" name="btnSubmit">Annuler la vente</button>
			</c:if>
		</form>
	</body>
</html>