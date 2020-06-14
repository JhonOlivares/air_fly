package jsf;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.Query;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import model.*;
import util.Conexion;

public class AvionBean {
	private Avion oAvion = new Avion();
	private Avion selectedAircraft = new Avion();
	private EstadoAvion oEstadoAvion = new EstadoAvion();
	private List<EstadoAvion> estadosDeAvion;
	private ModeloAvion oModeloAvion = new ModeloAvion();
	private List<ModeloAvion> oModelos;
	private List<Avion> oAviones;
	private List<Avion> filteredAircrafts;
	private boolean showAircraftDetail = false;
	private String aircraftModelImage = "defaultAircraft";
	Conexion cn=new Conexion();
	
	public AvionBean() {
		//oAvion = new Avion();
		//oEstadoAvion = new EstadoAvion();
		//oModeloAvion = new ModeloAvion();
		listaModelos();
		listaEstados();
		listaAviones();		
	}
	
	public void registrarAvion()
	{
		cn.abrir_conexion();
		Avion _avion = new Avion();
		EstadoAvion _estadoAvion = new EstadoAvion();
		ModeloAvion _modeloAvion = new ModeloAvion();
		
		try
		{
			_avion.setAvionMatricula(oAvion.getAvionMatricula());
			_avion.setSerie(oAvion.getSerie());
			_estadoAvion = findEstadoAvion(oEstadoAvion.getEstadoID(),estadosDeAvion);
			_avion.setEstadoAvion(_estadoAvion);
			_modeloAvion = findModeloAvion(oModeloAvion.getModeloAvionID(),oModelos);
			_avion.setModeloavion(_modeloAvion);
			cn.em.getTransaction().begin();
			cn.em.persist(_avion);
			cn.em.getTransaction().commit();
			oAviones.add(_avion);
			FacesMessage msg = new FacesMessage("Nuevo avion agregado", oAvion.getAvionMatricula());
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "algo no está bien");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally
		{
			cn.cerrar_conexion();
		}
	}
	
	public ModeloAvion findModeloAvion(Long id, List<ModeloAvion> modelos)
	{			 
		for (ModeloAvion modelo : modelos) {
		if (modelo.getModeloAvionID() == id)
		{
			return modelo;
		}
		}
		 return null;
	}
	
	public EstadoAvion findEstadoAvion(Long id, List<EstadoAvion> estados)
	{			 
		for (EstadoAvion estado : estados) {
		if (estado.getEstadoID() == id)
		{
			return estado;
		}
		}
		 return null;
	}
	
	public Avion findAvion(String id, List<Avion> aviones)
	{			 
		for (Avion avion : aviones) {
		if (avion.getAvionMatricula().equals(id))
		{
			return avion;
		}
		}
		 return null;
	}
	
	public void handleClose(CloseEvent event)
	{
		resetValues();
    }
	
	public void resetValues()
	{
		oAvion.setAvionMatricula(null);
		oEstadoAvion.setEstadoID(-1);
		oModeloAvion.setModeloAvionID(-1);
		oAvion.setSerie(null);
	}
	
