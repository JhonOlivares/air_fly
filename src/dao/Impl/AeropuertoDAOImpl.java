package dao.Impl;

import util.Conexion;
import model.*;
import dao.Local.AeropuertoDAOLocal;

public class AeropuertoDAOImpl implements  AeropuertoDAOLocal{
	
	Conexion cn=new Conexion();
	@Override
	
	public void registrarAeropuerto(Aeropuerto _aeropuerto) throws Exception
	{
		cn.abrir_conexion();
		Aeropuerto oAeropuerto = new Aeropuerto();
		Ciudad oCiudad = new Ciudad();
			
		try 
		{
			oAeropuerto.setIataAeropuertoID(_aeropuerto.getIataAeropuertoID());
			oAeropuerto.setNombreaeropuerto(_aeropuerto.getNombreaeropuerto());
			oCiudad.setCiudadID(_aeropuerto.getCiudad().getCiudadID());
			oAeropuerto.setCiudad(oCiudad);
			cn.em.getTransaction().begin();
			cn.em.persist(oAeropuerto);
			cn.em.getTransaction().commit();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			cn.cerrar_conexion();
		}
	}

}
