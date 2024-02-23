package bo;

import java.util.List;

public class Restaurant {
	private int id;
	private String nom;
	private String adresse;
	private String cpo;
	private String ville;
	private Carte carte;
	private List<Horaire> horaire;
	private TableRes tableRes;
	
	public Restaurant() {}
  
	public Restaurant(int id) {
		super();
		this.id = id;
	}

	public Restaurant(int id, String nom, String adresse, String cpo, String ville, Carte carte) {
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.cpo = cpo;
		this.ville = ville;
		this.carte = carte;
	}
	
	public Restaurant(String nom, String adresse, String cpo, String ville, Carte carte) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.cpo = cpo;
		this.ville = ville;
		this.carte = carte;
	}

	public Restaurant(String nom, String adresse, String cpo, String ville) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.cpo = cpo;
		this.ville = ville;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCpo() {
		return cpo;
	}

	public void setCpo(String cpo) {
		this.cpo = cpo;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public Carte getCarte() {
		return carte;
	}

	public void setCarte(Carte carte) {
		this.carte = carte;
	}

	public List<Horaire> getHoraire() {
		return horaire;
	}

	public void setHoraire(List<Horaire> horaire) {
		this.horaire = horaire;
	}

	public TableRes getTableRes() {
		return tableRes;
	}

	public void setTableRes(TableRes tableRes) {
		this.tableRes = tableRes;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", cpo=" + cpo + ", ville=" + ville
				+ ", carte=" + carte + ", horaire=" + horaire + ", tableRes=" + tableRes + "]";
	}


	

	

	

	
	
	
	
	
}
