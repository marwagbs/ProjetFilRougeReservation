package bll;

import java.util.List;

import bo.Carte;
import bo.Restaurant;
import dal.DALException;
import dal.RestaurantDAO;

public class RestaurantBLL {
	private RestaurantDAO dao;
	
	public RestaurantBLL() throws BLLException {
		try {
			dao = new RestaurantDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}	
	}
	
	public List<Restaurant> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des restaurants", e);
		}
	}
	
	public Restaurant selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération d'id " + id, e);
		}
	}
	
	public Restaurant insert(String nom, String adresse, String cpo, String ville) throws BLLException {
		verifierLesDonnees(nom, adresse, cpo, ville);
		Restaurant restaurant = new Restaurant(nom, adresse, cpo, ville);
		try {
			dao.insert(restaurant);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return restaurant;
	}
	
	public void update(Restaurant restaurant) throws BLLException {
		verifierLesDonnees(restaurant.getNom(), restaurant.getAdresse(), restaurant.getCpo(), restaurant.getVille());
		try {
			dao.update(restaurant);
		} catch (DALException e) {
			throw new BLLException("Echec de la mise à jour", e);
		}
	}
	
	public void delete(int id) throws BLLException {
		try {
			dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la suppression", e);
		}
	}
	
	private static void verifierLesDonnees(String nom, String adresse, String cpo, String ville) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		
		if (nom.length() < 2) {
			bllException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}
		
		if (nom.length() > 50) {
			bllException.ajouterErreur("Le nom doit faire max 50 caractères");
		}
		
		if (adresse.length() < 5) {
			bllException.ajouterErreur("L'adresse doit faire au moins 5 caractères");
		}
		
		if (adresse.length() > 60) {
			bllException.ajouterErreur("L'adresse doit faire max 60 caractères");
		}
		
		if (Integer.valueOf(cpo) < 01000 || Integer.valueOf(cpo) > 95999) {
			bllException.ajouterErreur("Le code postal doit être entre 01000 et 95999");
		}
		
		if (ville.length() < 2) {
			bllException.ajouterErreur("Le nom de la ville doit faire au moins 2 caractères");
		}
		
		if (ville.length() > 40) {
			bllException.ajouterErreur("Le nom de la ville doit faire max 40 caractères");
		}
		
		if (bllException.getErreurs().size() > 0) {
			throw bllException;
		}
	}
}
