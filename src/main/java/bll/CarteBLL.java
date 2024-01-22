package bll;

import java.util.List;

import bo.Carte;
import dal.CarteDAO;
import dal.DALException;

public class CarteBLL {
	private CarteDAO dao;
	
	public CarteBLL() throws BLLException {
		try {
			dao = new CarteDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}
	
	public List<Carte> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des cartes", e);
		}
	}
	
	public Carte selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération d'id " + id, e);
		}
	}
	
	public Carte insert(String libelle) throws BLLException {
		Carte carte = new Carte(libelle);
		
		if (libelle.length() < 2 || libelle.length() > 40) {

			throw new BLLException("Le libellé doit faire entre 2 et 40 caractères", null);

		}
		
		try {
			dao.insert(carte);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return carte;
	}
	
	public void update(Carte carte) throws BLLException {
		if (carte.getLibelle().length() < 2 || carte.getLibelle().length() > 40) {

			throw new BLLException("Le libellé doit faire entre 2 et 40 caractères", null);

		}
		
		try {
			dao.update(carte);
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
}
