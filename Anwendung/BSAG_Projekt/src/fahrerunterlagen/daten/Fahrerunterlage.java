package fahrerunterlagen.daten;

import java.util.Date;

public abstract class Fahrerunterlage {
	
	private int fahrerunterlage_id;
	private String typ;
	private String titel;
	/*
	 * Für das Sortieren nach Aktualität: wenn eingereicht, hier rein das Einreichungsdatum,
	 * wenn bearbeitet, hier das Bearbeitungsdatum, wenn nur gespeichert, hier das 
	 * Speicherdatum
	 */
	private Date datum_sortiern; 
	private String status;
	private String p_nr_fahrer;
	
	public Fahrerunterlage() {
		super();
	}
	
	
	public Date getDatum_sortiern() {
		return datum_sortiern;
	}


	public void setDatum_sortiern(Date datum_sortiern) {
		this.datum_sortiern = datum_sortiern;
	}


	public int getFahrerunterlage_id() {
		return fahrerunterlage_id;
	}
	public void setFahrerunterlage_id(int fahrerunterlage_id) {
		this.fahrerunterlage_id = fahrerunterlage_id;
	}
	public String getTyp() {
		return typ;
	}
	public void setTyp(String typ) {
		this.typ = typ;
	}
	public String getTitel() {
		return titel;
	}
	public void setTitel(String titel) {
		this.titel = titel;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getP_nr_fahrer() {
		return p_nr_fahrer;
	}
	public void setP_nr_fahrer(String p_nr_fahrer) {
		this.p_nr_fahrer = p_nr_fahrer;
	}


	@Override
	public String toString() {
		return "Fahrerunterlage [fahrerunterlage_id=" + fahrerunterlage_id + ", typ=" + typ + ", titel=" + titel
				+ ", datum_sortiern=" + datum_sortiern + ", status=" + status + ", p_nr_fahrer=" + p_nr_fahrer + "]";
	}
	
	

}
