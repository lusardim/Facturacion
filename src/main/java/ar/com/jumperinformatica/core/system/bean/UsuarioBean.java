package ar.com.jumperinformatica.core.system.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import ar.com.jumperinformatica.core.business.bean.BusinessABMBean;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;

public class UsuarioBean {
	private EntityManager entityManager;
	/**
	 * Agrega un usuario al sistema
	 * @param pUsuario
	 * @throws LogicaException
	 */
	public void addUsuario(Usuario pUsuario) throws LogicaException{
		this.validarUsuario(pUsuario);
		try{
			BusinessABMBean.add(pUsuario);
		}
		catch(LogicaException e){
			throw e;
		}
		catch(Exception e){
			throw new LogicaException("No se ha podido agregar el usuario. Verifique los datos ingresados");
		}
	}
	

	/**
	 * Valida que no exista un usuario agregado ya con el mismo nombre 
	 * @param pUsuario
	 * @throws LogicaException
	 */
	private void validarUnicidadUsuario(Usuario pUsuario) throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			
			String locConsulta = "select u from Usuario u where u.nombre like ?";
			Map<Integer, Object> locListaParametros = new HashMap<Integer,Object>();
			Query locQuery;
			
			locListaParametros.put(1,pUsuario.getNombre());
			if (pUsuario.getIdUsuario()!=null){
				locConsulta+=" and u.idUsuario != ?";
				locListaParametros.put(2, pUsuario.getIdUsuario());
			}
			
			locQuery = locEntityManager.createQuery(locConsulta);
			for (Integer cadaIndiceParametro: locListaParametros.keySet()){
				locQuery.setParameter(cadaIndiceParametro, locListaParametros.get(cadaIndiceParametro));
			}
			
			if (!locQuery.getResultList().isEmpty()){
				throw new LogicaException("El nombre de usuario ya existe.");
			}
		}
		finally{
			locEntityManager.close();
		}
	}


	/**
	 * Actualiza los datos de los usuarios
	 * @param pUsuario
	 * @throws LogicaException
	 */
	public void updateUsuario(Usuario pUsuario) throws LogicaException{
		this.validarUsuario(pUsuario);
		try{
			BusinessABMBean.update(pUsuario);
		}
		catch(LogicaException e){
			throw e;
		}
		catch(Exception e){
			throw new LogicaException("No se ha podido agregar el usuario. Verifique los datos ingresados");
		}
	}
	
	
	/**
	 * Elimina un usuario del sistema
	 * @param pUsuario
	 * @throws LogicaException
	 */
	public void removeUsuario(Usuario pUsuario) throws LogicaException{
		this.entityManager = GestorPersistencia.getInstance().getEntityManager();
		EntityTransaction locTransaction = null;
		try{
			locTransaction = this.entityManager.getTransaction();
			locTransaction.begin();
			Usuario locUsuario = (Usuario)this.entityManager.getReference(Usuario.class, pUsuario.getIdUsuario());
			this.validarEliminarUsuario(pUsuario);
			this.entityManager.remove(locUsuario);
			locTransaction.commit();
		}
		catch(LogicaException e){
			if ((locTransaction!=null)&&(locTransaction.isActive())){
				locTransaction.rollback();
			}
			throw e;
		}
		catch(Exception e){
			if ((locTransaction!=null)&&(locTransaction.isActive())){
				locTransaction.rollback();
			}
			e.printStackTrace();
			throw new LogicaException("No se ha podido eliminar el usuario. Verifique los datos ingresados");
		}
		finally{
			this.entityManager.close();
		}
	}

	/**
	 * Valida si el usuario está habilitado para ser eliminado
	 * @param usuario
	 * @throws LogicaException
	 */
	private void validarEliminarUsuario(Usuario usuario) throws LogicaException {
		Long locCantidad = (Long)this.entityManager.createQuery("select count(e) from Usuario e").getSingleResult();
		if (locCantidad<=1){
			throw new LogicaException("Debe haber al menos un usuario registrado en el sistema. No puede eliminar todos los usuarios.");
		}
	}


	
	/**
	 * Valida los datos de un usuario
	 * @param pUsuario
	 * @throws LogicaException
	 */
	private void validarUsuario(Usuario pUsuario) throws LogicaException{
		if ((pUsuario.getNombre()==null) || ("".equals(pUsuario.getNombre()))){
			throw new LogicaException("El nombre de usuario no puede estar vacío.");
		}
		if (pUsuario.getClave()==null){
			pUsuario.setClave("");
		}
		validarUnicidadUsuario(pUsuario);
	}
	
	
	/**
	 * Busca la lista de usuarios
	 * @param pValor
	 * @param pParametro
	 * @return
	 * @throws LogicaException
	 */
	@SuppressWarnings("unchecked")
	public List<Usuario> findListaUsuarios(Object pValor, ParametroBusqueda pParametro) throws LogicaException{
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			String locConsulta = "select u from Usuario u ";
			Map<Integer, Object> locMapaParametros = new HashMap<Integer, Object>();
			
			if (pValor!=null){
				locConsulta+=" where u."+pParametro.getNombreAtributo();
				
				if (pParametro.getTipoAtributo().equals(String.class)){
					locConsulta+=" like ?";
					pValor = pValor.toString().trim()+"%";
				}
				else{
					locConsulta+=" = ?";
				}
				locMapaParametros.put(1, pValor);
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
	 * Obtiene el listado de posibles parámetros de búsqueda para usuario
	 * @return
	 */
	public static List<ParametroBusqueda> getListaParametrosBusqueda(){
		List<ParametroBusqueda> locListaParametros = new ArrayList<ParametroBusqueda>();
		
		ParametroBusqueda locParametroNombreUsuario = new ParametroBusqueda();
		locParametroNombreUsuario.setNombreAtributo("nombre");
		locParametroNombreUsuario.setNombre("Nombre de usuario");
		locParametroNombreUsuario.setTipoAtributo(String.class);
		locListaParametros.add(locParametroNombreUsuario);
		
		
		ParametroBusqueda locParametroNombrePersona  = new ParametroBusqueda();
		locParametroNombrePersona.setNombreAtributo("nombrePersona");
		locParametroNombrePersona.setNombre("Nombre de persona");
		locParametroNombrePersona.setTipoAtributo(String.class);
		locListaParametros.add(locParametroNombrePersona);
		
		return locListaParametros;
	}
	
	
}
