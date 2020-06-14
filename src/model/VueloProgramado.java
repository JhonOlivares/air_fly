package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the VUELO_PROGRAMADO database table.
 * 
 */
@Entity
@Table(name="VUELO_PROGRAMADO")
@NamedQuery(name="VueloProgramado.findAll", query="SELECT v FROM VueloProgramado v")
public class VueloProgramado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String numeroVuelo;

	@Column(name="ES_RECURRENTE")
	private String esRecurrente;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_FIN")
	private Date fechaFin;

	@Temporal(TemporalType.DATE)
	@Column(name="FECHA_INICIO")
	private Date fechaInicio;

	@Temporal(TemporalType.TIME)
	@Column(name="HORA_PROGRAMADA")
	private Date horaProgramada;
	
	@Temporal(TemporalType.TIME)
	@Column(name="TIEMPODEVUELO")
	private Date tiempodevuelo;
	
	@Temporal(TemporalType.TIME)
	@Column(name="HORA_LLEGADA")
	private Date horaDeLlegada;

	//bi-directional many-to-one association to RecurrenciaVueloProg
	@OneToMany(mappedBy="vueloProgramado")
	private List<RecurrenciaVueloProg> recurrenciaVueloProgs;

	//bi-directional many-to-one association to Vuelo
	@OneToMany(mappedBy="vueloProgramado")
	private List<Vuelo> vuelos;

	//bi-directional many-to-one association to Aeropuerto
	@ManyToOne
	@JoinColumn(name="DESTINO")
	private Aeropuerto aeropuerto1;

	//bi-directional many-to-one association to Aeropuerto
	@ManyToOne
	@JoinColumn(name="ORIGEN")
	private Aeropuerto aeropuerto2;

	//bi-directional many-to-one association to ModeloAvion
	@ManyToOne
	@JoinColumn(name="ID_MODELOAVION")
	private ModeloAvion modeloavion;

	public VueloProgramado() {
	}

	public String getNumeroVuelo() {
		return this.numeroVuelo;
	}

	public void setNumeroVuelo(String numeroVuelo) {
		this.numeroVuelo = numeroVuelo;
	}

	public String getEsRecurrente() {
		return this.esRecurrente;
	}

	public void setEsRecurrente(String esRecurrente) {
		this.esRecurrente = esRecurrente;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getHoraProgramada() {
		return this.horaProgramada;
	}

	public void setHoraProgramada(Date horaProgramada) {
		this.horaProgramada = horaProgramada;
	}	
	
	public Date getTiempodevuelo() {
		return tiempodevuelo;
	}

	public void setTiempodevuelo(Date tiempodevuelo) {
		this.tiempodevuelo = tiempodevuelo;
	}
	

	public Date getHoraDeLlegada() {
		return horaDeLlegada;
	}

	public void setHoraDeLlegada(Date horaDeLlegada) {
		this.horaDeLlegada = horaDeLlegada;
	}

	public List<RecurrenciaVueloProg> getRecurrenciaVueloProgs() {
		return this.recurrenciaVueloProgs;
	}

	public void setRecurrenciaVueloProgs(List<RecurrenciaVueloProg> recurrenciaVueloProgs) {
		this.recurrenciaVueloProgs = recurrenciaVueloProgs;
	}

	public RecurrenciaVueloProg addRecurrenciaVueloProg(RecurrenciaVueloProg recurrenciaVueloProg) {
		getRecurrenciaVueloProgs().add(recurrenciaVueloProg);
		recurrenciaVueloProg.setVueloProgramado(this);

		return recurrenciaVueloProg;
	}

	public RecurrenciaVueloProg removeRecurrenciaVueloProg(RecurrenciaVueloProg recurrenciaVueloProg) {
		getRecurrenciaVueloProgs().remove(recurrenciaVueloProg);
		recurrenciaVueloProg.setVueloProgramado(null);

		return recurrenciaVueloProg;
	}

	public List<Vuelo> getVuelos() {
		return this.vuelos;
	}

	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}

	public Vuelo addVuelo(Vuelo vuelo) {
		getVuelos().add(vuelo);
		vuelo.setVueloProgramado(this);

		return vuelo;
	}

	public Vuelo removeVuelo(Vuelo vuelo) {
		getVuelos().remove(vuelo);
		vuelo.setVueloProgramado(null);

		return vuelo;
	}

	public Aeropuerto getAeropuerto1() {
		return this.aeropuerto1;
	}

	public void setAeropuerto1(Aeropuerto aeropuerto1) {
		this.aeropuerto1 = aeropuerto1;
	}

	public Aeropuerto getAeropuerto2() {
		return this.aeropuerto2;
	}

	public void setAeropuerto2(Aeropuerto aeropuerto2) {
		this.aeropuerto2 = aeropuerto2;
	}

	public ModeloAvion getModeloavion() {
		return this.modeloavion;
	}

	public void setModeloavion(ModeloAvion modeloavion) {
		this.modeloavion = modeloavion;
	}

	

}