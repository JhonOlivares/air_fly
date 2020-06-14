package dao.Impl;

import javax.persistence.Query;

import util.Conexion;

public class LoginDAO {
	
	Conexion cn = new Conexion();
		
	public boolean validar(String user, String password)
	{
		cn.abrir_conexion();
		try
		{
			Query consulta=cn.em.createQuery("SELECT e FROM Empleado e where e.usuario = :p1 and e.password = :p2 ");
			consulta.setParameter("p1", user);
			consulta.setParameter("p2", password);
			if(consulta.getResultList().size()>0)
			{
				return true;
			}
		
		} catch (Exception ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			cn.cerrar_conexion();
		}
		return false;
	}
}
