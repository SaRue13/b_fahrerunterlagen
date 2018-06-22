package fahrerunterlagen.daten;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="linie")
public class Linie {
	@Id
	@Column(name="bezeichnung")
	private String bezeichnung;
	
	public Linie() {
		super();
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void setBezeichnung(String bezeichnung) {
		this.bezeichnung = bezeichnung;
	}

	@Override
	public String toString() {
		return "Linie [bezeichnung=" + bezeichnung + "]";
	}
	
}
