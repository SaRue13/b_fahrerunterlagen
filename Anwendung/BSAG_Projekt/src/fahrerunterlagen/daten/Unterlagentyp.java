package fahrerunterlagen.daten;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="unterlagentypen")
public class Unterlagentyp {
	
	@Id
	@Column(name="typ_id")
	int id;
	
	@Column(name="typname")
	String typName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTypName() {
		return typName;
	}

	public void setTypName(String name) {
		this.typName = name;
	}

	@Override
	public String toString() {
		return "Unterlagentyp [id=" + id + ", typName=" + typName + "]";
	}
	
}
