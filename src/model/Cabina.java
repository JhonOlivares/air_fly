package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the CABINA database table.
 * 
 */
@Entity
@NamedQuery(name="Cabina.findAll", query="SELECT c FROM Cabina c")
public class Cabina implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long cabinaID;

	private String cabinanombre;

	//bi-directional many-to-one association to AsientoModeloAvion
	@OneToMany(mappedBy="cabina")
	private List<AsientoModeloAvion> asientoModeloavions;

	//bi-directional many-to-one association to PrecioVueloClase
	@OneToMany(mappedBy="cabina")
	private List<PrecioVueloClase> precioVueloClases;

	public Cabina() {
	}

	public long getCabinaID() {
		return this.cabinaID;
	}

	public void setCabinaID(long cabinaID) {
		this.cabinaID = cabinaID;
	}

	public String getCabinanombre() {
		return this.cabinanombre;
	}

	public void setCabinanombre(String cabinanombre) {
		this.cabinanombre = cabinanombre;
	}

	public List<AsientoModeloAvion> getAsientoModeloavions() {
		return this.asientoModeloavions;
	}

	public void setAsientoModeloavions(List<AsientoModeloAvion> asientoModeloavions) {
		this.asientoModeloavions = asientoModeloavions;
	}

	public AsientoModeloAvion addAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		getAsientoModeloavions().add(asientoModeloavion);
		asientoModeloavion.setCabina(this);

		return asientoModeloavion;
	}

	public AsientoModeloAvion removeAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		getAsientoModeloavions().remove(asientoModeloavion);
		asientoModeloavion.setCabina(null);

		return asientoModeloavion;
	}

	public List<PrecioVueloClase> getPrecioVueloClases() {
		return this.precioVueloClases;
	}

	public void setPrecioVueloClases(List<PrecioVueloClase> precioVueloClases) {
		this.precioVueloClases = precioVueloClases;
	}

	public PrecioVueloClase addPrecioVueloClas(PrecioVueloClase precioVueloClas) {
		getPrecioVueloClases().add(precioVueloClas);
		precioVueloClas.setCabina(this);

		return precioVueloClas;
	}

	public PrecioVueloClase removePrecioVueloClas(PrecioVueloClase precioVueloClas) {
		getPrecioVueloClases().remove(precioVueloClas);
		precioVueloClas.setCabina(null);

		return precioVueloClas;
	}

}