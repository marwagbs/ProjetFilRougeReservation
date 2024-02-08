package bo;

public class Message {
	private int id;
	private String contenu;
	private String sujet;
	private Utilisateur utilisateur;
	private Restaurant Restaurant;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	public Message(String contenu, String sujet, Utilisateur utilisateur, bo.Restaurant restaurant) {
		super();
		this.contenu = contenu;
		this.sujet = sujet;
		this.utilisateur = utilisateur;
		Restaurant = restaurant;
	}

	public Message(int id, String contenu, String sujet, Utilisateur utilisateur, bo.Restaurant restaurant) {
		super();
		this.id = id;
		this.contenu = contenu;
		this.sujet = sujet;
		this.utilisateur = utilisateur;
		Restaurant = restaurant;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getSujet() {
		return sujet;
	}

	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Restaurant getRestaurant() {
		return Restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		Restaurant = restaurant;
	}

	@Override
	public String toString() {
		return "Messages [id=" + id + ", contenu=" + contenu + ", sujet=" + sujet + ", utilisateur=" + utilisateur
				+ ", Restaurant=" + Restaurant + "]";
	}
	
	
	

}
