package bll;


import bo.Carte;
import bo.Produit;
import bo.ProduitCarte;
import dal.DALException;
import dal.ProduitCarteDAOJdbcImpl;

public class ProduitCarteBLL {
	private ProduitCarteDAOJdbcImpl dao;

	public ProduitCarteBLL() throws BLLException {
		try {
			dao=new ProduitCarteDAOJdbcImpl();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion",e);
			
		}
	}
	
	public ProduitCarte selectById(int id ) throws BLLException{
		 try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération du composant", e);
		}

	}
	
	
	public ProduitCarte insert(Produit produit, Carte carte)throws BLLException {
		 BLLException bll=new BLLException();
		 
		 ProduitCarte produitCarte=new ProduitCarte(produit, carte);
		try {
		 if (dao.selectWhere(produit.getId(),carte.getId()) != null) {
			 bll.ajouterErreur("Le produit existe déja dans la carte");

		 }else {
				dao.insert(produitCarte);
			
			 }	
		} catch (DALException e) {
			
			throw new BLLException("Echec de l'insertion",e);
		}
		 
		 if (bll.getErreurs().size()>0) {
	        	throw bll;
	        	
		 }
		return produitCarte;
		
	}
	
	
	public void delete(int id) throws BLLException {
		try {
			 dao.delete(id);
		} catch (DALException e) {
			throw new BLLException("Echec de suppression", e);
		}
	}
}
