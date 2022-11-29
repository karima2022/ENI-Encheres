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

	////////////////////////////////LOGIN////////////////////////////////////////////////////
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
	
	
////////////////////////////////INSERT UTILISATEUR///////////////////////////////////////
	private static final String INSERT_UTILISATEUR = "INSERT into UTILISATEURS ( pseudo,  nom,  prenom,  email,  telephone,  rue, codePostal,ville, motDePasse, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";

	@Override
	public void insert(Utilisateur utilisateur) throws BusinessException {

		try (Connection cnx = ConnectionProvider.getConnection();

				PreparedStatement pstmt = cnx.prepareStatement(INSERT_UTILISATEUR,
						PreparedStatement.RETURN_GENERATED_KEYS))

		{

			pstmt.setString(1, utilisateur.getPseudo());
			pstmt.setString(2, utilisateur.getNom());
			pstmt.setString(3, utilisateur.getPrenom());
			pstmt.setString(4, utilisateur.getEmail());
			pstmt.setString(5, utilisateur.getTelephone());
			pstmt.setString(6, utilisateur.getRue());
			pstmt.setString(7, utilisateur.getCodePostal());
			pstmt.setString(8, utilisateur.getVille());
			pstmt.setString(9, utilisateur.getMotDePasse());
			pstmt.setInt(10, utilisateur.getCredit());
			pstmt.setInt(11, utilisateur.getAdministrateur());

			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				utilisateur.setNoUtilisateur(rs.getInt(1));
			}
		} catch (SQLException e) {

			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_UTILISATEUR_ECHEC);
			e.printStackTrace();
			throw businessException;
		}
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
public void modifierProfil(Utilisateur utilisateur) throws BusinessException {
	// TODO Auto-generated method stub
	
}
