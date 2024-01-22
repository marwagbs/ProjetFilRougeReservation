package dal;

import java.util.ArrayList;
import java.util.List;

public class DALException extends Exception {
	private static final long serialVersionUID = 1L;
	private List<String> erreurs = new ArrayList<>();
	public DALException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public DALException() {}

	public void ajouterErreur(String erreur) {
		erreurs.add(erreur);
	}
	
	public List<String> getErreurs() {
		return erreurs;
	}
}

