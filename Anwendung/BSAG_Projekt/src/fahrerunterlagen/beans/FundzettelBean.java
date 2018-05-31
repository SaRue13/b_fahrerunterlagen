package fahrerunterlagen.beans;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fahrerunterlagen.daten.Fundzettel;

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
	
	
}
