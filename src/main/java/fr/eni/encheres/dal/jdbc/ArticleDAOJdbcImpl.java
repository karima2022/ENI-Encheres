package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Retrait;

public class ArticleDAOJdbcImpl implements ArticleDAO {

	private static final String INSERT_ARTICLE = "INSERT INTO ARTICLES VALUES(?,?,?,?,?,?,?,?);";
	private static final String INSERT_RETRAIT = "INSERT INTO RETRAITS VALUES(?,?,?,?);";
	private static final String SELECT_ARTICLE_BY_ID = "SELECT * FROM ARTICLES WHERE noArticle=?;";
	private static final String SELECT_RETRAIT_BY_ID = "SELECT * FROM RETRAITS WHERE noArticle=?;";
	private static final String UPDATE_ARTICLE = "UPDATE ARTICLES SET nom =?, descriptif=?, debutEnchere=?, finEnchere=?, "
													+ "prix=?, categorie=? WHERE noArticle=?;";
	private static final String UPDATE_RETRAIT = "UPDATE RETRAITS SET rue=?, codePostal=?, ville=? WHERE noArticle=?";
	private static final String DELETE_ARTICLE = "DELETE FROM ARTICLES WHERE noArticle=?;";
	private static final String SELECT_ENCHERES_OUVERTES = "SELECT * FROM ARTICLES a JOIN RETRAITS r ON r.noArticle = a.noArticle WHERE statut=2;";
	private static final String SELECT_MES_ENCHERES_EN_COURS = "";
	private static final String SELECT_MES_ENCHERES_REMPORTEES = "";
	private static final String SELECT_MES_VENTES_NON_COMMENCEES = "SELECT * FROM ARTICLES a JOIN RETRAITS r ON r.noArticle = a.noArticle "
																		+ "WHERE idVendeur=? AND statut=1;";
	private static final String SELECT_MES_VENTES_EN_COURS = "SELECT * FROM ARTICLES a JOIN RETRAITS r ON r.noArticle = a.noArticle "
																	+ "WHERE idVendeur=? AND statut=2;";
	private static final String SELECT_MES_VENTES_TERMINEES = "SELECT * FROM ARTICLES a JOIN RETRAITS r ON r.noArticle = a.noArticle "
																	+ "WHERE idVendeur=? AND statut=3;";
	
	
	
