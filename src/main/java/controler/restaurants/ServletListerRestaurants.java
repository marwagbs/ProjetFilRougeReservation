package controler.restaurants;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import bll.BLLException;
import bll.RestaurantBLL;

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
<<<<<<< HEAD
			List<Restaurant> restaurants = restaurantBLL.selectAll();
			
			 request.setAttribute("restaurants", restaurants);
			 
			 RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/accueil.jsp");
	            
			 dispatcher.forward(request, response);

=======
			request.setAttribute("restaurants", restaurantBLL.selectAll());
>>>>>>> 295eed659466d2ab011d2d09a0adc7d6a413c93a
		} catch (BLLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/WEB-INF/jsp/restaurants.jsp").forward(request, response);
	}

}
