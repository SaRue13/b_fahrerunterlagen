package fahrerunterlagen.dao;

import java.io.Serializable;
import java.util.List;
import fahrerunterlagen.daten.Unterlagentyp;

public interface FahrerunterlagenDAO<T, Id extends Serializable> {
		
	public List<Unterlagentyp> getUnterlagenTypen(); 
	public void unterlageSpeichern(T entity);
	public void unterlageBearbeiten(T entity);
	public List<T> findeFahrerUnterlagen(String fahrer, boolean eingereicht, int typ);
	public void unterlageLoeschen(T entity);
	
}
