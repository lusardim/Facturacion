package ar.com.jumperinformatica.core.persistent;

import ar.com.jumperinformatica.core.exceptions.LogicaException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;

public class GestorPersistencia {
	private EntityManagerFactory fabrica;
	
	private static GestorPersistencia instance;
	private boolean inicializado;
	
	public static GestorPersistencia getInstance(){
		if (instance==null){
			instance= new GestorPersistencia();
		}
		return instance;
	}
	
	/**
	 * Inicializa el gestor de persistencia
	 */
	public void inicializarGestorPersistencia(){
		try{
			this.fabrica = Persistence.createEntityManagerFactory("facturacion");
			this.inicializado=true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Valida la fábrica de EntityManagers ha sido inicializada
	 */
	private boolean isInicializado(){
		return this.inicializado;
	}
	
	/**
	 * Obtiene el nuevo éntity manager 
	 * @return
	 */
	public EntityManager getEntityManager() throws LogicaException{
		if (!this.isInicializado()){
			this.inicializarGestorPersistencia();
			if (!this.isInicializado()){
				throw new LogicaException("Ha ocurrido un error al iniciar el gestor de bases de datos. Verifique que el motor se encuentre funcionando.");
			}
		}
		return this.fabrica.createEntityManager();
	}
	
	
	/**
	 * cierra el gestor de persistencia y 
	 */
	public static void cerrarGestorPersistencia(){
		GestorPersistencia locGestor = getInstance();
		locGestor.fabrica.close();
		instance=null;
	}
	
	
	/*@SuppressWarnings("deprecation")
	public Connection getConnection(){
		if (this.fabrica.isOpen()){
			return ((EntityManagerImpl)this.fabrica.createEntityManager()).getSession().connection();
		}
		return null;
	}*/
}
