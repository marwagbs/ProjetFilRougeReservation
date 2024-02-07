package bll;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import bo.Reservation;
import bo.Restaurant;
import bo.Utilisateur;
import dal.DALException;
import dal.ReservationDAO;

public class ReservationBLL {
		private ReservationDAO dao;


	    public ReservationBLL() {
			super();
		}


		public ReservationBLL(ReservationDAO dao) throws BLLException {
	        this.dao = dao; // Use this.dao instead of overwriting the parameter dao
	    }
	    
	
//----------------------------------------------------//
	
	public List<Reservation> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des restaurants", e);
		}
	}
	//----------------------------------------------------//
	public List<Reservation> selectAllJoin() throws BLLException {
		try {
			return dao.selectAllJoin();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des restaurants", e);
		}
	}
	//------------------------------------------------------//
	public Reservation selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération d'id " + id, e);
		}
	}
//-----------------------------------------------------------//
	public Reservation insert(LocalDate dateRes, LocalTime heure, int nbrPersonnes, Utilisateur utilisateur, Restaurant restaurant, String statut, String commentaire ) throws BLLException {
	
		
		Reservation reservation = new Reservation (dateRes, heure, nbrPersonnes, utilisateur, restaurant, statut, commentaire );
	
		try {
			dao = new ReservationDAO();
			dao.insert(reservation);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return reservation;
	}
//--------------------------------------------------------//
	public void update(Reservation reservation) throws BLLException {
		try {
			dao.update(reservation);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise à jour", e);
		}
	}
	//--------------------------------------------------//
	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
	
	
}