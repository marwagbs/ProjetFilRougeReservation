package bo;

public class Carte {
	private int id;
	private String libelle;
	private Restaurant restaurant;
	
	public Carte() {}

	public Carte(String libelle) {
		this.libelle = libelle;
	}

	
	public Carte(int id, String libelle, Restaurant restaurant) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.restaurant = restaurant;
	}

	public Carte(String libelle, Restaurant restaurant) {
		super();
		this.libelle = libelle;
		this.restaurant = restaurant;
	}

	public Carte(int id, String libelle) {
		this.id = id;
		this.libelle = libelle;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Carte [id=" + id + ", libelle=" + libelle + ", restaurant=" + restaurant + "]";
	}
	
	
	
}
