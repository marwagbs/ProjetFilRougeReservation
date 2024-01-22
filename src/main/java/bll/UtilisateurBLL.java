package bll;

import java.util.List;

import bo.Utilisateur;
import dal.DALException;
import dal.UtilisateurDAO;

public class UtilisateurBLL {
private UtilisateurDAO dao;
	
	public UtilisateurBLL() throws BLLException {
		try {
			dao = new UtilisateurDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}	
	}
	
	public List<Utilisateur> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des utilisateurs", e);
		}
	}
	
	public Utilisateur selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération d'id " + id, e);
		}
	}
	
	public Utilisateur insert(String nom, String prenom, String email, String motDePasse, String telephone, Boolean isAdmin) throws BLLException {
		verifierLesDonnees(nom, prenom, email, motDePasse, telephone, isAdmin);
		Utilisateur utilisateur = new Utilisateur(nom, prenom, email, motDePasse, telephone, isAdmin);
		try {
			dao.insert(utilisateur);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return utilisateur;
	}
	
	public void update(Utilisateur utilisateur) throws BLLException {
		verifierLesDonnees(utilisateur.getNom(), utilisateur.getPrenom(), utilisateur.getEmail(), utilisateur.getMotDePasse(), utilisateur.getTelephone(), utilisateur.getIsAdmin());
		try {
			dao.update(utilisateur);
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
	
	private static void verifierLesDonnees(String nom, String prenom, String email, String motDePasse, String telephone, Boolean isAdmin) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		
		if (nom.length() < 2) {
			bllException.ajouterErreur("Le nom doit faire au moins 2 caractères");
		}
		
		if (nom.length() > 50) {
			bllException.ajouterErreur("Le nom doit faire max 50 caractères");
		}
		
		if (prenom.length() < 2) {
			bllException.ajouterErreur("Le prenom doit faire au moins 2 caractères");
		}
		
		if (prenom.length() > 50) {
			bllException.ajouterErreur("Le prenom doit faire max 50 caractères");
		}
		
		if (email.length() < 5) {
			bllException.ajouterErreur("L'email doit faire au moins 5 caractères");
		}
		
		if (email.length() > 50) {
			bllException.ajouterErreur("L'email doit faire max 50 caractères");
		}
		
		if (motDePasse.length() < 5) {
			bllException.ajouterErreur("Le mot de passe doit faire au moins 5 caractères");
		}
		
		if (motDePasse.length() > 50) {
			bllException.ajouterErreur("Le mot de passe doit faire max 50 caractères");
		}
		
		if (telephone.length() < 8) {
			bllException.ajouterErreur("Le numéro de telephone doit faire au moins 8 caractères");
		}
		
		if (telephone.length() > 50) {
			bllException.ajouterErreur("Le numéro de telephone doit faire max 50 caractères");
		}
		
		if (bllException.getErreurs().size() > 0) {
			throw bllException;
		}
	}
}
