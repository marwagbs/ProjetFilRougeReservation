package bll;

import java.util.Arrays;
import java.util.List;

import bo.Categorie;
import dal.CategorieDAOJdbcImpl;
import dal.DALException;


public class CategorieBLL {
	private CategorieDAOJdbcImpl dao;

	public CategorieBLL() throws BLLException {
		try {
			dao=new CategorieDAOJdbcImpl();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion",e);
			
		}
	}
	
	public List <Categorie> selectALL()throws BLLException{
			
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la récupération du categorie", e);
			}	
		}
		
	public  Categorie selectById(int id ) throws BLLException{
		 try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération du categorie", e);
		}

	}
	
	public Categorie insert(String libelle) throws BLLException{
		BLLException bll=new BLLException();
		if (libelle.length() <2 || libelle.length()>20) {
			bll.ajouterErreur("Le libelle doit faire entre 2 et 20 caractères");
		}
		List<String> CategorieListe =  Arrays.asList("entree","plat", "dessert", "boisson");
		if(! CategorieListe.contains(libelle)) {
			bll.ajouterErreur("Le libelle du categorie n'est pas valide ");
		}
		
		 if (bll.getErreurs().size()>0) {
	        	throw bll;
	        	
	        }
		 Categorie categorie=new Categorie(libelle);
		 try {
			dao.insert(categorie);
		} catch (DALException e) {
		
			throw new BLLException("Echec de l'insertion",e);
		}
		 return categorie;
	}
	
	
	public void delete(int id) throws BLLException {
		try {
			 dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de suppression", e);
		}
	}
	
	
	
}
