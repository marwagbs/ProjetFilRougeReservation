package vo;

public class Horaire {

	private String ouverture;
	private String fermeture;
	
	public Horaire() {}

	public String getOuverture() {
		return ouverture;
	}

	public Horaire(String ouverture, String fermeture) {
		this.ouverture = ouverture;
		this.fermeture = fermeture;
	}

	public void setOuverture(String ouverture) {
		this.ouverture = ouverture;
	}

	public String getFermeture() {
		return fermeture;
	}

	public void setFermeture(String fermeture) {
		this.fermeture = fermeture;
	}
}
