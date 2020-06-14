package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VUELO database table.
 * 
 */
@Entity
@NamedQuery(name="Vuelo.findAll", query="SELECT v FROM Vuelo v")
public class Vuelo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator="InvSeq") 
    @SequenceGenerator(name="InvSeq",sequenceName="seq_vuelo", allocationSize=1)
	private long vueloID;

	@Temporal(TemporalType.DATE)
	private Date fechahoraaterrizaje;

	@Temporal(TemporalType.DATE)
	private Date fechahoradespegue;

	@Temporal(TemporalType.DATE)
	private Date fechavuelo;

	//bi-directional many-to-one association to PrecioVueloClase
	@OneToMany(mappedBy="vuelo")
	private List<PrecioVueloClase> precioVueloClases;

	//bi-directional many-to-one association to Avion
	@ManyToOne
	@JoinColumn(name="AVIONMATRICULA")
	private Avion avion;

	//bi-directional many-to-one association to EstadoVuelo
	@ManyToOne
	@JoinColumn(name="ESTADOVUELOID")
	private EstadoVuelo estadoVuelo;

	//bi-directional many-to-one association to VueloProgramado
	@ManyToOne
	@JoinColumn(name="NUMERO_VUELO")
	private VueloProgramado vueloProgramado;

	public Vuelo() {
	}

	public long getVueloID() {
		return this.vueloID;
	}

	public void setVueloID(long vueloID) {
		this.vueloID = vueloID;
	}

	public Date getFechahoraaterrizaje() {
		return this.fechahoraaterrizaje;
	}

	public void setFechahoraaterrizaje(Date fechahoraaterrizaje) {
		this.fechahoraaterrizaje = fechahoraaterrizaje;
	}

	public Date getFechahoradespegue() {
		return this.fechahoradespegue;
	}

	public void setFechahoradespegue(Date fechahoradespegue) {
		this.fechahoradespegue = fechahoradespegue;
	}

	public Date getFechavuelo() {
		return this.fechavuelo;
	}

	public void setFechavuelo(Date fechavuelo) {
		this.fechavuelo = fechavuelo;
	}

	public List<PrecioVueloClase> getPrecioVueloClases() {
		return this.precioVueloClases;
	}

	public void setPrecioVueloClases(List<PrecioVueloClase> precioVueloClases) {
		this.precioVueloClases = precioVueloClases;
	}

	public PrecioVueloClase addPrecioVueloClas(PrecioVueloClase precioVueloClas) {
		getPrecioVueloClases().add(precioVueloClas);
		precioVueloClas.setVuelo(this);

		return precioVueloClas;
	}

	public PrecioVueloClase removePrecioVueloClas(PrecioVueloClase precioVueloClas) {
		getPrecioVueloClases().remove(precioVueloClas);
		precioVueloClas.setVuelo(null);

		return precioVueloClas;
	}

	public Avion getAvion() {
		return this.avion;
	}

	public void setAvion(Avion avion) {
		this.avion = avion;
	}

	public EstadoVuelo getEstadoVuelo() {
		return this.estadoVuelo;
	}

	public void setEstadoVuelo(EstadoVuelo estadoVuelo) {
		this.estadoVuelo = estadoVuelo;
	}

	public VueloProgramado getVueloProgramado() {
		return this.vueloProgramado;
	}

	public void setVueloProgramado(VueloProgramado vueloProgramado) {
		this.vueloProgramado = vueloProgramado;
	}

}