package controler.restaurants;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bll.BLLException;
import bll.RestaurantBLL;
//import vo.Horaire;
//import vo.HoraireParJour;
import bo.Horaire;
import bo.Restaurant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
		
		//try {
		
//			Comparator<String> dayOfWeekComparator = new Comparator<String>() {
//			    private final Map<String, Integer> dayOrder = new HashMap<String, Integer>() {{
//			        put("Lundi", 1);
//			        put("Mardi", 2);
//			        put("Mercredi", 3);
//			        put("Jeudi", 4);
//			        put("Vendredi", 5);
//			        put("Samedi", 6);
//			        put("Dimanche", 7);
//			    }};
//
//			    @Override
//			    public int compare(String jour1, String jour2) {
//			        return Integer.compare(dayOrder.get(jour1), dayOrder.get(jour2));
//			    }
//			};
//
//			List<Restaurant> restaurantsHoraire = restaurantBLL.selectAllRes();
//			
//			request.setAttribute("restaurantsHoraire", restaurantsHoraire);
//			Map<Integer, Map<String, List<Horaire>>> horairesParRestaurant = new HashMap<>();
//
//			for (Restaurant restaurant : restaurantsHoraire) {
//			    Map<String, List<Horaire>> horairesParJour = new TreeMap<>(dayOfWeekComparator);
//			    List<Horaire> horairesRestaurant = restaurant.getHoraire();
//
//			    for (Horaire horaire : horairesRestaurant) {
//			        String jour = horaire.getJour();
//			    
//			        if (!horairesParJour.containsKey(jour)) {
//			            horairesParJour.put(jour, new ArrayList<>());
//			        }
//
//			        horairesParJour.get(jour).add(horaire);
//			    }
//
//			    horairesParRestaurant.put(restaurant.getId(), horairesParJour);
//			    
//			}
//			System.out.println(horairesParRestaurant.get(1) + "\n");
//			request.setAttribute("horairesParRestaurant", horairesParRestaurant);


			

//			List<Restaurant> restaurants = restaurantBLL.selectAll();
//			
//			HoraireParJour hpj = new HoraireParJour();
//			hpj.ajouterHoraire("Lundi", new Horaire("10h00", "20h00"));
//			hpj.ajouterHoraire("Mardi", new Horaire("10h00", "20h00"));
//			hpj.ajouterHoraire("Mercredi", new Horaire("10h00", "20h00"));
//			hpj.ajouterHoraire("Jeudi", new Horaire("10h00", "20h00"));
//			hpj.ajouterHoraire("Vendredi", new Horaire("10h00", "23h00"));
//			hpj.ajouterHoraire("Samedi", new Horaire("10h00", "23h00"));
//			hpj.ajouterHoraire("Dimanche", new Horaire("10h00", "23h00"));
//			
//			request.setAttribute("horaires", hpj);
//			
//			request.setAttribute("restaurants", restaurants);
			 

//		} catch (BLLException e) {
//			e.printStackTrace();
//		}
//		
		request.getRequestDispatcher("/WEB-INF/jsp/restaurants.jsp").forward(request, response);
	}

}