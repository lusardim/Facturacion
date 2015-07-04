package ar.com.jumperinformatica.core.system.bean;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.event.EventListenerList;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.core.persistent.ParametroConfiguracion;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.system.eventos.UsuarioLogeadoListener;

public class LoginBean {
	
	private EventListenerList listaEventos=new EventListenerList();
	
	private Usuario usuarioLogeado;
	private ConfiguracionBean configuracionBean=new ConfiguracionBean();
	
	public Usuario getUsuario(){
		return this.usuarioLogeado;
	}
	
	private void setUsuario(Usuario pUsuario){
		this.fireUsuarioLoggeadoEvent(pUsuario);
		this.usuarioLogeado = pUsuario;
	}
	
	/**
	 * Dispara el evento de cambio de usuario
	 * @param pUsuario
	 */
	private void fireUsuarioLoggeadoEvent(Usuario pUsuario) {
		for (UsuarioLogeadoListener cadaUsuarioLoggeado : this.listaEventos.getListeners(UsuarioLogeadoListener.class)){
			cadaUsuarioLoggeado.usuarioLogeado(this.usuarioLogeado, pUsuario);
		}
		
	}

	/**
	 * Agrega un listener al usuario loggeado
	 * @param pUsuarioLogeadoListener
	 */
	public void addUsuarioLoggeadoListener(UsuarioLogeadoListener pUsuarioLogeadoListener){
		this.listaEventos.add(UsuarioLogeadoListener.class, pUsuarioLogeadoListener);
	}
	
	/**
	 * Elimina del registro de eventos al listener pasado por parámetro
	 * @param pUsuarioLogeadoListener
	 */
	public void removeUsuarioLoggeadoListener(UsuarioLogeadoListener pUsuarioLogeadoListener){
		this.listaEventos.remove(UsuarioLogeadoListener.class, pUsuarioLogeadoListener);
	}
	

	/**
	 * Ingresa al sistema 
	 * @param pNombreUsuario
	 * @param pClave
	 * @throws Exception 
	 */
	public void login(String pNombreUsuario, String pClave) throws LogicaException{
		this.validarConfiguracion();
		EntityManager locEntityManager = GestorPersistencia.getInstance().getEntityManager();
		try{
			Usuario locUsuario = (Usuario)locEntityManager.createNamedQuery("Usuario.login")
									.setParameter("nombreUsuario", pNombreUsuario)
									.setParameter("clave", pClave)
									.getSingleResult();
			this.setUsuario(locUsuario);
		}
		catch(NoResultException e){
			throw new LogicaException("El nombre de usuario no se encuentra registrado, Verifique su nombre de usuario y su clave.");
		}
		catch(Exception e){
			e.printStackTrace();
			throw new LogicaException("No se ha podido ingresar al sistema. Verifique que el motor de bases de datos se encuentre funcionando correctamente.");
		}
		finally{
			locEntityManager.close();
		}
	}
	
	
	private void validarConfiguracion() throws LogicaException {
		ParametroConfiguracion locParametro = this.configuracionBean.getParametro("CANTIDAD_EJECUCIONES");
		
		if (locParametro==null){
			this.crearConfiguracionInicial();
		}
		else{
			Integer locValor = Integer.parseInt(locParametro.getValor());
			locValor++;
			locParametro.setValor(locValor.toString());
			this.configuracionBean.setParametro(locParametro);

		}
		
	}

	private void crearConfiguracionInicial() throws LogicaException {
		this.inicializarParametrosConfiguracion();
		this.crearUsuarioPorDefecto();
	}

	private void crearUsuarioPorDefecto() {
		Usuario locUsuario = new Usuario();
		locUsuario.setNombre("emilio");
		locUsuario.setClave("emilio");
		locUsuario.setNombrePersona("Emilio Etienot");
	}

	private void inicializarParametrosConfiguracion() throws LogicaException {
		ParametroConfiguracion locParametroConfiguracion = new ParametroConfiguracion();
		locParametroConfiguracion.setNombre("REALIZAR_BACKUP");
		locParametroConfiguracion.setValor("cmd /C start \"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\" " +
					"\"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\"");
		configuracionBean.setParametro(locParametroConfiguracion);
		
		
		locParametroConfiguracion.setNombre("RESTAURAR_BACKUP");
		locParametroConfiguracion.setValor("cmd /C start \"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\" " +
				"\"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\restaurar.bat\" ");
		configuracionBean.setParametro(locParametroConfiguracion);
		
		
		locParametroConfiguracion.setNombre("CANTIDAD_EJECUCIONES");
		locParametroConfiguracion.setValor("1");
		configuracionBean.setParametro(locParametroConfiguracion);		
	}

	/**
	 * Sale del sistema
	 */
	public void logout(){
		this.setUsuario(null);
	}
	
}
