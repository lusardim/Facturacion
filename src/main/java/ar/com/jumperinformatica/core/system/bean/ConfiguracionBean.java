package ar.com.jumperinformatica.core.system.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.core.persistent.ParametroConfiguracion;

/**
 * Esta clase administra un conjunto de parámetros obtenidos desde la base de datos.
 * @author mal
 */
public class ConfiguracionBean {
	private EntityManager entityManager;
	
	
	/**
	 * Lee un parámetro de configuración
	 * @param pNombreParametro
	 * @return
	 * @throws LogicaException
	 */
	public ParametroConfiguracion getParametro(String pNombreParametro) throws LogicaException{
		this.entityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			if (pNombreParametro==null){
				throw new LogicaException("Debe ingresar un nombre de parámetro");
			}
			return this.entityManager.find(ParametroConfiguracion.class, pNombreParametro);
		}
		finally{
			this.entityManager.close();
		}
	}
	
	/**
	 * Agrega un parámetro de confirugación
	 * @param pParametroConfiguracion
	 * @throws LogicaException
	 */
	public void setParametro(ParametroConfiguracion pParametroConfiguracion) throws LogicaException{
		this.entityManager = GestorPersistencia.getInstance().getEntityManager();
		EntityTransaction locTransaccion =null;
		try{
			locTransaccion = this.entityManager.getTransaction();
			locTransaccion.begin();

			this.entityManager.merge(pParametroConfiguracion);
			
			locTransaccion.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			if ((locTransaccion!=null)&&(locTransaccion.isActive())){
				locTransaccion.rollback();
			}
			throw new LogicaException("No se puede agregar el parámetro de configuración");
		}
		finally{
			this.entityManager.close();
		}
	}
}
