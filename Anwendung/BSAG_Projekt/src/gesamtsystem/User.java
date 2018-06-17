package gesamtsystem;

import java.util.List;

public class User {
	
	private String pers_nr;
	private String name;
	private String gruppenleiter;
	private String betriebsstaette;
	private List<Rolle> rollen;
	
	public User() {
	}

	public String getPers_nr() {
		return pers_nr;
	}

	public void setPers_nr(String pers_nr) {
		this.pers_nr = pers_nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGruppenleiter() {
		return gruppenleiter;
	}

	public void setGruppenleiter(String gruppenleiter) {
		this.gruppenleiter = gruppenleiter;
	}

	public String getBetriebsstaette() {
		return betriebsstaette;
	}

	public void setBetriebsstaette(String betriebsstaette) {
		this.betriebsstaette = betriebsstaette;
	}

	public List<Rolle> getRollen() {
		return rollen;
	}

	public void setRollen(List<Rolle> rollen) {
		this.rollen = rollen;
	}
	
	public boolean hasRolle(Rolle rolle) {		
		return this.rollen.contains(rolle);
	}
}
