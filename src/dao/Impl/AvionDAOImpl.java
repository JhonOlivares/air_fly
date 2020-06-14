package dao.Impl;


import util.Conexion;
import model.*;
import dao.Local.AvionDAOLocal;
public class AvionDAOImpl implements AvionDAOLocal{
	Conexion cn=new Conexion();
	@Override
	
	public void registrarAvion(Avion _avion) throws Exception
	{
		cn.abrir_conexion();
		Avion oAvion = new Avion();
		EstadoAvion oEstadoAvion = new EstadoAvion();
		
			
		try 
		{		
			
			oAvion.setAvionMatricula(_avion.getAvionMatricula());
			oAvion.setModeloavion(_avion.getModeloavion());
			oAvion.setSerie(_avion.getSerie());
			//oAvion.setFabricante(_avion.getFabricante());
			//oEstadoAvion.setEstadoid(_avion.getEstadoAvion().getEstadoid());
			oAvion.setEstadoAvion(oEstadoAvion);			
			cn.em.getTransaction().begin();
			cn.em.persist(oAvion);
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
