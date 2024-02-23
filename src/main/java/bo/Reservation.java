package bo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {


	private int id;
	private  LocalDate dateRes; 
	private LocalTime heure;
	private int nbrPersonnes;
	private Utilisateur utilisateur;
	private Restaurant restaurant; 
	private String statut;
	private String commentaire;	
	private TableRes tableRes;



// Constructeur avec tous les champs 
public Reservation(int id, LocalDate dateRes, LocalTime heure, int nbrPersonnes, Utilisateur utilisateur,
		Restaurant restaurant, String statut, String commentaire) {
	
	this.id = id;
	this.dateRes = dateRes;
	this.heure = heure;
	this.nbrPersonnes = nbrPersonnes;
	this.utilisateur = utilisateur;
	this.restaurant = restaurant;
	this.statut = statut;
	this.commentaire = commentaire;
}
// Constructeur sans l'id

public Reservation(LocalDate dateRes, LocalTime heure, int nbrPersonnes, Utilisateur utilisateur, Restaurant restaurant,
		String statut, String commentaire) {
	
	this.dateRes = dateRes;
	this.heure = heure;
	this.nbrPersonnes = nbrPersonnes;
	this.utilisateur = utilisateur;
	this.restaurant = restaurant;
	this.statut = statut;
	this.commentaire = commentaire;
}


// Constructeur vide
public Reservation() {
	
}
 // Consructeur sans Utilisateur, statut et id





// Getters et Setters 

public int getId() {
	return id;
}




public void setId(int id) {
	this.id = id;
}



public LocalDate getDateRes() {
	return dateRes;
}



public void setDateRes(LocalDate dateRes) {
	this.dateRes = dateRes;
}



public LocalTime getHeure() {
	return heure;
}



public void setHeure(LocalTime heure) {
	this.heure = heure;
}



public int getNbrPersonnes() {
	return nbrPersonnes;
}



public void setNbrPersonnes(int nbrPersonnes) {
	this.nbrPersonnes = nbrPersonnes;
}



public Utilisateur getUtilisateur() {
	return utilisateur;
}



public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}



public Restaurant getRestaurant() {
	return restaurant;
}



public void setRestaurant(Restaurant restaurant) {
	this.restaurant = restaurant;
}



public String getStatut() {
	return statut;
}



public void setStatut(String statut) {
	this.statut = statut;
}



public String getCommentaire() {
	return commentaire;
}



public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
}

public TableRes getTableRes() {
	return tableRes;
}

public void setTableRes(TableRes tableRes) {
	this.tableRes = tableRes;
}



}
