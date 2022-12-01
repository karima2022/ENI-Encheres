package fr.eni.encheres.servlet;

import java.io.IOException;
import java.time.LocalDateTime;
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
import fr.eni.encheres.bo.Article;

/**
 * Servlet implementation class ServletNouvelleVente
 */
@WebServlet(
		urlPatterns= {
				"/nouvelle_vente",
				"/modifier"
})
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if (request.getServletPath().equals("/modifier")) {
			ArticleManager articleManager = new ArticleManager();
			List<Integer> listeCodesErreur = new ArrayList<>();
			int idArticle = lireParametreIdArticle(request, listeCodesErreur);
			
			if (listeCodesErreur.size() > 0) {
				request.setAttribute("listeCodesErreur",listeCodesErreur);
			} else {
				Article article = new Article();
				try {
					article = articleManager.afficherArticle(idArticle);
					request.setAttribute("objArticle", article);
				} catch (BusinessException e) {
					request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
				}
			}
			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/nouvelle_vente.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String btnSubmit = request.getParameter("btnSubmit");
		
		
		if (btnSubmit != null) {
			if (btnSubmit.equalsIgnoreCase("enregistrer")) {
				if (request.getParameter("idArticle") != null) {
					modifierVente(request, response);
				} else {
					ajouterNouvelleVente(request, response);
				}
			} else if (btnSubmit.equalsIgnoreCase("supprimer")) {
				ArticleManager articleManager = new ArticleManager();
				List<Integer> listeCodesErreur = new ArrayList<>();
				int idArticle = lireParametreIdArticle(request, listeCodesErreur);
				
				if (listeCodesErreur.size() > 0) {
					request.setAttribute("listeCodesErreur",listeCodesErreur);
				} else {
					try {
						articleManager.supprimerArticle(idArticle);
					} catch (BusinessException e) {
						request.setAttribute("listeCodesErreur", e.getListeCodesErreur());
					}
				}
			} 
		}
		
		response.sendRedirect("accueil");
		
	}
	
	
	
	private void ajouterNouvelleVente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom;
		String description;
		int categorie;
		int prix;
		LocalDateTime debutEnchere;
		LocalDateTime finEnchere;
		String rue;
		String codePostal;
		String ville;
		int idVendeur;
		List<Integer> listeCodesErreur=new ArrayList<>();
		
		nom = lireParametreNomArticle(request, listeCodesErreur);
		description = lireParametreDescriptionArticle(request, listeCodesErreur);
		debutEnchere = lireParametreDateDebutEnchere(request, listeCodesErreur);
		finEnchere = lireParametreDateFinEnchere(request, listeCodesErreur);
		idVendeur = lireParametreIdVendeur(request, listeCodesErreur);
		rue = lireParametreRueRetrait(request, listeCodesErreur);
		codePostal = lireParametreCPRetrait(request, listeCodesErreur);
		ville = lireParametreVilleRetrait(request, listeCodesErreur);
		
		categorie = Integer.parseInt(request.getParameter("categorie"));
		prix = Integer.parseInt(request.getParameter("prix"));
		
		
		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
			request.getRequestDispatcher("WEB-INF/nouvelle_vente.jsp").forward(request, response);
		} else {
			ArticleManager articleManager = new ArticleManager();
			try {
				articleManager.ajouterArticle(nom, description, debutEnchere, finEnchere, prix, categorie, idVendeur, rue, codePostal, ville);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				request.getRequestDispatcher("WEB-INF/nouvelle_vente.jsp").forward(request, response);
			}
		}
	}
	
	
	private void modifierVente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idArticle;
		String nom;
		String description;
		int categorie;
		int prix;
		LocalDateTime debutEnchere;
		LocalDateTime finEnchere;
		String rue;
		String codePostal;
		String ville;
		int idVendeur;
		List<Integer> listeCodesErreur = new ArrayList<>();
		
		idArticle = lireParametreIdArticle(request, listeCodesErreur);
		nom = lireParametreNomArticle(request, listeCodesErreur);
		description = lireParametreDescriptionArticle(request, listeCodesErreur);
		debutEnchere = lireParametreDateDebutEnchere(request, listeCodesErreur);
		finEnchere = lireParametreDateFinEnchere(request, listeCodesErreur);
		idVendeur = lireParametreIdVendeur(request, listeCodesErreur);
		rue = lireParametreRueRetrait(request, listeCodesErreur);
		codePostal = lireParametreCPRetrait(request, listeCodesErreur);
		ville = lireParametreVilleRetrait(request, listeCodesErreur);
		
		categorie = Integer.parseInt(request.getParameter("categorie"));
		prix = Integer.parseInt(request.getParameter("prix"));
		
		
		if (listeCodesErreur.size() > 0) {
			request.setAttribute("listeCodesErreur",listeCodesErreur);
		} else {
			ArticleManager articleManager = new ArticleManager();
			try {
				articleManager.modifierArticle(idArticle, nom, description, debutEnchere, finEnchere, prix, categorie, rue, codePostal, ville);
			} catch (BusinessException e) {
				request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				try {
					request.setAttribute("objArticle", articleManager.afficherArticle(idArticle));
				} catch (BusinessException e1) {
					request.setAttribute("listeCodesErreur",e.getListeCodesErreur());
				}
				request.getRequestDispatcher("WEB-INF/nouvelle_vente.jsp").forward(request, response);
			}
		}
	}
	
	
	
	private int lireParametreIdArticle(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int id = 0;
		try {
			if (request.getParameter("idArticle") != null) {
				id = Integer.parseInt(request.getParameter("idArticle"));
			}
		} catch(NumberFormatException e) {
			listeCodesErreur.add(CodesResultatServlets.FORMAT_NO_ARTICLE_ERREUR);
		}
		return id;
	}
	
	private String lireParametreNomArticle(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String nom;
		nom = request.getParameter("nom");
		if (nom == null || nom.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.NOM_D_ARTICLE_OBLIGATOIRE);
		}
		return nom;
	}
	
	private String lireParametreDescriptionArticle(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String description;
		description = request.getParameter("description");
		if (description == null || description.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.DESCRIPTION_ARTICLE_OBLIGATOIRE);
		}
		return description;
	}
	
	private LocalDateTime lireParametreDateDebutEnchere(HttpServletRequest request, List<Integer> listeCodesErreur) {
		LocalDateTime debutEnchere;
		debutEnchere = LocalDateTime.parse(request.getParameter("debutEnchere"));
		if (debutEnchere == null) {
			listeCodesErreur.add(CodesResultatServlets.DATE_DEBUT_ENCHERE_OBLIGATOIRE);
		}
		return debutEnchere;
	}
	
	private LocalDateTime lireParametreDateFinEnchere(HttpServletRequest request, List<Integer> listeCodesErreur) {
		LocalDateTime finEnchere;
		finEnchere = LocalDateTime.parse(request.getParameter("finEnchere"));
		if (finEnchere == null) {
			listeCodesErreur.add(CodesResultatServlets.DATE_FIN_ENCHERE_OBLIGATOIRE);
		}
		return finEnchere;
	}
	
	private int lireParametreIdVendeur(HttpServletRequest request, List<Integer> listeCodesErreur) {
		int id = 0;
		
		try {
			if (request.getParameter("idVendeur") != null) {
				id = Integer.parseInt(request.getParameter("idVendeur"));
			}
		} catch (NumberFormatException e) {
			listeCodesErreur.add(CodesResultatServlets.FORMAT_ID_USER);
		}
		
		return id;
	}
	
	private String lireParametreRueRetrait(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String rue;
		rue = request.getParameter("rue");
		if (rue == null || rue.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.RUE_OBLIGATOIRE);
		}
		return rue;
	}
	
	private String lireParametreCPRetrait(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String codePostal;
		codePostal = request.getParameter("codePostal");
		if (codePostal == null || codePostal.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.CODE_POSTAL_OBLIGATOIRE);
		}
		return codePostal;
	}
	
	private String lireParametreVilleRetrait(HttpServletRequest request, List<Integer> listeCodesErreur) {
		String ville;
		ville = request.getParameter("ville");
		if (ville == null || ville.trim().equals("")) {
			listeCodesErreur.add(CodesResultatServlets.VILLE_OBLIGATOIRE);
		}
		return ville;
	}

}
