package fahrerunterlagen.daten;

import java.util.Date;

public class Urlaubstag {
	
	private int tag_id;
	private char typ;
	private Date datum;
	private int wichtigkeit;
	
	public Urlaubstag() {
		super();
	}
	
	public int getTag_id() {
		return tag_id;
	}
	public void setTag_id(int tag_id) {
		this.tag_id = tag_id;
	}
	public char getTyp() {
		return typ;
	}
	public void setTyp(char typ) {
		this.typ = typ;
	}
	public Date getDatum() {
		return datum;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public int getWichtigkeit() {
		return wichtigkeit;
	}
	public void setWichtigkeit(int wichtigkeit) {
		this.wichtigkeit = wichtigkeit;
	}
	@Override
	public String toString() {
		return "Urlaubstag [tag_id=" + tag_id + ", typ=" + typ + ", datum=" + datum + ", wichtigkeit=" + wichtigkeit
				+ "]";
	}
	

}
