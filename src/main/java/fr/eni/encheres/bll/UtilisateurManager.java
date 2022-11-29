package fr.eni.encheres.bll;



import java.util.List;

import fr.eni.encheres.bll.CodesResultatBLL;
import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;

import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	
	private static UtilisateurDAO utilisateurDAO;
	 private static UtilisateurManager instance;
	 //private static ArticleDAO articleDAO;
	
	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	public static UtilisateurManager getInstance() {
		if(instance==null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	
	
	/////////////////////////AJOUTER UTILISATEUR////////////////////////////////////////////
	public void ajouterUtilisateur(Utilisateur u) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.emailValidator(u.getEmail(), businessException);
		this.verificationPseudo(u.getPseudo(), businessException);

		if (!businessException.hasErreurs()) {

			utilisateurDAO.insert(u);
		} else {

			throw businessException;
		}
	}
	
	//VERIF presence @ dans le mail 
	public void emailValidator(String email, BusinessException businessException) {
		if (!email.contains("@")) {
			businessException.ajouterErreur(CodesResultatBLL.EMAIL_INVALID);
		}
	}
	
	//VERIF si pseudo alpanumerique
	public void verificationPseudo(String pseudo, BusinessException businessException) {
		char mdp;

		boolean pseudoOk = false;

		for (int i = 0; i < pseudo.length(); i++) {
			mdp = pseudo.charAt(i);
			if (Character.isDigit(mdp)) {
				pseudoOk = true;
			} else if (Character.isLetter(mdp)) {
				pseudoOk = true;
			} else {
				pseudoOk = false;
				break;
			}
		}

		if (!pseudoOk) {
			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_INVALID);
		}
	}
	
////////////////////////SE CONNECTER////////////////////////////////////////////
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
	public Utilisateur modifierProfil(String pseudo) {
		// TODO Auto-generated method stub
		return null;
	}
	public Article afficher() throws BusinessException{
		articleDAO = DAOFactory.getArticleDAO();
		List<Article> articles = articleDAO.findAll();
		return articles.get(0);
	}
	
}}
