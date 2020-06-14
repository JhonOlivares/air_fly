package jsf;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import util.Conexion;


import model.Aeropuerto;
import model.Avion;
import model.EstadoVuelo;
import model.ModeloAvion;
import model.Vuelo;
import model.VueloProgramado;

public class VueloProgramadoBean{
	
	private Aeropuerto aOrigen = new Aeropuerto();
	private Aeropuerto aDestino = new Aeropuerto();
	private List<Aeropuerto> oAeropuertos;
	
	private ModeloAvion oModeloAvion = new ModeloAvion();
	private List<ModeloAvion> oModeloDeAviones;
	
	private VueloProgramado oVueloProgramado = new VueloProgramado();
	private List<VueloProgramado> oVuelosProgramados;
	
	private VueloProgramado selectedVueloProgramado;
	private Date currentDate = new Date();
	
	private Vuelo vuelo = new Vuelo();
	private List<Vuelo> vuelos;
	
	private Avion avion = new Avion();
	private List<Avion> aviones;
	
	
	private String borra;
	
	Conexion cn = new Conexion();
	
	public VueloProgramadoBean() {
		todosLosVuelos();
		listaVuelosProgramados();
		listaAeropuertos();
		listaModeloDeAviones();		
	}
	
	
	public void registrarHorario()
	{
		cn.abrir_conexion();
		Aeropuerto _origen = new Aeropuerto();
		Aeropuerto _destino = new Aeropuerto();
		VueloProgramado _vueloProgramado = new VueloProgramado();
		ModeloAvion _modeloAvion = new ModeloAvion();
		
		try
		{
			_vueloProgramado.setNumeroVuelo(oVueloProgramado.getNumeroVuelo());
			
			_origen.setIataAeropuertoID(aOrigen.getIataAeropuertoID());
			_vueloProgramado.setAeropuerto1(_origen);
			_destino.setIataAeropuertoID(aDestino.getIataAeropuertoID());
			_vueloProgramado.setAeropuerto2(_destino);
			
			_modeloAvion.setModeloAvionID(oModeloAvion.getModeloAvionID());
			_vueloProgramado.setModeloavion(_modeloAvion);
			
			_vueloProgramado.setHoraProgramada(oVueloProgramado.getHoraProgramada());
			_vueloProgramado.setTiempodevuelo(oVueloProgramado.getTiempodevuelo());
			_vueloProgramado.setEsRecurrente("n");
			_vueloProgramado.setFechaInicio(new Date());
			_vueloProgramado.setFechaCreacion(new Date());
			
			Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(oVueloProgramado.getHoraProgramada()); // sets calendar time/date
		    cal.add(Calendar.HOUR_OF_DAY, oVueloProgramado.getTiempodevuelo().getHours());
		    cal.add(Calendar.MINUTE,oVueloProgramado.getTiempodevuelo().getMinutes());
			_vueloProgramado.setHoraDeLlegada(cal.getTime());
			
			cn.em.getTransaction().begin();
			cn.em.persist(_vueloProgramado);
			cn.em.getTransaction().commit();
			FacesMessage msg = new FacesMessage("Nuevo horario agregado", "El vuelo " + oVueloProgramado.getNumeroVuelo() + " se agregó correctamente");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "algo salió mal :(");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally
		{
			cn.cerrar_conexion();
			listaVuelosProgramados();
			resetValues();
		}
	}
	
	public void resetValues()
	{
		oVueloProgramado.setNumeroVuelo(null);
		aOrigen.setIataAeropuertoID(null);
		aDestino.setIataAeropuertoID(null);
		oModeloAvion.setModeloAvionID(-1);
		oVueloProgramado.setHoraProgramada(null);
		oVueloProgramado.setTiempodevuelo(null);
		avion.setAvionMatricula(null);
		vuelo.setFechavuelo(null);
	}
	
	
	@SuppressWarnings("unchecked")
	public void listaVuelosProgramados()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT v FROM VueloProgramado v order by func('TO_CHAR',v.horaProgramada,'HH24:MI')");
			//Query q1=cn.em.createQuery("SELECT v FROM VueloProgramado v where v.aeropuerto1.nombreaeropuerto = 'Ministro Pistarini International Airport'");
			if(q1.getResultList().size()>0)
			{
				oVuelosProgramados = q1.getResultList();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.cerrar_conexion();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void listaModeloDeAviones()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT m FROM ModeloAvion m order by upper(m.modelo) asc");
			if(q1.getResultList().size()>0)
			{
				oModeloDeAviones = new ArrayList<ModeloAvion>();
				oModeloDeAviones = q1.getResultList();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.cerrar_conexion();
		}
	}
	
