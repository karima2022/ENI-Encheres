package fr.eni.encheres.bll;



import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private static UtilisateurDAO utilisateurDAO;
	 private static UtilisateurManager instance;
	 private static ArticleDAO articleDAO;
	
	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	public static UtilisateurManager getInstance() {
		if(instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	public Utilisateur  seConnecter(String identifiant, String mdp) throws BusinessException {
		Utilisateur user =null;
		if(identifiant.contains("@")) 
		{
			user=utilisateurDAO.getUtilisateurByMail(identifiant);
		}
		else user = utilisateurDAO.getUtilisateurByPseudo(identifiant);
		if (user==null || !mdp.equals(user.getMotDePasse())) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.IDENTIFIANT_KO);
			throw be;

		}
		return user;
	}
//	public Utilisateur modifierProfil(String pseudo) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	public Article afficher() throws BusinessException{
//		articleDAO = DAOFactory.getArticleDAO();
//		List<Article> articles = articleDAO.findAll();
//		return articles.get(0);
//	}
	
}
