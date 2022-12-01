package fr.eni.encheres.bll;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
//import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {

	private static UtilisateurDAO utilisateurDAO;
	private static UtilisateurManager instance;
	// private static ArticleDAO articleDAO;

	private UtilisateurManager() {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}

	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

////////////////////////////AJOUTER UTILISATEUR////////////////////////////////////////////
	public void ajouterUtilisateur(Utilisateur u) throws BusinessException {
		BusinessException businessException = new BusinessException();
		this.emailValidator(u.getEmail(), businessException);
		this.verificationPseudo(u.getPseudo(), businessException);
		this.uniciteMail(u.getEmail(), businessException);
		this.unicitePseudo(u.getPseudo(), businessException);
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

	//VERIF si email unique 
	public void uniciteMail(String email, BusinessException businessException) throws BusinessException {
		Utilisateur u = null;
		System.out.println(email);

		u = utilisateurDAO.getUtilisateurByMail(email);
		if ( u!=null) {
		

			businessException.ajouterErreur(CodesResultatBLL.EMAIL_KO);
			
		}

	}

	//VERIF si pseudo unique
	public void unicitePseudo(String pseudo, BusinessException businessException) throws BusinessException {
	
		Utilisateur u = null;
		System.out.println(pseudo);

		u = utilisateurDAO.getUtilisateurByPseudo(pseudo);
		if ( u!=null) {

			businessException.ajouterErreur(CodesResultatBLL.PSEUDO_KO);
			
		}

	}

	////////////////////////// AFFICHER UTILISATEUR BY PSEUDO ////////////////////////// 
	public Utilisateur afficherUtilisateurbyPseudo(String pseudo) throws BusinessException {

		Utilisateur user;
		return user = utilisateurDAO.getUtilisateurByPseudo(pseudo);
	}

	///////////////////////////// SE CONNECTER /////////////////////////////

	public Utilisateur seConnecter(String identifiant, String mdp) throws BusinessException {
		Utilisateur user = null;
		if (identifiant.contains("@")) {
			user = utilisateurDAO.getUtilisateurByMail(identifiant);
		} else
			user = utilisateurDAO.getUtilisateurByPseudo(identifiant);
		if (user == null || !mdp.equals(user.getMotDePasse())) {
			BusinessException be = new BusinessException();
			be.ajouterErreur(CodesResultatBLL.IDENTIFIANT_KO);
			throw be;

		}
		return user;
	}

	/////////////////////////////////// MODIFIER UTILISATEUR /////////////////////////////////// 
	
	public void modifierUtilisateur(Utilisateur u ) throws BusinessException {
		
		BusinessException businessException = new BusinessException();
		this.emailValidator(u.getEmail(), businessException);
		this.verificationPseudo(u.getPseudo(), businessException);
		if (!businessException.hasErreurs()) {
			utilisateurDAO.updateUtilisateur(u.getPseudo());
		} else {
			throw businessException;
		}
	}

/////////////////////////////////// SUPP UTILISATEUR ///////////////////////////////////

	public void supprimerUtilisateur(String pseudo) throws BusinessException {
		//Utilisateur u = null;
		//BusinessException businessException = new BusinessException();
		//{
			utilisateurDAO.deleteUtilisateur(pseudo);
		}
		//{
		//	throw businessException;
		//}
	//}

}
