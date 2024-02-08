package bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	public Utilisateur selectByEmail(String email) throws BLLException {
		try {
			return dao.selectByEmail(email);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération de l'email " + email, e);
		}
	}
	
	public Utilisateur insert(String nom, String prenom, String email, String motDePasse, String telephone, Boolean isAdmin) throws BLLException {
		BLLException bllException = new BLLException();
		verifierLesDonnees(nom, prenom, email, motDePasse, telephone, isAdmin);

         byte[] saltBytes = email.getBytes();
        String hashedMotDePasse = hashMotDePasse(motDePasse, saltBytes);
		
        Utilisateur utilisateur = new Utilisateur(nom, prenom, email, hashedMotDePasse, telephone, isAdmin);
		try {
			
			if(dao.selectByEmail(email) != null) {
				bllException.ajouterErreur("Cette adreese email existe déja");
			}else {
				dao.insert(utilisateur);
			}
			
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		if (bllException.getErreurs().size() > 0) {
			throw bllException;
		}
		return utilisateur;
	}
	
	public void update(Utilisateur utilisateur) throws BLLException {
		BLLException bllException = new BLLException();
		
		if (utilisateur.getNom().isEmpty() || utilisateur.getNom().length() < 2 || utilisateur.getNom().length() > 50) {
			bllException.ajouterErreur("Le nom doit avoir entre 2 et 50 caractères");
		}
		
		if (utilisateur.getPrenom().isEmpty() || utilisateur.getPrenom().length() < 2 || utilisateur.getPrenom().length() > 50) {
			bllException.ajouterErreur("Le prénom doit avoir entre 2 et 50 caractères");
		}
		
		if (utilisateur.getTelephone().isEmpty() || utilisateur.getTelephone().length() < 8 || utilisateur.getTelephone().length() > 50) {
			bllException.ajouterErreur("Le numéro de téléphone doit avoir entre 8 et 50 caractères");
		}
		
		if (bllException.getErreurs().size() > 0) {
			throw bllException;
		}
		
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
	
	private  void verifierLesDonnees(String nom, String prenom, String email, String motDePasse, String telephone, Boolean isAdmin) throws BLLException {
		
		BLLException bllException = new BLLException();
		
		
		if (nom.isEmpty() || nom.length() < 2 || nom.length() > 50) {
		    bllException.ajouterErreur("Le nom doit avoir entre 2 et 50 caractères");
		}

		if (prenom.isEmpty() || prenom.length() < 2 || prenom.length() > 50) {
		    bllException.ajouterErreur("Le prénom doit avoir entre 2 et 50 caractères");
		}
		if (motDePasse.isEmpty() || !validatePassword(motDePasse)) {
		    bllException.ajouterErreur("Le mot de passe doit contenir entre 8 et 20 caractères, "
		            + "incluant au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial");
		}

		if (email.isEmpty() || !validateEmail(email)) {
		    bllException.ajouterErreur("L'e-mail n'est pas valide");
		}
		
		if (telephone.isEmpty() || telephone.length() < 8 || telephone.length() > 50) {
		    bllException.ajouterErreur("Le numéro de téléphone doit avoir entre 8 et 50 caractères");
		}
		
		if (bllException.getErreurs().size() > 0) {
			throw bllException;
		}
	}
	
	 public  boolean validatePassword(String motDePasse) {
	        // la regex MDP
	        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	        // Création du pattern
	        Pattern pattern = Pattern.compile(regex);

	        // Création du matcher
	        Matcher matcher = pattern.matcher(motDePasse);

	        // Vérification de la correspondance
	        return matcher.matches();
	    }
	 
	 public  boolean validateEmail(String email) {

	        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	        Pattern pattern = Pattern.compile(regex);

	        Matcher matcher = pattern.matcher(email);
	        return matcher.matches();
	    }
	 
	 public  String  hashMotDePasse(String motDePasseToHash, byte [] salt) {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");
	            md.update(salt);
	            md.update(motDePasseToHash.getBytes(StandardCharsets.UTF_8));
	            byte[] bytes = md.digest();

	            StringBuilder sb = new StringBuilder();
	            for (byte b : bytes) {
	                sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
	            }

	            generatedPassword = sb.toString();
	        } catch (NoSuchAlgorithmException e) {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	 	
	 
	 public void verifierUtilisateur(String email, String motDePasse) throws BLLException {
		 BLLException bllException = new BLLException();
		 try {
	            
	            Utilisateur utilisateur = dao.selectByEmail(email);
	            if (utilisateur != null) { 
	                    String motDePasseSaisie = hashMotDePasse(motDePasse, email.getBytes());
	                    String mdpHache=utilisateur.getMotDePasse();
	                   
	                    if (!mdpHache.equals(motDePasseSaisie)) {
	                        bllException.ajouterErreur("Mot de passe incorrect");
	                   
	                    } 
	            } else {
	                bllException.ajouterErreur("L'adresse e-mail fournie ne correspond à aucun compte."); 
	            }
	        } catch (DALException e) {
	           
	            e.printStackTrace();
	            bllException.ajouterErreur("Erreur lors de la vérification des informations d'identification");
	        }

	        if (bllException.getErreurs().size() > 0) {
	            throw bllException;
	        }
	    }


}
