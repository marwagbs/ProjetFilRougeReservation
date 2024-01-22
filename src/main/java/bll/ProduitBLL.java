package bll;

import java.util.List;

import bo.Categorie;
import bo.Produit;
import dal.DALException;
import dal.ProduitDAOJdbcImpl;

public class ProduitBLL {
	private ProduitDAOJdbcImpl dao;

	public ProduitBLL() throws BLLException {
		try {
			dao=new ProduitDAOJdbcImpl();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion",e);
			
		}
	}
	
	public List <Produit> selectALL()throws BLLException{
			
			try {
				return dao.selectAll();
			} catch (DALException e) {
				throw new BLLException("Echec de la récupération du produit", e);
			}	
		}
	
	public List <Produit> selectAllByCarte(int id)throws BLLException{
		
		try {
			return dao.selectAllByCarte(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération du produit", e);
		}	
	}
	
	
	public Produit selectById(int id ) throws BLLException{
		 try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération du composant", e);
		}

	}
	public Produit selectByNom(String nom ) throws BLLException{
		 try {
			return dao.selectByNom(nom);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération du composant", e);
		}

	}
	
	public Produit insert(String nom, String description,float prix, Categorie categorie) throws BLLException{
		BLLException bll=new BLLException();
		if (nom.length() <2 || nom.length()>20) {
			bll.ajouterErreur("Le nom du produit doit faire entre 2 et 50 caractères");
		}
		
		if (description.length() <2 || description.length()>180) {
			bll.ajouterErreur("La description du produit doit faire entre 2 et 180 caractères");
		}
	
		 Produit produit=new Produit(nom, description ,prix, categorie);
		 try {
			 if (selectByNom(nom) != null) {
				 bll.ajouterErreur("Le nom du produit existe déja");
			 }else {
				 dao.insert(produit);
			 }
			 if (bll.getErreurs().size()>0) {
		        	throw bll;
		        	
			 }
		} catch (DALException e) {
		
			throw new BLLException("Echec de l'insertion",e);
		}
		 return produit;
	}
	public Produit update (Produit produit) throws BLLException{
		BLLException bll=new BLLException();
		if (produit.getNom().length() <2 || produit.getNom().length()>20) {
			bll.ajouterErreur("Le nom du produit doit faire entre 2 et 50 caractères");
		}
		if (produit.getDescription().length() <2 || produit.getDescription().length()>180) {
			bll.ajouterErreur("La description du produit doit faire entre 2 et 180 caractères");
		}
	
		 if (bll.getErreurs().size()>0) {
	        	throw bll;
	        	
	        }
		 
		 try {
			dao.update(produit);
		} catch (DALException e) {
		
			throw new BLLException("Echec de la mise à jour",e);
		}
		 return produit;
	}
	
	public void delete(int id) throws BLLException {
		try {
			 dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de suppression", e);
		}
	}
	
	
	
}
