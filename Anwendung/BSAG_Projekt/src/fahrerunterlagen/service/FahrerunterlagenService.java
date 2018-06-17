package fahrerunterlagen.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fahrerunterlagen.dao.FahrerunterlagenImplDAO;
import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.daten.Unterlagentyp;

public class FahrerunterlagenService {
	private static FahrerunterlagenImplDAO fahrerunterlagenImplDAO;

	public FahrerunterlagenService() {
		fahrerunterlagenImplDAO = FahrerunterlagenImplDAO.getInstance();
	}
	
	public List<Unterlagentyp> getUnterlagenTypen() {
		fahrerunterlagenImplDAO.openCurrentSession();
		List<Unterlagentyp> unterlagentypen = fahrerunterlagenImplDAO.getUnterlagenTypen();
		fahrerunterlagenImplDAO.closeCurrentSession();
		return unterlagentypen;
	}
	
	public String[] getTypenNamen() {
		
		List<Unterlagentyp> unterlagentypen = this.getUnterlagenTypen(); 
		String[] typen = new String[unterlagentypen.size()];
		int i = 0;
		for (Unterlagentyp typ : unterlagentypen) {
			//typen.add(typ.getTypName());
			typen[i] = typ.getTypName();
			i++;
		}
		return typen;
	}

	public void unterlageSpeichern(Fahrerunterlage entity) {
		Date date = new Date();
		if (entity.getStatus()=="nicht_bearbeitet") {
			entity.setEinreichung_datum(date);
		}
		if (entity.getStatus()=="entwurf") {
			entity.setSpeicher_datum(date);
		}
		entity.setAenderung_datum(date);
		//speicher_datum
		//aenderungs_datum
		fahrerunterlagenImplDAO.openCurrentSessionwithTransaction();
		fahrerunterlagenImplDAO.unterlageSpeichern(entity);
		fahrerunterlagenImplDAO.closeCurrentSessionwithTransaction();
	}

	public void unterlageBearbeiten(Fahrerunterlage entity) {
		Date date = new Date();
		if (entity.getStatus()=="nicht_bearbeitet") {
			entity.setEinreichung_datum(date);
		}
		if (entity.getStatus()=="entwurf") {
			entity.setSpeicher_datum(date);
		}
		entity.setAenderung_datum(date);
		fahrerunterlagenImplDAO.openCurrentSessionwithTransaction();
		fahrerunterlagenImplDAO.unterlageBearbeiten(entity);
		fahrerunterlagenImplDAO.closeCurrentSessionwithTransaction();
	}
	
	public List<Fahrerunterlage> findeFahrerUnterlagen(String fahrer, boolean eingereicht, int typ) {
		fahrerunterlagenImplDAO.openCurrentSession();
		List<Fahrerunterlage> unterlagen = fahrerunterlagenImplDAO.findeFahrerUnterlagen(fahrer, eingereicht, typ);
		fahrerunterlagenImplDAO.closeCurrentSession();
		return unterlagen;
	}

	public void unterlageLoeschen(Fahrerunterlage entity) {
		fahrerunterlagenImplDAO.openCurrentSessionwithTransaction();
		fahrerunterlagenImplDAO.unterlageLoeschen(entity);
		fahrerunterlagenImplDAO.closeCurrentSessionwithTransaction();
	}
	
	public FahrerunterlagenImplDAO fahrerunterlagenImplDAO() {
		return fahrerunterlagenImplDAO;
	}
	
}