package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import model.User;

@ManagedBean(name="userHandler")
@SessionScoped
public class UserHandler {

	private User user;
	private List<User> users;
	private String pers_nr;
	private String passwort;

	public UserHandler() {
		super();
		initialize();
	}

	private void initialize() {

		// Insert Dummy data
		User a = new User("p000001");
		a.setName("Anna Begemann");
		a.setPasswort("p000001");
		a.setGruppenleiter("p000002"); // User b ist Gruppenleiter von User a
		a.setBetriebsstaette("test");
		a.addRole(User.FAHRER);

		User b = new User("p000002");
		b.setName("Daniel Bode");
		b.setPasswort("p000002");
		b.setBetriebsstaette("test");
		b.addRole(User.GRUPPENLEITER);
		b.addRole(User.MELDUNGSERSTELLER);
		b.addRole(User.ADMIN);

		User c = new User("p000003");
		c.setName("Eric Brümmer");
		c.setPasswort("p000003");
		c.setGruppenleiter("p000002"); // User b ist Gruppenleiter von User c
		c.setBetriebsstaette("test");
		c.addRole(User.FAHRER);

		User d = new User("p000004");
		d.setName("Alena Wolff");
		d.setPasswort("p000004");
		d.setGruppenleiter("p000002"); // User b ist Gruppenleiter von User a
		d.setBetriebsstaette("test");
		d.addRole(User.FAHRER);

		User e = new User("p000005");
		e.setName("Fritz Heyn");
		e.setPasswort("p000005");
		e.setBetriebsstaette("test");
		e.addRole(User.MELDUNGSERSTELLER);
		e.addRole(User.ADMIN);
		
		User f = new User("p1225");
		f.setName("Ellen Mahler");
		f.setPasswort("p1225");
		f.setGruppenleiter("p000002"); 
		f.setBetriebsstaette("test");
		f.addRole(User.FAHRER);

		users = new ArrayList<User>();
		users.add(a);
		users.add(b);
		users.add(c);
		users.add(d);
		users.add(e);
		users.add(f);
	}

	/**
	 * Diese Methode ueberprueft, die Korrektheit der Logindaten in der
	 * Datenbank. Stimmen diese, wird zur persoenlichen User-Startseite
	 * navigiert.
	 * 
	 * TODO: Fehlermeldungen anzeigen
	 * 
	 * @return
	 */
	public String login() {
		System.out.println("start login");
		User temp = null;
		// temp sucht aus Liste User mit pnummer
		for (User x : users) {
			if (x.getPers_nr().equals(pers_nr)) {
				temp = x;
				break;
			}
		}

		
	
		// Test, ob der Nutzer ueberhaupt existiert
		if (temp == null) {
			System.out.println("Nutzer existiert nicht");
			return null;
		}

		System.out.println("pw: " + passwort + " temppw: " + temp.getPasswort());
		
		if (passwort.equals(temp.getPasswort())) {
			setUser(temp);
			System.out.println("Login: " + getUser().getPers_nr());
		} else {
			System.out.println("Login failed: Falsches Passwort");
		}
		
	
	return "/jsf/schwarzesBrett/display_schwarzesBrett/schwarzesBrett_einsehen.xhtml?faces-redirect=true";
	}

	/**
	 * Ueberprueft, ob der User angemeldet ist.
	 * 
	 * @param event
	 */
	public void checkLoggedIn(ComponentSystemEvent event) {

		FacesContext context = FacesContext.getCurrentInstance();
		if (getUser() == null) {
			System.out.println("Nutzer ist nicht eingeloggt");
			context.getApplication().getNavigationHandler().handleNavigation(context, null,
					"/jsf/gesamtsystem/authentifizierung/display_authentifizierung/login.xhtml?faces-redirect=true");
		}
	}

	/**
	 * Loescht die komplette Session.
	 * 
	 */
	public String logout() {
		System.out.println("Logout");

		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

		return "/jsf/gesamtsystem/authentifizierung/display_authentifizierung/login.xhtml?faces-redirect=true"; // redirect to login page
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getPers_nr() {
		return pers_nr;
	}

	public void setPers_nr(String pers_nr) {
		this.pers_nr = pers_nr;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}