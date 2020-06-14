package model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the RECURRENCIA_VUELO_PROG database table.
 * 
 */
@Embeddable
public class RecurrenciaVueloProgPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ID_RECURRENCIA")
	private long recurrenciaID;

	@Column(name="NUMERO_VUELO", insertable=false, updatable=false)
	private String numeroVuelo;

	public RecurrenciaVueloProgPK() {
	}
	public long getRecurrenciaID() {
		return this.recurrenciaID;
	}
	public void setRecurrenciaID(long recurrenciaID) {
		this.recurrenciaID = recurrenciaID;
	}
	public String getNumeroVuelo() {
		return this.numeroVuelo;
	}
	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof RecurrenciaVueloProgPK)) {
			return false;
		}
		RecurrenciaVueloProgPK castOther = (RecurrenciaVueloProgPK)other;
		return 
			(this.recurrenciaID == castOther.recurrenciaID)
			&& this.numeroVuelo.equals(castOther.numeroVuelo);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.recurrenciaID ^ (this.recurrenciaID >>> 32)));
		hash = hash * prime + this.numeroVuelo.hashCode();
		
		return hash;
	}
}