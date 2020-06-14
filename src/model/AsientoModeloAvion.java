package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ASIENTO_MODELOAVION database table.
 * 
 */
@Entity
@Table(name="ASIENTO_MODELOAVION")
@NamedQuery(name="AsientoModeloAvion.findAll", query="SELECT a FROM AsientoModeloAvion a")
public class AsientoModeloAvion implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private AsientoModeloAvionPK id;

	//bi-directional many-to-one association to Asiento
	@ManyToOne
	@JoinColumn(name="ASIENTOID")
	private Asiento asiento;

	//bi-directional many-to-one association to Cabina
	@ManyToOne
	@JoinColumn(name="CABINAID")
	private Cabina cabina;

	//bi-directional many-to-one association to ModeloAvion
	@ManyToOne
	@JoinColumn(name="ID_MODELOAVION")
	private ModeloAvion modeloavion;

	//bi-directional many-to-one association to TarjetaEmbarque
	@OneToMany(mappedBy="asientoModeloavion")
	private List<TarjetaEmbarque> tarjetaEmbarques;

	public AsientoModeloAvion() {
	}

	public AsientoModeloAvionPK getId() {
		return this.id;
	}

	public void setId(AsientoModeloAvionPK id) {
		this.id = id;
	}

	public Asiento getAsiento() {
		return this.asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	public Cabina getCabina() {
		return this.cabina;
	}

	public void setCabina(Cabina cabina) {
		this.cabina = cabina;
	}

	public ModeloAvion getModeloavion() {
		return this.modeloavion;
	}

	public void setModeloavion(ModeloAvion modeloavion) {
		this.modeloavion = modeloavion;
	}

	public List<TarjetaEmbarque> getTarjetaEmbarques() {
		return this.tarjetaEmbarques;
	}

	public void setTarjetaEmbarques(List<TarjetaEmbarque> tarjetaEmbarques) {
		this.tarjetaEmbarques = tarjetaEmbarques;
	}

	public TarjetaEmbarque addTarjetaEmbarque(TarjetaEmbarque tarjetaEmbarque) {
		getTarjetaEmbarques().add(tarjetaEmbarque);
		tarjetaEmbarque.setAsientoModeloavion(this);

		return tarjetaEmbarque;
	}

	public TarjetaEmbarque removeTarjetaEmbarque(TarjetaEmbarque tarjetaEmbarque) {
		getTarjetaEmbarques().remove(tarjetaEmbarque);
		tarjetaEmbarque.setAsientoModeloavion(null);

		return tarjetaEmbarque;
	}

}