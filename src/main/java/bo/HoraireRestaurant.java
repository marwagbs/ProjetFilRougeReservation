package bo;

public class HoraireRestaurant {
private int id; 
private Horaire horaire;
private Restaurant restaurant;



// Constructeur avec tous les champs 
public HoraireRestaurant(int id, Horaire horaire, Restaurant restaurant) {
	this.id = id;
	this.horaire = horaire;
	this.restaurant = restaurant;
}

//Constructeur vide
public HoraireRestaurant() {

}

//Constructeur sans le ID
public HoraireRestaurant(Horaire horaire, Restaurant restaurant) {
	this.horaire = horaire;
	this.restaurant = restaurant;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public Horaire getHoraire() {
	return horaire;
}

public void setHoraire(Horaire horaire) {
	this.horaire = horaire;
}

public Restaurant getRestaurant() {
	return restaurant;
}

public void setRestaurant(Restaurant restaurant) {
	this.restaurant = restaurant;
}

@Override
public String toString() {
	return "HoraireRestaurant [id=" + id + ", horaire=" + horaire + ", restaurant=" + restaurant + "]";
}





}