	@SuppressWarnings("unchecked")
	public void listaAviones()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT a FROM Avion a order by upper(a.avionMatricula) asc");
			if(q1.getResultList().size()>0)
			{
				oAviones = q1.getResultList();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.cerrar_conexion();
			if(showAircraftDetail)
			{
				if(findAvion(selectedAircraft.getAvionMatricula(),oAviones) == null)
				{
					setAircraftModelImage("defaultAircraft");
					setShowAircraftDetail(false);
				}				
			}
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public void listaEstados()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT e FROM EstadoAvion e");
			if(q1.getResultList().size()>0)
			{
				estadosDeAvion = new ArrayList<EstadoAvion>();
				estadosDeAvion = q1.getResultList();
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
	public void listaModelos()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("SELECT m FROM ModeloAvion m order by upper(m.modelo) asc");
			if(q1.getResultList().size()>0)
			{
				oModelos = new ArrayList<ModeloAvion>();
				oModelos = q1.getResultList();
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
	
	public void editarAvion()
	{
		cn.abrir_conexion();
		Avion _avion = new Avion();
		EstadoAvion _estadoAvion = new EstadoAvion();
		ModeloAvion _modeloAvion = new ModeloAvion();
		try 
		{
			_avion=cn.em.find(Avion.class,selectedAircraft.getAvionMatricula());
			
			// do not update primary key
			cn.em.getTransaction().begin();
			_avion.setSerie(selectedAircraft.getSerie());
			_estadoAvion.setEstadoID(oEstadoAvion.getEstadoID());
			_avion.setEstadoAvion(_estadoAvion);
			_modeloAvion.setModeloAvionID(oModeloAvion.getModeloAvionID());
			_avion.setModeloavion(_modeloAvion);
			cn.em.merge(_avion);
			cn.em.getTransaction().commit();
			
			oAviones.set(findAircraftIndex(selectedAircraft.getAvionMatricula(),oAviones), _avion);
			aircraftModelImage = _avion.getModeloavion().getModelo();
			selectedAircraft = _avion;
			
			FacesMessage msg = new FacesMessage("Correcto!!","Se modificó el avion: ");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo actualizar el registro");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally {
			cn.cerrar_conexion();
		}
	}
	
	public int findAircraftIndex(String id, List<Avion> aircrafts)
	{			 
		for(Avion avion : aircrafts)
		{
			if (avion.getAvionMatricula().equals(id))
			{
				return aircrafts.indexOf(avion);
			}
		 }
		 return -1;
	}
	
	public void eliminarAvion()
	{
		cn.abrir_conexion();
		Avion dAvion = new Avion();
		try 
		{
			dAvion=cn.em.find(Avion.class,selectedAircraft.getAvionMatricula().toString());
			cn.em.getTransaction().begin();
			cn.em.remove(dAvion);
			cn.em.getTransaction().commit();
			oAviones.remove(selectedAircraft);
			FacesMessage msg = new FacesMessage("Eliminado!!","El avion: " + selectedAircraft.getAvionMatricula() + " se eliminó correctamente");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	        selectedAircraft = null;
	        setAircraftModelImage("defaultAircraft");
	        setShowAircraftDetail(false);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo eliminar el registro");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally {
			cn.cerrar_conexion();
		}
	}
		
	
	public void onRowSelect(SelectEvent event) {
        selectedAircraft = (Avion)event.getObject();
        oModeloAvion.setModeloAvionID(selectedAircraft.getModeloavion().getModeloAvionID());
        oEstadoAvion.setEstadoID(selectedAircraft.getEstadoAvion().getEstadoID());
        setAircraftModelImage(selectedAircraft.getModeloavion().getModelo());
        setShowAircraftDetail(true);
    }
 
    public void onRowUnselect(UnselectEvent event) {
    	selectedAircraft = null;
        oModeloAvion.setModeloAvionID(-1);
        oEstadoAvion.setEstadoID(-1);
        setAircraftModelImage("defaultAircraft");
        setShowAircraftDetail(false);
    }
	

	public Avion getoAvion() {
		return oAvion;
	}

	public void setoAvion(Avion oAvion) {
		this.oAvion = oAvion;
	}

	public EstadoAvion getoEstadoAvion() {
		return oEstadoAvion;
	}

	public void setoEstadoAvion(EstadoAvion oEstadoAvion) {
		this.oEstadoAvion = oEstadoAvion;
	}

	public ModeloAvion getoModeloAvion() {
		return oModeloAvion;
	}

	public void setoModeloAvion(ModeloAvion oModeloAvion) {
		this.oModeloAvion = oModeloAvion;
	}

	public List<Avion> getoAviones() {
		return oAviones;
	}

	public void setoAviones(List<Avion> oAviones) {
		this.oAviones = oAviones;
	}

	public List<Avion> getFilteredAircrafts() {
		return filteredAircrafts;
	}

	public void setFilteredAircrafts(List<Avion> filteredAircrafts) {
		this.filteredAircrafts = filteredAircrafts;
	}
	
	public Avion getSelectedAircraft() {
		return selectedAircraft;
	}

	public void setSelectedAircraft(Avion selectedAircraft) {
		this.selectedAircraft = selectedAircraft;
	}

	public List<EstadoAvion> getEstadosDeAvion() {
		return estadosDeAvion;
	}

	public void setEstadosDeAvion(List<EstadoAvion> estadosDeAvion) {
		this.estadosDeAvion = estadosDeAvion;
	}

	public List<ModeloAvion> getoModelos() {
		return oModelos;
	}

	public void setoModelos(List<ModeloAvion> oModelos) {
		this.oModelos = oModelos;
	}

	public boolean isShowAircraftDetail() {
		return showAircraftDetail;
	}

	public void setShowAircraftDetail(boolean showAircraftDetail) {
		this.showAircraftDetail = showAircraftDetail;
	}

	public String getAircraftModelImage() {
		return aircraftModelImage;
	}

	public void setAircraftModelImage(String aircraftModelImage) {
		this.aircraftModelImage = aircraftModelImage;
	}
	
}
