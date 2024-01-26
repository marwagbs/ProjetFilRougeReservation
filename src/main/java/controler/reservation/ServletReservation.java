package controler.reservation;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import bll.BLLException;
import bll.HoraireBLL;
import bll.HoraireRestaurantBLL;
import bll.ReservationBLL;
import bll.RestaurantBLL;
import bll.UtilisateurBLL;
import bo.Horaire;
import bo.HoraireRestaurant;
import bo.Reservation;
import bo.Restaurant;
import bo.Utilisateur;


public class ServletReservation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReservationBLL reservationBLL;
	private RestaurantBLL restaurantBLL;
	private HoraireRestaurantBLL horaireRestaurantBLL;
	private UtilisateurBLL utilisateurBLL;


     
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		try {
   restaurantBLL = new RestaurantBLL();
   reservationBLL = new ReservationBLL();
   utilisateurBLL = new  UtilisateurBLL();
   horaireRestaurantBLL = new HoraireRestaurantBLL();

		} catch (BLLException e) {
			e.printStackTrace();
		}
	}  
	
	
	//-------------------Récupérer l'id du restaurant -----------------------------------------------------------------//
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 1. Récupération des paramètres
		String idRestaurantStr = request.getParameter("idRestaurant");
		String idUtilisateurStr = request.getParameter("idUtilisatur");
		
		
// 2. Passage des paramètres dans le type voulu
		int idRestaurant;
	
		if (idRestaurantStr != null && !idRestaurantStr.isEmpty()) {
			idRestaurant = Integer.parseInt(idRestaurantStr);
		} else { 
			idRestaurant = 0; 
		}
		
		int idUtilisateur;
		if (idUtilisateurStr != null && !idUtilisateurStr.isEmpty()) {
			idUtilisateur= Integer.parseInt(idUtilisateurStr);
		} else { 
			idUtilisateur = 0; 
		}
		

// bll	
Restaurant restaurant = null;
//Utilisateur utilisateur = null; 
try {
restaurant = restaurantBLL.selectById(idRestaurant);
//utilisateur = utilisateurBLL.selectById(idUtilisateur);
} catch (Exception e) {
e.printStackTrace();
}
// ajout
request.setAttribute("restaurant", restaurant );
//request.setAttribute("utilisateur", utilisateur );
//------------------------------Récupérer la liste HoraireRestaurant pour l'id Restaurant ------------------------------------------//

try {
List<HoraireRestaurant> horaireRestaurants = new ArrayList<>();

horaireRestaurants = horaireRestaurantBLL.selectAllByRestaurant(idRestaurant);
 // ajout
    request.setAttribute("horaireRestaurants", horaireRestaurants);
    
} catch (Exception e) {
e.printStackTrace();
}



//----------------------------- rediriger vers la jsp ---------------------------------------------------//
request.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp").forward(request, response);
}

	//------------------------------------------------------------------------------------//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
// Récupération des paramètres
	    String idRestaurantStr = request.getParameter("idRestaurant");
	    String dateStr = request.getParameter("dateres");
	    String heureStr = request.getParameter("heure");
	    String nbrPersonnesStr = request.getParameter("nbrPersonnes");
	    String commentaire = request.getParameter("commentaire");

	    // Passage des paramètres dans le type voulu
	    int idRestaurant;
	    if (idRestaurantStr != null && !idRestaurantStr.isEmpty()) {
	        idRestaurant = Integer.parseInt(idRestaurantStr);
	    } else {
	        idRestaurant = 0;
	    }

	    LocalDate dateRes = null;
	    if (dateStr != null && !dateStr.isEmpty()) {
	        dateRes = LocalDate.parse(dateStr);
	    }

	    LocalTime heure = LocalTime.parse(heureStr);
	    int nbrPersonnes = Integer.parseInt(nbrPersonnesStr);

	    // Création de l'objet Reservation
	 //   Reservation reservation = new Reservation(dateRes, heure, nbrPersonnes, new Utilisateur(1), new Restaurant(idRestaurant), null ,commentaire);

	    // Création de la BLL et insertion de la réservation
	    ReservationBLL reservationBLL = new ReservationBLL();
	    try {
	    	 reservationBLL.insert(dateRes, heure, nbrPersonnes, new Utilisateur(1), new Restaurant(idRestaurant), null ,commentaire);
	    
	    	
	        
	    } catch (BLLException e) {
	        e.printStackTrace();
	        // Gérer l'erreur selon les besoins
	    }
		doGet(request, response);
	}

}