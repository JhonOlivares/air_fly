package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADO_VUELO database table.
 * 
 */
@Entity
@Table(name="ESTADO_VUELO")
@NamedQuery(name="EstadoVuelo.findAll", query="SELECT e FROM EstadoVuelo e")
public class EstadoVuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long estadoVueloID;

	private String nombreestadovuelo;

	//bi-directional many-to-one association to Vuelo
	@OneToMany(mappedBy="estadoVuelo")
	private List<Vuelo> vuelos;

	public EstadoVuelo() {
	}

	public long getEstadoVueloID() {
		return this.estadoVueloID;
	}

	public void setEstadoVueloID(long estadoVueloID) {
		this.estadoVueloID = estadoVueloID;
	}

	public String getNombreestadovuelo() {
		return this.nombreestadovuelo;
	}

	public void setNombreestadovuelo(String nombreestadovuelo) {
		this.nombreestadovuelo = nombreestadovuelo;
	}

	public List<Vuelo> getVuelos() {
		return this.vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public Vuelo addVuelo(Vuelo vuelo) {
		getVuelos().add(vuelo);
		vuelo.setEstadoVuelo(this);

		return vuelo;
	}

	public Vuelo removeVuelo(Vuelo vuelo) {
		getVuelos().remove(vuelo);
		vuelo.setEstadoVuelo(null);

		return vuelo;
	}

}