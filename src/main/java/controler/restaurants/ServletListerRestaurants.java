package controler.restaurants;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.RestaurantBLL;
import bo.Restaurant;

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
//-----------------------------------------------------------------------------------//
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {


			List<Restaurant> restaurants = restaurantBLL.selectAll();
			
			 request.setAttribute("restaurants", restaurants);
			 
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
	            
			 dispatcher.forward(request, response);



		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/restaurants.jsp").forward(request, response);
	}

}
