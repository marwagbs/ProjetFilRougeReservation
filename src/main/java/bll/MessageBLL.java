package bll;

import java.util.List;

import bo.Message;
import bo.Restaurant;
import bo.Utilisateur;
import dal.DALException;
import dal.MessageDAO;

public class MessageBLL {
	private MessageDAO dao;
	
	public MessageBLL() throws BLLException {
		try {
			dao = new MessageDAO();
		} catch (DALException e) {
			throw new BLLException("Echec de la connexion", e);
		}
	}
	
	public List<Message> selectAll() throws BLLException {
		try {
			return dao.selectAll();
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération des messages", e);
		}
	}
	
	public Message selectById(int id) throws BLLException {
		try {
			return dao.selectById(id);
		} catch (DALException e) {
			throw new BLLException("Echec de la récupération d'id " + id, e);
		}
	}
	
	public Message insert(String contenu , String sujet, Utilisateur utilisateur, Restaurant restaurant) throws BLLException {
		
		BLLException bll=new BLLException();
		Message message = new Message(contenu, sujet, utilisateur, restaurant);
		
		if (contenu.length() < 2 || contenu.length() > 250) {

			bll.ajouterErreur("Le contenu doit faire entre 2 et 250 caractères");

		}
		if (sujet.length() < 2 || sujet.length() > 100) {

			bll.ajouterErreur("Le  sujet doit faire entre 2 et 100 caractères");

		}
		if (sujet==null || sujet.isBlank()){

				bll.ajouterErreur("le sujet et le contenu doit etre renseigné");
			}
		if (contenu==null || contenu.isBlank()){

			bll.ajouterErreur("Le contenu doit etre renseigné");
		}
		if (restaurant==null){

			bll.ajouterErreur("Le restaurant doit etre renseigné");
		}
		
		 if (bll.getErreurs().size()>0) {
	        	throw bll;
	        	
		 }
		try {
			dao.insert(message);
		} catch (DALException e) {
			throw new BLLException("Echec de l'insertion", e);
		}
		return message;
	}
	
	public void update(Message message) throws BLLException {
		if (message.getContenu().length() < 2 || message.getContenu().length() > 250) {

			throw new BLLException("Le contenu doit faire entre 2 et 250 caractères", null);

		}
		if (message.getSujet().length() < 2 || message.getSujet().length() > 100) {

			throw new BLLException("Le  sujet doit faire entre 2 et 100 caractères", null);

		}
		
		try {
			dao.update(message);
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
