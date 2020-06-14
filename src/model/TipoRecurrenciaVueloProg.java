package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPO_RECURR_VUELO_PROG database table.
 * 
 */
@Entity
@Table(name="TIPO_RECURR_VUELO_PROG")
@NamedQuery(name="TipoRecurrenciaVueloProg.findAll", query="SELECT t FROM TipoRecurrenciaVueloProg t")
public class TipoRecurrenciaVueloProg implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_TIPO")
	private long tipoRVPID;

	@Column(name="TIPO_RECURRENCIA")
	private String tipoRecurrencia;

	//bi-directional many-to-one association to RecurrenciaVueloProg
	@OneToMany(mappedBy="tipoRecurrVueloProg")
	private List<RecurrenciaVueloProg> recurrenciaVueloProgs;

	public TipoRecurrenciaVueloProg() {
	}

	public long getTipoRVPID() {
		return this.tipoRVPID;
	}

	public void setTipoRVPID(long tipoRVPID) {
		this.tipoRVPID = tipoRVPID;
	}

	public String getTipoRecurrencia() {
		return this.tipoRecurrencia;
	}

	public void setTipoRecurrencia(String tipoRecurrencia) {
		this.tipoRecurrencia = tipoRecurrencia;
	}

	public List<RecurrenciaVueloProg> getRecurrenciaVueloProgs() {
		return this.recurrenciaVueloProgs;
	}

	public void setRecurrenciaVueloProgs(List<RecurrenciaVueloProg> recurrenciaVueloProgs) {
		this.recurrenciaVueloProgs = recurrenciaVueloProgs;
	}

	public RecurrenciaVueloProg addRecurrenciaVueloProg(RecurrenciaVueloProg recurrenciaVueloProg) {
		getRecurrenciaVueloProgs().add(recurrenciaVueloProg);
		recurrenciaVueloProg.setTipoRecurrVueloProg(this);

		return recurrenciaVueloProg;
	}

	public RecurrenciaVueloProg removeRecurrenciaVueloProg(RecurrenciaVueloProg recurrenciaVueloProg) {
		getRecurrenciaVueloProgs().remove(recurrenciaVueloProg);
		recurrenciaVueloProg.setTipoRecurrVueloProg(null);

		return recurrenciaVueloProg;
	}

}