	public void listaAeropuertos()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("select a from Aeropuerto a order by a.nombreaeropuerto");
			if(q1.getResultList().size()>0)
			{
				oAeropuertos = new ArrayList<Aeropuerto>();
				oAeropuertos = q1.getResultList();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.cerrar_conexion();
		}
	}
	
	public void editarHorario()
	{
		cn.abrir_conexion();
		VueloProgramado _horario = new VueloProgramado();
		try 
		{
			_horario=cn.em.find(VueloProgramado.class,selectedVueloProgramado.getNumeroVuelo().toString());
			
			// do not update primary key
			cn.em.getTransaction().begin();
			_horario.setHoraProgramada(selectedVueloProgramado.getHoraProgramada());
			_horario.setTiempodevuelo(selectedVueloProgramado.getTiempodevuelo());
			
			Calendar cal = Calendar.getInstance(); // creates calendar
		    cal.setTime(selectedVueloProgramado.getHoraProgramada()); // sets calendar time/date
		    cal.add(Calendar.HOUR_OF_DAY, selectedVueloProgramado.getTiempodevuelo().getHours());
		    cal.add(Calendar.MINUTE,selectedVueloProgramado.getTiempodevuelo().getMinutes());
		    _horario.setHoraDeLlegada(cal.getTime());
			
			cn.em.merge(_horario);
			cn.em.getTransaction().commit();
			
			FacesMessage msg = new FacesMessage("Correcto!!","Se modificó el horario: ");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo actualizar el registro");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally {
			cn.cerrar_conexion();
		}
	}
	
	public void eliminarHorario()
	{
		cn.abrir_conexion();
		VueloProgramado dVueloProgramado = new VueloProgramado();
		try 
		{
			dVueloProgramado=cn.em.find(VueloProgramado.class,selectedVueloProgramado.getNumeroVuelo().toString());
			cn.em.getTransaction().begin();
			cn.em.remove(dVueloProgramado);
			cn.em.getTransaction().commit();
			oVuelosProgramados.remove(selectedVueloProgramado);
			
			FacesMessage msg = new FacesMessage("Eliminado!!","El vuelo: " + selectedVueloProgramado.getNumeroVuelo() + ". se eliminó correctamente" );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No es posible eliminar el registro");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally {
			cn.cerrar_conexion();
		}
	}
	
	
	
	public void availableAircrafts()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT a FROM Avion a where a.estadoAvion.estadoID='1' and a.modeloavion.modeloAvionID = :p1 order by upper(a.avionMatricula) asc");
			q1.setParameter("p1", selectedVueloProgramado.getModeloavion().getModeloAvionID());
			if(q1.getResultList().size()>0)
			{
				aviones = q1.getResultList();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.cerrar_conexion();
		}
	}
	
	public void registrarVuelo()
	{
		cn.abrir_conexion();
		Avion _avion = new Avion();
		Vuelo _vuelo = new Vuelo();
		EstadoVuelo _estado = new EstadoVuelo();
		
		try
		{
			_estado.setEstadoVueloID(1);
			_vuelo.setEstadoVuelo(_estado);
			
			_vuelo.setVueloProgramado(selectedVueloProgramado);
			
			_avion.setAvionMatricula(avion.getAvionMatricula());
			_vuelo.setAvion(_avion);
			
			_vuelo.setFechavuelo(vuelo.getFechavuelo());
			
			cn.em.getTransaction().begin();
			cn.em.persist(_vuelo);
			cn.em.getTransaction().commit();
			FacesMessage msg = new FacesMessage("Nueva Fecha agregado", "Para el vuelo " + selectedVueloProgramado.getNumeroVuelo() + ".");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "algo salió mal :(");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally
		{
			cn.cerrar_conexion();
			todosLosVuelos();
			resetValues();
		}
	}
	
	public void todosLosVuelos()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT v FROM Vuelo v");
			if(q1.getResultList().size()>0)
			{
				vuelos = q1.getResultList();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.cerrar_conexion();
		}
	}


	public Aeropuerto getaOrigen() {
		return aOrigen;
	}


	public void setaOrigen(Aeropuerto aOrigen) {
		this.aOrigen = aOrigen;
	}


	public Aeropuerto getaDestino() {
		return aDestino;
	}


	public void setaDestino(Aeropuerto aDestino) {
		this.aDestino = aDestino;
	}


	public List<Aeropuerto> getoAeropuertos() {
		return oAeropuertos;
	}


	public void setoAeropuertos(List<Aeropuerto> oAeropuertos) {
		this.oAeropuertos = oAeropuertos;
	}
	
	public ModeloAvion getoModeloAvion() {
		return oModeloAvion;
	}


	public void setoModeloAvion(ModeloAvion oModeloAvion) {
		this.oModeloAvion = oModeloAvion;
	}


	public List<ModeloAvion> getoModeloDeAviones() {
		return oModeloDeAviones;
	}


	public void setoModeloDeAviones(List<ModeloAvion> oModeloDeAviones) {
		this.oModeloDeAviones = oModeloDeAviones;
	}


	public VueloProgramado getoVueloProgramado() {
		return oVueloProgramado;
	}


	public void setoVueloProgramado(VueloProgramado oVueloProgramado) {
		this.oVueloProgramado = oVueloProgramado;
	}


	public List<VueloProgramado> getoVuelosProgramados() {
		return oVuelosProgramados;
	}


	public void setoVuelosProgramados(List<VueloProgramado> oVuelosProgramados) {
		this.oVuelosProgramados = oVuelosProgramados;
	}


	


	public VueloProgramado getSelectedVueloProgramado() {
		return selectedVueloProgramado;
	}


	public void setSelectedVueloProgramado(VueloProgramado selectedVueloProgramado) {
		this.selectedVueloProgramado = selectedVueloProgramado;
	}


	public String getBorra() {
		return borra;
	}


	public void setBorra(String borra) {
		this.borra = borra;
	}


	public Date getCurrentDate() {
		return currentDate;
	}


	public Vuelo getVuelo() {
		return vuelo;
	}


	public void setVuelo(Vuelo vuelo) {
		this.vuelo = vuelo;
	}


	public List<Vuelo> getVuelos() {
		return vuelos;
	}


	public void setVuelos(List<Vuelo> vuelos) {
		this.vuelos = vuelos;
	}


	public Avion getAvion() {
		return avion;
	}


	public void setAvion(Avion avion) {
		this.avion = avion;
	}


	public List<Avion> getAviones() {
		return aviones;
	}


	public void setAviones(List<Avion> aviones) {
		this.aviones = aviones;
	}
	
	
}
