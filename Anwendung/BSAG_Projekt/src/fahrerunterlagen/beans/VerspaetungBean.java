package fahrerunterlagen.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import fahrerunterlagen.daten.Verspaetungsmeldung;
import fahrerunterlagen.service.FahrerunterlagenService;

@ManagedBean
@SessionScoped
public class VerspaetungBean {
	//Liste 1 geladener VerspMeld (gespeicherte)
	
	//Liste 2 geladener VerspMeld (eingereichte)
	
	private Verspaetungsmeldung verspmeldung;
	private List<Verspaetungsmeldung> meldungen; //gespeicherte fundzettel
	private List<Verspaetungsmeldung> meldungen2; //Eingereichte fundzettel
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


	public List<Verspaetungsmeldung> getMeldungen() {
		return meldungen;
	}


	public void setMeldungen(List<Verspaetungsmeldung> meldungen) {
		this.meldungen = meldungen;
	}


	public List<Verspaetungsmeldung> getMeldungen2() {
		return meldungen2;
	}


	public void setMeldungen2(List<Verspaetungsmeldung> meldungen2) {
		this.meldungen2 = meldungen2;
	}


	public MainBean getMainBean() {
		return mainBean;
	}


	public void setMainBean(MainBean mainBean) {
		this.mainBean = mainBean;
	}


	private void ladeMeldungen(){
		Verspaetungsmeldung v = new Verspaetungsmeldung();
		v.setTitel("Zu spät");
		v.setAngeordnet_durch("Herbert Hasenbär");
		Verspaetungsmeldung v1 = new Verspaetungsmeldung();
		v1.setTitel("Noch später");
		v1.setDienst_nr(1234);
		Verspaetungsmeldung v2 = new Verspaetungsmeldung();
		v2.setTitel("Niemals");
		v2.setDiensttauschkonto(true);
	}
	
	private void ladeMeldungen2(){
		Verspaetungsmeldung v = new Verspaetungsmeldung();
		v.setTitel("Zu spät");
		v.setAngeordnet_durch("Herbert Hasenbär");
		Verspaetungsmeldung v1 = new Verspaetungsmeldung();
		v1.setTitel("Noch später");
		v1.setDienst_nr(1234);
		Verspaetungsmeldung v2 = new Verspaetungsmeldung();
		v2.setTitel("Niemals");
		v2.setDiensttauschkonto(true);
		meldungen = new ArrayList<Verspaetungsmeldung>();
		meldungen.add(v2);
		meldungen.add(v1);
		meldungen.add(v);
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
		return "fahrerunterlagen_form_Fundzettel.xhtml?faces-redirect=true";
	}
	public String einreichen() {
		
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	public String speichern() {
	
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
		
	public String abbrechen() {
		
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
	
	
	public String loeschen() {
		
		return "fahrerunterlagen_ansicht_Verspaetungsmeldung.xhtml?faces-redirect=true";
	}
		
		
	
	
}
