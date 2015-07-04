package ar.com.jumperinformatica.core.system.eventos;

import java.util.EventListener;

import ar.com.jumperinformatica.core.persistent.Usuario;

/**
 * Evento disparado cuando el usuario se loggea o deslogea
 * @author MAL 
 *
 */
public interface UsuarioLogeadoListener extends EventListener{
	
	/**
	 * @param pUsuarioAnterior es el usuario que se encontraba anteriormente loggeado,
	 * en caso de ser nulo significa que no había ningun usuario loggeado
	 * @param pUsuarioNuevo es el nuevo usuario que se ha logeado, si es nulo
	 * significa que el usuario se ha deslogeado
	 */
	public void usuarioLogeado(Usuario pUsuarioAnterior, Usuario pUsuarioNuevo);
}
