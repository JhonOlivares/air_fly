package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TIPOEMPLEADO database table.
 * 
 */
@Entity
@NamedQuery(name="TipoEmpleado.findAll", query="SELECT t FROM TipoEmpleado t")
public class TipoEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDTIPOEMPLEADO")
	private long tipoEmpleadoID;

	private String tipoempleado;

	//bi-directional many-to-one association to Empleado
	@OneToMany(mappedBy="tipoempleado")
	private List<Empleado> empleados;

	public TipoEmpleado() {
	}

	public long getTipoEmpleadoID() {
		return this.tipoEmpleadoID;
	}

	public void setTipoEmpleadoID(long tipoEmpleadoID) {
		this.tipoEmpleadoID = tipoEmpleadoID;
	}

	public String getTipoempleado() {
		return this.tipoempleado;
	}

	public void setTipoempleado(String tipoempleado) {
		this.tipoempleado = tipoempleado;
	}

	public List<Empleado> getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}

	public Empleado addEmpleado(Empleado empleado) {
		getEmpleados().add(empleado);
		empleado.setTipoempleado(this);

		return empleado;
	}

	public Empleado removeEmpleado(Empleado empleado) {
		getEmpleados().remove(empleado);
		empleado.setTipoempleado(null);

		return empleado;
	}

}