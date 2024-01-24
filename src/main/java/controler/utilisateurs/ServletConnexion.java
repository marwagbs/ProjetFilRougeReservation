package controler.utilisateurs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bll.BLLException;
import bll.UtilisateurBLL;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletConnexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private UtilisateurBLL utilisateurBLL;
		@Override
			public void init() throws ServletException {
				try {
					utilisateurBLL=new UtilisateurBLL();
				} catch (BLLException e) {
					
					e.printStackTrace();
				}
			}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/utilisateur/connexion.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String identifiant=request.getParameter("identifiant");
		String motDePasse=request.getParameter("motDePasse");
	
		try {
			utilisateurBLL.verifierUtilisateur(identifiant, motDePasse);
			//response.sendRedirect("profile");
			request.getSession().setAttribute("identifiant",identifiant);
		} catch (BLLException e) {
			request.setAttribute("erreurs", e.getErreurs());
			for (String erreur: e.getErreurs()) {
				System.out.println("\t"+ erreur);	 
			}
			e.printStackTrace();
			request.getRequestDispatcher("WEB-INF/jsp/utilisateur/connexion.jsp").forward(request, response);
		}

	


	}
}
