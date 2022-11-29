package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Article;

/**
 * Servlet implementation class ServletAcceuil
 */
@WebServlet("/acceuil")
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Article article = null;
		try {
			article = UtilisateurManager.getInstance().afficher();
			request.setAttribute("nomArticle", article.getNom()); 
			request.setAttribute("prix", article.getPrix()); 
			request.setAttribute("finEnchere", article.getFinEnchere()); 
			request.setAttribute("vendeur", article.getIdVendeur()); 
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		request.setAttribute(getServletName(), response);
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/Acceuil.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
