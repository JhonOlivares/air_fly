package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the PRECIO_VUELO_CLASE database table.
 * 
 */
@Entity
@Table(name="PRECIO_VUELO_CLASE")
@NamedQuery(name="PrecioVueloClase.findAll", query="SELECT p FROM PrecioVueloClase p")
public class PrecioVueloClase implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PrecioVueloClasePK id;

	@Column(name="PRECIO_CLASE")
	private BigDecimal precioClase;

	//bi-directional many-to-one association to Cabina
	@ManyToOne
	@JoinColumn(name="CABINAID")
	private Cabina cabina;

	//bi-directional many-to-one association to Vuelo
	@ManyToOne
	@JoinColumn(name="VUELOID")
	private Vuelo vuelo;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="precioVueloClase")
	private List<Reserva> reservas;

	public PrecioVueloClase() {
	}

	public PrecioVueloClasePK getId() {
		return this.id;
	}

	public void setId(PrecioVueloClasePK id) {
		this.id = id;
	}

	public BigDecimal getPrecioClase() {
		return this.precioClase;
	}

	public void setPrecioClase(BigDecimal precioClase) {
		this.precioClase = precioClase;
	}

	public Cabina getCabina() {
		return this.cabina;
	}

	public void setCabina(Cabina cabina) {
		this.cabina = cabina;
	}

	public Vuelo getVuelo() {
		return this.vuelo;
	}

	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setPrecioVueloClase(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setPrecioVueloClase(null);

		return reserva;
	}

}