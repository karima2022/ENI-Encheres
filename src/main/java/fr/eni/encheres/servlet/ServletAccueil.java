package fr.eni.encheres.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.ArticleManager;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAcceuil
 */
@WebServlet("/accueil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List <Article> article=new ArrayList <>();
		ArticleManager articleManager = new ArticleManager();
		Utilisateur user=null;
		try {
			//user=UtilisateurManager.getInstance().afficherUtilisateurById();
			article =articleManager.afficherEncheresOuvertes();
			if (article==null) {
				System.out.println("article null");
				
			}
			for(Article art : article) {
				System.out.println(art.getNom());
			}
			request.setAttribute("ListeArticle", article);
//			request.setAttribute("nomArticle", article.getNom()); 
//			request.setAttribute("prix", article.getPrix()); 
//			request.setAttribute("finEnchere", article.getFinEnchere()); 
//			request.setAttribute("vendeur", article.getIdVendeur()); 
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		//request.setAttribute(getServletName(), response);
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/Accueil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
