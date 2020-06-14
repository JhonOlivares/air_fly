package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the AEROPUERTO database table.
 * 
 */
@Entity
@NamedQuery(name="Aeropuerto.findAll", query="SELECT a FROM Aeropuerto a")
public class Aeropuerto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String iataAeropuertoID;

	private String nombreaeropuerto;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="CIUDADID")
	private Ciudad ciudad;

	//bi-directional many-to-one association to VueloProgramado
	@OneToMany(mappedBy="aeropuerto1", cascade = CascadeType.PERSIST)
	private List<VueloProgramado> vueloProgramados1;

	//bi-directional many-to-one association to VueloProgramado
	@OneToMany(mappedBy="aeropuerto2", cascade = CascadeType.PERSIST)
	private List<VueloProgramado> vueloProgramados2;

	public Aeropuerto() {
	}

	public String getIataAeropuertoID() {
		return this.iataAeropuertoID;
	}

	public void setIataAeropuertoID(String iataAeropuertoID) {
		this.iataAeropuertoID = iataAeropuertoID;
	}

	public String getNombreaeropuerto() {
		return this.nombreaeropuerto;
	}

	public void setNombreaeropuerto(String nombreaeropuerto) {
		this.nombreaeropuerto = nombreaeropuerto;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public List<VueloProgramado> getVueloProgramados1() {
		return this.vueloProgramados1;
	}

	public void setVueloProgramados1(List<VueloProgramado> vueloProgramados1) {
		this.vueloProgramados1 = vueloProgramados1;
	}

	public VueloProgramado addVueloProgramados1(VueloProgramado vueloProgramados1) {
		getVueloProgramados1().add(vueloProgramados1);
		vueloProgramados1.setAeropuerto1(this);

		return vueloProgramados1;
	}

	public VueloProgramado removeVueloProgramados1(VueloProgramado vueloProgramados1) {
		getVueloProgramados1().remove(vueloProgramados1);
		vueloProgramados1.setAeropuerto1(null);

		return vueloProgramados1;
	}

	public List<VueloProgramado> getVueloProgramados2() {
		return this.vueloProgramados2;
	}

	public void setVueloProgramados2(List<VueloProgramado> vueloProgramados2) {
		this.vueloProgramados2 = vueloProgramados2;
	}

	public VueloProgramado addVueloProgramados2(VueloProgramado vueloProgramados2) {
		getVueloProgramados2().add(vueloProgramados2);
		vueloProgramados2.setAeropuerto2(this);

		return vueloProgramados2;
	}

	public VueloProgramado removeVueloProgramados2(VueloProgramado vueloProgramados2) {
		getVueloProgramados2().remove(vueloProgramados2);
		vueloProgramados2.setAeropuerto2(null);

		return vueloProgramados2;
	}

}