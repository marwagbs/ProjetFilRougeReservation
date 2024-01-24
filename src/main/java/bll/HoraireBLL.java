package bll;



import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import bo.Horaire;
import dal.DALException;
import dal.GenericDAO;
import dal.HoraireDAOJdbcImpl;

public class HoraireBLL {
	private GenericDAO<Horaire> dao;
	
	
	//--------------------------------------------------------------//


	public HoraireBLL() throws BLLException {
		try {
			dao = new HoraireDAOJdbcImpl();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
		
	}
//--------------------------------------------------------------------------//
	public List<Horaire> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation des horaires", e);
		}
	}
//--------------------------------------------------------------------------//
	public Horaire selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la recuperation de l'horaire d'id " + id, e);
		}
	}
//--------------------------------------------------------------------------------//
public Horaire insert(String jour, LocalTime heureOuverture,LocalTime heureFermeture ) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		
		List<String> joursSemaine = Arrays.asList("LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI", "SAMEDI", "DIMANCHE");
		if (!joursSemaine.contains(jour)) {
			bllException.ajouterErreur("Ce jour de semaine n'est pas valide");
		}
		
		if (heureFermeture.isBefore(heureOuverture)) {
			bllException.ajouterErreur("L'heure de fermeture ne peut pas etre anterieure à l'heure d'ouverture");
		}
		
		
		if (bllException.getErreurs().size() > 0) {
			throw bllException;
		}
		
		Horaire horaire = new Horaire(jour, heureOuverture, heureFermeture);
		try {
			dao.insert(horaire);
		}catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return horaire;
}
//------------------------------------------------------------------------------------------------//
public void update (Horaire horaire) throws BLLException {
	try {
		dao.update(horaire);
	} catch (DALException e) {
		throw new BLLException("Echec de la mise a jour", e);
	}
}
//------------------------------------------------------------------------------------------------//
public void delete(int id) throws BLLException {
	try {
		dao.delete(id);
	} catch (DALException e) {
		throw new BLLException("Echec de la suppression", e);
	}
}
}
	

	
	

