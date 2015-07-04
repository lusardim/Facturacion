package ar.com.jumperinformatica.core.system.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.PropertyValueException;

import ar.com.jumperinformatica.core.business.bean.BusinessABMBean;
import ar.com.jumperinformatica.core.enums.EstadoComitente;
import ar.com.jumperinformatica.core.enums.EstadoFactura;
import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.FacturaEgreso;
import ar.com.jumperinformatica.core.persistent.FacturaIngreso;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.core.persistent.TipoIva;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;
import ar.com.jumperinformatica.core.transients.ReporteFacturacion;

public class FacturacionBean {
	private static final int NUMERO_FACTURA_CANTIDAD_PREFIJO = 4;
	private static final int NUMERO_FACTURA_CANTIDAD_SUFIJO = 8;
	/**
	 * Carga la factura al sistema
	 * @param pFactura
	 * @throws LogicaException
	 */
	public void addFactura(Factura pFactura) throws LogicaException{
		pFactura.setEstadoFactura(EstadoFactura.ACTIVA);
		this.validarFactura(pFactura);
		try{
			BusinessABMBean.add(pFactura);
		}
		catch(LogicaException e){
			throw e;
		}
		catch(Exception e){
			e.printStackTrace();
			throw new LogicaException("No se ha podido agregar la factura. Verifique los datos ingresados");
		}
	}

	/**
	 * 
	 * @param pFactura
	 * @throws LogicaException
	 */
	public void addFacturaC(Factura pFactura) throws LogicaException{
		pFactura.setEstadoFactura(EstadoFactura.ACTIVA);
		this.validarFactura(pFactura);
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		EntityTransaction locTransaction = null;
		try{
			locTransaction = locEntityManager.getTransaction();
			locTransaction.begin();
			if (pFactura.getComitente().getIdComitente()==null){
				pFactura.getComitente().setEstadoComitente(EstadoComitente.INACTIVO);
				locEntityManager.persist(pFactura.getComitente());
			}
			locEntityManager.merge(pFactura);
			locTransaction.commit();
		}
		catch(PropertyValueException e){
			if ((locTransaction!=null)&&(locTransaction.isActive())){
				locTransaction.rollback();
			}
			throw new LogicaException("El atributo "+e.getPropertyName()+" no puede ser nulo. Verifique los datos ingresados");
		}
		catch(Exception e){
			if ((locTransaction!=null)&&(locTransaction.isActive())){
				locTransaction.rollback();
			}
			e.printStackTrace();
			throw new LogicaException("No se ha podido agregar la factura. Verifique los datos ingresados");
		}
		finally{
			locEntityManager.close();
		}
	}

	
	/**
	 * Anula una factura creada
	 * @param pFactura
	 * @throws LogicaException
	 */
	public void anularFactura(Factura pFactura) throws LogicaException{
		pFactura.setEstadoFactura(EstadoFactura.ANULADA);
		try{
			BusinessABMBean.update(pFactura);
		}
		catch(LogicaException e){
			throw e;
		}
		catch(Exception e){
			throw new LogicaException("No se ha podido anular la factura.");
		}
	}
	
	/**
	 * Valida los datos de la factura
	 * @param pFactura
	 * @throws LogicaException
	 */
	private void validarFactura(Factura pFactura) throws LogicaException{
		if (pFactura.getNumeroFactura()==null){
			throw new LogicaException("El número de factura no puede estar vacío.");
		}
		else{
			pFactura.setNumeroFactura(this.validarNumeroFactura(pFactura.getNumeroFactura()));
		}
		if (pFactura.getDetalleFactura().isEmpty()){
			throw new LogicaException("La factura debe tener algún item.");
		}
		
		if (pFactura.getTipoFactura()!=TipoFactura.C && pFactura.getComitente()==null){
			throw new LogicaException("El comitente debe estar cargado");
		}
		
		if (pFactura.getTipoIva()==null){
			throw new LogicaException("Debe seleccionar un tipo de iva.");
		}
		
	}
	
	private String validarNumeroFactura(String pNumeroFactura) throws LogicaException{
		String[] locNumeroFactura = pNumeroFactura.split("-");
		if (locNumeroFactura[0].length()<NUMERO_FACTURA_CANTIDAD_PREFIJO){
			locNumeroFactura[0] = this.completarConCeros(NUMERO_FACTURA_CANTIDAD_PREFIJO,locNumeroFactura[0]);
		}
		if (locNumeroFactura[1].length()<NUMERO_FACTURA_CANTIDAD_SUFIJO){
			locNumeroFactura[1] = this.completarConCeros(NUMERO_FACTURA_CANTIDAD_SUFIJO, locNumeroFactura[1]);
		}
		return locNumeroFactura[0]+"-"+locNumeroFactura[1];
	}


	private String completarConCeros(int pCantidadNumeros, String locNumeroFactura) {
		int loCantidadCaractares = locNumeroFactura.length();
		int agregar = pCantidadNumeros - loCantidadCaractares;
		StringBuilder locCadenaRetorno = new StringBuilder(); 
		for (int i=0; i<agregar;i++){
			locCadenaRetorno.append("0");
		}
		locCadenaRetorno.append(locNumeroFactura.trim());
		return locCadenaRetorno.toString();
	}


