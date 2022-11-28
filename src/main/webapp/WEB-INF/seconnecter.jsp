<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="fr.eni.encheres.messages.LecteurMessage"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Se connecter</title>
</head>
<body>
<%@ include file="Entete.html" %>
<%-- <c:out value="${login}"></c:out> --%>
<%-- <%String login=(String)request.getAttribute("login"); %> --%>
	<form method="POST">
		<c:if test="${!empty listeCodesErreur}">
			  <strong>Erreur!</strong>
			  <ul>
			  	<c:forEach var="code" items="${listeCodesErreur}">
			  		<li>${LecteurMessage.getMessageErreur(code)}</li>
			  	</c:forEach>
			  </ul>
		</c:if>
	
	<label for = "login"  >Identifiant</label>
	<input type="text" id="login" name="login"value="${login}"/>
	<label for="mdp" >mot de passe</label>
	<input type="password" id="mdp" name="mdp"value="${mdp}"/>
	<button type="submit">Connexion</button>
	
	<p>se souvenir de moi<input type="checkbox" name="souvenir" id="souvenir"  value="true" checked="checked"></p>
	
<!-- 	<a href=" ModifMot de passe   ">Mot de passe oublié</a> -->
	
	
	
	</form>
</body>
</html>