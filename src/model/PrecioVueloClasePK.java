package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the PRECIO_VUELO_CLASE database table.
 * 
 */
@Embeddable
public class PrecioVueloClasePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private long cabinaid;

	@Column(insertable=false, updatable=false)
	private long vueloid;

	public PrecioVueloClasePK() {
	}
	public long getCabinaid() {
		return this.cabinaid;
	}
	public void setCabinaid(long cabinaid) {
		this.cabinaid = cabinaid;
	}
	public long getVueloid() {
		return this.vueloid;
	}
	public void setVueloid(long vueloid) {
		this.vueloid = vueloid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof PrecioVueloClasePK)) {
			return false;
		}
		PrecioVueloClasePK castOther = (PrecioVueloClasePK)other;
		return 
			(this.cabinaid == castOther.cabinaid)
			&& (this.vueloid == castOther.vueloid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.cabinaid ^ (this.cabinaid >>> 32)));
		hash = hash * prime + ((int) (this.vueloid ^ (this.vueloid >>> 32)));
		
		return hash;
	}
}