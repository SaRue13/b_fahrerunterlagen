package fahrerunterlagen.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class MainBean {
	
	private ArrayList<String> unterlagentypen;
	private String[] typen = {"Urlaubsantrag", "Fundzettel", "Verschmutzungsmeldung", "Wagenlaufkarte", "Überstunden"};
	private SelectItem[] linien;
	
	@PostConstruct
	public void init() {
		//Alle benötigten Werte laden: typen, linien
		unterlagentypen = new ArrayList<String>();
		Collections.addAll(unterlagentypen, typen);
		//System.out.println("Array: "+typen.length);
		fillLinien();
	}

	
	
	public SelectItem[] getLinien() {
		return linien;
	}



	public void setLinien(SelectItem[] linien) {
		this.linien = linien;
	}



	public String[] getTypen() {
		System.out.println("get Typen");
		return typen;
	}

	public void setTypen(String[] typen) {
		this.typen = typen;
	}

	public ArrayList<String> getUnterlagentypen() {
		return unterlagentypen;
	}

	public void setUnterlagentypen(ArrayList<String> unterlagentypen) {
		this.unterlagentypen = unterlagentypen;
	}

	private void fillLinien() {
		linien = new SelectItem[5];
		for(int i = 0; i< 5; i++) {
			linien[i] = new SelectItem(i+1, "Linie "+(i+1));//wert, label
		}
	}
}
