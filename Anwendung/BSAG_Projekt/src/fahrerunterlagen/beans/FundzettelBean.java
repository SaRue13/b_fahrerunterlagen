package fahrerunterlagen.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;

import fahrerunterlagen.daten.Fundzettel;


/*
 * Differenzierte Validierung für speichern/einreichen:
 * Evtl mit Actionlistener attribut des -Buttons abgreifen (speich/einreich)
 * damit einen Status setzen und den in einer required Validierungsmethode verwenden?
 */

@ManagedBean
@SessionScoped
public class FundzettelBean {

	private Fundzettel fundzettel;
	
	@PostConstruct
	public void init() {
		fundzettel = new Fundzettel();
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
	}
	
	
	public void einreichen() {
		System.out.println("Fundzettel, Einreichen: "+fundzettel.toString());
	}
	/*public void speicherListener(ActionEvent event) {
		String status = (String)event.getComponent().getAttributes().get("status");
		System.out.println("Status: "+status);
	}*/
}
