package fr.eni.encheres.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;


/**
 * Servlet implementation class SeConnecter
 */
@WebServlet("/ServletSeConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/seconnecter.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
			String identifiant=request.getParameter("login");
			String mdp = request.getParameter("mdp");
			HttpSession session; 
			Utilisateur u = null;
			try {
				
				UtilisateurManager.getInstance().seConnecter(identifiant, mdp);
				session= request.getSession();
				response.sendRedirect("http://google.fr");
				
			} catch (BusinessException e) {
				session= request.getSession();
				session.setAttribute("utilisateurActuel", u);
				request.setAttribute("ListeCodesErreur", e.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/Acceuil.jsp").forward(request, response);
				
				e.printStackTrace();
				

			}
			}	
	}	

