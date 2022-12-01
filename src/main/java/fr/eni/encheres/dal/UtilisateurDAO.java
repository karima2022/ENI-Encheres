package fr.eni.encheres.dal;



import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;

public interface UtilisateurDAO {
	public  Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException;
	public  Utilisateur getUtilisateurByMail(String mail) throws BusinessException;

	public void insert(Utilisateur utilisateur) throws  BusinessException;
	public void updateUtilisateur(String pseudo)throws  BusinessException;
	
	
	public Utilisateur selectByPseudo(String pseudo) throws BusinessException;
	public void deleteUtilisateur(String pseudo) throws BusinessException;
}