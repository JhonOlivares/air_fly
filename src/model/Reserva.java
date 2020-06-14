package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the RESERVA database table.
 * 
 */
@Entity
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long reservaID;

	@Temporal(TemporalType.DATE)
	private Date fechahorareserva;

	@Column(name="PRECIO_VUELO")
	private BigDecimal precioVuelo;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="CLIENTEID")
	private Cliente cliente;

	//bi-directional many-to-one association to EstadoReserva
	@ManyToOne
	@JoinColumn(name="ESTADORESERVAID")
	private EstadoReserva estadoReserva;

	//bi-directional many-to-one association to PrecioVueloClase
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="CABINAID", referencedColumnName="CABINAID"),
		@JoinColumn(name="VUELOID", referencedColumnName="VUELOID")
		})
	private PrecioVueloClase precioVueloClase;

	//bi-directional many-to-one association to TarjetaEmbarque
	@OneToMany(mappedBy="reserva")
	private List<TarjetaEmbarque> tarjetaEmbarques;

	public Reserva() {
	}

	public long getReservaID() {
		return this.reservaID;
	}

	public void setReservaID(long reservaID) {
		this.reservaID = reservaID;
	}

	public Date getFechahorareserva() {
		return this.fechahorareserva;
	}

	public void setFechahorareserva(Date fechahorareserva) {
		this.fechahorareserva = fechahorareserva;
	}

	public BigDecimal getPrecioVuelo() {
		return this.precioVuelo;
	}

	public void setPrecioVuelo(BigDecimal precioVuelo) {
		this.precioVuelo = precioVuelo;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public EstadoReserva getEstadoReserva() {
		return this.estadoReserva;
	}

	public void setEstadoReserva(EstadoReserva estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public PrecioVueloClase getPrecioVueloClase() {
		return this.precioVueloClase;
	}

	public void setPrecioVueloClase(PrecioVueloClase precioVueloClase) {
		this.precioVueloClase = precioVueloClase;
	}

	public List<TarjetaEmbarque> getTarjetaEmbarques() {
		return this.tarjetaEmbarques;
	}

	public void setTarjetaEmbarques(List<TarjetaEmbarque> tarjetaEmbarques) {
		this.tarjetaEmbarques = tarjetaEmbarques;
	}

	public TarjetaEmbarque addTarjetaEmbarque(TarjetaEmbarque tarjetaEmbarque) {
		getTarjetaEmbarques().add(tarjetaEmbarque);
		tarjetaEmbarque.setReserva(this);

		return tarjetaEmbarque;
	}

	public TarjetaEmbarque removeTarjetaEmbarque(TarjetaEmbarque tarjetaEmbarque) {
		getTarjetaEmbarques().remove(tarjetaEmbarque);
		tarjetaEmbarque.setReserva(null);

		return tarjetaEmbarque;
	}

}