package bll;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import bo.Carte;
import bo.Categorie;
import bo.Produit;
import dal.DALException;
import dal.ProduitDAOFileImpl;


public class ProduitBLLFille {
	private ProduitDAOFileImpl dao;

	private ProduitCarteBLL bllProduitCarte;
	public ProduitBLLFille() throws BLLException {
		try {
			dao=new ProduitDAOFileImpl();
			bllProduitCarte= new ProduitCarteBLL();
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
	
	public Produit insert(String chemin, boolean aEntete, Carte carte) throws BLLException{
		BLLException bll=new BLLException();
		int cpt=0;
		Produit produit=null;
		File fichier=new File(chemin);
		try(Scanner scan=new Scanner(fichier)) {
			if(aEntete) {
				scan.nextLine();
				cpt++;
			}
			while (scan.hasNext()) {
				produit =lingeVersProduit(bll, scan,cpt);
				if(produit!=null) {
					try {
						dao.insert(produit);
						bllProduitCarte.insert(produit,carte);
						
					} catch (DALException e) {
						throw new BLLException("Echec de l'insertion",e);
						
					}
				}
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return produit;
			
	}
	
	private Produit lingeVersProduit(BLLException bll, Scanner scan , int cpt)throws BLLException {
		String ligne="";
		ligne=scan.nextLine();
		 Produit produit = new Produit();
		String [] attributs=ligne.split(",");
		if (attributs.length!=4) {
			bll.ajouterErreur("["+ cpt + "]"+ ligne + "(la ligne doit contenir 4 informations)");
		}else {
			 	
			 
			    produit.setNom(attributs[0].trim());
			    produit.setDescription(attributs[1].trim());
			    produit.setPrix(Float.parseFloat(attributs[2].trim()));
			    Categorie categorie = new Categorie();
			    categorie.setId(Integer.parseInt(attributs[3].trim()));
			    produit.setCategorie(categorie);
			
			    

			   
		}
		 if (bll.getErreurs().size()>0) {
	        	throw bll;
	        	
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
