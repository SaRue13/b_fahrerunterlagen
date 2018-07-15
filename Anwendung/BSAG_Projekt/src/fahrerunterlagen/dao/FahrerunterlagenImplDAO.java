package fahrerunterlagen.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Linie;
import fahrerunterlagen.daten.Unterlagentyp;
import fahrerunterlagen.daten.Urlaubstag;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

/**
 * @author Mariia Muravytska
 * Klasse die FahrerunterlagenDAO implementiert
 */
public class FahrerunterlagenImplDAO implements FahrerunterlagenDAO<Fahrerunterlage, Integer>{
	
	private Session currentSession;
	private Transaction currentTransaction;
	private static FahrerunterlagenImplDAO instance;
	
	/**
	 * Konstruktor
	 */
	private FahrerunterlagenImplDAO() {
		
	} 

	/**
	 * Singelton
	 * Diese Methode checkt, ob Klassenobjekt schon vorhanden ist
	 * wenn ja gibt dies zurueck
	 * wenn nein legt dies an
	 * @return Klassenobjekt
	 */
	public static synchronized FahrerunterlagenImplDAO getInstance() {
		if (instance == null) {
			instance = new FahrerunterlagenImplDAO();
		}
		return instance;
	}
	
	/**
	 * Methode die DB-Verbindung-Session oeffnet
	 * @return Session
	 */
	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
	
	/**
	 * Methode die DB-Verbindung-Session mit Transaktion oeffnet
	 * @return Session
	 */
	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}	 
		
	/**
	 * Methode die DB-Verbindung-Session schliesst
	 */
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	/**
	 * Methode die DB-Verbindung-Session mit Transaktion schliesst
	 */
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
		
	/**
	 * Methode die Session Factory aufbaut und configuriert
	 * @return SessionFactory
	 */
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
		//
		//configuration.addAnnotatedClass(fahrerunterlagen.daten.Fahrerunterlage.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Fundzettel.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Urlaubsantrag.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Wagenlaufkarte.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Unterlagentyp.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Verschmutzungsmeldung.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Verspaetungsmeldung.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Linie.class);
		//
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
													.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
		    
	public Session getCurrentSession() {
		return currentSession;
	}
		    
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}
		    		    	
    public Transaction getCurrentTransaction() {
		return currentTransaction;
	}
		    			    	
	public void setCurrentTransaction(Transaction currentTransaction) {
		 this.currentTransaction = currentTransaction;   	
	}	    	        
		    		    	
	/**
	 * Methode die alle in BD vorhandene Unterlagentypen laedt
	 * @return Unterlagentypen vom Typ List
	 */
	@Override
	public List<Unterlagentyp> getUnterlagenTypen() {
		@SuppressWarnings("unchecked")
		List<Unterlagentyp> unterlagentypen = (List<Unterlagentyp>) getCurrentSession().createQuery("from Unterlagentyp").list();
		return unterlagentypen;
	}
	
	/**
	 * Methode die alle in BD vorhandene Linien laedt
	 * @return Linien vom Typ List
	 */
	public List<Linie> getLinien() {
		@SuppressWarnings("unchecked")
		List<Linie> linien = getCurrentSession().createQuery("from Linie").list();
		return linien;	
	}

	/**
	 * Methode die Insert in DB macht
	 * @param Objekt vom Fahrerunterlagen, das zu speichern ist
	 */
	@Override
	public void unterlageSpeichern(Fahrerunterlage entity) {
		getCurrentSession().save(entity);		
	}

	/**
	 * Methode die Udate an einem Fahrerunterlageeintrag in DB macht
	 * @param Objekt was geaendert werden soll
	 */
	@Override
	public void unterlageBearbeiten(Fahrerunterlage entity) {
		getCurrentSession().update(entity);
	}
	
	/**
	 * Liste von Fahrerunterlagen holen, die bestimmtem Fahrer gehoeren und Entwuerfe 
	 * oder Einreichungen sind, auch von einem bestimmten Unterlagentyp
	 * @param fahrer: p Nummer des Fahrers (String)
	 * @param eingereicht false: nach Entwuerfe suchen, true: nach Einreichungen
	 * @param typ: typ_id (int)
	 * @return Fahrerunterlagen (List)
	 */
	@Override
	public List<Fahrerunterlage> findeFahrerUnterlagen(String fahrer, boolean eingereicht, int typ) {
		@SuppressWarnings("deprecation")
		Criteria criteria = getCurrentSession().createCriteria(Fahrerunterlage.class);
		Criterion p_nr = Restrictions.eq("p_nr_fahrer", fahrer);
		
		LogicalExpression andExp;
		if (!eingereicht) {
			// Entwuerfe aussuchen
			Criterion status = Restrictions.eq("status", "entwurf");
			andExp = Restrictions.and(p_nr, status);
		}
		else {
			// Einreichungen mit unterschiedlichen Status
			Criterion bearbeitet = Restrictions.eq("status", "bearbeitet");
			Criterion nicht_bearbeitet = Restrictions.eq("status", "nicht_bearbeitet");
			Criterion in_bearbeitung = Restrictions.eq("status", "in_bearbeitung");
			LogicalExpression or1 = Restrictions.or(bearbeitet, nicht_bearbeitet);
			LogicalExpression or2 = Restrictions.or(or1, in_bearbeitung);
			andExp = Restrictions.and(p_nr, or2);
		}		
		
		Criterion typ_crit = Restrictions.eq("typ", typ);
		LogicalExpression and2 = Restrictions.and(andExp, typ_crit);
		criteria.add(and2);
		@SuppressWarnings("unchecked")
		List<Fahrerunterlage> unterlagen = (List<Fahrerunterlage>) criteria.list();	
		return unterlagen;
	}

	/**
	 * Methode die Delete in DB ausfuehrt
	 * @param das Objek zuloeschender Fahrerunterlage
	 */
	@Override
	public void unterlageLoeschen(Fahrerunterlage entity) {
		getCurrentSession().delete(entity);		
	}

	/**
	 * Methode zum Speichern Urlaubstade
	 * diese Funktion ist noch nicht umgesetzt, da Urlaubsantrag nicht umgesetz wird
	 */
	@Override
	public void urlaubstagSpeichern(Urlaubstag urlaubstag) {
		getCurrentSession().save(urlaubstag);	
	}

	/**
	 * Methode holt alle Urlaubstage die zu einem Urlaubsantrag gehoeren
	 * diese Funktion ist noch nicht umgesetzt, da Urlaubsantrag nicht umgesetz wird
	 */
	@Override
	public List<Urlaubstag> getUrlaubstage(int urlaubsantrag_id) {
		Criterion id = Restrictions.eq("status", "bearbeitet");
		return null;
	}	
}
