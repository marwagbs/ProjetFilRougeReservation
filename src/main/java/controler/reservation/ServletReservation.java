package controler.reservation;

import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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
import bo.Restaurant;
import bo.Utilisateur;

public class ServletReservation extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ReservationBLL reservationBLL;
    private RestaurantBLL restaurantBLL;
    private HoraireRestaurantBLL horaireRestaurantBLL;
    private HoraireBLL horaireBLL;
	private UtilisateurBLL utilisateurBLL;
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
    @Override
    public void init() throws ServletException {
        super.init();
        try {
            restaurantBLL = new RestaurantBLL();
            reservationBLL = new ReservationBLL();
            horaireRestaurantBLL = new HoraireRestaurantBLL();
            horaireBLL = new HoraireBLL();
            utilisateurBLL = new UtilisateurBLL(); 
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }
  //------------------------------------------------------------------------------------------------------------------------------------------------------//
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRestaurantStr = request.getParameter("idRestaurant");
        int idRestaurant = (idRestaurantStr != null && !idRestaurantStr.isEmpty()) ? Integer.parseInt(idRestaurantStr) : 0;

        Restaurant restaurant = null;
        try {
            restaurant = restaurantBLL.selectById(idRestaurant);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("restaurant", restaurant);
        
       

            
            try {
                List<Horaire> horaires = new ArrayList<>();
                List<HoraireRestaurant> horaireRestaurants = horaireRestaurantBLL.selectAllByRestaurant(idRestaurant);
                for (HoraireRestaurant horaireRestaurant : horaireRestaurants) {
                    Horaire horaire = horaireRestaurant.getHoraire();
                    if (horaire != null) {
                        int idHoraire = horaire.getId();
                        Horaire selectedHoraire = horaireBLL.selectById(idHoraire);
                        horaires.add(selectedHoraire);
                    }
                }
                request.setAttribute("horaires", horaires);
               
            } catch (BLLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("/WEB-INF/jsp/reservation.jsp").forward(request, response);
        } 
    
//------------------------------------------------------------------------------------------------------------------------------------------------------//
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idRestaurantStr = request.getParameter("idRestaurant");
        String dateStr = request.getParameter("dateres");
        String heureStr = request.getParameter("heure");
        String nbrPersonnesStr = request.getParameter("nbrPersonnes");
        String commentaire = request.getParameter("commentaire");

        int idRestaurant = Integer.parseInt(idRestaurantStr);
        LocalDate dateRes = LocalDate.parse(dateStr);

        LocalTime heure = LocalTime.parse(heureStr);
        int nbrPersonnes = Integer.parseInt(nbrPersonnesStr);
        
     
		  // Retrieve the "identifiant" attribute from the session
	    String identifiant = (String) request.getSession().getAttribute("identifiant"); 
	    // Set the "identifiant" attribute as a request attribute
	     Utilisateur utilisateur;
		
	     
	     try {
			utilisateur = utilisateurBLL.selectByEmail(identifiant);
			if (utilisateur != null) {
				try {
			reservationBLL.insert(dateRes, heure, nbrPersonnes, utilisateur, new Restaurant(idRestaurant), null,commentaire);
     } catch (BLLException e) {
				 e.printStackTrace();
					            }
            doGet(request, response);
        }
			 request.setAttribute("utilisateur", utilisateur);
	    	 System.out.println(utilisateur);
		} catch (BLLException e) {
			
			e.printStackTrace();
		}

	    }  } 
