package controler.utilisateurs;

import java.io.IOException;

import bll.BLLException;
import bll.UtilisateurBLL;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletPageProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		try {
			utilisateurBLL = new UtilisateurBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userMail = (String) request.getSession().getAttribute("identifiant");
		try {
			Utilisateur utilisateur = utilisateurBLL.selectByEmail(userMail);
			request.setAttribute("utilisateur", utilisateur);
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/utilisateur/profilutilisateur.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr = request.getParameter("id");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String email = request.getParameter("email");
		String telephone = request.getParameter("telephone");
		
		int id = Integer.parseInt(idStr);
		
		try {
			Utilisateur utilisateur = utilisateurBLL.selectById(id);
			utilisateur.setNom(nom);
			utilisateur.setPrenom(prenom);
			utilisateur.setEmail(email);
			utilisateur.setTelephone(telephone);
			
			utilisateurBLL.update(utilisateur);
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		response.sendRedirect("profil");
		
	}

}
