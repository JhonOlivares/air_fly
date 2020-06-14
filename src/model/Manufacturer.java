package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MANUFACTURER database table.
 * 
 */
@Entity
@NamedQuery(name="Manufacturer.findAll", query="SELECT m FROM Manufacturer m")
public class Manufacturer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDMANUFACTURER")
	private long fabricanteID;

	private String fabricante;

	//bi-directional many-to-one association to ModeloAvion
	@OneToMany(mappedBy="manufacturer")
	private List<ModeloAvion> modeloavions;

	public Manufacturer() {
	}

	public long getFabricanteID() {
		return this.fabricanteID;
	}

	public void setFabricanteID(long fabricanteID) {
		this.fabricanteID = fabricanteID;
	}

	public String getFabricante() {
		return this.fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public List<ModeloAvion> getModeloavions() {
		return this.modeloavions;
	}

	public void setModeloavions(List<ModeloAvion> modeloavions) {
		this.modeloavions = modeloavions;
	}

	public ModeloAvion addModeloavion(ModeloAvion modeloavion) {
		getModeloavions().add(modeloavion);
		modeloavion.setManufacturer(this);

		return modeloavion;
	}

	public ModeloAvion removeModeloavion(ModeloAvion modeloavion) {
		getModeloavions().remove(modeloavion);
		modeloavion.setManufacturer(null);

		return modeloavion;
	}

}