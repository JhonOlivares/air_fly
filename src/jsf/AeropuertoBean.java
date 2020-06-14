package jsf;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.Query;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.primefaces.PrimeFaces;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;

import model.Aeropuerto;
import model.Ciudad;
import model.VueloProgramado;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import util.Conexion;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;



public class AeropuertoBean
{
	private Aeropuerto oAeropuerto = new Aeropuerto();
	private List<Aeropuerto> oAeropuertos;
	private List<Aeropuerto> filteredAirports;
	private Ciudad oCiudad = new Ciudad();
	private List<Ciudad> oCiudades;
	private boolean hayAeropuertos = false;
	private Aeropuerto selectedAirport;
	Conexion cn = new Conexion();
	
	public AeropuertoBean()
	{
		listaAeropuertos();
		listaCiudades();
	}
	
	public void registrarAeropuerto()
	{
		cn.abrir_conexion();
		Aeropuerto _aeropuerto = new Aeropuerto();
		Ciudad _ciudad = new Ciudad();
		
		try
		{
			_aeropuerto.setIataAeropuertoID(oAeropuerto.getIataAeropuertoID());
			//_ciudad.setCiudadID(oCiudad.getCiudadID());---- con esta linea es suficienta para grabar en base de datos
			_ciudad = findCity(oCiudad.getCiudadID(),oCiudades);// esta linea es necesaria para actualizar datatable
			_aeropuerto.setCiudad(_ciudad);
			_aeropuerto.setNombreaeropuerto(oAeropuerto.getNombreaeropuerto());
			cn.em.getTransaction().begin();
			cn.em.persist(_aeropuerto);
			cn.em.getTransaction().commit();
			oAeropuertos.add(_aeropuerto);
			//filteredAirports.add(_aeropuerto);
			FacesMessage msg = new FacesMessage("Nuevo aeropuerto agregado", oAeropuerto.getNombreaeropuerto());
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
			//listaAeropuertos();
		}		
	}
	
