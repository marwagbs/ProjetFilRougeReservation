package bo;

public class TableRes {
	private int id;
	private int nombrePlaces;
	private int numeroTable;
	private String statut;
	private Restaurant restaurant;
	
	public TableRes() {}

	public TableRes(int nombrePlaces, int numeroTable, String statut, Restaurant restaurant) {
		super();
		this.nombrePlaces = nombrePlaces;
		this.numeroTable = numeroTable;
		this.statut = statut;
		this.restaurant = restaurant;
	}

	public TableRes(int id, int nombrePlaces, int numeroTable, String statut, Restaurant restaurant) {
		this.id = id;
		this.nombrePlaces = nombrePlaces;
		this.numeroTable = numeroTable;
		this.statut = statut;
		this.restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNombrePlaces() {
		return nombrePlaces;
	}

	public void setNombrePlaces(int nombrePlaces) {
		this.nombrePlaces = nombrePlaces;
	}

	public int getNumeroTable() {
		return numeroTable;
	}

	public void setNumeroTable(int numeroTable) {
		this.numeroTable = numeroTable;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "TableRes [id=" + id + ", nombrePlaces=" + nombrePlaces + ", numeroTable=" + numeroTable + ", statut="
				+ statut + ", restaurant=" + restaurant + "]";
	}
}
