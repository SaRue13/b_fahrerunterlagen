package fahrerunterlagen;


import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Fundzettel;
import fahrerunterlagen.daten.Verspaetungsmeldung;
import fahrerunterlagen.service.FahrerunterlagenService;

import org.junit.After;
import org.junit.Assert;


public class FahrerunterlagenServiceTest {

	private FahrerunterlagenService fahrerunterlagenService  = new FahrerunterlagenService();
	private Fundzettel f;
	
	@Before
	public void before() {
		f = new Fundzettel();
		f.setP_nr_fahrer("p0003");
		f.setFundsache("Handy");
		f.setPlus50(true);
	    f.setBemerkung("Samsung, alt");
		f.setTyp(1);
		f.setFundort("Sitzplatz, letzte Reihe");
		f.setStatus("entwurf");
		
	}
	
	@Test
	public void testGetTypenNamen() {
		
		String [] typen = fahrerunterlagenService.getTypenNamen();
		
		Assert.assertEquals(true, Arrays.asList(typen).contains("Fundzettel"));
		Assert.assertEquals(true, Arrays.asList(typen).contains("Verspaetungsmeldung"));
		
	}

	@Test
	public void testGetLinienBezeichnung() {
		
		List<String> linien = fahrerunterlagenService.getLinienBezeichnung();
		
		Assert.assertEquals(true, linien.contains("1"));
		Assert.assertEquals(true, linien.contains("2"));
		Assert.assertEquals(true, linien.contains("3"));
		Assert.assertEquals(true, linien.contains("4"));
		
	}
	
	@Test
	public void testUnterlageSpeichern() {
		
		
		fahrerunterlagenService.unterlageSpeichern(f);
		
		List<Fahrerunterlage> fahrerunterlagen = fahrerunterlagenService.findeFahrerUnterlagen("p0003", false, 1);
		Assert.assertNotNull(fahrerunterlagen);
		Assert.assertEquals(true, (fahrerunterlagen.size()>0));
		// prüfen, ob id ist gleich wie beim letzten Objekt
		Assert.assertEquals(f.getFahrerunterlage_id(), (fahrerunterlagen.get(fahrerunterlagen.size()-1).getFahrerunterlage_id()));
		
	}
	

	@Test
	public void testUnterlageBearbeiten() {
		
		//erstmal speichern
		fahrerunterlagenService.unterlageSpeichern(f);
		
		//Daten ändern und "update"
		f.setTitel("Kugelschreiber");
		fahrerunterlagenService.unterlageBearbeiten(f);
		
		List<Fahrerunterlage> fahrerunterlagen = fahrerunterlagenService.findeFahrerUnterlagen("p0003", false, 1);
		
		// prüfen, ob id ist gleich wie beim letzten Objekt
		Assert.assertEquals(f.getFahrerunterlage_id(), (fahrerunterlagen.get(fahrerunterlagen.size()-1).getFahrerunterlage_id()));
		
		//prüfen, ob Fundsache aktualisiert wurde
		Assert.assertEquals("Kugelschreiber", (fahrerunterlagen.get(fahrerunterlagen.size()-1).getTitel()));				
	}

	@Test
	public void testFindeFahrerUnterlagen() {
		
		//Test-Daten speichern mit unterschiedlichen Fahrer, Status und Typ
		
		Verspaetungsmeldung v = new Verspaetungsmeldung(); 
		
		v.setTitel("VerspTest");
		v.setStatus("nicht_bearbeitet");
		v.setTyp(5);
		f.setP_nr_fahrer("p0001");
		
		fahrerunterlagenService.unterlageSpeichern(f);
		fahrerunterlagenService.unterlageSpeichern(v);
		
		//Unterlagen vom Fahrer p0003, false -> nicht eingereichte(Entwürfe), 1 -> Vom Typ 1(Fundzettel)
		List<Fahrerunterlage> fundzettel = fahrerunterlagenService.findeFahrerUnterlagen("p0003", false, 1);
		
		//Unterlagen vom Fahrer p0001, true -> eingereichte, 5 -> Vom Typ 5(Fundzettel)
		List<Fahrerunterlage> verpaetungsmeldungen = fahrerunterlagenService.findeFahrerUnterlagen("p0001", true, 5);
		
		//prüfen, ob die Liste nicht leer ist
		Assert.assertNotNull(fundzettel);
		Assert.assertNotNull(verpaetungsmeldungen);
		
		for(Fahrerunterlage fund : fundzettel) {
			//prüfen, ob geladene Fahrerunterlagen dem P-Nummer p0003 gehören
			Assert.assertEquals("p0003", fund.getP_nr_fahrer());
			//prüfen, ob geladene Fahrerunterlagen nicht eingereicht sind (false) 
			Assert.assertEquals("entwurf", fund.getStatus());
			//prüfen, ob geladene Fahrerunterlagen vom Typ 1 sind (Fundzettel)
			Assert.assertEquals(1, fund.getTyp());
		}
		
		for(Fahrerunterlage versp : verpaetungsmeldungen) {
			//prüfen, ob geladene Fahrerunterlagen dem P-Nummer p0001 gehören
			Assert.assertEquals("p0001", versp.getP_nr_fahrer());
			//prüfen, ob geladene Fahrerunterlagen eingereicht: alles ausser Entwurf (true)
			Assert.assertNotEquals("entwurf", versp.getStatus());
			//prüfen, ob geladene Fahrerunterlagen vom Typ 5 sind (Verspaetungsmeldung)
			Assert.assertEquals(5, versp.getTyp());
		}
	}

	@Test
	public void testUnterlageLoeschen() {
		
		fahrerunterlagenService.unterlageSpeichern(f);
		
		List<Fahrerunterlage> fahrerunterlagen = fahrerunterlagenService.findeFahrerUnterlagen("p0003", false, 1);
		
		//prüfen ob Fundzettel in DB ist
		Assert.assertEquals(f.getFahrerunterlage_id(), (fahrerunterlagen.get(fahrerunterlagen.size()-1).getFahrerunterlage_id()));
		
		//loeschen Fundzettel
		fahrerunterlagenService.unterlageLoeschen(f);
		
		List<Fahrerunterlage> fahrerunterlagen_neu = fahrerunterlagenService.findeFahrerUnterlagen("p0003", false, 1);
		
		//prüfen ob Fundzettel nicht mehr in DB ist
		Assert.assertNotEquals(f.getFahrerunterlage_id(), (fahrerunterlagen_neu.get(fahrerunterlagen_neu.size()-1).getFahrerunterlage_id()));
				
	}


	@After
    public void after() {
    }

}
