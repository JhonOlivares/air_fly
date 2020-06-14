package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ESTADO_AVION database table.
 * 
 */
@Entity
@Table(name="ESTADO_AVION")
@NamedQuery(name="EstadoAvion.findAll", query="SELECT e FROM EstadoAvion e")
public class EstadoAvion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (generator = "JPA_seq_EstadoAvion") 
    @SequenceGenerator (name = "JPA_seq_EstadoAvion", sequenceName = "seq_EstadoAvion", allocationSize = 1)
	private long estadoID;

	private String estado;

	//bi-directional many-to-one association to Avion
	@OneToMany(mappedBy="estadoAvion")
	private List<Avion> avions;

	public EstadoAvion() {
	}

	public long getEstadoID() {
		return this.estadoID;
	}

	public void setEstadoID(long estadoID) {
		this.estadoID = estadoID;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public List<Avion> getAvions() {
		return this.avions;
	}

	public void setAvions(List<Avion> avions) {
		this.avions = avions;
	}

	public Avion addAvion(Avion avion) {
		getAvions().add(avion);
		avion.setEstadoAvion(this);

		return avion;
	}

	public Avion removeAvion(Avion avion) {
		getAvions().remove(avion);
		avion.setEstadoAvion(null);

		return avion;
	}

}