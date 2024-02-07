package controler.restaurants;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vo.Horaire;
import vo.HoraireParJour;

public class ServletListerRestaurants extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RestaurantBLL restaurantBLL;
	

	public void init() throws ServletException {
		try {
			restaurantBLL = new RestaurantBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {


			List<Restaurant> restaurants = restaurantBLL.selectAll();
			
			HoraireParJour hpj = new HoraireParJour();
			hpj.ajouterHoraire("Lundi", new Horaire("10h00", "20h00"));
			hpj.ajouterHoraire("Mardi", new Horaire("10h00", "20h00"));
			hpj.ajouterHoraire("Mercredi", new Horaire("10h00", "20h00"));
			hpj.ajouterHoraire("Jeudi", new Horaire("10h00", "20h00"));
			hpj.ajouterHoraire("Vendredi", new Horaire("10h00", "23h00"));
			hpj.ajouterHoraire("Samedi", new Horaire("10h00", "23h00"));
			hpj.ajouterHoraire("Dimanche", new Horaire("10h00", "23h00"));
			
			request.setAttribute("horaires", hpj);
			
			request.setAttribute("restaurants", restaurants);
			 

		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/restaurants.jsp").forward(request, response);
	}

}