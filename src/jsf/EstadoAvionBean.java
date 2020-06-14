package jsf;

import util.Conexion;
import model.EstadoAvion;


public class EstadoAvionBean
{
	Conexion cn=new Conexion();
	private EstadoAvion oEstadoAvion;
	
	public EstadoAvionBean()
	{
		oEstadoAvion = new EstadoAvion();
	}
	
	public void registrarEstadoAvion()
	{
		cn.abrir_conexion();
		EstadoAvion _estadoAvion = new EstadoAvion();
			
		try 
		{
			_estadoAvion.setEstado(oEstadoAvion.getEstado());
			cn.em.getTransaction().begin();
			cn.em.persist(_estadoAvion);
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

	public EstadoAvion getoEstadoAvion() {
		return oEstadoAvion;
	}

	public void setoEstadoAvion(EstadoAvion oEstadoAvion) {
		this.oEstadoAvion = oEstadoAvion;
	}

}
