package bo;


import java.time.LocalTime;

public class Horaire {

	private int id;
	private String jour;
	private LocalTime heureOuverture;
	private LocalTime heureFermeture;

	
	
	// Constructeur avec tous les champs
	public Horaire(int id, String jour, LocalTime heureOuverture, LocalTime heureFermeture) {
		
		this.id = id;
		this.jour = jour;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}


	// Constructor sans id
	public Horaire(String jour, LocalTime heureOuverture, LocalTime heureFermeture) {
		this.jour = jour;
		this.heureOuverture = heureOuverture;
		this.heureFermeture = heureFermeture;
	}

	// Constructeur vide 
	public Horaire() {

	}
// Getters et Setters

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getJour() {
		return jour;
	}


	public void setJour(String jour) {
		this.jour = jour;
	}


	public LocalTime getHeureOuverture() {
		return heureOuverture;
	}


	public void setHeureOuverture(LocalTime localTime) {
		this.heureOuverture = localTime;
	}


	public LocalTime getHeureFermeture() {
		return heureFermeture;
	}


	public void setHeureFermeture(LocalTime heureFermeture) {
		this.heureFermeture = heureFermeture;
	}

// To String
	
	@Override
	public String toString() {
		return "Horaire [id=" + id + ", jour=" + jour + ", heureOuverture=" + heureOuverture + ", heureFermeture="
				+ heureFermeture + "]";
	}
	
	
}
