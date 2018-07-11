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
 * Differenzierte Validierung für speichern/einreichen:
 * Evtl mit Actionlistener attribut des -Buttons abgreifen (speich/einreich)
 * damit einen Status setzen und den in einer required Validierungsmethode verwenden?
 */

@ManagedBean
@SessionScoped
public class FundzettelBean {

	private Fundzettel fundzettel;
	private List<Fahrerunterlage> zettel; //gespeicherte fundzettel
	private List<Fahrerunterlage> zettel2; //Eingereichte fundzettel
	private FahrerunterlagenService fahrerunterlagenService;
	private String test = "Test";
	@ManagedProperty("#{mainBean}")
	private MainBean mainBean;
	
	@PostConstruct
	public void init() {
		System.out.println("init");
		fundzettel = new Fundzettel();
		fahrerunterlagenService = new FahrerunterlagenService();
		loadZettel();
		loadZettel2();
		System.out.println(mainBean.getUserHandler().getPers_nr());
		fundzettel.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
	}
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
		System.out.println("LoadZettel: "+zettel.size());
	}
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
		System.out.println("fundzettelbean: setSomeValues: "+fundzettel.getLinie());
		this.fundzettel = fundzettel;
	}
	
public String details() {
		
		System.out.println("Details: "+fundzettel.toString());
		return "fahrerunterlagen_form_Fundzettel.xhtml?faces-redirect=true";
	}

public String details2() {
	
	System.out.println("Details der Einreichungen: "+fundzettel.toString());
	return "fahrerunterlagen_form_Fundzettel2.xhtml?faces-redirect=true";
}

public String neu() {
	fundzettel = new Fundzettel();
	fundzettel.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
	System.out.println("fundzBean, Neu :"+fundzettel.toString());
	return "fahrerunterlagen_form_Fundzettel.xhtml?faces-redirect=true";
}
	
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
	
	
	
	public String einreichen() {
		fundzettel.setStatus("nicht_bearbeitet");
		System.out.println("Fundzettel, Einreichen: "+fundzettel.toString());
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
		loadZettel();
		loadZettel2();
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	public String neuEinreichen() {
		System.out.println("fundzBean: NeuEinreichen, TODO");
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml?faces-redirect=true";
	}
	
	public String abbrechen() {
		System.out.println("fundzBean Abbrechen");
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
	public String abbrechen2() {
		System.out.println("fundzBean Abbrechen2");
		return "fahrerunterlagen_ansicht_Fundzettel2.xhtml?faces-redirect=true";
	}
	

	
	public String loeschen() {
		fahrerunterlagenService.unterlageLoeschen(fundzettel);
		loadZettel();
		return "fahrerunterlagen_ansicht_Fundzettel.xhtml?faces-redirect=true";
	}
	
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
