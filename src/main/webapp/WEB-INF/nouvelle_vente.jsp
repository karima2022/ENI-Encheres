<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ page import="java.time.LocalDateTime" %> --%>
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
		
		<form action="" method="post">
			<label for="nom-input">Article : </label>
			<input type="text" name="nom" id="nom-input" maxlength="50" required>
			<br>
			<br>
			
			<label for="categorie-select">Catégorie : </label>
			<select name="categorie" id="categorie-select">
				<option value="1">Informatique</option>
				<option value="2">Ameublement</option>
				<option value="3">Vêtement</option>
				<option value="4">Sport&Loisirs</option>
			</select>
			<br>
			<br>
			
			<label for="description-textarea">Description : </label>
			<textarea rows="" cols="" name="description" id="description-textarea" maxlength="300" required></textarea>
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
			<input type="number" name="prix" id="prix-input" value="111" min="107" required>
			<br>
			<br>
			
			<label for="debutenchere-datetime">Début de l'enchère : </label>
			<input type="datetime-local" name="debutEnchere" id="debutenchere-datetime" 
					min='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
					max=""
					value='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
					required
			>
			<br>
			<br>
			
			
<!-- 			<p> -->
<%-- 				<fmt:formatDate value="${LocalDateTime.now()}" pattern="yyyy-MM-dd hh:mm"/> --%>
<%-- 				<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/> --%>
<!-- 			</p> -->
			
			
			<label for="finenchere-datetime">Fin de l'enchère : </label>
			<input type="datetime-local" name="finEnchere" id="finenchere-datetime"
					min='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
					max=""
					value='<fmt:formatDate value="${GregorianCalendar.getInstance().getTime()}" pattern="yyyy-MM-dd HH:mm"/>'
					required
			>
			<br>
			<br>
			
			<fieldset>
				<legend>Retrait</legend>
				
				<label for="rue-input">Rue : </label>
				<input type="text" name="rue" id="rue-input" maxlength="100" required>
				<br>
				
				<label for="codepostal-input">Code postal : </label>
				<input type="text" name="codePostal" id="codepostal-input" maxlength="10" required>
				<br>
				
				<label for="ville-input">Ville : </label>
				<input type="text" name="ville" id="ville-input" maxlength="50" required>
			</fieldset>
			<br>
			
			<button type="submit" value="enregistrer" name="btnEnregistrer">Enregistrer</button>
			<button type="submit" value="annuler" name="btnAnnuler">Annuler</button>
			<button type="submit" value="supprimer" name="btnSupprimer">Annuler la vente</button>
		</form>
	</body>
</html>