package fahrerunterlagen.daten;

import java.util.ArrayList;

import fahrerunterlagen.daten.Fahrerunterlage;

public class Urlaubsantrag extends Fahrerunterlage {
	
	private String fahrer_name; //können wir über P-Nr ermitteln
	private String b;
	private String gl;
	private boolean schulpflicht;
	private boolean osterferien;
	private boolean herbstferien;
	private int urlaubsanspruch;
	private ArrayList<Urlaubstag> urlaubstage;
	
	public Urlaubsantrag() {
		super();
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
	public ArrayList<Urlaubstag> getUrlaubstage() {
		return urlaubstage;
	}
	public void setUrlaubstage(ArrayList<Urlaubstag> urlaubstage) {
		this.urlaubstage = urlaubstage;
	}
	@Override
	public String toString() {
		return "Urlaubsantrag [b=" + b + ", gl=" + gl + ", schulpflicht=" + schulpflicht + ", osterferien="
				+ osterferien + ", herbstferien=" + herbstferien + ", urlaubsanspruch=" + urlaubsanspruch
				+ ", urlaubstage=" + urlaubstage + "]";
	}

	
}
