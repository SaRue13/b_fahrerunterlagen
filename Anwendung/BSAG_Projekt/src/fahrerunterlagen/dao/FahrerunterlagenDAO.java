package fahrerunterlagen.dao;

import java.io.Serializable;
import java.util.List;

import fahrerunterlagen.daten.Fahrerunterlage;
import fahrerunterlagen.daten.Unterlagentyp;
import fahrerunterlagen.daten.Urlaubstag;

public interface FahrerunterlagenDAO<T, Id extends Serializable> {
		
	public List<Unterlagentyp> getUnterlagenTypen(); 
	public void unterlageSpeichern(T entity);
	public void unterlageBearbeiten(T entity);
	public List<T> findeFahrerUnterlagen(String fahrer, boolean eingereicht, int typ);
	public void unterlageLoeschen(T entity);
	public void urlaubstagSpeichern(Urlaubstag urlaubstag);
	public List<Urlaubstag> getUrlaubstage(int urlaubsantrag_id);
	
}
