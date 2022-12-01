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

@WebServlet(urlPatterns = { "/CreerUtilisateur", "/ModifierUtilisateur" })
public class ServletCreerUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getServletPath().equals("/ModifierUtilisateur")) {

			UtilisateurManager utilisateurManager = UtilisateurManager.getInstance();
			Utilisateur u = null;
			String pseudo = request.getParameter("pseudo");
			try {

				u = utilisateurManager.afficherUtilisateurbyPseudo(pseudo);
				request.setAttribute("utilisateurActuel", u);

			} catch (BusinessException e) {
				e.printStackTrace();
				request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
			}
		}

		request.getRequestDispatcher("/WEB-INF/CreerUtilisateur.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String profil = request.getParameter("profil");
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		String rue = request.getParameter("rue");
		String codePostal = request.getParameter("codePostal");
		String ville = request.getParameter("ville");
		String motDePasse = request.getParameter("motDePasse");
		Utilisateur u = null;
		HttpSession session;
		if (profil.equals("enregistrer")) {

			try {
				u = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);

				UtilisateurManager.getInstance().modifierUtilisateur(pseudo);
				
			} catch (BusinessException e) {
				
				e.printStackTrace();
			}
		} else if (profil.equals("creer")) {

			try {
				u = new Utilisateur(pseudo, nom, prenom, email, telephone, rue, codePostal, ville, motDePasse);
				UtilisateurManager.getInstance().ajouterUtilisateur(u);
				session = request.getSession();
				session.setAttribute("utilisateurActuel", u);
				response.sendRedirect("accueil");

			} catch (BusinessException businessException) {
				request.setAttribute("listeCodesErreur", businessException.getListeCodesErreur());
				request.getRequestDispatcher("/WEB-INF/CreerUtilisateur.jsp").forward(request, response);
			}
		}else if (profil.equals("annuler")) {
			response.sendRedirect("accueil");
	}
	else if (profil.equals("supprimer")) {
		
		try {
			UtilisateurManager.getInstance().supprimerUtilisateur(pseudo);
		//	request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			response.sendRedirect("accueil");
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}}

