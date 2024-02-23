package controler.utilisateurs;

import java.io.IOException;

import bll.BLLException;
import bll.UtilisateurBLL;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletCreationCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private UtilisateurBLL utilisateurBLL;
	@Override
		public void init() throws ServletException {
			try {
				utilisateurBLL=new UtilisateurBLL();
			} catch (BLLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/jsp/utilisateur/creationducompte.jsp").forward(request, response);
	}

	@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String nom=request.getParameter("nom");
			String prenom=request.getParameter("prenom");
			String numTel=request.getParameter("numTel");
			String email=request.getParameter("email");
			String motDepasse=request.getParameter("motDePasse");
		
			try {
				utilisateurBLL.insert(nom, prenom, email, motDepasse, numTel, "client");
				request.getRequestDispatcher("WEB-INF/jsp/utilisateur/connexion.jsp").forward(request, response);
			} catch (BLLException e) {
				for (String erreur: e.getErreurs()) {
					System.out.println("\t"+ erreur);	 
				}
				 request.setAttribute("erreurs", e.getErreurs());
				e.printStackTrace();
				request.getRequestDispatcher("WEB-INF/jsp/utilisateur/creationducompte.jsp").forward(request, response);
				return;
			}
			
		}
	
}
