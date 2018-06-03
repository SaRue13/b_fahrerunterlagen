package fahrerunterlagen.beans;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
	
	private FahrerunterlagenService fahrerunterlagenService;
	
	@PostConstruct
	public void init() {
		fundzettel = new Fundzettel();
		fahrerunterlagenService = new FahrerunterlagenService();
	}

	public Fundzettel getFundzettel() {
		return fundzettel;
	}

	public void setFundzettel(Fundzettel fundzettel) {
		System.out.println("fundzettelbean: setSomeValues: "+fundzettel.getLinie());
		this.fundzettel = fundzettel;
	}
	
	public void speichern() {
		System.out.println("Fundzettel, Speichern: "+fundzettel.toString());
		//Date date = new Date();
		//fundzettel.setSpeicher_datum(date);
		//fundzettel.setAenderung_datum(date);
		fundzettel.setStatus("entwurf");
		fundzettel.setTyp(1);
		fahrerunterlagenService.unterlageSpeichern(fundzettel);
		//fundzettel = new Fundzettel();
		clearDatum();
	}
	
	
	public void einreichen() {
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
		clearDatum();
	}
	
	private void clearDatum() {
		fundzettel.setAenderung_datum(null);
		fundzettel.setSpeicher_datum(null);
		fundzettel.setEinreichung_datum(null);
	}
	/*public void speicherListener(ActionEvent event) {
		String status = (String)event.getComponent().getAttributes().get("status");
		System.out.println("Status: "+status);
	}*/
}
