package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the EMPLEADO database table.
 * 
 */
@Entity
@NamedQuery(name="Empleado.findAll", query="SELECT e FROM Empleado e")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="IDEMPLEADO")
	private long empleadoID;

	private String correo;

	private String password;

	private String usuario;

	//bi-directional many-to-one association to Persona
	@ManyToOne
	@JoinColumn(name="IDPERSONA")
	private Persona persona;

	//bi-directional many-to-one association to TipoEmpleado
	@ManyToOne
	@JoinColumn(name="IDTIPOEMPLEADO")
	private TipoEmpleado tipoempleado;

	public Empleado() {
	}

	public long getEmpleadoID() {
		return this.empleadoID;
	}

	public void setEmpleadoID(long empleadoID) {
		this.empleadoID = empleadoID;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoEmpleado getTipoempleado() {
		return this.tipoempleado;
	}

	public void setTipoempleado(TipoEmpleado tipoempleado) {
		this.tipoempleado = tipoempleado;
	}

}