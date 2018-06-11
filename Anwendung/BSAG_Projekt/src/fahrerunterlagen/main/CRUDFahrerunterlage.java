package fahrerunterlagen.main;

import java.util.Date;
import java.util.List;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.daten.Unterlagentyp;
import fahrerunterlagen.daten.Urlaubsantrag;
import fahrerunterlagen.daten.Verschmutzungsmeldung;
import fahrerunterlagen.daten.Wagenlaufkarte;
import fahrerunterlagen.service.FahrerunterlagenService;

public class CRUDFahrerunterlage {

	public static void main(String[] args) {
		
		FahrerunterlagenService fahrerunterlagenService = new FahrerunterlagenService();
		
		
		/**
		//Urlaubsantrag Speichern
		
		Urlaubsantrag u = new Urlaubsantrag();
		u.setStatus("entwurf");
		u.setP_nr_fahrer("P3245");
		u.setTyp(2);
		u.setB("b-test");
		u.setSchulpflicht(true);
		u.setUrlaubsanspruch(12);
		
		fahrerunterlagenService.unterlageSpeichern(u);
		*/
		
		/**
		// Wagenlaufkarte speichern
		Wagenlaufkarte w = new Wagenlaufkarte();
		w.setStatus("entwurf");
		w.setP_nr_fahrer("P3245");
		
		fahrerunterlagenService.unterlageSpeichern(w);
		*/
		
		// Verschmutzungsmeldung speichern
		Verschmutzungsmeldung v = new Verschmutzungsmeldung();
			v.setStatus("entwurf");
			v.setP_nr_fahrer("P3245");
			v.setReinigungskosten(50);
			v.setReinigungskosten_bezahlt(true);
			v.setVerunreinigungsart("Verschmutzung");
		fahrerunterlagenService.unterlageSpeichern(v);
		
		//Unterlagentypen laden
		List<Unterlagentyp> typen = fahrerunterlagenService.getUnterlagenTypen();
		System.out.println("Unterlagentypen :");
		for (Unterlagentyp b : typen) {
			System.out.println("-" + b.toString());
		}
		
		//Suche aller Fundzettel-Entwuerfe vom Fahrer P3245
		System.out.println("*** Suche aller Fundzettel-Entwuerfe vom Fahrer P3245 - Start ***");
		List<Fahrerunterlage> forms = fahrerunterlagenService.findeFahrerUnterlagen("P3245", false, 3);
		System.out.println("Entwuerfe :");
		for (Fahrerunterlage b : forms) {
			System.out.println("-" + b.toString());
		}
		System.out.println("*** Suche - Ende ***");
		/**		
		
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
		/**
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
		*/
		System.exit(0);
	
	}

}
