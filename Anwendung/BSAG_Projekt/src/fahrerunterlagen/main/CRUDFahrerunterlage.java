package fahrerunterlagen.main;

import java.util.Date;
import java.util.List;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.service.FahrerunterlagenService;

public class CRUDFahrerunterlage {

	public static void main(String[] args) {
		
		FahrerunterlagenService fahrerunterlagenService = new FahrerunterlagenService();
		
		//Fahrerunterlage Speichern
		
		Fundzettel f = new Fundzettel();
		Date datum = new Date();
		f.setP_nr_fahrer("P3245");
		f.setFundsache("Handy");
		f.setPlus50(true);
	    f.setBemerkung("Samsung, alt");
		f.setTyp(1);
		f.setFundort("Sitzplatz, letzte Reihe");
		f.setDatumZeit(datum);
		f.setStatus("entwurf");
		
		fahrerunterlagenService.unterlageSpeichern(f);
		
		//Suche aller Fundzettel-Entwuerfe vom Fahrer P3245
		System.out.println("*** Suche aller Fundzettel-Entwuerfe vom Fahrer P3245 - Start ***");
		List<Fahrerunterlage> forms = fahrerunterlagenService.findeFahrerUnterlagen("P3245", false, 1);
		System.out.println("Entwuerfe :");
		for (Fahrerunterlage b : forms) {
		    System.out.println("-" + b.toString());
		}
		System.out.println("*** Suche - Ende ***");
		
		//Fahrerunterlage Bearbeiten
		f.setTitel(f.getP_nr_fahrer()+"_"+f.getTitel()+"_"+f.getDatumZeit());
		f.setStatus("nicht_bearbeitet");
		fahrerunterlagenService.unterlageBearbeiten(f);
		
		//Suche aller Fundzettel-Entwuerfe vom Fahrer P3245
		System.out.println("*** Suche aller Fundzettel-Entwuerfe vom Fahrer P3245 - Start ***");
		List<Fahrerunterlage> form = fahrerunterlagenService.findeFahrerUnterlagen("P3245", false, 1);
		System.out.println("Entwuerfe :");
		for (Fahrerunterlage b : form) {
			//loeschen
			if (b.getFahrerunterlage_id() == 4) 
				fahrerunterlagenService.unterlageLoeschen(b);
		    System.out.println("-" + b.toString());	
		}
		System.out.println("*** Suche - Ende ***");		

		System.exit(0);
	
	}

}
