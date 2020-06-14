package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ASIENTO_MODELOAVION database table.
 * 
 */
@Embeddable
public class AsientoModeloAvionPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private String asientoid;

	@Column(name="ID_MODELOAVION", insertable=false, updatable=false)
	private long idModeloavion;

	public AsientoModeloAvionPK() {
	}
	public String getAsientoid() {
		return this.asientoid;
	}
	public void setAsientoid(String asientoid) {
		this.asientoid = asientoid;
	}
	public long getIdModeloavion() {
		return this.idModeloavion;
	}
	public void setIdModeloavion(long idModeloavion) {
		this.idModeloavion = idModeloavion;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof AsientoModeloAvionPK)) {
			return false;
		}
		AsientoModeloAvionPK castOther = (AsientoModeloAvionPK)other;
		return 
			this.asientoid.equals(castOther.asientoid)
			&& (this.idModeloavion == castOther.idModeloavion);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.asientoid.hashCode();
		hash = hash * prime + ((int) (this.idModeloavion ^ (this.idModeloavion >>> 32)));
		
		return hash;
	}
}