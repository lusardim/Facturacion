package ar.com.jumperinformatica.core.system.bean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ar.com.jumperinformatica.core.business.bean.BusinessABMBean;
import ar.com.jumperinformatica.core.enums.EstadoComitente;
import ar.com.jumperinformatica.core.enums.EstadoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;

public class ComitenteBean {
	
	private EntityManager entityManager;
	
	/**
	 * Agrega un comitente al sistema
	 * @param pComitente
	 * @throws LogicaException
	 */
	public void addComitente(Comitente pComitente) throws LogicaException{
		this.entityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			this.validarComitente(pComitente);
			BusinessABMBean.add(pComitente);
		}
		catch(LogicaException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new LogicaException("Ha ocurrido un error al intentar el comitente.");			
		}
		finally{
			this.entityManager.close();
		}
	}
	
	/**
	 * Actualiza los datos del comitente
	 * @param pComitente
	 * @throws LogicaException
	 */
	public void updateComitente(Comitente pComitente) throws LogicaException{
		this.entityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			this.validarComitente(pComitente);
			BusinessABMBean.update(pComitente);
		}
		catch(LogicaException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new LogicaException("Ha ocurrido un error al actualizar el comitente.");			
		}
		finally{
			this.entityManager.close();
		}
	}
	
	/**
	 * Elimina un comitente del sistema
	 * @param pComitente
	 * @throws LogicaException
	 */
	public void removeComitente(Comitente pComitente) throws LogicaException{
		this.entityManager = GestorPersistencia.getInstance().getEntityManager();
		EntityTransaction locTransaction  = null;
		try{
			locTransaction = this.entityManager.getTransaction();
			locTransaction.begin();
			this.removeComitenteSegunEstado(pComitente);
			locTransaction.commit();
		}
		catch(Exception e){
			e.printStackTrace();
			if ((locTransaction!=null)&&(locTransaction.isActive())){
				locTransaction.rollback();
			}
			throw new LogicaException("No se ha podido eliminar el comitente "+pComitente.toString());
		}
		
	}

	
	
	/**
	 * Verifica el estado del comitente ántes de realizar una eliminación lógica o física
	 * @param locComitente
	 */
	private void removeComitenteSegunEstado(Comitente pComitente) {
		Comitente locComitente =  this.entityManager.getReference(Comitente.class,pComitente.getIdComitente());
		if (!locComitente.getListaFacturas().isEmpty()){
			locComitente.setEstadoComitente(EstadoComitente.INACTIVO);
			this.entityManager.merge(locComitente);
		}
		else{
			this.entityManager.remove(locComitente);
		}
	}

	


	private void validarDatosComitente(Comitente pComitente) throws LogicaException {
		if (pComitente.getNombre()==null){
			throw new LogicaException("El nombre del comitente no puede estar vacío, verifique los datos ingresados");
		}
	}
	/**
	 * Valida el ingreso del comitente
	 * @param pComitente
	 * @throws LogicaException
	 */
	private void validarComitente(Comitente pComitente) throws LogicaException {
		this.validarDatosComitente(pComitente);
		if (pComitente.getCuit()!=null){
			Map<String, Object> locListaParametros = new HashMap<String,Object>();
			String locConsulta = "select c from Comitente c where c.cuit = :cuit";
			locListaParametros.put("cuit",pComitente.getCuit());
			
			if (pComitente.getIdComitente()!=null){
				locConsulta+=" and c.idComitente != :idComitente";
				locListaParametros.put("idComitente", pComitente.getIdComitente());
			}
			Query locQuery = this.entityManager.createQuery(locConsulta);

			for (String cadaClave : locListaParametros.keySet()){
				locQuery.setParameter(cadaClave, locListaParametros.get(cadaClave));
			}
			
			@SuppressWarnings("unchecked")
			List<Comitente> locResultado = locQuery.getResultList();
			if (!locResultado.isEmpty()){
				Comitente locComitente = locResultado.get(0);
				throw new LogicaException("Ya existe un comitente con el mismo cuit y con estado "+locComitente.getEstadoComitente()+" registrado.");
			}
		}
	}
	
	
	
	
	/**
	 * Realiza una búsqueda de los comitentes, trae por defecto solo los comitentes activos a menos que 
	 * se coloque como parámetro de búsqueda el estado del comitente
	 * @param pValor valor del parámetro por el cual consultar
	 * @param pParametro parametro por el cual consultar
	 * @return
	 * @throws LogicaException
	 */
	@SuppressWarnings("unchecked")
	public List<Comitente> findListaComitentes(Object pValor, ParametroBusqueda pParametro) throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			String locConsulta = "select c from Comitente c";

			Map<Integer, Object> locMapaParametros = new HashMap<Integer, Object>();
			
			if (pValor!=null){
				locConsulta+=" where c."+pParametro.getNombreAtributo();
				
				if (pParametro.getTipoAtributo().equals(String.class)){
					locConsulta+=" like ?";
					pValor = pValor.toString().trim()+"%";
				}
				else{
					locConsulta+=" = ?";
				}
				locMapaParametros.put(1, pValor);
				
				if (!pParametro.getNombreAtributo().equals("estadoComitente")){
					locConsulta+=" and c.estadoComitente = ?";
					locMapaParametros.put(2, EstadoComitente.ACTIVO);
				}
			}
			else{
				locConsulta+=" where c.estadoComitente = ?";
				locMapaParametros.put(1, EstadoComitente.ACTIVO);
			}
			
			
			Query locQuery = locEntityManager.createQuery(locConsulta);
			for (Integer cadaNombreParametro : locMapaParametros.keySet()){
				locQuery.setParameter(cadaNombreParametro, locMapaParametros.get(cadaNombreParametro));
			}
			
			return locQuery.getResultList();
		}
		finally{
			locEntityManager.close();
		}
	}
	
	
	/**
	 * Lista de parametros de búsqueda
	 * @return
	 */
	public static List<ParametroBusqueda> getListaParametrosBusqueda(){
		List<ParametroBusqueda> locListaParametros = new ArrayList<ParametroBusqueda>();
		
		ParametroBusqueda locParametroCuit = new ParametroBusqueda();
		locParametroCuit.setNombreAtributo("cuit");
		locParametroCuit.setNombre("CUIT");
		locParametroCuit.setTipoAtributo(String.class);
		
		locListaParametros.add(locParametroCuit);
		
		ParametroBusqueda locParametroNombre = new ParametroBusqueda();
		locParametroNombre.setNombre("Nombre del Comitente");
		locParametroNombre.setNombreAtributo("nombre");
		locParametroNombre.setTipoAtributo(String.class);
		
		locListaParametros.add(locParametroNombre);
		
		ParametroBusqueda locParametroDomicilio = new ParametroBusqueda();
		locParametroDomicilio .setNombre("Domicilio del Comitente");
		locParametroDomicilio .setNombreAtributo("domicilio");
		locParametroDomicilio .setTipoAtributo(String.class);
		
		locListaParametros.add(locParametroDomicilio );

		return locListaParametros;
	}

	
	
	@SuppressWarnings("unchecked")
	public List<Factura> getFacturacionComitente(Comitente pComitente, Date pFechaDesde, Date pFechaHasta) throws LogicaException{
		DateFormat locDateFormat = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
		System.out.println(pComitente+"   "+locDateFormat.format(pFechaDesde)+"  "+locDateFormat.format(pFechaHasta));
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try {
			Query locConsulta = locEntityManager.createQuery(
					"select f from Factura f where f.comitente = ? " +
					" and f.estadoFactura = ? and f.fecha between ? and ?");	
					
			locConsulta.setParameter(1, pComitente);
			locConsulta.setParameter(2, EstadoFactura.ACTIVA);
			locConsulta.setParameter(3, pFechaDesde);
			locConsulta.setParameter(4, pFechaHasta);
			
			return locConsulta.getResultList();
		}
		finally{
			locEntityManager.close();
		}
	}
}
