package controler.reservation;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import bll.BLLException;
import bll.ReservationBLL;
import bll.RestaurantBLL;
import bo.Reservation;
import bo.Restaurant;
import bo.Utilisateur;


public class ServletReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationBLL reservationBLL;
	private RestaurantBLL restaurantBLL;
     
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
   restaurantBLL = new RestaurantBLL();
  reservationBLL = new ReservationBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}  
	
	
	//------------------------------------------------------------------------------------//
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

	    try {
	        List<Restaurant> restaurants = restaurantBLL.selectAll();
	     
	        request.setAttribute("restaurants", restaurants);

	      
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp");
	 
	        dispatcher.forward(request, response);
	   
	       
	    } catch (Exception e) {
	      
	}

		}
	
	//------------------------------------------------------------------------------------//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
		String datestr = request.getParameter("dateres");
		String heurestr = request.getParameter("heure");
		String nbrPersonnesStr = request.getParameter("nbrPersonnes");
		String commentaire = request.getParameter("commentaire");
		String idRestaurantStr = request.getParameter("idRestaurant");
		String idUtilisateurStr = request.getParameter("idUtilisateur");
		
		LocalDate dateRes = null;
		if (datestr != null && !datestr.isEmpty()) {
		    dateRes = LocalDate.parse(datestr);
		}		
	
	LocalTime heure = LocalTime.parse(heurestr);
	int nbrPersonnes = Integer.parseInt(nbrPersonnesStr);
	//int idUtilisateur = Integer.parseInt(idUtilisateurStr);
	int idRestaurant = Integer.parseInt(idRestaurantStr);
	
	
	
	
	
    Reservation reservation = new Reservation(dateRes, heure, nbrPersonnes, new Utilisateur(3), new Restaurant(idRestaurant), null, commentaire);
	
	request.setAttribute("reservation", reservation);
	
	reservationBLL = new ReservationBLL();
	try {
		reservationBLL.insert(dateRes, heure, nbrPersonnes, new Utilisateur(3), new Restaurant(idRestaurant), null, commentaire);
		
	  	
		} catch (Exception e) {
			 e.printStackTrace();
		}
	request.setAttribute("reservation", reservation);
	}

	}
