package controler.utilisateurs;

import java.io.IOException;

import bll.BLLException;
import bll.UtilisateurBLL;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletSupprimerProfil extends HttpServlet {
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
		String idStr = request.getParameter("id");
		int id = Integer.parseInt(idStr);
		try {
			Utilisateur utilisateur = utilisateurBLL.selectById(id);
			utilisateurBLL.delete(id);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		response.sendRedirect("connexion");
	}


}
