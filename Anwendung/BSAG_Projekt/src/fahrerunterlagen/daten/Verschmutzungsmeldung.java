package fahrerunterlagen.daten;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="verschmutzungsmeldung")
@SecondaryTable(name = "fahrerunterlagen", pkJoinColumns = @PrimaryKeyJoinColumn(name ="fahrerunterlage_id"))
public class Verschmutzungsmeldung extends Fahrerunterlage {
	
	@Column(name="datum")
	private Date datum; 
	
	@Column(name="wagen_nr")
	private int wagen_nr;
	
	@Column(name="linie")
	private String linie;
	
	@Column(name="verunreinigt_ausgewechselt")
	private boolean verunreinigt_ausgewechselt;
	
	@Column(name="verursacher_ermittelt")
	private boolean verursacher_ermittelt;
	
	@Column(name="reinigungskosten")
	private int reinigungskosten;
	
	@Column(name="reinigungskosten_bezahlt")
	private boolean reinigungskosten_bezahlt;
	
	@Column(name="verunreinigungsart")
	private String verunreinigungsart;
	
	@Column(name="verursachersname")
	private String verursachersname;
	
	@Column(name="verursachersalter")
	private int verursachersalter;
	
	@Column(name="verursachersstrasse")
	private String verursachersstrasse; 
	
	@Column(name="verursachersstadt_city")
	private String verursachersstadt_city;
	
	@Column(name="gesetzlicher_vertreter")
	private String gesetzlicher_vertreter;
	
	@Column(name="pers_nr_b")
	private String pers_nr_b;
	
	@Column(name="pers_nr_f")
	private String pers_nr_f;
	
	@Column(name="dienststelle")
	private String dienststelle;
	
	@Column(name="einzuzahlen")
	private int einzuzahlen;
	
	public Verschmutzungsmeldung() {
		super();
		this.setTyp(3);
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getWagen_nr() {
		return wagen_nr;
	}

	public void setWagen_nr(int wagen_nr) {
		this.wagen_nr = wagen_nr;
	}

	public String getLinie() {
		return linie;
	}

	public void setLinie(String linie) {
		this.linie = linie;
	}

	public boolean isVerunreinigt_ausgewechselt() {
		return verunreinigt_ausgewechselt;
	}

	public void setVerunreinigt_ausgewechselt(boolean verunreinigt_ausgewechselt) {
		this.verunreinigt_ausgewechselt = verunreinigt_ausgewechselt;
	}

	public boolean isVerursacher_ermittelt() {
		return verursacher_ermittelt;
	}

	public void setVerursacher_ermittelt(boolean verursacher_ermittelt) {
		this.verursacher_ermittelt = verursacher_ermittelt;
	}

	public int getReinigungskosten() {
		return reinigungskosten;
	}

	public void setReinigungskosten(int reinigungskosten) {
		this.reinigungskosten = reinigungskosten;
	}

	public boolean isReinigungskosten_bezahlt() {
		return reinigungskosten_bezahlt;
	}

	public void setReinigungskosten_bezahlt(boolean reinigungskosten_bezahlt) {
		this.reinigungskosten_bezahlt = reinigungskosten_bezahlt;
	}

	public String getVerunreinigungsart() {
		return verunreinigungsart;
	}

	public void setVerunreinigungsart(String verunreinigungsart) {
		this.verunreinigungsart = verunreinigungsart;
	}

	public String getVerursachersname() {
		return verursachersname;
	}

	public void setVerursachersname(String verursachersname) {
		this.verursachersname = verursachersname;
	}

	public int getVerursachersalter() {
		return verursachersalter;
	}

	public void setVerursachersalter(int verursachersalter) {
		this.verursachersalter = verursachersalter;
	}

	public String getVerursachersstrasse() {
		return verursachersstrasse;
	}

	public void setVerursachersstrasse(String verursachersstrasse) {
		this.verursachersstrasse = verursachersstrasse;
	}

	public String getVerursachersstadt_city() {
		return verursachersstadt_city;
	}

	public void setVerursachersstadt_city(String verursachersstadt_city) {
		this.verursachersstadt_city = verursachersstadt_city;
	}

	public String getGesetzlicher_vertreter() {
		return gesetzlicher_vertreter;
	}

	public void setGesetzlicher_vertreter(String gesetzlicher_vertreter) {
		this.gesetzlicher_vertreter = gesetzlicher_vertreter;
	}

	public String getPers_nr_b() {
		return pers_nr_b;
	}

	public void setPers_nr_b(String pers_nr_b) {
		this.pers_nr_b = pers_nr_b;
	}

	public String getPers_nr_f() {
		return pers_nr_f;
	}

	public void setPers_nr_f(String pers_nr_f) {
		this.pers_nr_f = pers_nr_f;
	}

	public String getDienststelle() {
		return dienststelle;
	}

	public void setDienststelle(String dienststelle) {
		this.dienststelle = dienststelle;
	}

	public int getEinzuzahlen() {
		return einzuzahlen;
	}

	public void setEinzuzahlen(int einzuzahlen) {
		this.einzuzahlen = einzuzahlen;
	}

	@Override
	public String toString() {
		return "Verschmutzungsmeldung [datum=" + datum + ", wagen_nr=" + wagen_nr + ", linie=" + linie
				+ ", verunreinigt_ausgewechselt=" + verunreinigt_ausgewechselt + ", verursacher_ermittelt="
				+ verursacher_ermittelt + ", reinigungskosten=" + reinigungskosten + ", reinigungskosten_bezahlt="
				+ reinigungskosten_bezahlt + ", verunreinigungsart=" + verunreinigungsart + ", verursachersname="
				+ verursachersname + ", verursachersalter=" + verursachersalter + ", verursachersstrasse="
				+ verursachersstrasse + ", verursachersstadt_city=" + verursachersstadt_city
				+ ", gesetzlicher_vertreter=" + gesetzlicher_vertreter + ", pers_nr_b=" + pers_nr_b + ", pers_nr_f="
				+ pers_nr_f + ", dienststelle=" + dienststelle + ", einzuzahlen=" + einzuzahlen +", Basisdaten"+super.toString()+  "]";
	}
		

}