	public Ciudad findCity(Long id, List<Ciudad> ciudades)
	{			 
			    for (Ciudad ciudad : ciudades) {
			        if (ciudad.getCiudadID() == id)
			        {
			            return ciudad;
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
		oAeropuerto.setIataAeropuertoID(null);
		oCiudad.setCiudadID(-1);
		oAeropuerto.setNombreaeropuerto(null);
	}
	
	
		
	@SuppressWarnings("unchecked")
	public void listaAeropuertos()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("select a from Aeropuerto a order by upper(a.nombreaeropuerto) asc");
			if(q1.getResultList().size()>0)
			{
				oAeropuertos = q1.getResultList();
				//filteredAirports = q1.getResultList();
				setHayAeropuertos(true);
			}
			else
			{
				setHayAeropuertos(false);
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
	public void listaCiudades()
	{
		cn.abrir_conexion();
		try
		{
			Query q1=cn.em.createQuery("select a from Ciudad a");
			if(q1.getResultList().size()>0)
			{
				oCiudades = new ArrayList<Ciudad>();
				oCiudades = q1.getResultList();
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
	
	public void eliminarAeropuerto(Aeropuerto airport)
	{
		cn.abrir_conexion();
		Aeropuerto dAeropuerto = new Aeropuerto();
		try 
		{
			dAeropuerto=cn.em.find(Aeropuerto.class,airport.getIataAeropuertoID().toString());
			cn.em.getTransaction().begin();
			cn.em.remove(dAeropuerto);
			cn.em.getTransaction().commit();
			oAeropuertos.remove(airport);
			//filteredAirports.remove(airport);
			FacesMessage msg = new FacesMessage("Eliminado!!","El aeropuerto: " + airport.getNombreaeropuerto() + ". se eliminó correctamente" );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No es posible eliminar el registro");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally {
			cn.cerrar_conexion();
		}
	}
	
	public void mostrarRegistro(Aeropuerto airport)
	{
		oAeropuerto.setIataAeropuertoID(airport.getIataAeropuertoID());
		oCiudad.setCiudadID(airport.getCiudad().getCiudadID());
		oAeropuerto.setCiudad(airport.getCiudad());
		oAeropuerto.setNombreaeropuerto(airport.getNombreaeropuerto());
	}
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Airport Selected", ((Aeropuerto) event.getObject()).getIataAeropuertoID());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
	
	public void actualizarAeropuerto()
	{
		cn.abrir_conexion();
		Aeropuerto _aeropuerto = new Aeropuerto();
		Ciudad _ciudad = new Ciudad();
		try 
		{
			_aeropuerto=cn.em.find(Aeropuerto.class,oAeropuerto.getIataAeropuertoID().toString());
			
			// do not update primary key
			cn.em.getTransaction().begin();
			_ciudad.setCiudadID(oCiudad.getCiudadID());
			_aeropuerto.setCiudad(_ciudad);
			_aeropuerto.setNombreaeropuerto(oAeropuerto.getNombreaeropuerto());
			cn.em.merge(_aeropuerto);
			cn.em.getTransaction().commit();
			
			oAeropuertos.set(findAirportIndex(oAeropuerto.getIataAeropuertoID().toString(),oAeropuertos), _aeropuerto);
			//filteredAirports.set(findAirportIndex(oAeropuerto.getIataAeropuertoID().toString(),filteredAirports), _aeropuerto);
			
			FacesMessage msg = new FacesMessage("Correcto!!","Se modificó el aeropuerto: ");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		} catch (Exception e) {			
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "No se pudo actualizar el registro");
	        FacesContext.getCurrentInstance().addMessage(null, msg);
		}
		finally {
			cn.cerrar_conexion();
			//resetValues();
			//listaAeropuertos();
		}
	}
	
	public int findAirportIndex(String id, List<Aeropuerto> airports)
	{			 
		for(Aeropuerto airport : airports)
		{
			if (airport.getIataAeropuertoID().equals(id))
			{
				return airports.indexOf(airport);
			}
		 }
		 return -1;
	}
	
	public void exportarReportePDF(ActionEvent e) throws JRException, IOException
	{
		try
		{
			Map<String,Object> parametros= new HashMap<String,Object>();
			//parametros.put("texto", "Lista de Aeropuertos");
			
			File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("report10.jasper"));
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasper.getPath(), parametros,new JRBeanCollectionDataSource(this.getoAeropuertos()));
			
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			response.addHeader("Content-disposition","attachment; filename=Reporteew.pdf");
			ServletOutputStream stream = response.getOutputStream();
			
			JasperExportManager.exportReportToPdfStream(jasperPrint, stream);
			
			stream.flush();
			stream.close();
			FacesContext.getCurrentInstance().responseComplete();
		}
		catch (Exception eg)
		{
			throw new RuntimeException("error!", eg);
		} 
	}
	

	public Aeropuerto getoAeropuerto() {
		return oAeropuerto;
	}

	public void setoAeropuerto(Aeropuerto oAeropuerto) {
		this.oAeropuerto = oAeropuerto;
	}

	public Ciudad getoCiudad() {
		return oCiudad;
	}

	public void setoCiudad(Ciudad oCiudad) {
		this.oCiudad = oCiudad;
	}

	public List<Aeropuerto> getoAeropuertos() {
		return oAeropuertos;
	}

	public void setoAeropuertos(List<Aeropuerto> oAeropuertos) {
		this.oAeropuertos = oAeropuertos;
	}

	public List<Ciudad> getoCiudades() {
		return oCiudades;
	}

	public void setoCiudades(List<Ciudad> oCiudades) {
		this.oCiudades = oCiudades;
	}

	public List<Aeropuerto> getFilteredAirports() {
		return filteredAirports;
	}

	public void setFilteredAirports(List<Aeropuerto> filteredAirports) {
		this.filteredAirports = filteredAirports;
	}

	public boolean isHayAeropuertos() {
		return hayAeropuertos;
	}

	public void setHayAeropuertos(boolean hayAeropuertos) {
		this.hayAeropuertos = hayAeropuertos;
	}

	public Aeropuerto getSelectedAirport() {
		return selectedAirport;
	}

	public void setSelectedAirport(Aeropuerto selectedAirport) {
		this.selectedAirport = selectedAirport;
	}
	
	
}
