package bll;

import java.util.List;

import bo.Restaurant;
import bo.TableRes;
import dal.DALException;
import dal.TableResDAO;

public class TableResBLL {
	private TableResDAO dao;
	
	public TableResBLL() throws BLLException {
		try {
			dao = new TableResDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}	
	}
	
	public List<TableRes> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des tables", e);
		}
	}
	
	public TableRes selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération d'id " + id, e);
		}
	}
	
	public TableRes insert(int nombrePlaces, int numeroTable, String statut, Restaurant restaurant) throws BLLException {
		TableRes tableRes = new TableRes(nombrePlaces, numeroTable, statut, restaurant);
		
		if (!tableRes.getStatut().equals("absent") && !tableRes.getStatut().equals("present")) {
			throw new BLLException("La valeur du statut doit être soit 'present', soit 'absent'", null);
		}
		
		try {
			dao.insert(tableRes);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return tableRes;
	}
	
	public void update(TableRes tableRes) throws BLLException {
		if (!tableRes.getStatut().equals("absent") && !tableRes.getStatut().equals("present")) {
			throw new BLLException("La valeur du statut doit être soit 'present', soit 'absent'", null);
		}
		
		try {
			dao.update(tableRes);
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
