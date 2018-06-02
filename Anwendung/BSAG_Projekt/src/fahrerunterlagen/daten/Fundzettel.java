package fahrerunterlagen.daten;

import java.util.Date;

public class Fundzettel extends Fahrerunterlage {
	//!!!!!!!!!Ist die einzie FAhrerunterlage wo nicht nach der P-Nr gefragt wird, ich pack die dazu

	private int linie;
	private String strecke;
	private String fundort;
	private String wagenNr;
	private Date datumZeit;
	private String fundsache;
	private boolean vonFahrgast = false;
	private boolean plus50;
	private String stammNr;
	private String b;
	private String nameFinder;
	private String strasse;
	private String hausnummer;
	private int plz;
	private String wohnort;
	private int wert;
	private String bemerkung;
	private boolean sofortigeRueckgabe;
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

	public int getLinie() {
		return linie;
	}
	public void setLinie(int linie) {
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
	public String getWagenNr() {
		return wagenNr;
	}
	public void setWagenNr(String wagenNr) {
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
		System.out.println("von Fahrgast = "+vonFahrgast);
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
				+ ", BASISDATEN: "+super.toString()+ "]";
	}
	
	
}
