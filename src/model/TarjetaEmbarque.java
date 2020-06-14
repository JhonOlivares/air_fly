package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the TARJETA_EMBARQUE database table.
 * 
 */
@Entity
@Table(name="TARJETA_EMBARQUE")
@NamedQuery(name="TarjetaEmbarque.findAll", query="SELECT t FROM TarjetaEmbarque t")
public class TarjetaEmbarque implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long tarjetaEmbarqueID;

	@Temporal(TemporalType.DATE)
	private Date fechaemision;

	//bi-directional many-to-one association to AsientoModeloAvion
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="ASIENTOID", referencedColumnName="ASIENTOID"),
		@JoinColumn(name="ID_MODELOAVION", referencedColumnName="ID_MODELOAVION")
		})
	private AsientoModeloAvion asientoModeloavion;

	//bi-directional many-to-one association to Reserva
	@ManyToOne
	@JoinColumn(name="RESERVAID")
	private Reserva reserva;

	public TarjetaEmbarque() {
	}

	public long getTarjetaEmbarqueID() {
		return this.tarjetaEmbarqueID;
	}

	public void setTarjetaEmbarqueID(long tarjetaEmbarqueID) {
		this.tarjetaEmbarqueID = tarjetaEmbarqueID;
	}

	public Date getFechaemision() {
		return this.fechaemision;
	}

	public void setFechaemision(Date fechaemision) {
		this.fechaemision = fechaemision;
	}

	public AsientoModeloAvion getAsientoModeloavion() {
		return this.asientoModeloavion;
	}

	public void setAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		this.asientoModeloavion = asientoModeloavion;
	}

	public Reserva getReserva() {
		return this.reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

}