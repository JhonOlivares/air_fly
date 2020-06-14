package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Conexion
{
	public EntityManager em;
	public EntityManagerFactory emf;
	
	public void abrir_conexion()
	{
		emf=Persistence.createEntityManagerFactory("AerolineaPeru");
		em=emf.createEntityManager();
	}
	public void cerrar_conexion()
	{
		em.close();
		emf.close();
	}
}