	@SuppressWarnings("unchecked")
	public List<FacturaIngreso> findListaFacturasIngreso(Object pValor, ParametroBusqueda pParametro) throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			String locConsulta = "select f from FacturaIngreso f join f.comitente c";
			Map<Integer, Object> locMapaParametros = new HashMap<Integer, Object>();
			int locIndiceParametro = 1; 
			
			if (!((pParametro!=null)&&(pParametro.getNombreAtributo().equals("f.estadoFactura")))){
				locConsulta += " where f.estadoFactura = ? ";
				locMapaParametros.put(locIndiceParametro,EstadoFactura.ACTIVA);
				locIndiceParametro++;
			}
			
			if (pValor!=null){
				if (locIndiceParametro==1){
					locConsulta+=" where ";
				}
				else{
					locConsulta+=" and ";
				}
				
				if (pParametro.getTipoAtributo().equals(String.class)){
					locConsulta+=pParametro.getNombreAtributo();
					locConsulta+=" like ?";
					pValor = pValor.toString().trim()+"%";
				}
				else if (pParametro.getTipoAtributo().equals(Date.class)){
					locConsulta+=" date("+pParametro.getNombreAtributo()+") = ?";
				}
				else{
					locConsulta+=pParametro.getNombreAtributo();
					locConsulta+=" = ?";
				}
				locMapaParametros.put(locIndiceParametro, pValor);
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
	
	@SuppressWarnings("unchecked")
	public List<FacturaEgreso> findListaFacturasEgreso(Object pValor, ParametroBusqueda pParametro) throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			String locConsulta = "select f from FacturaEgreso f join f.comitente c";
			Map<Integer, Object> locMapaParametros = new HashMap<Integer, Object>();
			int locIndiceParametro = 1; 
			
			if (!((pParametro!=null)&&(pParametro.getNombreAtributo().equals("f.estadoFactura")))){
				locConsulta += " where f.estadoFactura = ? ";
				locMapaParametros.put(locIndiceParametro,EstadoFactura.ACTIVA);
				locIndiceParametro++;
			}
			
			if (pValor!=null){
				if (locIndiceParametro==1){
					locConsulta+=" where ";
				}
				else{
					locConsulta+=" and ";
				}
				
				if (pParametro.getTipoAtributo().equals(String.class)){
					locConsulta+=pParametro.getNombreAtributo();
					locConsulta+=" like ?";
					pValor = pValor.toString().trim()+"%";
				}
				else if (pParametro.getTipoAtributo().equals(Date.class)){
					locConsulta+=" date("+pParametro.getNombreAtributo()+") = ?";
				}
				else{
					locConsulta+=pParametro.getNombreAtributo();
					locConsulta+=" = ?";
				}
				locMapaParametros.put(locIndiceParametro, pValor);
			}
			System.out.println(locConsulta);
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
		
		ParametroBusqueda locNumeroFactura = new ParametroBusqueda();
		locNumeroFactura.setNombreAtributo("f.numeroFactura");
		locNumeroFactura.setNombre("Número de factura");
		locNumeroFactura.setTipoAtributo(String.class);
		
		locListaParametros.add(locNumeroFactura);
		
		ParametroBusqueda locParametroNombreComitente = new ParametroBusqueda();
		locParametroNombreComitente.setNombre("Nombre del Comitente");
		locParametroNombreComitente.setNombreAtributo("c.nombre");
		locParametroNombreComitente.setTipoAtributo(String.class);
		
		locListaParametros.add(locParametroNombreComitente);
		
		ParametroBusqueda locParametroFecha = new ParametroBusqueda();
		locParametroFecha .setNombre("Fecha de facturación");
		locParametroFecha .setNombreAtributo("f.fecha");
		locParametroFecha .setTipoAtributo(Date.class);
		
		locListaParametros.add(locParametroFecha );
		
		ParametroBusqueda locParametroTipoFactura = new ParametroBusqueda();
		locParametroTipoFactura.setNombre("Tipo de factura");
		locParametroTipoFactura.setNombreAtributo("f.tipoFactura");
		locParametroTipoFactura.setTipoAtributo(TipoFactura.class);
		locListaParametros.add(locParametroTipoFactura);
		
		ParametroBusqueda locParametroEstadoFactura = new ParametroBusqueda();
		locParametroEstadoFactura.setNombre("Estado de la Factura");
		locParametroEstadoFactura.setNombreAtributo("f.estadoFactura");
		locParametroEstadoFactura.setTipoAtributo(EstadoFactura.class);
		locListaParametros.add(locParametroEstadoFactura);
		
		return locListaParametros;
	}
	
