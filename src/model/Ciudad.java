package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CIUDAD database table.
 * 
 */
@Entity
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long ciudadID;

	private String nombreciudad;

	//bi-directional many-to-one association to Aeropuerto
	@OneToMany(mappedBy="ciudad", cascade = CascadeType.ALL)
	private List<Aeropuerto> aeropuertos;

	//bi-directional many-to-one association to Pais
	@ManyToOne
	@JoinColumn(name="IATA_PAISID")
	private Pais pai;

	public Ciudad() {
	}

	public long getCiudadID() {
		return this.ciudadID;
	}

	public void setCiudadID(long ciudadID) {
		this.ciudadID = ciudadID;
	}

	public String getNombreciudad() {
		return this.nombreciudad;
	}

	public void setNombreciudad(String nombreciudad) {
		this.nombreciudad = nombreciudad;
	}

	public List<Aeropuerto> getAeropuertos() {
		return this.aeropuertos;
	}

	public void setAeropuertos(List<Aeropuerto> aeropuertos) {
		this.aeropuertos = aeropuertos;
	}

	public Aeropuerto addAeropuerto(Aeropuerto aeropuerto) {
		getAeropuertos().add(aeropuerto);
		aeropuerto.setCiudad(this);

		return aeropuerto;
	}

	public Aeropuerto removeAeropuerto(Aeropuerto aeropuerto) {
		getAeropuertos().remove(aeropuerto);
		aeropuerto.setCiudad(null);

		return aeropuerto;
	}

	public Pais getPai() {
		return this.pai;
	}

	public void setPai(Pais pai) {
		this.pai = pai;
	}

}