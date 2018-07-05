package fahrerunterlagen.daten;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

@Entity
@Table(name="verspaetungsmeldung")
@SecondaryTable(name = "fahrerunterlagen", pkJoinColumns = @PrimaryKeyJoinColumn(name ="fahrerunterlage_id"))
public class Verspaetungsmeldung extends Fahrerunterlage {
	
	private boolean anordnung; //neu machen bitte =)
	
	@Column(name="datum")
	private Date datum;
	
	@Column(name="dienst_nr")
	private int dienst_nr;
	
	@Column(name="schlusszeit_der_dienst_nr") //als Date
	private String schlusszeit_der_dienst_nr;
	
	@Column(name="verspaetungs_bis") //als Date
	private LocalTime verspaetungs_bis;
	
	@Column(name="soll_ist_vergleich")
	private boolean soll_ist_vergleich;
	
	@Column(name="diensttauschkonto")
	private boolean diensttauschkonto;
	
	@Column(name="begruendung")
	private String begruendung;
	
	@Column(name="dienstnr_angeordnet") //entfällt
	private int dienstnr_angeordnet;
	
	@Column(name="schlusszeit_der_dienstnr_andeordnet") //entfällt
	private String schlusszeit_der_dienstnr_andeordnet;
	
	@Column(name="verspaetung_bis_angeordnet") //entfällt
	private LocalTime verspaetung_bis_angeordnet;
	
	@Column(name="verspaetung") 
	private String verspaetung;
	


	@Column(name="verspaetung_angeordnet") //entfällt
	private String verspaetung_angeordnet;
	
	
	@Column(name="angeordnet_durch")
	private String angeordnet_durch;
	
	
	public boolean isAnordnung() {
		return anordnung;
	}

	public void setAnordnung(boolean anordnung) {
		this.anordnung = anordnung;
	}

	public Verspaetungsmeldung() {
		super();
		this.setTyp(5);
	}

	public String getVerspaetung() {
		return verspaetung;
	}

	public void setVerspaetung(String verspaetung) {
		this.verspaetung = verspaetung;
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getVerspaetung_angeordnet() {
		return verspaetung_angeordnet;
	}

	public void setVerspaetung_angeordnet(String verspaetung_angeordnet) {
		this.verspaetung_angeordnet = verspaetung_angeordnet;
	}

	public int getDienst_nr() {
		return dienst_nr;
	}

	public void setDienst_nr(int dienst_nr) {
		this.dienst_nr = dienst_nr;
	}

	public String getSchlusszeit_der_dienst_nr() {
		return schlusszeit_der_dienst_nr;
	}

	public void setSchlusszeit_der_dienst_nr(String schlusszeit_der_dienst_nr) {
		this.schlusszeit_der_dienst_nr = schlusszeit_der_dienst_nr;
	}

	public LocalTime getVerspaetungs_bis() {
		return verspaetungs_bis;
	}

	public void setVerspaetungs_bis(LocalTime verspaetungs_bis) {
		this.verspaetungs_bis = verspaetungs_bis;
	}


	public boolean isSoll_ist_vergleich() {
		return soll_ist_vergleich;
	}

	public void setSoll_ist_vergleich(boolean soll_ist_vergleich) {
		this.soll_ist_vergleich = soll_ist_vergleich;
	}

	public boolean isDiensttauschkonto() {
		return diensttauschkonto;
	}

	public void setDiensttauschkonto(boolean diensttauschkonto) {
		this.diensttauschkonto = diensttauschkonto;
	}

	public String getBegruendung() {
		return begruendung;
	}

	public void setBegruendung(String begruendung) {
		this.begruendung = begruendung;
	}

	public int getDienstnr_angeordnet() {
		return dienstnr_angeordnet;
	}

	public void setDienstnr_angeordnet(int dienstnr_angeordnet) {
		this.dienstnr_angeordnet = dienstnr_angeordnet;
	}

	public String getSchlusszeit_der_dienstnr_andeordnet() {
		return schlusszeit_der_dienstnr_andeordnet;
	}

	public void setSchlusszeit_der_dienstnr_andeordnet(String schlusszeit_der_dienstnr_andeordnet) {
		this.schlusszeit_der_dienstnr_andeordnet = schlusszeit_der_dienstnr_andeordnet;
	}

	public LocalTime getVerspaetung_bis_angeordnet() {
		return verspaetung_bis_angeordnet;
	}

	public void setVerspaetung_bis_angeordnet(LocalTime verspaetung_bis_angeordnet) {
		this.verspaetung_bis_angeordnet = verspaetung_bis_angeordnet;
	}

	
	public String getAngeordnet_durch() {
		return angeordnet_durch;
	}

	public void setAngeordnet_durch(String angeordnet_durch) {
		this.angeordnet_durch = angeordnet_durch;
	}

	@Override
	public String toString() {
		return "Verspaetungsmeldung [datum=" + datum + ", dienst_nr=" + dienst_nr + ", schlusszeit_der_dienst_nr="
				+ schlusszeit_der_dienst_nr + ", verspaetungs_bis=" + verspaetungs_bis + ", verspaetung=" + verspaetung + ", soll_ist_vergleich=" + soll_ist_vergleich
				+ ", diensttauschkonto=" + diensttauschkonto + ", begruendung=" + begruendung + ", dienstnr_angeordnet="
				+ dienstnr_angeordnet + ", schlusszeit_der_dienstnr_andeordnet=" + schlusszeit_der_dienstnr_andeordnet
				+ ", verspaetung_bis_angeordnet=" + verspaetung_bis_angeordnet + ", verspaetung_angeordnet="
				+ verspaetung_angeordnet + ", angeordnet_durch=" + angeordnet_durch +", Basisdaten"+super.toString()+ "]";
	}

}
