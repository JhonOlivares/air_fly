package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADO_RESERVA database table.
 * 
 */
@Entity
@Table(name="ESTADO_RESERVA")
@NamedQuery(name="EstadoReserva.findAll", query="SELECT e FROM EstadoReserva e")
public class EstadoReserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long estadoReservaID;

	@Lob
	private String descripcionestadoreserva;

	private String nombreestadoreserva;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="estadoReserva")
	private List<Reserva> reservas;

	public EstadoReserva() {
	}

	public long getEstadoReservaID() {
		return this.estadoReservaID;
	}

	public void setEstadoReservaID(long estadoReservaID) {
		this.estadoReservaID = estadoReservaID;
	}

	public String getDescripcionestadoreserva() {
		return this.descripcionestadoreserva;
	}

	public void setDescripcionestadoreserva(String descripcionestadoreserva) {
		this.descripcionestadoreserva = descripcionestadoreserva;
	}

	public String getNombreestadoreserva() {
		return this.nombreestadoreserva;
	}

	public void setNombreestadoreserva(String nombreestadoreserva) {
		this.nombreestadoreserva = nombreestadoreserva;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setEstadoReserva(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setEstadoReserva(null);

		return reserva;
	}

}