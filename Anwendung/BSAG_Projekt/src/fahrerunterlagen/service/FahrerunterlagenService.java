package fahrerunterlagen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fahrerunterlagen.dao.FahrerunterlagenImplDAO;
import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.daten.Linie;
import fahrerunterlagen.daten.Unterlagentyp;

/**
 * Diese Classe ist fuer Service von Fahrerunterlagen zustaendig
 * Backend Struktur ist nach dem Tutorium gemacht, siehe Link
 * https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-jpa-dao-example/
 * @author Mariia Muravytska
 */
public class FahrerunterlagenService {
	
	private static FahrerunterlagenImplDAO fahrerunterlagenImplDAO;

	/**
	 * Konstruktor holt Objekt vom fahrerUnterlagenImplDAO
	 */
	public FahrerunterlagenService() {
		fahrerunterlagenImplDAO = FahrerunterlagenImplDAO.getInstance();
	}
	
	/**
	 * Methode die oeffnet DB Session
	 * ruft andere Methode auf und schliesst DB Session
	 * @see FahrerunterlagenImplDAO.getUnterlagentypen();
	 * @return Unterlagentypen
	 */
	public List<Unterlagentyp> getUnterlagenTypen() {
		fahrerunterlagenImplDAO.openCurrentSession();
		List<Unterlagentyp> unterlagentypen = fahrerunterlagenImplDAO.getUnterlagenTypen();
		fahrerunterlagenImplDAO.closeCurrentSession();
		return unterlagentypen;
	}
	
	/**
	 * Methode die sortiert die Namen der Fahrerunterlagentypen
	 * @return String Array von Typnamen
	 */
	public String[] getTypenNamen() {
		
		List<Unterlagentyp> unterlagentypen = this.getUnterlagenTypen(); 
		String[] typen = new String[unterlagentypen.size()];
		int i = 0;
		for (Unterlagentyp typ : unterlagentypen) {
			//typen.add(typ.getTypName());
			typen[i] = typ.getTypName().trim();
			i++;
		}
		return typen;
	}
	
	/**
	 * Methode die oeffnet DB Session
	 * ruft andere Methode auf und schliesst DB Session
	 * @see FahrerunterlagenImplDAO.getLinien();
	 * @return ArrayList von Linienbezeichnungen
	 */
	public List<String> getLinienBezeichnung() {
		fahrerunterlagenImplDAO.openCurrentSession();
		List<Linie> linien = fahrerunterlagenImplDAO.getLinien();
		fahrerunterlagenImplDAO.closeCurrentSession();
		ArrayList<String> linie_bezeichnung = new ArrayList<String>(); 
		for (Linie linie : linien) {
			linie_bezeichnung.add(linie.getBezeichnung());
		}
		return linie_bezeichnung;
	}

	/**
	 * Methode die oeffnet DB Session
	 * ruft andere Methode auf und schliesst DB Session
	 * @see FahrerunterlagenImplDAO.unterlageSpeichern();
	 * @param Fahrerunterlagen Objekt was gespeichert werden muss
	 */
	public void unterlageSpeichern(Fahrerunterlage entity) {
		//wenn eine Einreichung in DB gespeichert wird, wird einreichng_datum gesetzt
		Date date = new Date();
		if (entity.getStatus()=="nicht_bearbeitet") {
			entity.setEinreichung_datum(date);
		}
		//wenn ein Entwurf in DB gespeichert wird, wird speicher_datum gesetzt
		if (entity.getStatus()=="entwurf") {
			entity.setSpeicher_datum(date);
		}
		//aenderung_datum wird bei jeder Aktion neu gesetzt
		entity.setAenderung_datum(date);
		
		fahrerunterlagenImplDAO.openCurrentSessionwithTransaction();
		fahrerunterlagenImplDAO.unterlageSpeichern(entity);
		fahrerunterlagenImplDAO.closeCurrentSessionwithTransaction();
	}

	/**
	 * Methode die oeffnet DB Session
	 * ruft andere Methode auf und schliesst DB Session
	 * @see FahrerunterlagenImplDAO.unterlageBearbeiten();
	 * @param Fahrerunterlagen Objekt was geaendert werden soll
	 */
	public void unterlageBearbeiten(Fahrerunterlage entity) {
		Date date = new Date();
		//wenn eine Einreichung in DB geaendert wird, wird einreichng_datum gesetzt
		if (entity.getStatus()=="nicht_bearbeitet") {
			entity.setEinreichung_datum(date);
		}
		//wenn ein Entwurf in DB geaendert wird, wird speicher_datum gesetzt
		if (entity.getStatus()=="entwurf") {
			entity.setSpeicher_datum(date);
		}
		entity.setAenderung_datum(date);
		fahrerunterlagenImplDAO.openCurrentSessionwithTransaction();
		fahrerunterlagenImplDAO.unterlageBearbeiten(entity);
		fahrerunterlagenImplDAO.closeCurrentSessionwithTransaction();
	}
	
	/**
	 * Methode die oeffnet DB Session
	 * ruft andere Methode auf und schliesst DB Session
	 * @see FahrerunterlagenImplDAO.findeFahrerUnterlagen();
	 * @param Fahrer P-Nummer
	 * @param Boolean eingereicht
	 * @param int Unterlagentyp
	 * @return List von Fahrerunterlagen
	 */
	public List<Fahrerunterlage> findeFahrerUnterlagen(String fahrer, boolean eingereicht, int typ) {
		fahrerunterlagenImplDAO.openCurrentSession();
		List<Fahrerunterlage> unterlagen = fahrerunterlagenImplDAO.findeFahrerUnterlagen(fahrer, eingereicht, typ);
		fahrerunterlagenImplDAO.closeCurrentSession();
		return unterlagen;
	}

	/**
	 * Methode die oeffnet DB Session
	 * ruft andere Methode auf und schliesst DB Session
	 * @see FahrerunterlagenImplDAO.unterlageLoeschen();
	 * @param Fahrerunterlage die geloescht werden soll
	 */ 
	public void unterlageLoeschen(Fahrerunterlage entity) {
		fahrerunterlagenImplDAO.openCurrentSessionwithTransaction();
		fahrerunterlagenImplDAO.unterlageLoeschen(entity);
		fahrerunterlagenImplDAO.closeCurrentSessionwithTransaction();
	}
	
	
	public FahrerunterlagenImplDAO fahrerunterlagenImplDAO() {
		return fahrerunterlagenImplDAO;
	}
	
}