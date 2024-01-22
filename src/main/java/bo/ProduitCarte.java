package bo;

public class ProduitCarte {
	private int id;
	private Carte carte;
    private Produit produit;
	public ProduitCarte(int id, Produit produit, Carte carte) {
		
		this.id = id;
		this.carte = carte;
		this.produit = produit;
	}
	
	public ProduitCarte(Produit produit, Carte carte) {
	
		this.carte = carte;
		this.produit = produit;
	}

	public ProduitCarte() {
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Carte getCarte() {
		return carte;
	}
	public void setCarte(Carte carte) {
		this.carte = carte;
	}
	public Produit getProduit() {
		return produit;
	}
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String toString() {
		return "ProduitCarte [id=" + id + ", carte=" + carte + ", produit=" + produit + "]";
	}
    
    


}
