package jsf;

import util.Conexion;
import model.ModeloAvion;
import model.Manufacturer;


public class ModeloAvionBean
{
	private ModeloAvion oModeloAvion = new ModeloAvion();
	private Manufacturer oManufacturer = new Manufacturer();
	Conexion cn=new Conexion();
	
	public void registrarModeloAvion()
	{
		cn.abrir_conexion();
		ModeloAvion _modeloAvion = new ModeloAvion();
		Manufacturer _manufacturer = new Manufacturer();
			
		try 
		{
			/*_manufacturer.setFabricanteID(oManufacturer.getFabricanteID());
			_modeloAvion.setManufacturer(_manufacturer);
			_modeloAvion.setModelo(oModeloAvion.getModelo());
			_modeloAvion.setDescripcion(oModeloAvion.getDescripcion());
			cn.em.getTransaction().begin();
			cn.em.persist(_modeloAvion);
			cn.em.getTransaction().commit();*/
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			cn.cerrar_conexion();
		}
	}

	public ModeloAvion getoModeloAvion() {
		return oModeloAvion;
	}

	public void setoModeloAvion(ModeloAvion oModeloAvion) {
		this.oModeloAvion = oModeloAvion;
	}

	public Manufacturer getoManufacturer() {
		return oManufacturer;
	}

	public void setoManufacturer(Manufacturer oManufacturer) {
		this.oManufacturer = oManufacturer;
	}

}
