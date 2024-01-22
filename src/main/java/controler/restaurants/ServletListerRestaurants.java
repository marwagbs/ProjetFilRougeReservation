package controler.restaurants;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
	
	@Override
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
			for (Restaurant current : restaurants) {
				System.out.println(current);
			}
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}
