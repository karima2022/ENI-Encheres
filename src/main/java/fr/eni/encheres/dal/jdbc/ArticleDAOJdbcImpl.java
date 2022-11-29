package fr.eni.encheres.dal.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.dal.ArticleDAO;
import fr.eni.encheres.dal.CodesResultatDAL;
import fr.eni.encheres.dal.ConnectionProvider;

public class ArticleDAOJdbcImpl implements  ArticleDAO {
	
	private static final String INSERT_ARTICLE= "INSERT INTO ARTICLES (noArticle,nom,descriptif,debutEnhere,"
			+ "finEnchere,prix,categorie,idVendeur,statut) VALUES (?,?,?,?,?,?,?,?,?);";


	
	//private static final String ARTICLECO = "SELECT (nom ,descriptif,debutEnchere,finEnchere,prix,"
	//		+"categorie,idVendeur,statut)FROM ARTICLES";
	private static final String ARTICLECO = "SELECT * FROM ARTICLES";
	@Override
	public void insert(Article article) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void select(Article article) throws BusinessException {
		// TODO Auto-generated method stub
		
	}
	public List<Article> findAll() throws BusinessException {

		List<Article> articles = new ArrayList<Article>();
		Article article = null;
		try (Connection cnx = ConnectionProvider.getConnection();
				PreparedStatement psmt = cnx.prepareStatement(ARTICLECO)){
			
			try(ResultSet rs = psmt.executeQuery()) {
				if(rs.next()) {
					article= new Article(
							rs.getString("nom"),
							rs.getString("descriptif"),
							rs.getDate("debutEnchere"),
							rs.getDate("finEnchere"),
							rs.getInt("prix"),
							rs.getInt("categorie"),
							rs.getInt("idVendeur"),
							rs.getInt("statut"));
					
					articles.add(article);
				}
			
			}} catch (SQLException e) {
				BusinessException be = new BusinessException();
				be.ajouterErreur(CodesResultatDAL.SQL_EXCEPTION);
			e.printStackTrace();
			throw be;
		}
		return articles;
	}
	

			
	
}
	
	

