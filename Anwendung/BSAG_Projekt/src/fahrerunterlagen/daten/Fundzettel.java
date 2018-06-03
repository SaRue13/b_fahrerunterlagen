package fahrerunterlagen.daten;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="fundzettel")
@SecondaryTable(name = "fahrerunterlagen", pkJoinColumns = @PrimaryKeyJoinColumn(name ="fahrerunterlage_id"))
public class Fundzettel extends Fahrerunterlage {
	
	@Column(name="linie")
	private String linie;
	
	@Column(name="strecke")
	private String strecke;
	
	@Column(name="fundort")
	private String fundort;
	
	@Column(name="wg_nr")
	private int wagenNr;
	
	@Column(name="datum")
	private Date datumZeit;
	
	@Column(name="fundsache")
	private String fundsache;
	
	@Column(name="vom_fahrgast_gefunden")
	private boolean vonFahrgast = false;
	
	@Column(name="ueber_fuenfzig_euro")
	private boolean plus50;
	
	@Column(name="stamm_nr")
	private String stammNr;
	
	@Column(name="b")
	private String b;
	
	@Column(name="finder_name")
	private String nameFinder;
	
	@Column(name="strasse")
	private String strasse;
	
	@Column(name="hausnr")
	private String hausnummer;
	
	@Column(name="plz")
	private int plz;
	
	@Column(name="wohnort")
	private String wohnort;
	
	@Column(name="wert")
	private int wert;
	
	@Column(name="bemerkung")
	private String bemerkung;
	
	@Column(name="sofortige_rueckgabe")
	private boolean sofortigeRueckgabe;
	
	@Column(name="spind_nr")
	private int spind_nr;
	
	public Fundzettel() {
		super();
	}
	
	public int getSpind_nr() {
		return spind_nr;
	}
	public void setSpind_nr(int spind_nr) {
		this.spind_nr = spind_nr;
	}
	public String getLinie() {
		return linie;
	}
	public void setLinie(String linie) {
		this.linie = linie;
	}
	public String getStrecke() {
		return strecke;
	}
	public void setStrecke(String strecke) {
		this.strecke = strecke;
	}
	public String getFundort() {
		return fundort;
	}
	public void setFundort(String fundort) {
		this.fundort = fundort;
	}
	public int getWagenNr() {
		return wagenNr;
	}
	public void setWagenNr(int wagenNr) {
		this.wagenNr = wagenNr;
	}
	public Date getDatumZeit() {
		return datumZeit;
	}
	public void setDatumZeit(Date datumZeit) {
		this.datumZeit = datumZeit;
	}
	public String getFundsache() {
		return fundsache;
	}
	public void setFundsache(String fundsache) {
		this.fundsache = fundsache;
	}
	public boolean isVonFahrgast() {
		return vonFahrgast;
	}
	public void setVonFahrgast(boolean vonFahrgast) {
		this.vonFahrgast = vonFahrgast;
	}
	public boolean isPlus50() {
		return plus50;
	}
	public void setPlus50(boolean plus50) {
		this.plus50 = plus50;
	}
	public String getStammNr() {
		return stammNr;
	}
	public void setStammNr(String stammNr) {
		this.stammNr = stammNr;
	}
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getNameFinder() {
		return nameFinder;
	}
	public void setNameFinder(String nameFinder) {
		this.nameFinder = nameFinder;
	}
	public String getStrasse() {
		return strasse;
	}
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}
	public String getHausnummer() {
		return hausnummer;
	}
	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}
	public int getPlz() {
		return plz;
	}
	public void setPlz(int plz) {
		this.plz = plz;
	}
	public String getWohnort() {
		return wohnort;
	}
	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}
	public int getWert() {
		return wert;
	}
	public void setWert(int wert) {
		this.wert = wert;
	}
	public String getBemerkung() {
		return bemerkung;
	}
	public void setBemerkung(String bemerkung) {
		this.bemerkung = bemerkung;
	}
	public boolean isSofortigeRueckgabe() {
		return sofortigeRueckgabe;
	}
	public void setSofortigeRueckgabe(boolean sofortigeRueckgabe) {
		this.sofortigeRueckgabe = sofortigeRueckgabe;
	}

	@Override
	public String toString() {
		return "Fundzettel [linie=" + linie + ", strecke=" + strecke + ", fundort=" + fundort + ", wagenNr=" + wagenNr
				+ ", datumZeit=" + datumZeit + ", fundsache=" + fundsache + ", vonFahrgast=" + vonFahrgast + ", plus50="
				+ plus50 + ", stammNr=" + stammNr + ", b=" + b + ", nameFinder=" + nameFinder + ", strasse=" + strasse
				+ ", hausnummer=" + hausnummer + ", plz=" + plz + ", wohnort=" + wohnort + ", wert=" + wert
				+ ", bemerkung=" + bemerkung + ", sofortigeRueckgabe=" + sofortigeRueckgabe + ", spind_nr=" + spind_nr
				+", Basisdaten"+super.toString()+ "]";
	}
	
	
	
	
	
}
