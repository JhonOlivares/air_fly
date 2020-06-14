package model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MODELOAVION database table.
 * 
 */
@Entity
@NamedQuery(name="ModeloAvion.findAll", query="SELECT m FROM ModeloAvion m")
public class ModeloAvion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (generator = "JPA_seq_ModeloAvion") 
    @SequenceGenerator (name = "JPA_seq_ModeloAvion", sequenceName = "seq_ModeloAvion", allocationSize = 1)
	@Column(name="IDMODELOAVION")
	private long modeloAvionID;
	
	private String modelo;

	@Column(name="LONGITUD")
	private BigDecimal longitud;
	
	@Column(name="CAPACIDAD")
	private long capacidad;

	@Column(name="ENVERGADURA")
	private BigDecimal envergadura;
	
	@Column(name="ALTURA")
	private BigDecimal altura;
	
	@Column(name="PESOMAX_DESPEGUE")
	private BigDecimal pesoMax_Despegue;

	//bi-directional many-to-one association to AsientoModeloAvion
	@OneToMany(mappedBy="modeloavion")
	private List<AsientoModeloAvion> asientoModeloavions;

	//bi-directional many-to-one association to Avion
	@OneToMany(mappedBy="modeloavion")
	private List<Avion> avions;

	//bi-directional many-to-one association to Manufacturer
	@ManyToOne
	@JoinColumn(name="ID_MANUFACTURER")
	private Manufacturer manufacturer;

	//bi-directional many-to-one association to VueloProgramado
	@OneToMany(mappedBy="modeloavion")
	private List<VueloProgramado> vueloProgramados;

	public ModeloAvion() {
	}

	public long getModeloAvionID() {
		return this.modeloAvionID;
	}

	public void setModeloAvionID(long modeloAvionID) {
		this.modeloAvionID = modeloAvionID;
	}

	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public List<AsientoModeloAvion> getAsientoModeloavions() {
		return this.asientoModeloavions;
	}

	public void setAsientoModeloavions(List<AsientoModeloAvion> asientoModeloavions) {
		this.asientoModeloavions = asientoModeloavions;
	}

	public AsientoModeloAvion addAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		getAsientoModeloavions().add(asientoModeloavion);
		asientoModeloavion.setModeloavion(this);

		return asientoModeloavion;
	}

	public AsientoModeloAvion removeAsientoModeloavion(AsientoModeloAvion asientoModeloavion) {
		getAsientoModeloavions().remove(asientoModeloavion);
		asientoModeloavion.setModeloavion(null);

		return asientoModeloavion;
	}

	public List<Avion> getAvions() {
		return this.avions;
	}

	public void setAvions(List<Avion> avions) {
		this.avions = avions;
	}

	public Avion addAvion(Avion avion) {
		getAvions().add(avion);
		avion.setModeloavion(this);

		return avion;
	}

	public Avion removeAvion(Avion avion) {
		getAvions().remove(avion);
		avion.setModeloavion(null);

		return avion;
	}

	public Manufacturer getManufacturer() {
		return this.manufacturer;
	}

	public void setManufacturer(Manufacturer manufacturer) {
		this.manufacturer = manufacturer;
	}

	public List<VueloProgramado> getVueloProgramados() {
		return this.vueloProgramados;
	}

	public void setVueloProgramados(List<VueloProgramado> vueloProgramados) {
		this.vueloProgramados = vueloProgramados;
	}

	public VueloProgramado addVueloProgramado(VueloProgramado vueloProgramado) {
		getVueloProgramados().add(vueloProgramado);
		vueloProgramado.setModeloavion(this);

		return vueloProgramado;
	}

	public VueloProgramado removeVueloProgramado(VueloProgramado vueloProgramado) {
		getVueloProgramados().remove(vueloProgramado);
		vueloProgramado.setModeloavion(null);

		return vueloProgramado;
	}

	public BigDecimal getLongitud() {
		return longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	public BigDecimal getEnvergadura() {
		return envergadura;
	}

	public void setEnvergadura(BigDecimal envergadura) {
		this.envergadura = envergadura;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPesoMax_Despegue() {
		return pesoMax_Despegue;
	}

	public void setPesoMax_Despegue(BigDecimal pesoMax_Despegue) {
		this.pesoMax_Despegue = pesoMax_Despegue;
	}

}