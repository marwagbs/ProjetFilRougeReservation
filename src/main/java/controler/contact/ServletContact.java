package controler.contact;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.MessageBLL;
import bll.RestaurantBLL;
import bll.UtilisateurBLL;
import bo.Restaurant;
import bo.Utilisateur;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletContact extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBLL;
	private UtilisateurBLL utilisateurBLL;
	private MessageBLL messageBLL;
      @Override
    public void init() throws ServletException {
    	  try {
			restaurantBLL=new RestaurantBLL();
			utilisateurBLL=new UtilisateurBLL();
			messageBLL=new MessageBLL();
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Restaurant> listeRestaurant=restaurantBLL.selectAll();
			request.setAttribute("listeRestaurant", listeRestaurant);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
		request.getRequestDispatcher("WEB-INF/jsp/contact/contact.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mailUser = (String) request.getSession().getAttribute("identifiant");
		String contenu=request.getParameter("contenu");
		String sujet=request.getParameter("sujet");
		String idRestaurant=request.getParameter("idRestaurant");
	
		System.out.println(sujet);
		System.out.println(contenu);
	
		System.out.println(idRestaurant);
		try {
			Utilisateur utilisateur = utilisateurBLL.selectByEmail(mailUser);
			Restaurant restaurant =restaurantBLL.selectById(Integer.parseInt(idRestaurant));
			messageBLL.insert(contenu, sujet,utilisateur,restaurant);
			request.setAttribute("message", "Nous avons bien reçu votre message et nous vous contacterons dans les meilleurs délais.");
			request.getRequestDispatcher("WEB-INF/jsp/contact/contact.jsp").forward(request, response);
		} catch (BLLException e) {
			for (String erreur: e.getErreurs()) {
				System.out.println("\t"+ erreur);	 
			}
			 request.setAttribute("erreurs", e.getErreurs());
			e.printStackTrace();
			request.getRequestDispatcher("WEB-INF/jsp/contact/contact.jsp").forward(request, response);
			return;
			
		} 
	}

}
