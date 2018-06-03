package fahrerunterlagen.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Unterlagentyp;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;


public class FahrerunterlagenImplDAO implements FahrerunterlagenDAO<Fahrerunterlage, Integer>{
	
	private Session currentSession;
	private Transaction currentTransaction;
	
	public FahrerunterlagenImplDAO() {
		
	} 

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
		
	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}	 
		
	public void closeCurrentSession() {
		currentSession.close();
	}
	
	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}
		
	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure("/hibernate.cfg.xml");
		//
		//configuration.addAnnotatedClass(fahrerunterlagen.daten.Fahrerunterlage.class);
		configuration.addAnnotatedClass(fahrerunterlagen.daten.Fundzettel.class);
		//configuration.addClass(fahrerunterlagen.daten.Fahrerunterlage.class);
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
		    		    	    	
	@Override
	public List<Unterlagentyp> getUnterlagenTypen() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void unterlageSpeichern(Fahrerunterlage entity) {
		getCurrentSession().save(entity);		
	}

	@Override
	public void unterlageBearbeiten(Fahrerunterlage entity) {
		getCurrentSession().update(entity);
	}
	
	/**
	 * Liste von Fahrerunterlagen holen, die bestimmtem Fahrer gehoeren und Entwuerfe 
	 * oder Einreichungen sind, auch von einem bestimmten Unterlagentyp
	 * @param fahrer: p Nummer des Fahrers
	 * @param eingereicht false: nach Entwuerfe suchen, true: nach Einreichungen
	 * @param typ: typ_id
	 * @return Fahrerunterlagen
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
			// Einreichungen mit unterschiedlichen Statusen
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

	@Override
	public void unterlageLoeschen(Fahrerunterlage entity) {
		getCurrentSession().delete(entity);		
	}
	
	

}
