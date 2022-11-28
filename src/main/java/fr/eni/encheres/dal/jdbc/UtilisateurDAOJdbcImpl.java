package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurDAOJdbcImpl implements UtilisateurDAO {

	private static final String SELECT_USER_BY_PSEUDO = "SELECT  idUtilisateur,pseudo,"
			+ "  nom, prenom, email,telephone, rue, codePostal, ville, "
			+ "motDePasse,credit,administrateur FROM UTILISATEURS WHERE pseudo = ?;";
	private static final String SELECT_USER_BY_MAIL = "SELECT  idUtilisateur,pseudo,"
			+ "  nom, prenom, email,telephone, rue, codePostal, ville, "
			+ "motDePasse,credit,administrateur FROM UTILISATEURS WHERE email = ?;";

	
	private Utilisateur getUtilisateurByLogin(String login,String requete) throws BusinessException {
		Utilisateur u = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(requete)){
			psmt.setString(1, login);
			try(ResultSet rs = psmt.executeQuery()) {
				if(rs.next()) {
					u= new Utilisateur(rs.getInt("idUtilisateur"),
							rs.getString("pseudo"),
							rs.getString("nom"),
							rs.getString("prenom"),
							rs.getString("email"),
							rs.getString("telephone"),
							rs.getString("rue"),
							rs.getString("codePostal"),
							rs.getString("ville"),
							rs.getString("motDePasse"),
							rs.getInt("credit"),
							rs.getInt("administrateur"));
				}
			
			}} catch (SQLException e) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return u;
	}

@Override
public Utilisateur getUtilisateurByMail(String mail) throws BusinessException {
	
	return  getUtilisateurByLogin(mail, SELECT_USER_BY_MAIL);
}
@Override
public Utilisateur getUtilisateurByPseudo(String pseudo) throws BusinessException {
	return getUtilisateurByLogin(pseudo, SELECT_USER_BY_PSEUDO);
}
@Override
public void insert(Utilisateur utilisateur) throws BusinessException {
	// TODO Auto-generated method stub
	
}
@Override
public void modifierProfil(Utilisateur utilisateur) throws BusinessException {
	// TODO Auto-generated method stub
	
}

	

}
