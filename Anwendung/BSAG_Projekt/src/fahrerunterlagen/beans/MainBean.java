package fahrerunterlagen.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import beans.UserHandler;
import fahrerunterlagen.service.FahrerunterlagenService;

@ManagedBean
@SessionScoped
public class MainBean {

	private FahrerunterlagenService fahrerunterlagenService;
	private ArrayList<String> unterlagentypen;
	private String[] typen;// = {"Urlaubsantrag", "Fundzettel", "Verschmutzungsmeldung", "Wagenlaufkarte", "Ueberstunden"};
	private SelectItem[] linien;
	private Date minDate;
	private Date maxDate;
	
	@ManagedProperty("#{userHandler}")
	private UserHandler userHandler;
	
	@PostConstruct
	public void init() {
		//Alle benötigten Werte laden: typen, linien
		fahrerunterlagenService = new FahrerunterlagenService();
		this.setTypen(fahrerunterlagenService.getTypenNamen());
		unterlagentypen = new ArrayList<String>();
		Collections.addAll(unterlagentypen, this.getTypen());
		//System.out.println("Array: "+typen.length);
		fillLinien();
		Calendar cal = Calendar.getInstance();
		minDate = cal.getTime();
		cal.add(Calendar.YEAR, 1);
		maxDate = cal.getTime();
	}

	
	
	public UserHandler getUserHandler() {
		return userHandler;
	}



	public void setUserHandler(UserHandler userHandler) {
		this.userHandler = userHandler;
	}



	public Date getMinDate() {
		System.out.println("get MinDAte");
		return minDate;
	}



	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}



	public Date getMaxDate() {
		return maxDate;
	}



	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}



	public SelectItem[] getLinien() {
		return linien;
	}



	public void setLinien(SelectItem[] linien) {
		this.linien = linien;
	}



	public String[] getTypen() {
		//System.out.println("get Typen");
		return typen;
	}

	public void setTypen(String[] typen) {
		for(String typ: typen) {
			System.out.println(" typ: "+typ);
		}
		this.typen = typen;
	}

	public ArrayList<String> getUnterlagentypen() {
		return unterlagentypen;
	}

	public void setUnterlagentypen(ArrayList<String> unterlagentypen) {
		this.unterlagentypen = unterlagentypen;
	}

	private void fillLinien() {
		//Werte der Linien laden (1, 2, 6E ...)
		List<String> linienbezeichnung = fahrerunterlagenService.getLinienBezeichnung();
		linien = new SelectItem[linienbezeichnung.size()];
		int i=0;
		for(String linie : linienbezeichnung) {
			linien[i] = new SelectItem(linie, "Linie "+ linie);//wert, label
			i++;
		}
	}
	
	
	
}
