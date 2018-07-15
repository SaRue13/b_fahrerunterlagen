package fahrerunterlagen.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.service.FahrerunterlagenService;


/*
 * Bean die Funktionalitäten des Fundzettels managed.
 */

@ManagedBean
@SessionScoped
public class FundzettelBean {

	/*
	 * Fundzettelobjekt das aus der view referenziert wird.
	 */
	private Fundzettel fundzettel;
	private List<Fahrerunterlage> zettel; //Liste gespeicherter fundzettel
	private List<Fahrerunterlage> zettel2; //Liste Eingereichter fundzettel
	private FahrerunterlagenService fahrerunterlagenService;
	private String test = "Test";
	@ManagedProperty("#{mainBean}")
	private MainBean mainBean;
	
	@PostConstruct
	public void init() {
		//System.out.println("init");
		fundzettel = new Fundzettel();
		fahrerunterlagenService = new FahrerunterlagenService();
		loadZettel();
		loadZettel2();
		//System.out.println(mainBean.getUserHandler().getPers_nr());
		fundzettel.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
	}
	
	/*
	 * Lädt eine Liste gespeicherter Fundzettel aus der Datenbank
	 */
	private void loadZettel() {
		//FahrerunterlagenService methode aufrufen: pnr, 
		//DAzu ManagedProperty von UserHandler, von dem P-Nr holen
		zettel = fahrerunterlagenService.findeFahrerUnterlagen(mainBean.getUserHandler().getPers_nr(), false, 1);
	
		/*Fundzettel f = new Fundzettel();
		f.setTitel("Titel");
		f.setFundort("Sitzgruppe x");
		Fundzettel f1 = new Fundzettel();
		f1.setTitel("Titel-1");
		Fundzettel f2 = new Fundzettel();
		f2.setTitel("Titel-2");
		Fundzettel f3 = new Fundzettel();
		f3.setTitel("some title");
		zettel = new ArrayList<Fundzettel>();
		zettel.add(f);
		zettel.add(f1);
		zettel.add(f2);
		zettel.add(f3);*/
		//System.out.println("LoadZettel: "+zettel.size());
	}
	
	/*
	 * Lädt eine Liste eingereichter Fundzettel aus der Datenbank
	 */
	private void loadZettel2() {
	/*	Fundzettel f = new Fundzettel();
		f.setTitel("Fundzettel 1");
		f.setStatus("nicht_bearbeitet");
		Fundzettel f1 = new Fundzettel();
		f1.setTitel("Fundzettel 11");
		f1.setStatus("bearbeitet");
		Fundzettel f2 = new Fundzettel();
		f2.setTitel("Fundzettel 12");
		Fundzettel f3 = new Fundzettel();
		f3.setTitel("Fundzettel 13");
		Fundzettel f4 = new Fundzettel();
		f4.setTitel("Fundzettel 14");
		zettel2 = new List<Fahrerunterlage>();
		zettel2.add(f);
		zettel2.add(f1);
		zettel2.add(f2);
		zettel2.add(f3);
		zettel2.add(f4);*/
		zettel2 = fahrerunterlagenService.findeFahrerUnterlagen(mainBean.getUserHandler().getPers_nr(), true, 1);
	}
	
	public MainBean getMainBean() {
		return mainBean;
	}
	public void setMainBean(MainBean mainBean) {
		this.mainBean = mainBean;
	}
	public List<Fahrerunterlage> getZettel2() {
		return zettel2;
	}
	public void setZettel2(List<Fahrerunterlage> zettel2) {
		this.zettel2 = zettel2;
	}
	public List<Fahrerunterlage> getZettel() {
		return zettel;
	}
	public void setZettel(List<Fahrerunterlage> zettel) {
		this.zettel = zettel;
	}
	public String getTest() {
		return test;
	}
	public void setTest(String test) {
		this.test = test;
	}
	public Fundzettel getFundzettel() {
		return fundzettel;
	}

	public void setFundzettel(Fundzettel fundzettel) {
		//System.out.println("fundzettelbean: setSomeValues: "+fundzettel.getLinie());
		this.fundzettel = fundzettel;
	}
	
	/*
	 * Leitet auf die Detailansicht (das Formular) für gespeicherte Fundzettel weiter
	 */
	public String details() {
		
		//System.out.println("Details: "+fundzettel.toString());
		return "fahrerunterlagen_form_Fundzettel.xhtml?faces-redirect=true";
	}

