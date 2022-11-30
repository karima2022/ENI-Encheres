package fr.eni.encheres.bll;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Retrait;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleManager {
	
	private ArticleDAO articleDAO;
	
	public ArticleManager() {
		this.articleDAO = DAOFactory.getArticleDAO();
	}
	
	
	/**
	 * Ajoute un nouvel article.
	 * @param nom   Nom de l'article.
	 * @param description   Description de l'article.
	 * @param debutEnchere   Date de début de l'enchère de type LocalDateTime.
	 * @param finEnchere   Date de fin de l'enchère de type LocalDateTime.
	 * @param prix   Prix de mise en vente de l'article.
	 * @param categorie   Catégorie de l'article.
	 * @param idVendeur   Identifiant du vendeur.
	 * @param rue   Rue pour le retrait de l'article.
	 * @param codePostal   Code postal pour le retrait de l'article.
	 * @param ville Ville   pour le retrait de l'article.
	 * @throws BusinessException
	 */
	public void ajouterArticle(String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, 
			int prix, int categorie, int idVendeur, String rue, String codePostal, String ville) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerNomArticle(nom, businessException);
		this.validerDescriptionArticle(description, businessException);
		this.validerDateDebutEnchere(debutEnchere, businessException);
		this.validerDateFinEnchere(debutEnchere, finEnchere, businessException);
		this.validerIdVendeur(idVendeur, businessException);
		this.validerRueRetrait(rue, businessException);
		this.validerCPRetrait(codePostal, businessException);
		this.validerVilleRetrait(ville, businessException);
		
		if (!businessException.hasErreurs()) {
			Article article = new Article(nom, description, debutEnchere, finEnchere, prix, categorie, idVendeur);
			Retrait retrait = new Retrait(rue, codePostal, ville);
			article.setRetrait(retrait);
			this.articleDAO.insert(article);
		} else {
			throw businessException;
		}
	}
	
	
	/**
	 * Affiche un article.
	 * @param id Identifiant de l'article.
	 * @return Un objet de type Article correspondant au paramètre id.
	 * @throws BusinessException
	 */
	public Article afficherArticle(int id) throws BusinessException {
		return this.articleDAO.selectById(id);
	}
	
	
	/**
	 * Modifie un article.
	 * 
	 * @param nom   Nom de l'article.
	 * @param description   Description de l'article.
	 * @param debutEnchere   Date de début de l'enchère de type LocalDateTime.
	 * @param finEnchere   Date de fin de l'enchère de type LocalDateTime.
	 * @param prix   Prix de mise en vente de l'article.
	 * @param categorie   Catégorie de l'article.
	 * @param rue   Rue pour le retrait de l'article.
	 * @param codePostal   Code postal pour le retrait de l'article.
	 * @param ville Ville   pour le retrait de l'article.
	 * @throws BusinessException
	 */
	public void modifierArticle(int id, String nom, String description, LocalDateTime debutEnchere, LocalDateTime finEnchere, 
			int prix, int categorie, String rue, String codePostal, String ville) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		
		this.validerNomArticle(nom, businessException);
		this.validerDescriptionArticle(description, businessException);
		this.validerDateDebutEnchere(debutEnchere, businessException);
		this.validerDateFinEnchere(debutEnchere, finEnchere, businessException);
		this.validerRueRetrait(rue, businessException);
		this.validerCPRetrait(codePostal, businessException);
		this.validerVilleRetrait(ville, businessException);
		
		if (!businessException.hasErreurs()) {
			Article article = new Article(nom, description, debutEnchere, finEnchere, prix, categorie);
			article.setNoArticle(id);
			Retrait retrait = new Retrait(id, rue, codePostal, ville);
			article.setRetrait(retrait);
			this.articleDAO.update(article);
		} else {
			throw businessException;
		}
	}
	
	
	/**
	 * Supprime un article.
	 * @param id Identifiant de l'article.
	 * @throws BusinessException
	 */
	public void supprimerArticle(int id) throws BusinessException {
		this.articleDAO.delete(id);
	}
	
	
	/**
	 * Affiche toutes les enchères ouvertes.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> afficherEncheresOuvertes() throws BusinessException {
		return this.articleDAO.selectEncheresOuvertes();
	}
	
	
	/**
	 * Affiche les enchères auxquelles un utilisateur participe.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> afficherMesEncheresEnCours(int id) throws BusinessException {
		return this.articleDAO.selectMesEncheresEnCours(id);
	}
	
	
	/**
	 * Affiche les enchères remportées par un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> afficherMesEncheresRemportees(int id) throws BusinessException {
		return this.articleDAO.selectMesEncheresRemportees(id);
	}
	
	
	/**
	 * Affiche les ventes non commencées d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> afficherMesVentesNonCommencees(int id) throws BusinessException {
		return this.articleDAO.selectMesVentesNonCommencees(id);
	}
	
	
	/**
	 * Affiche les ventes en cours d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> afficherMesVentesEnCours(int id) throws BusinessException {
		return this.articleDAO.selectMesVentesEnCours(id);
	}
	
	
	/**
	 * Affiche les ventes terminées d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> afficherMesVentesTerminees(int id) throws BusinessException {
		return this.articleDAO.selectMesVentesTerminees(id);
	}
	
	
	
	// -- Méthodes de vérification des champs de saisies du formulaire --
	
	private void validerNomArticle(String nom, BusinessException businessException) {
		if (nom == null || nom.trim().length() > 50) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_NOM_ERREUR);
		}
	}
	
	private void validerDescriptionArticle(String description, BusinessException businessException) {
		if (description == null || description.trim().length() > 300) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DESCRIPTION_ERREUR);
		}
	}
	
	private void validerDateDebutEnchere(LocalDateTime debutEnchere, BusinessException businessException) {
		if (debutEnchere.isBefore(LocalDateTime.now())) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_DEBUT_ENCHERE_ERREUR);
		}
	}
	
	private void validerDateFinEnchere(LocalDateTime debutEnchere, LocalDateTime finEnchere, BusinessException businessException) {
		if (finEnchere.isBefore(debutEnchere)) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_DATE_FIN_ENCHERE_ERREUR);
		}
	}
	
	private void validerIdVendeur(int id, BusinessException businessException) {
		if (id == 0) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_ID_VENDEUR_ERREUR);
		}
	}
	
	private void validerRueRetrait(String rue, BusinessException businessException) {
		if (rue == null || rue.trim().length() > 100) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_RUE_ERREUR);
		}
	}
	
	private void validerCPRetrait(String codePostal, BusinessException businessException) {
		if (codePostal == null || codePostal.trim().length() > 10) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_CODE_POSTAL_ERREUR);
		}
	}
	
	private void validerVilleRetrait(String ville, BusinessException businessException) {
		if (ville == null || ville.trim().length() > 50) {
			businessException.ajouterErreur(CodesResultatBLL.REGLE_ARTICLE_VILLE_ERREUR);
		}
	}
	
}
