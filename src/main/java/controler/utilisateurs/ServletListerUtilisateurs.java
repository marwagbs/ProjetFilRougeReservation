package controler.utilisateurs;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.UtilisateurBLL;
import bo.Utilisateur;

public class ServletListerUtilisateurs extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurBLL utilisateurBLL;
	
	@Override
	public void init() throws ServletException {
		super.init();
		try {
			utilisateurBLL = new UtilisateurBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Utilisateur> utilisateurs = utilisateurBLL.selectAll();
			for (Utilisateur current : utilisateurs) {
				System.out.println(current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}
}
