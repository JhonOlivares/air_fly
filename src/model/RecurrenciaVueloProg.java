package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the RECURRENCIA_VUELO_PROG database table.
 * 
 */
@Entity
@Table(name="RECURRENCIA_VUELO_PROG")
@NamedQuery(name="RecurrenciaVueloProg.findAll", query="SELECT r FROM RecurrenciaVueloProg r")
public class RecurrenciaVueloProg implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private RecurrenciaVueloProgPK id;

	@Column(name="CONTADOR_RECURRENCIA")
	private BigDecimal contadorRecurrencia;

	@Column(name="DIA_DE_SEMANA")
	private BigDecimal diaDeSemana;

	@Column(name="DIA_DEL_MES")
	private BigDecimal diaDelMes;

	@Column(name="MAX_NUM_OCURRENCIAS")
	private BigDecimal maxNumOcurrencias;

	@Column(name="MONTH_OF_YEAR")
	private BigDecimal monthOfYear;

	@Column(name="SEMANA_DEL_MES")
	private BigDecimal semanaDelMes;

	//bi-directional many-to-one association to TipoRecurrenciaVueloProg
	@ManyToOne
	@JoinColumn(name="ID_TIPO")
	private TipoRecurrenciaVueloProg tipoRecurrVueloProg;

	//bi-directional many-to-one association to VueloProgramado
	@ManyToOne
	@JoinColumn(name="NUMERO_VUELO")
	private VueloProgramado vueloProgramado;

	public RecurrenciaVueloProg() {
	}

	public RecurrenciaVueloProgPK getId() {
		return this.id;
	}

	public void setId(RecurrenciaVueloProgPK id) {
		this.id = id;
	}

	public BigDecimal getContadorRecurrencia() {
		return this.contadorRecurrencia;
	}

	public void setContadorRecurrencia(BigDecimal contadorRecurrencia) {
		this.contadorRecurrencia = contadorRecurrencia;
	}

	public BigDecimal getDiaDeSemana() {
		return this.diaDeSemana;
	}

	public void setDiaDeSemana(BigDecimal diaDeSemana) {
		this.diaDeSemana = diaDeSemana;
	}

	public BigDecimal getDiaDelMes() {
		return this.diaDelMes;
	}

	public void setDiaDelMes(BigDecimal diaDelMes) {
		this.diaDelMes = diaDelMes;
	}

	public BigDecimal getMaxNumOcurrencias() {
		return this.maxNumOcurrencias;
	}

	public void setMaxNumOcurrencias(BigDecimal maxNumOcurrencias) {
		this.maxNumOcurrencias = maxNumOcurrencias;
	}

	public BigDecimal getMonthOfYear() {
		return this.monthOfYear;
	}

	public void setMonthOfYear(BigDecimal monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public BigDecimal getSemanaDelMes() {
		return this.semanaDelMes;
	}

	public void setSemanaDelMes(BigDecimal semanaDelMes) {
		this.semanaDelMes = semanaDelMes;
	}

	public TipoRecurrenciaVueloProg getTipoRecurrVueloProg() {
		return this.tipoRecurrVueloProg;
	}

	public void setTipoRecurrVueloProg(TipoRecurrenciaVueloProg tipoRecurrVueloProg) {
		this.tipoRecurrVueloProg = tipoRecurrVueloProg;
	}

	public VueloProgramado getVueloProgramado() {
		return this.vueloProgramado;
	}

	public void setVueloProgramado(VueloProgramado vueloProgramado) {
		this.vueloProgramado = vueloProgramado;
	}

}