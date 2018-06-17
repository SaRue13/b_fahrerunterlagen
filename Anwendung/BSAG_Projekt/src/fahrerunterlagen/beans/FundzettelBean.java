package fahrerunterlagen.beans;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.service.FahrerunterlagenService;


/*
 * Differenzierte Validierung für speichern/einreichen:
 * Evtl mit Actionlistener attribut des -Buttons abgreifen (speich/einreich)
 * damit einen Status setzen und den in einer required Validierungsmethode verwenden?
 */

@ManagedBean
@SessionScoped
public class FundzettelBean {

	private Fundzettel fundzettel;
	private ArrayList<Fundzettel> zettel; //gespeicherte fundzettel
	private ArrayList<Fundzettel> zettel2; //Eingereichte fundzettel
	private FahrerunterlagenService fahrerunterlagenService;
	private String test = "Test";
	
	@PostConstruct
	public void init() {
		System.out.println("init");
		fundzettel = new Fundzettel();
		fahrerunterlagenService = new FahrerunterlagenService();
		loadZettel();
		loadZettel2();
	}
	private void loadZettel() {
		Fundzettel f = new Fundzettel();
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
		zettel.add(f3);
		System.out.println("LoadZettel: "+zettel.size());
	}
	private void loadZettel2() {
		Fundzettel f = new Fundzettel();
		f.setTitel("Einreichung Fundzettel 1");
		f.setStatus("nicht_bearbeitet");
		Fundzettel f1 = new Fundzettel();
		f1.setTitel("Einreichung Fundzettel 11");
		f1.setStatus("bearbeitet");
		Fundzettel f2 = new Fundzettel();
		f2.setTitel("Einreichung Fundzettel 12");
		Fundzettel f3 = new Fundzettel();
		f3.setTitel("Einreichung Fundzettel 13");
		Fundzettel f4 = new Fundzettel();
		f4.setTitel("Einreichung Fundzettel 14");
		zettel2 = new ArrayList<Fundzettel>();
		zettel2.add(f);
		zettel2.add(f1);
		zettel2.add(f2);
		zettel2.add(f3);
		zettel2.add(f4);
	}
	
	public ArrayList<Fundzettel> getZettel2() {
		return zettel2;
	}
	public void setZettel2(ArrayList<Fundzettel> zettel2) {
		this.zettel2 = zettel2;
	}
	public ArrayList<Fundzettel> getZettel() {
		return zettel;
	}
	public void setZettel(ArrayList<Fundzettel> zettel) {
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
		System.out.println("fundzettelbean: setSomeValues: "+fundzettel.getLinie());
		this.fundzettel = fundzettel;
	}
	
public String details() {
		
		System.out.println("Details: "+fundzettel.toString());
		return "fahrerunterlagen_form_Fundzettel.xhtml";
	}

public String details2() {
	
	System.out.println("Details der Einreichungen: "+fundzettel.toString());
	return "fahrerunterlagen_form_Fundzettel2.xhtml";
}

public String neu() {
	fundzettel = new Fundzettel();
	System.out.println("fundzBean, Neu :"+fundzettel.toString());
	return "fahrerunterlagen_form_Fundzettel.xhtml";
}
	
	public String speichern() {
		System.out.println("Fundzettel, Speichern: "+fundzettel.toString());
		//Date date = new Date();
		//fundzettel.setSpeicher_datum(date);
		//fundzettel.setAenderung_datum(date);
		fundzettel.setStatus("entwurf");
		fundzettel.setTyp(1);
		fahrerunterlagenService.unterlageSpeichern(fundzettel);
		
		//fundzettel = new Fundzettel(); //nicht nötig: NEU bzw Wahl setzt fundzettel
//		clearDatum();
//		clearVonFahrgast();
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	
	
	public String einreichen() {
		System.out.println("Fundzettel, Einreichen: "+fundzettel.toString());
		fundzettel.setStatus("nicht_bearbeitet");
		if (fundzettel.getAenderung_datum()==null) {
			System.out.println("FundzettelBean Einreichen Aenderungsdatum ist null");
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
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml";
	}
	
	public String neuEinreichen() {
		System.out.println("fundzBean: NeuEinreichen, TODO");
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml";
	}
	
	public String abbrechen() {
		System.out.println("fundzBean Abbrechen");
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml";
	}
	
	public String abbrechen2() {
		System.out.println("fundzBean Abbrechen2");
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml";
	}
	
	private void clearDatum() {
		fundzettel.setAenderung_datum(null);
		fundzettel.setSpeicher_datum(null);
		fundzettel.setEinreichung_datum(null);
	}
	private void clearVonFahrgast() {
		fundzettel.setNameFinder(null);
		fundzettel.setStrasse(null);
		fundzettel.setHausnummer(null);
		fundzettel.setPlz(0);
		fundzettel.setWohnort(null);
	}
	/*public void speicherListener(ActionEvent event) {
		String status = (String)event.getComponent().getAttributes().get("status");
		System.out.println("Status: "+status);
	}*/
}
