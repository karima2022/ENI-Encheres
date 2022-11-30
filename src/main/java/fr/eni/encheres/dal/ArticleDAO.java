package fr.eni.encheres.dal;

import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;

public interface ArticleDAO {
	
	/**
	 * Ajoute un nouvel article.
	 * @param article Objet article à ajouter.
	 * @throws BusinessException
	 */
	public void insert(Article article) throws BusinessException;
	
	
	/**
	 * Affiche un article.
	 * @param id Identifiant de l'article.
	 * @return Un objet de type Article correspondant au paramètre id.
	 * @throws BusinessException
	 */
	public Article selectById(int id) throws BusinessException;
	
	
	/**
	 * Modifie un article.
	 * @param article Objet Article à modifier.
	 * @throws BusinessException
	 */
	public void update(Article article) throws BusinessException;
	
	
	/**
	 * Supprime un article.
	 * @param id Identifiant de l'article.
	 * @throws BusinessException
	 */
	public void delete(int id) throws BusinessException;
	
	
//	/**
//	 * Affiche toutes les enchères qui n'ont pas encore commencées.
//	 * @return
//	 * @throws BusinessException
//	 */
//	public List<Article> selectEncheresNonCommencees() throws BusinessException;
	
	
	/**
	 * Affiche toutes les enchères ouvertes.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> selectEncheresOuvertes() throws BusinessException;
	
	
	/**
	 * Affiche les enchères auxquelles un utilisateur participe.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> selectMesEncheresEnCours(int id) throws BusinessException;
	
	
	/**
	 * Affiche les enchères remportées par un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> selectMesEncheresRemportees(int id) throws BusinessException;
	
	
	/**
	 * Affiche les ventes non commencées d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> selectMesVentesNonCommencees(int id) throws BusinessException;
	
	
	/**
	 * Affiche les ventes en cours d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> selectMesVentesEnCours(int id) throws BusinessException;
	
	
	/**
	 * Affiche les ventes terminées d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	public List<Article> selectMesVentesTerminees(int id) throws BusinessException;
	
}
