package bo;

public class Produit {
	private int id;
	private String nom;
	private String description;
	private float prix;
	private Categorie categorie;
	private Carte carte;
	
	
	public Produit(int id, String nom, String description, float prix, Categorie categorie, Carte carte) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.categorie = categorie;
		this.setCarte(carte);
	}


	public Produit(String nom, String description, float prix, Categorie categorie) {
		super();
		this.nom = nom;
		this.description = description;
		this.prix = prix;
		this.categorie = categorie;
		
	}


	public Produit() {
		super();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public float getPrix() {
		return prix;
	}


	public void setPrix(float prix) {
		this.prix = prix;
	}


	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}



	public Carte getCarte() {
		return carte;
	}


	public void setCarte(Carte carte) {
		this.carte = carte;
	}


	@Override
	public String toString() {
		return "Produit [id=" + id + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", categorie="
				+ categorie + ", carte=" + carte + "]";
	}


	
	
	

}
