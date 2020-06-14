package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the PAIS database table.
 * 
 */
@Entity
@NamedQuery(name="Pais.findAll", query="SELECT p FROM Pais p")
public class Pais implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String iataPaisID;

	private String nombrepais;

	//bi-directional many-to-one association to Ciudad
	@OneToMany(mappedBy="pai")
	private List<Ciudad> ciudads;

	public Pais() {
	}

	public String getIataPaisID() {
		return this.iataPaisID;
	}

	public void setIataPaisID(String iataPaisID) {
		this.iataPaisID = iataPaisID;
	}

	public String getNombrepais() {
		return this.nombrepais;
	}

	public void setNombrepais(String nombrepais) {
		this.nombrepais = nombrepais;
	}

	public List<Ciudad> getCiudads() {
		return this.ciudads;
	}

	public void setCiudads(List<Ciudad> ciudads) {
		this.ciudads = ciudads;
	}

	public Ciudad addCiudad(Ciudad ciudad) {
		getCiudads().add(ciudad);
		ciudad.setPai(this);

		return ciudad;
	}

	public Ciudad removeCiudad(Ciudad ciudad) {
		getCiudads().remove(ciudad);
		ciudad.setPai(null);

		return ciudad;
	}

}