	/**
	 * Ajoute un nouvel article.
	 * @param article Objet article à ajouter.
	 * @throws BusinessException
	 */
	@Override
	public void insert(Article article) throws BusinessException {
		
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw businessException;
		}
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				PreparedStatement pstmt = cnx.prepareStatement(INSERT_ARTICLE, PreparedStatement.RETURN_GENERATED_KEYS);
				pstmt.setString(1, article.getNom());
				pstmt.setString(2, article.getDescription());
				pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDebutEnchere()));
				pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getFinEnchere()));
				pstmt.setInt(5, article.getPrix());
				pstmt.setInt(6, article.getCategorie());
				pstmt.setInt(7, article.getIdVendeur());
				pstmt.setInt(8, 1);
				pstmt.executeUpdate();
				
				ResultSet rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					article.setNoArticle(rs.getInt(1));
				}
				
				rs.close();
				pstmt.close();
				
				pstmt = cnx.prepareStatement(INSERT_RETRAIT);
				pstmt.setInt(1, article.getNoArticle());
				pstmt.setString(2, article.getRetrait().getRue());
				pstmt.setString(3, article.getRetrait().getCodePostal());
				pstmt.setString(4, article.getRetrait().getVille());
				pstmt.executeUpdate();
				
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			throw businessException;
		}

	}
	
	
	/**
	 * Affiche un article.
	 * @param id Identifiant de l'article.
	 * @return Un objet de type Article correspondant au paramètre id.
	 * @throws BusinessException
	 */
	@Override
	public Article selectById(int id) throws BusinessException {
		Article article = null;
		Retrait retrait = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ARTICLE_BY_ID);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article();
				article.setNoArticle(rs.getInt("noArticle"));
				article.setNom(rs.getString("nom"));
				article.setDescription(rs.getString("descriptif"));
				article.setDebutEnchere(rs.getTimestamp("debutEnchere").toLocalDateTime());
				article.setFinEnchere(rs.getTimestamp("finEnchere").toLocalDateTime());
				article.setPrix(rs.getInt("prix"));
				article.setCategorie(rs.getInt("categorie"));
				article.setIdVendeur(rs.getInt("idVendeur"));
				article.setStatut(rs.getInt("statut"));
			}
			
			rs.close();
			pstmt.close();
			
			if (article != null) {
				pstmt = cnx.prepareStatement(SELECT_RETRAIT_BY_ID);
				pstmt.setInt(1, article.getNoArticle());
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					retrait = new Retrait();
					retrait.setNoArticle(rs.getInt("noArticle"));
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("codePostal"));
					retrait.setVille(rs.getString("ville"));
					article.setRetrait(retrait);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_ECHEC);
			throw businessException;
		}
		
		if (article.getNoArticle() == 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLE_INEXISTANT);
			throw businessException;
		}
		
		return article;
	}
	
	
	/**
	 * Modifie un article.
	 * @param article Objet Article à modifier.
	 * @throws BusinessException
	 */
	@Override
	public void update(Article article) throws BusinessException {
		if (article == null) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			
			try {
				PreparedStatement pstmt = cnx.prepareStatement(UPDATE_ARTICLE);
				pstmt.setString(1, article.getNom());
				pstmt.setString(2, article.getDescription());
				pstmt.setTimestamp(3, java.sql.Timestamp.valueOf(article.getDebutEnchere()));
				pstmt.setTimestamp(4, java.sql.Timestamp.valueOf(article.getFinEnchere()));
				pstmt.setInt(5, article.getPrix());
				pstmt.setInt(6, article.getCategorie());
				pstmt.setInt(7, article.getNoArticle());
				pstmt.executeUpdate();
				
				pstmt.close();
				
				pstmt = cnx.prepareStatement(UPDATE_RETRAIT);
				pstmt.setString(1, article.getRetrait().getRue());
				pstmt.setString(2, article.getRetrait().getCodePostal());
				pstmt.setString(3, article.getRetrait().getVille());
				pstmt.setInt(4, article.getNoArticle());
				pstmt.executeUpdate();
				
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_ECHEC);
			throw businessException;
		}
	}
	
	
	/**
	 * Supprime un article.
	 * @param id Identifiant de l'article.
	 * @throws BusinessException
	 */
	@Override
	public void delete(int id) throws BusinessException {
		if (id == 0) {
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.INSERT_ARTICLE_NULL);
			throw businessException;
		}
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(DELETE_ARTICLE);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.SUPPRESSION_ARTICLE_ERREUR);
			throw businessException;
		}
	}
	
	
