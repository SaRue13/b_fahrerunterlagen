package model;

import java.util.ArrayList;
import java.util.List;

public class User {
	
	private String pers_nr;
	private String name;
	private String passwort;
	private String gruppenleiter;
	private String betriebsstaette;
	private List<Integer> roles;
	
	public static final int GRUPPENLEITER = 1;
	public static final int FAHRER = 2;
	public static final int MITARBEITER = 3;
	public static final int MELDUNGSERSTELLER = 4;
	public static final int ADMIN = 5;
	

	

	public User(String pers_nr) {
		super();
		this.pers_nr = pers_nr;
		roles = new ArrayList<Integer>();
	}

	public String getPers_nr() {
		return pers_nr;
	}

	public void setPers_nr(String pers_nr) {
		this.pers_nr = pers_nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGruppenleiter() {
		return gruppenleiter;
	}

	public void setGruppenleiter(String gruppenleiter) {
		this.gruppenleiter = gruppenleiter;
	}

	public String getBetriebsstaette() {
		return betriebsstaette;
	}

	public void setBetriebsstaette(String betriebsstaette) {
		this.betriebsstaette = betriebsstaette;
	}

	public List<Integer> getRoles() {
		return roles;
	}

	public void setRoles(List<Integer> roles) {
		this.roles = roles;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	@Override
	public String toString() {
		return "User [pers_nr=" + pers_nr + ", name=" + name + ", passwort=" + passwort + ", gruppenleiter="
				+ gruppenleiter + ", betriebsstaette=" + betriebsstaette + ", roles=" + roles + "]";
	}

	public void addRole(int role){
		
		roles.add(role);
	}
	
	private boolean userHasRole(int role) {
		boolean is = false;
		for(int r: roles) {
			if(r == role)
				is= true;
		}
		return is;
	}
	
	public boolean isGruppenleiter() {
		return userHasRole(User.GRUPPENLEITER);
	}

}
