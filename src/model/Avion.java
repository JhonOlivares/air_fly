package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AVION database table.
 * 
 */
@Entity
@NamedQuery(name="Avion.findAll", query="SELECT a FROM Avion a")
public class Avion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String avionMatricula;

	private String serie;

	//bi-directional many-to-one association to EstadoAvion
	@ManyToOne
	@JoinColumn(name="ESTADOID")
	private EstadoAvion estadoAvion;

	//bi-directional many-to-one association to ModeloAvion
	@ManyToOne
	@JoinColumn(name="ID_MODELOAVION")
	private ModeloAvion modeloavion;

	//bi-directional many-to-one association to Vuelo
	@OneToMany(mappedBy="avion")
	private List<Vuelo> vuelos;

	public Avion() {
	}

	public String getAvionMatricula() {
		return this.avionMatricula;
	}

	public void setAvionMatricula(String avionMatricula) {
		this.avionMatricula = avionMatricula;
	}

	public String getSerie() {
		return this.serie;
	}

	public void setSerie(String serie) {
		this.serie = serie;
	}

	public EstadoAvion getEstadoAvion() {
		return this.estadoAvion;
	}

	public void setEstadoAvion(EstadoAvion estadoAvion) {
		this.estadoAvion = estadoAvion;
	}

	public ModeloAvion getModeloavion() {
		return this.modeloavion;
	}

	public void setModeloavion(ModeloAvion modeloavion) {
		this.modeloavion = modeloavion;
	}

	public List<Vuelo> getVuelos() {
		return this.vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public Vuelo addVuelo(Vuelo vuelo) {
		getVuelos().add(vuelo);
		vuelo.setAvion(this);

		return vuelo;
	}

	public Vuelo removeVuelo(Vuelo vuelo) {
		getVuelos().remove(vuelo);
		vuelo.setAvion(null);

		return vuelo;
	}

}