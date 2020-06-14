package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ASIENTO database table.
 * 
 */
@Entity
@NamedQuery(name="Asiento.findAll", query="SELECT a FROM Asiento a")
public class Asiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String asientoID;

	//bi-directional many-to-one association to AsientoModeloAvion
	@OneToMany(mappedBy="asiento")
	private List<AsientoModeloAvion> asientoModeloavions;

	public Asiento() {
	}

	public String getAsientoID() {
		return this.asientoID;
	}

	public void setAsientoID(String asientoID) {
		this.asientoID = asientoID;
	}

	public List<AsientoModeloAvion> getAsientoModeloavions() {
		return this.asientoModeloavions;
	}

	public void setAsientoModeloavions(List<AsientoModeloAvion> asientoModeloavions) {
		this.asientoModeloavions = asientoModeloavions;
	}

	public AsientoModeloAvion addAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		getAsientoModeloavions().add(asientoModeloavion);
		asientoModeloavion.setAsiento(this);

		return asientoModeloavion;
	}

	public AsientoModeloAvion removeAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		getAsientoModeloavions().remove(asientoModeloavion);
		asientoModeloavion.setAsiento(null);

		return asientoModeloavion;
	}

}