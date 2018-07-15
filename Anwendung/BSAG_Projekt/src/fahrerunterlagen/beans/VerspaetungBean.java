package fahrerunterlagen.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Verspaetungsmeldung;
import fahrerunterlagen.service.FahrerunterlagenService;

/*
 * Bean die Funktionalitäten der Verspaetungsmeldung verwaltet.
 */

@ManagedBean
@SessionScoped
public class VerspaetungBean {
	//Liste 1 geladener VerspMeld (gespeicherte)
	
	//Liste 2 geladener VerspMeld (eingereichte)
	
	private Verspaetungsmeldung verspmeldung; //Referenziertes Verspaetungsmeldungsobjekt
	private List<Fahrerunterlage> meldungen; //gespeicherte Verspaetungsmeldungen
	private List<Fahrerunterlage> meldungen2; //Eingereichte Verspaetungsmeldungen
	private FahrerunterlagenService fahrerunterlagenService;
	@ManagedProperty("#{mainBean}")
	private MainBean mainBean;
	
	
	@PostConstruct
	public void init() {
		//System.out.println("init");
		verspmeldung = new Verspaetungsmeldung();
		fahrerunterlagenService = new FahrerunterlagenService();
		ladeMeldungen();
		ladeMeldungen2();
		verspmeldung.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
	}
	
	
	public Verspaetungsmeldung getVerspmeldung() {
		return verspmeldung;
	}


	public void setVerspmeldung(Verspaetungsmeldung verspmeldung) {
		this.verspmeldung = verspmeldung;
	}


	public List<Fahrerunterlage> getMeldungen() {
		return meldungen;
	}


	public void setMeldungen(List<Fahrerunterlage> meldungen) {
		this.meldungen = meldungen;
	}


	public List<Fahrerunterlage> getMeldungen2() {
		return meldungen2;
	}


	public void setMeldungen2(List<Fahrerunterlage> meldungen2) {
		this.meldungen2 = meldungen2;
	}


	public MainBean getMainBean() {
		return mainBean;
	}


	public void setMainBean(MainBean mainBean) {
		this.mainBean = mainBean;
	}

	/*
	 * Lädt eine Liste gespeicherter Verspätungsmeldungen.
	 */
	private void ladeMeldungen(){
		meldungen = fahrerunterlagenService.findeFahrerUnterlagen(mainBean.getUserHandler().getPers_nr(), false, 5);
	}
	
	/*
	 * Lädt eine Liste eingereichter Verspätungsmeldungen.
	 */
	private void ladeMeldungen2(){
		meldungen2 = fahrerunterlagenService.findeFahrerUnterlagen(mainBean.getUserHandler().getPers_nr(), true, 5);
		//System.out.println("Verspmeld, eingereichte: "+meldungen2.size());
	}
	
	/*
	 * Leitet auf die Detailansicht (das Formular) für Entwürfe weiter.
	 */
	public String details() {
		return "fahrerunterlagen_form_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	/*
	 * Leitet auf die Detailansicht (das Formular) für Einreichungen weiter.
	 */
	public String details2() {
		return "fahrerunterlagen_form_Verspaetungsmeldung2.xhtml?faces-redirect=true";
	}

	/*
	 *  Leitet auf die Detailansicht (das Formular) für Entwürfe weiter und setzt
	 *  dabei ein neues Verspaetungsmeldungsobjekt als Referenz.
	 */
	public String neu() {
		verspmeldung = new Verspaetungsmeldung();
		verspmeldung.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
		return "fahrerunterlagen_form_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	/*
	 * Reicht einen Entwurf ein, dabei wird der Status gesetzt.
	 * Es wird unterschieden ob es ein gespeicherter Entwurf oder ein neuer eingereicht wird.
	 * Im Anschluss wird die Listen gespeicherten Entwürfe und eingreichten Fundzettel aktualisiert.
	 */	
	public String einreichen() {
		//System.out.println("Fundzettel, Einreichen: "+verspmeldung.toString());
		verspmeldung.setStatus("nicht_bearbeitet");
		if (verspmeldung.getAenderung_datum()==null) {
			//System.out.println("FundzettelBean Einreichen Aenderungsdatum ist null");
			fahrerunterlagenService.unterlageSpeichern(verspmeldung);
		}
		else if (verspmeldung.getAenderung_datum() != null) {
			fahrerunterlagenService.unterlageBearbeiten(verspmeldung);
		}
		ladeMeldungen(); 
		ladeMeldungen2();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	/*
	 * Reicht eine Einreichung neu ein.
	 * Im Anschluss wird die Liste eingreichten Fundzettel aktualisiert.
	 */	
	public String einreichen2() {
		fahrerunterlagenService.unterlageBearbeiten(verspmeldung);
		ladeMeldungen2();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung2.xhtml?faces-redirect=true";
	}
	
	/*
	 * Speichert einen Entwurf, dabei wird der Typ und der Status gesetzt.
	 * Es wird unterschieden ob ein Entwurf erstmals oder neu gespeichert wird.
	 * Im Anschluss wird die Liste gespeicherter Fundzettel aktualisiert.
	 */
	public String speichern() {
		
		//System.out.println("Fundzettel, Speichern: "+verspmeldung.toString());
		if (verspmeldung.getFahrerunterlage_id()==0) {
			verspmeldung.setStatus("entwurf");
			fahrerunterlagenService.unterlageSpeichern(verspmeldung);
		}
		else {
			fahrerunterlagenService.unterlageBearbeiten(verspmeldung);
		}
		ladeMeldungen();
		verspmeldung.toString();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
		
	/*
	 * Abbruch der Detailansicht, führt zurück auf Übersichtsansicht gespeicherter Verspätungsmeldungen.
	 */
	public String abbrechen() {
		
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	
	
	/*
	 * Abbruch der Detailansicht, führt zurück auf Übersichtsansicht eingereichter Verspätungsmeldungen.
	 */
	public String abbrechen2() {
		
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung2.xhtml?faces-redirect=true";
	}
	
	/*
	 * Löscht einen Entwurf und aktualisiert die Liste gespeicherter Verspätungsmeldungen.
	 */
	public String loeschen() {
		fahrerunterlagenService.unterlageLoeschen(verspmeldung);
		ladeMeldungen();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
		
	/*
	 * Löscht eine Fahrerunterlage und aktualisiert die Liste eingereichter Verspätungsmeldungen.
	 */
	public String loeschen2() {
		fahrerunterlagenService.unterlageLoeschen(verspmeldung);
		ladeMeldungen2();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung2.xhtml?faces-redirect=true";
	}
		
	
	
}
