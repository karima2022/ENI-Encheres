package fr.eni.encheres.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
@WebServlet("/seConnecter")
public class ServletSeConnecter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("get");
		Cookie [] cookies=request.getCookies();
		boolean ifLogin =false;
		boolean ifMdp=false;
		boolean ifCk=false;
		if(cookies!=null) {
			for (Cookie cookie:cookies) {
				if(cookie.getName().equals("login")) {
					request.setAttribute("login", cookie.getValue());
					ifLogin = true;
				}
				if(cookie.getName().equals("mdp")) {
					request.setAttribute("mdp", cookie.getValue());
					ifMdp = true;
				}
				if(cookie.getName().equals("check")) {
					request.setAttribute("check", cookie.getValue());
					ifCk=request.getAttribute("check").equals("true")?true:false;
				}
			}
		}
		System.out.println(request.getParameter("souvenir"));
		if(ifLogin && ifMdp && ifCk) 
		{
			
			request.getRequestDispatcher("WEB-INF/Accueil.jsp").forward(request, response);
		}
		else {
		request.getRequestDispatcher("WEB-INF/seconnecter.jsp").forward(request, response);
		}
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		HttpSession  session;
		Utilisateur user = null;
		System.out.println("post");
		String identifiant=request.getParameter("login");
		String mdp = request.getParameter("mdp");
		String check = request.getParameter("check");
		
		Cookie cookie = new Cookie("login",identifiant);
		Cookie cookieMdp = new Cookie("mdp",mdp);
		Cookie cookieCk = new Cookie("check",check);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		cookieMdp.setMaxAge(60 * 60 * 24 * 30);
		cookieCk.setMaxAge(60 * 60 * 24 * 30);
		response.addCookie(cookie);
		response.addCookie(cookieMdp);
		response.addCookie(cookieCk);
		try {
			
			user=UtilisateurManager.getInstance().seConnecter(identifiant, mdp);
			
			request.setAttribute("login", cookie.getValue());
//			request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
			session = request.getSession();
			session.setAttribute("utilisateurActuel", user);
//			response.sendRedirect("/Accueil");
			request.getRequestDispatcher("/WEB-INF/Accueil.jsp").forward(request, response);
			
		} catch (BusinessException e) {
			
			request.setAttribute("ListeCodesErreur", e.getListeCodesErreur());
			
			e.printStackTrace();
		
		request.getRequestDispatcher("/WEB-INF/seConnecter.jsp").forward(request, response);
		
		
		}
				
	}
	}
		
		
		
		 
		
		 
		
		
		


				