	@SuppressWarnings("unchecked")
	public List<TipoIva> getListaTiposIva() throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try {
			return locEntityManager.createQuery("select e from TipoIva e").getResultList();
		}
		finally {
			locEntityManager.close();
		}
	}


	public Comitente refrescarComitente(Comitente comitente) throws LogicaException {
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try {
			return (Comitente)locEntityManager.find(Comitente.class,comitente.getIdComitente());
		}
		finally {
			locEntityManager.close();
		}
	}
	
	public String getSiguienteNumeroFacturaEgreso(TipoFactura pTipoFactura) throws LogicaException {
		return this.getSiguienteNumeroFactura("FacturaEgreso", pTipoFactura);
	}
	
	public String getSiguienteNumeroFacturaIngreso(TipoFactura pTipoFactura) throws LogicaException {
		return this.getSiguienteNumeroFactura("FacturaIngreso", pTipoFactura);
	}
	
	private String getSiguienteNumeroFactura(String pClase, TipoFactura pTipoFactura) throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try {
			Query locQuery = locEntityManager
									.createQuery("select max(e.numeroFactura) from "+pClase+" e" +
											" where e.tipoFactura = ?");
							
			locQuery.setParameter(1, pTipoFactura);
			String locNumeroFactura = (String)locQuery.getSingleResult();
			
			if (locNumeroFactura==null) {
				return "0000-00000001";
			}
			String[] listaValores = locNumeroFactura.split("-");
			try {
				Integer valorNuevo = Integer.parseInt(listaValores[1])+1;
				listaValores[1]=this.completarConCeros(NUMERO_FACTURA_CANTIDAD_SUFIJO, String.valueOf(valorNuevo));
			}
			catch(NumberFormatException e){
				listaValores[1] = "00000001";
			}
			locNumeroFactura = listaValores[0]+"-"+listaValores[1];
			return locNumeroFactura;
		}
		catch(NoResultException e){
			return "0000-00000001";
		}
		finally {
			locEntityManager.close();
		}
	}
	
	public List<ReporteFacturacion> findListaFacturasEgreso(Date fechaDesde, Date fechaHasta,
			List<TipoFactura> listaTiposFacturas) throws LogicaException {
		return findListaFacturas("FacturaEgreso",fechaDesde,fechaHasta, listaTiposFacturas);
	}

	public List<ReporteFacturacion> findListaFacturasIngreso(Date fechaDesde, Date fechaHasta,
			List<TipoFactura> listaTiposFacturas) throws LogicaException {
		return findListaFacturas("FacturaIngreso",fechaDesde,fechaHasta, listaTiposFacturas);
	}
	
	
	
	/**
	 * Convierte un valor de n cantidad de cifras en String a un valor
	 * de 2 cifras
	 * @param pParametro
	 * @return
	 */
	public static Float redondearADosCifras(String pParametro) {
		Float valor = Float.valueOf(pParametro);
		return redondearADosCifras(valor);
	}
	
	public static Float redondearADosCifras(Float pValor){
		Float valor = pValor;
		valor *=100;
		valor = (float)Math.round(valor);
		return valor/=100;
	}
	
	@SuppressWarnings("unchecked")
	public List<ReporteFacturacion> findListaFacturas(String pClase,Date fechaDesde, Date fechaHasta,
			List<TipoFactura> listaTiposFacturas) throws LogicaException {
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			boolean primero = true;
			Map<String,Object> locListaParametros = new HashMap<String,Object>();
			String locConsulta = " select NEW " +
					"ar.com.jumperinformatica.core.transients.ReporteFacturacion(" +
					"sum(f.subtotal), sum(f.totalIva), sum(f.totalFactura) ) from "+pClase+ " f ";
			
			if (fechaDesde!=null){
				locConsulta+=(primero)?" where ":" and ";
				locConsulta+=" f.fecha >= :fechaDesde";
				locListaParametros.put("fechaDesde", fechaDesde);
				primero = false;
			}
			
			if (fechaHasta!=null){
				locConsulta+=(primero)?" where ":" and ";
				locConsulta+=" f.fecha <= :fechaHasta";
				locListaParametros.put("fechaHasta", fechaHasta);
				primero = false;
			}
			
			if (fechaHasta!=null){
				int i = 0;
				boolean segundo = false;
				
				locConsulta+=(primero)?" where ":" and ";
				
				for (TipoFactura cadaTipoFactura : listaTiposFacturas){
					locConsulta+=(segundo)?" or ":"";
					locConsulta+=" f.tipoFactura = :tipoFactura"+i;
					locListaParametros.put("tipoFactura"+i, cadaTipoFactura);
					i++;
					segundo=true;
				}
				primero = false;
			}
			
			
			locConsulta+=(primero)?" where ":" and ";
			locConsulta+=" f.estadoFactura = :estado";
			locListaParametros.put("estado", EstadoFactura.ACTIVA);
			primero = false;
			
			
			Query locQuery = locEntityManager.createQuery(locConsulta);
			for (String cadaClave : locListaParametros.keySet()){
				locQuery.setParameter(cadaClave, locListaParametros.get(cadaClave));
			}
			return locQuery.getResultList();
			
		}
		finally{
			locEntityManager.close();
		}
	}
}
