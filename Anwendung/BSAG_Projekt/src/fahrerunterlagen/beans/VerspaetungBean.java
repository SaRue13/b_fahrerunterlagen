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

@ManagedBean
@SessionScoped
public class VerspaetungBean {
	//Liste 1 geladener VerspMeld (gespeicherte)
	
	//Liste 2 geladener VerspMeld (eingereichte)
	
	private Verspaetungsmeldung verspmeldung;
	private List<Fahrerunterlage> meldungen; //gespeicherte Verspaetungsmeldungen
	private List<Fahrerunterlage> meldungen2; //Eingereichte Verspaetungsmeldungen
	private FahrerunterlagenService fahrerunterlagenService;
	@ManagedProperty("#{mainBean}")
	private MainBean mainBean;
	
	
	@PostConstruct
	public void init() {
		System.out.println("init");
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


	private void ladeMeldungen(){
		meldungen = fahrerunterlagenService.findeFahrerUnterlagen(mainBean.getUserHandler().getPers_nr(), false, 5);
	}
	
	private void ladeMeldungen2(){
		meldungen2 = fahrerunterlagenService.findeFahrerUnterlagen(mainBean.getUserHandler().getPers_nr(), true, 5);	
	}
	
	public String details() {
		return "fahrerunterlagen_form_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	public String details2() {
		return "fahrerunterlagen_form_Verspaetungsmeldung2.xhtml?faces-redirect=true";
	}

	public String neu() {
		verspmeldung = new Verspaetungsmeldung();
		verspmeldung.setP_nr_fahrer(mainBean.getUserHandler().getPers_nr());
		return "fahrerunterlagen_form_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	public String einreichen() {
		System.out.println("Fundzettel, Einreichen: "+verspmeldung.toString());
		verspmeldung.setStatus("nicht_bearbeitet");
		if (verspmeldung.getAenderung_datum()==null) {
			System.out.println("FundzettelBean Einreichen Aenderungsdatum ist null");
			fahrerunterlagenService.unterlageSpeichern(verspmeldung);
		}
		else if (verspmeldung.getAenderung_datum() != null) {
			fahrerunterlagenService.unterlageBearbeiten(verspmeldung);
		}
		ladeMeldungen(); 
		ladeMeldungen2();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	public String speichern() {
		
		System.out.println("Fundzettel, Speichern: "+verspmeldung.toString());
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
		
	public String abbrechen() {
		
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	
	public String loeschen() {
		fahrerunterlagenService.unterlageLoeschen(verspmeldung);
		ladeMeldungen();
		ladeMeldungen2();
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
		
		
	
	
}
