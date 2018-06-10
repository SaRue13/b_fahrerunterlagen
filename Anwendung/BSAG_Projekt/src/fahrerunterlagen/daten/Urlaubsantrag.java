 package fahrerunterlagen.daten;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;

import fahrerunterlagen.daten.Fahrerunterlage;

@Entity
@Table(name="urlaubsantrag")
@SecondaryTable(name = "fahrerunterlagen", pkJoinColumns = @PrimaryKeyJoinColumn(name ="fahrerunterlage_id"))
public class Urlaubsantrag extends Fahrerunterlage {
	
	//private String fahrer_name; //können wir über P-Nr ermitteln
	@Column(name="b")
	private String b;
	
	@Column(name="gl")
	private String gl;
	
	@Column(name="schulpflicht")
	private boolean schulpflicht;
	
	@Column(name="osterferien")
	private boolean osterferien;
	
	@Column(name="herbstferien")
	private boolean herbstferien;
	
	@Column(name="urlaubsanspruch")
	private int urlaubsanspruch;
	
	//private ArrayList<Urlaubstag> urlaubstage;
	
	public Urlaubsantrag() {
		super();
		this.setTyp(2);
	}
	
	public String getB() {
		return b;
	}
	public void setB(String b) {
		this.b = b;
	}
	public String getGl() {
		return gl;
	}
	public void setGl(String gl) {
		this.gl = gl;
	}
	public boolean isSchulpflicht() {
		return schulpflicht;
	}
	public void setSchulpflicht(boolean schulpflicht) {
		this.schulpflicht = schulpflicht;
	}
	public boolean isOsterferien() {
		return osterferien;
	}
	public void setOsterferien(boolean osterferien) {
		this.osterferien = osterferien;
	}
	public boolean isHerbstferien() {
		return herbstferien;
	}
	public void setHerbstferien(boolean herbstferien) {
		this.herbstferien = herbstferien;
	}
	public int getUrlaubsanspruch() {
		return urlaubsanspruch;
	}
	public void setUrlaubsanspruch(int urlaubsanspruch) {
		this.urlaubsanspruch = urlaubsanspruch;
	}
	/**
	public ArrayList<Urlaubstag> getUrlaubstage() {
		return urlaubstage;
	}
	public void setUrlaubstage(ArrayList<Urlaubstag> urlaubstage) {
		this.urlaubstage = urlaubstage;
	}
	*/
	@Override
	public String toString() {
		return "Urlaubsantrag [b=" + b + ", gl=" + gl + ", schulpflicht=" + schulpflicht + ", osterferien="
				+ osterferien + ", herbstferien=" + herbstferien + ", urlaubsanspruch=" + urlaubsanspruch
				+ ", urlaubstage=" /*+ urlaubstage*/ + "]";
	}

	
}
