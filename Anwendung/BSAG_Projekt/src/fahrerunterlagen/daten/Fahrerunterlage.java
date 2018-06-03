package fahrerunterlagen.daten;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import org.hibernate.annotations.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;


@MappedSuperclass
@Table(name="fahrerunterlagen")
public abstract class Fahrerunterlage {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(table="fahrerunterlagen", name="fahrerunterlage_id")
	private int fahrerunterlage_id;
	
	
	@Column(table="fahrerunterlagen", name="typ_id")
	private int typ;
	
	@Column(table="fahrerunterlagen", name="titel")
	private String titel;
	
	//----- Wir brauchen doch alle 3 Datums 
	@Column(table="fahrerunterlagen",name="spreicherzeit")
	private Date speicher_datum; 
	
	@Column(table="fahrerunterlagen",name="einreichungszeit")
	private Date einreichung_datum; 
	// Man kann nach aenderungzeit sortieren und diese Zeit nach jedem Aktivitaet mit diese Unterlage aktualisieren
	@Column(table="fahrerunterlagen",name="aenderungszeit")
	private Date aenderung_datum; 
	//-----
	
	@Column(table="fahrerunterlagen", name="status")
	private String status;
	
	@Column(table="fahrerunterlagen", name="fahrer_pers_nr")
	private String p_nr_fahrer;
	
	public Fahrerunterlage() {
		//super();
	}
	public Date getSpeicher_datum() {
		return speicher_datum;
	}
	public void setSpeicher_datum(Date speicher_datum) {
		this.speicher_datum = speicher_datum;
	}
	public Date getEinreichung_datum() {
		return einreichung_datum;
	}
	public void setEinreichung_datum(Date einreichung_datum) {
		this.einreichung_datum = einreichung_datum;
	}
	public Date getAenderung_datum() {
		return aenderung_datum;
	}
	public void setAenderung_datum(Date aenderung_datum) {
		this.aenderung_datum = aenderung_datum;
	}
	public int getFahrerunterlage_id() {
		return fahrerunterlage_id;
	}
	public void setFahrerunterlage_id(int fahrerunterlage_id) {
		this.fahrerunterlage_id = fahrerunterlage_id;
	}
	public int getTyp() {
		return typ;
	}
	public void setTyp(int typ) {
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
				+ ", speicher_datum=" + speicher_datum + ", einreichung_datum=" + einreichung_datum
				+ ", aenderung_datum=" + aenderung_datum + ", status=" + status + ", p_nr_fahrer=" + p_nr_fahrer + "]";
	}

}
