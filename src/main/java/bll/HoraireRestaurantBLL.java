package bll;

import java.util.List;

import bo.Horaire;
import bo.HoraireRestaurant;
import bo.Restaurant;
import dal.DALException;
import dal.GenericDAO;
import dal.HoraireRestaurantDAOJdbcimpl;


public class HoraireRestaurantBLL {
	
	private HoraireRestaurantDAOJdbcimpl daoHoraireRestaurant; 
	

	
	
//--------------------------------------------------------------//
public HoraireRestaurantBLL() throws BLLException {
			try {
				daoHoraireRestaurant = new HoraireRestaurantDAOJdbcimpl();
			} catch (DALException e) {
				throw new BLLException("Echec de la connexion", e);
			}
			
		}
	
		//--------------------------------------------------------------------------//
		public List<HoraireRestaurant> selectAll() throws BLLException {
			try {
				return daoHoraireRestaurant.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation des horaires", e);
			}
		}
	//--------------------------------------------------------------------------//
		public HoraireRestaurant selectById(int id) throws BLLException {
			try {
				return daoHoraireRestaurant.selectById(id);
			} catch (DALException e) {
				throw new BLLException("Echec de la recuperation de l'horaire d'id " + id, e);
			}
		}
	//--------------------------------------------------------------------------------//
		public HoraireRestaurant insert(Horaire horaire, Restaurant restaurant) throws BLLException, DALException {
		    BLLException bllException = new BLLException();
		    daoHoraireRestaurant = new HoraireRestaurantDAOJdbcimpl();

		    // Verifier si la combinaison existe deja
		    if (daoHoraireRestaurant.isCombinationExists(restaurant.getId(), horaire.getId())) {
		        bllException.ajouterErreur("Cette combinaison restaurant/horaire existe déjà dans la table.");
		    }

		    if (bllException.getErreurs().size() > 0) {
		        throw bllException;
		    }

		    HoraireRestaurant horaireRestaurant = new HoraireRestaurant(horaire, restaurant);
		    // 
		   
            
		    try {
		    	daoHoraireRestaurant.insert(horaireRestaurant);
		    } catch (Exception e) {
		        throw new BLLException("Echec de l'insertion", e);
		    }

		    return horaireRestaurant;
		}
//-----------------------------------------------------------------------------------//
public void delete(int id) throws BLLException {
	try {
		daoHoraireRestaurant.delete(id);
	} catch (DALException e) {
		throw new BLLException("Echec de la suppression", e);
	}
}
//------------------------------------------------------------------------------------------------//
public void update (HoraireRestaurant horaireRestaurant) throws BLLException {
	try {
		daoHoraireRestaurant.update(horaireRestaurant);
	} catch (DALException e) {
		throw new BLLException("Echec de la mise a jour", e);
	}
}
//------------------------------------------------------//
public List<HoraireRestaurant> selectAllByRestaurant(int id) throws BLLException {
	try {
		return daoHoraireRestaurant.selectAllByRestaurant(id);
	} catch (DALException e) {
		throw new BLLException("Echec de la recuperation des horaires", e);
	}
}

}