	/*
	 * Leitet auf die Detailansicht (das Formular) für eingereichte Fundzettel weiter
	 */
	public String details2() {
		
		//System.out.println("Details der Einreichungen: "+fundzettel.toString());
		return "fahrerunterlagen_form_Fundzettel2.xhtml?faces-redirect=true";
	}

	/*
	 * Leitet auf die Detailansicht (das Formular) für gespeicherte Fundzettel weiter
	 * und setzt dabei ein neues Fundzettelobjekt als Referenz ein
	 */
	public String neu() {
		fundzettel = new Fundzettel();
		fundzettel.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
		//System.out.println("fundzBean, Neu :"+fundzettel.toString());
		return "fahrerunterlagen_form_Fundzettel.xhtml?faces-redirect=true";
	}
	
	/*
	 * Speichert einen Entwurf, dabei wird der Typ und der Status gesetzt.
	 * Es wird unterschieden ob ein Entwurf erstmals oder neu gespeichert wird.
	 * Im Anschluss wird die Liste gespeicherter Fundzettel aktualisiert.
	 */
	public String speichern() {
		//System.out.println("Fundzettel, Speichern: "+fundzettel.toString());
		//Date date = new Date();
		//fundzettel.setSpeicher_datum(date);
		//fundzettel.setAenderung_datum(date);
		fundzettel.setStatus("entwurf");
		fundzettel.setTyp(1);
		//System.out.println("Speichern entwurf: "+fundzettel.getFahrerunterlage_id());
		if(fundzettel.getFahrerunterlage_id() == 0) {
		fahrerunterlagenService.unterlageSpeichern(fundzettel);
		}
		else {
			fahrerunterlagenService.unterlageBearbeiten(fundzettel);
		}
		
		//fundzettel = new Fundzettel(); //nicht nötig: NEU bzw Wahl setzt fundzettel
//		clearDatum();
//		clearVonFahrgast();
		loadZettel();
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	/*
	 * Reicht einen Entwurf ein, dabei wird der Typ und der Status gesetzt.
	 * Es wird unterschieden ob ein neuer Entwurf oder schon mal gespeicherter eingereicht wird.
	 * Im Anschluss werden die Listen der Entwürfe und eingreichten Fundzettel aktualisiert.
	 */	
	public String einreichen() {
		fundzettel.setStatus("nicht_bearbeitet");
		//System.out.println("Fundzettel, Einreichen: "+fundzettel.toString());
		if (fundzettel.getAenderung_datum()==null) {
			//System.out.println("FundzettelBean Einreichen Aenderungsdatum ist null");
			fundzettel.setTyp(1);
			fahrerunterlagenService.unterlageSpeichern(fundzettel);
		}
		else if (fundzettel.getAenderung_datum() != null) {
			fahrerunterlagenService.unterlageBearbeiten(fundzettel);
		}
		/*Neu: return zur Übersicht, Fundzettel = neu
		 * clearDatum();
		clearVonFahrgast();*/
		//fundzettel = new Fundzettel();
		loadZettel();
		loadZettel2();
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	/*
	 * Reicht eine Einreichung neu ein.
	 * Im Anschluss wird die Liste eingreichten Fundzettel aktualisiert.
	 */	
	public String neuEinreichen() {
		//System.out.println("fundzBean: NeuEinreichen, TODO");
		fahrerunterlagenService.unterlageBearbeiten(fundzettel);
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml?faces-redirect=true";
	}
	
	/*
	 * Abbruch der Detailansicht, führt zurück auf Übersichtsansicht gespeicherter Fundzettel.
	 */
	public String abbrechen() {
		//System.out.println("fundzBean Abbrechen");
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	/*
	 * Abbruch der Detailansicht, führt zurück auf Übersichtsansicht eingereichter Fundzettel.
	 */
	public String abbrechen2() {
		//System.out.println("fundzBean Abbrechen2");
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml?faces-redirect=true";
	}
	
	/*
	 * Löscht einen Entwurf und aktualisiert die Liste gespeicherter Fundzettel.
	 */
	public String loeschen() {
		fahrerunterlagenService.unterlageLoeschen(fundzettel);
		loadZettel();
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	/*
	 * Löscht eine Fahrerunterlage und aktualisiert die Liste eingereichter Fundzettel.
	 */
	public String loeschen2() {
		fahrerunterlagenService.unterlageLoeschen(fundzettel);
		loadZettel2();
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml?faces-redirect=true";
	}
	/*public void speicherListener(ActionEvent event) {
		String status = (String)event.getComponent().getAttributes().get("status");
		System.out.println("Status: "+status);
	}*/
}