//	/**
//	 * Affiche toutes les enchères qui n'ont pas encore commencées.
//	 * @return
//	 * @throws BusinessException
//	 */
//	@Override
//	public List<Article> selectEncheresNonCommencees() throws BusinessException {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	/**
	 * Affiche toutes les enchères ouvertes.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	@Override
	public List<Article> selectEncheresOuvertes() throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		Retrait retrait = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_ENCHERES_OUVERTES);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article();
				article.setNoArticle(rs.getInt("noArticle"));
				article.setNom(rs.getString("nom"));
				article.setDescription(rs.getString("descriptif"));
				article.setDebutEnchere(rs.getTimestamp("debutEnchere").toLocalDateTime());
				article.setFinEnchere(rs.getTimestamp("finEnchere").toLocalDateTime());
				article.setPrix(rs.getInt("prix"));
				article.setCategorie(rs.getInt("categorie"));
				article.setIdVendeur(rs.getInt("idVendeur"));
				article.setStatut(rs.getInt("statut"));
				
				retrait = new Retrait();
				retrait.setNoArticle(rs.getInt("noArticle"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("codePostal"));
				retrait.setVille(rs.getString("ville"));
				
				article.setRetrait(retrait);
				
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		
		return articles;
	}
	
	
	/**
	 * Affiche les enchères auxquelles un utilisateur participe.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	@Override
	public List<Article> selectMesEncheresEnCours(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Affiche les enchères remportées par un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	@Override
	public List<Article> selectMesEncheresRemportees(int id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	/**
	 * Affiche les ventes non commencées d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	@Override
	public List<Article> selectMesVentesNonCommencees(int id) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		Retrait retrait = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES_NON_COMMENCEES);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article();
				article.setNoArticle(rs.getInt("noArticle"));
				article.setNom(rs.getString("nom"));
				article.setDescription(rs.getString("descriptif"));
				article.setDebutEnchere(rs.getTimestamp("debutEnchere").toLocalDateTime());
				article.setFinEnchere(rs.getTimestamp("finEnchere").toLocalDateTime());
				article.setPrix(rs.getInt("prix"));
				article.setCategorie(rs.getInt("categorie"));
				article.setIdVendeur(rs.getInt("idVendeur"));
				article.setStatut(rs.getInt("statut"));
				
				retrait = new Retrait();
				retrait.setNoArticle(rs.getInt("noArticle"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("codePostal"));
				retrait.setVille(rs.getString("ville"));
				
				article.setRetrait(retrait);
				
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		
		return articles;
	}
	
	
	/**
	 * Affiche les ventes en cours d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	@Override
	public List<Article> selectMesVentesEnCours(int id) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		Retrait retrait = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES_EN_COURS);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article();
				article.setNoArticle(rs.getInt("noArticle"));
				article.setNom(rs.getString("nom"));
				article.setDescription(rs.getString("descriptif"));
				article.setDebutEnchere(rs.getTimestamp("debutEnchere").toLocalDateTime());
				article.setFinEnchere(rs.getTimestamp("finEnchere").toLocalDateTime());
				article.setPrix(rs.getInt("prix"));
				article.setCategorie(rs.getInt("categorie"));
				article.setIdVendeur(rs.getInt("idVendeur"));
				article.setStatut(rs.getInt("statut"));
				
				retrait = new Retrait();
				retrait.setNoArticle(rs.getInt("noArticle"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("codePostal"));
				retrait.setVille(rs.getString("ville"));
				
				article.setRetrait(retrait);
				
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		
		return articles;
	}
	
	
	/**
	 * Affiche les ventes terminées d'un utilisateur.
	 * @param id Identifiant de l'utilisateur.
	 * @return Une collection de type Article.
	 * @throws BusinessException
	 */
	@Override
	public List<Article> selectMesVentesTerminees(int id) throws BusinessException {
		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		Retrait retrait = null;
		
		try (Connection cnx = ConnectionProvider.getConnection()) {
			PreparedStatement pstmt = cnx.prepareStatement(SELECT_MES_VENTES_TERMINEES);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				article = new Article();
				article.setNoArticle(rs.getInt("noArticle"));
				article.setNom(rs.getString("nom"));
				article.setDescription(rs.getString("descriptif"));
				article.setDebutEnchere(rs.getTimestamp("debutEnchere").toLocalDateTime());
				article.setFinEnchere(rs.getTimestamp("finEnchere").toLocalDateTime());
				article.setPrix(rs.getInt("prix"));
				article.setCategorie(rs.getInt("categorie"));
				article.setIdVendeur(rs.getInt("idVendeur"));
				article.setStatut(rs.getInt("statut"));
				
				retrait = new Retrait();
				retrait.setNoArticle(rs.getInt("noArticle"));
				retrait.setRue(rs.getString("rue"));
				retrait.setCodePostal(rs.getString("codePostal"));
				retrait.setVille(rs.getString("ville"));
				
				article.setRetrait(retrait);
				
				articles.add(article);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			BusinessException businessException = new BusinessException();
			businessException.ajouterErreur(CodesResultatDAL.LECTURE_ARTICLES_ECHEC);
			throw businessException;
		}
		
		return articles;
	}


}
