package ar.com.jumperinformatica.core.business.bean;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.PropertyValueException;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;

public class BusinessABMBean {
	
	private enum Accion{
		AGREGAR, ACTUALIZAR, ELIMINAR
	}
	
	public static void add(Object pObject) throws Exception{
		action(pObject, Accion.AGREGAR);
	}
	
	public static void remove(Object pObject) throws Exception{
		action(pObject, Accion.ELIMINAR);
	}
	
	public static void update(Object pObject) throws Exception {
		action(pObject, Accion.ACTUALIZAR);
	}
	
	
	private static void action(Object pObject, Accion pAction ) throws Exception{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		EntityTransaction locTransaction  = locEntityManager.getTransaction();
		try{
			locTransaction.begin();
			switch(pAction){
				case AGREGAR: locEntityManager.persist(pObject);  break;
				case ELIMINAR: {
					locEntityManager.refresh(pObject);
					locEntityManager.remove(pObject);  
					break;
				}
				case ACTUALIZAR: locEntityManager.merge(pObject); break;
			}
			locTransaction.commit();
		}
		catch(PropertyValueException e){
			locTransaction.rollback();
			throw new LogicaException("El atributo "+e.getPropertyName()+" no puede ser nulo. Verifique los datos ingresados");
		}
		catch(Exception e){
			e.printStackTrace();
			locTransaction.rollback();
			throw e;
		}
		finally{
			locEntityManager.close();
		}
	}
	
}
