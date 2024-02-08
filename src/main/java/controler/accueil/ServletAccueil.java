package controler.accueil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bll.UtilisateurBLL;
import bo.Restaurant;
import bo.Utilisateur;


public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBLL;

       
	 //---------------------------------------------------------------------------------------------------//
	@Override
	public void init() throws ServletException {
	    super.init();
	    try {
	        restaurantBLL = new RestaurantBLL();
	       
	    } catch (BLLException e) {
	        e.printStackTrace();
	    }
	}

	
 //---------------------------------------------------------------------------------------------------//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Lister les restaurants
    try {
        List<Restaurant> restaurants = restaurantBLL.selectAll();
        request.setAttribute("restaurants", restaurants);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
        dispatcher.forward(request, response);  
    } catch (Exception e) {    
}
	}
	//---------------------------------------------------------------------------------------------------//
	 protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
	    	
	 }
	
}
