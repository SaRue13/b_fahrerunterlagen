package fahrerunterlagen.daten;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="wagenlaufkarte")
@SecondaryTable(name = "fahrerunterlagen", pkJoinColumns = @PrimaryKeyJoinColumn(name ="fahrerunterlage_id"))
public class Wagenlaufkarte extends Fahrerunterlage {

	@Column(name="wagen_nr")
	private int wagen_nr;
	
	@Column(name="betriebsstelle")
	private String betriebsstelle;
	
	@Column(name="datum")
	private Date datum;
	
	@Column(name="kurs_nr")
	private int kurs_nr;
	
	@Column(name="dienst_nr")
	private int dienst_nr;
	
	@Column(name="anfangszeit")
	private LocalTime anfangszeit;

	@Column(name="anfangsort")
	private String anfangsort;
	
	@Column(name="endezeit")
	private LocalTime endezeit;
	
	@Column(name="endeort")
	private String endeort;
	
	@Column(name="entwerter_nr")
	private int entwerter_nr;
	
	@Column(name="fahrttyp")
	private String fahrttyp;

	@Column(name="km_start")
	private int km_start;
	
	@Column(name="km_ende")
	private int km_ende;
	
	@Column(name="bemerkung")
	private String bemerkung;

	public Wagenlaufkarte() {
		super();
		this.setTyp(4);
	}

	public int getWagen_nr() {
		return wagen_nr;
	}

	public void setWagen_nr(int wagen_nr) {
		this.wagen_nr = wagen_nr;
	}

	public String getBetriebsstelle() {
		return betriebsstelle;
	}

	public void setBetriebsstelle(String betriebsstelle) {
		this.betriebsstelle = betriebsstelle;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public int getKurs_nr() {
		return kurs_nr;
	}

	public void setKurs_nr(int kurs_nr) {
		this.kurs_nr = kurs_nr;
	}

	public int getDienst_nr() {
		return dienst_nr;
	}

	public void setDienst_nr(int dienst_nr) {
		this.dienst_nr = dienst_nr;
	}

	public LocalTime getAnfangszeit() {
		return anfangszeit;
	}

	public void setAnfangszeit(LocalTime anfangszeit) {
		this.anfangszeit = anfangszeit;
	}

	public String getAnfangsort() {
		return anfangsort;
	}

	public void setAnfangsort(String anfangsort) {
		this.anfangsort = anfangsort;
	}

	public LocalTime getEndezeit() {
		return endezeit;
	}

	public void setEndezeit(LocalTime endezeit) {
		this.endezeit = endezeit;
	}

	public String getEndeort() {
		return endeort;
	}

	public void setEndeort(String endeort) {
		this.endeort = endeort;
	}

	public int getEntwerter_nr() {
		return entwerter_nr;
	}

	public void setEntwerter_nr(int entwerter_nr) {
		this.entwerter_nr = entwerter_nr;
	}

	public String getFahrttyp() {
		return fahrttyp;
	}

	public void setFahrttyp(String fahrttyp) {
		this.fahrttyp = fahrttyp;
	}

	public int getKm_start() {
		return km_start;
	}

	public void setKm_start(int km_start) {
		this.km_start = km_start;
	}

	public int getKm_ende() {
		return km_ende;
	}

	public void setKm_ende(int km_ende) {
		this.km_ende = km_ende;
	}

	public String getBemerkung() {
		return bemerkung;
	}

	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}

	@Override
	public String toString() {
		return "Wagenlaufkarte [wagen_nr=" + wagen_nr + ", betriebsstelle=" + betriebsstelle + ", datum=" + datum
				+ ", kurs_nr=" + kurs_nr + ", dienst_nr=" + dienst_nr + ", anfangszeit=" + anfangszeit + ", anfangsort="
				+ anfangsort + ", endezeit=" + endezeit + ", endeort=" + endeort + ", entwerter_nr=" + entwerter_nr
				+ ", fahrttyp=" + fahrttyp + ", km_start=" + km_start + ", km_ende=" + km_ende + ", bemerkung="
				+ bemerkung +", Basisdaten"+super.toString()+ "]";
	}
	
	
	
	
}
