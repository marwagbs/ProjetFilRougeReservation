package bll;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
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
	
	public Utilisateur insert(String nom, String prenom, String email, String motDePasse, String telephone, Boolean isAdmin) throws BLLException {
		verifierLesDonnees(nom, prenom, email, motDePasse, telephone, isAdmin);
		SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[16];
        secureRandom.nextBytes(saltBytes);
        String salt = new String(saltBytes, StandardCharsets.UTF_8);

        String hashedMotDePasse = hashPassword(motDePasse, salt);
		Utilisateur utilisateur = new Utilisateur(nom, prenom, email, hashedMotDePasse, telephone, isAdmin);
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
		
		if (!validatePassword(motDePasse)) {
			bllException.ajouterErreur("Le mot de passe doit contenir entre 8 et 20 caractères, "
					+ "incluant au moins une lettre majuscule, une lettre minuscule, un chiffre et un caractère spécial");
		}
		if (!validateEmail(email)) {
			bllException.ajouterErreur("l'email n'est pas valide");
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
	
	 private static boolean validatePassword(String motDePasse) {
	        // la regex MDP
	        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";

	        // Création du pattern
	        Pattern pattern = Pattern.compile(regex);

	        // Création du matcher
	        Matcher matcher = pattern.matcher(motDePasse);

	        // Vérification de la correspondance
	        return matcher.matches();
	    }
	 
	 public static boolean validateEmail(String email) {
	        // regex l'e-mail
	        String regex = "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

	        // Création du pattern
	        Pattern pattern = Pattern.compile(regex);

	        // Création du matcher
	        Matcher matcher = pattern.matcher(email);

	        // Vérification de la correspondance
	        return matcher.matches();
	    }
	 
	 public static String  hashPassword(String passwordToHash, String salt) {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-512");

	            // Utiliser SecureRandom pour générer un sel cryptographiquement sécurisé
	            SecureRandom secureRandom = new SecureRandom();
	            byte[] saltBytes = new byte[16];
	            secureRandom.nextBytes(saltBytes);

	            md.update(saltBytes);
	            md.update(passwordToHash.getBytes(StandardCharsets.UTF_8));

	            byte[] bytes = md.digest();

	            // Convertir les octets hachés en une chaîne hexadécimale
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

}
