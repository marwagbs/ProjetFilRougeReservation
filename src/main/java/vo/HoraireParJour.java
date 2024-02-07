package vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HoraireParJour {
	private List<String> jours = new ArrayList<>();
	private Map<String, List<Horaire>> horaires = new HashMap<>();
	
	public HoraireParJour() {}
	
	public void ajouterHoraire(String jour, Horaire horaire) {
		if (horaires.containsKey(jour)) {
			List<Horaire> horairesList = horaires.get(jour);
			horairesList.add(horaire);
			horaires.put(jour, horairesList);
		} else {
			List<Horaire> horairesList = new ArrayList<>();
			jours.add(jour);
			horairesList.add(horaire);
			horaires.put(jour, horairesList);
		}
	}

	public Map<String, List<Horaire>> getHoraires() {
		return horaires;
	}

	public void setHoraires(Map<String, List<Horaire>> horaires) {
		this.horaires = horaires;
	}
	
	public List<String> getJours() {
		return jours;
	}

	public void setJours(List<String> jours) {
		this.jours = jours;
	}
}
