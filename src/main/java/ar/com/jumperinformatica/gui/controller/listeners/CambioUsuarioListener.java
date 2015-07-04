package ar.com.jumperinformatica.gui.controller.listeners;

import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.system.eventos.UsuarioLogeadoListener;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class CambioUsuarioListener implements UsuarioLogeadoListener{
	
	@Override
	public void usuarioLogeado(Usuario usuarioAnterior, Usuario usuarioNuevo) {
		if (usuarioNuevo==null){
			System.exit(0);
		}
		else{
			Facturacion.getInstance().getPrincipalView().setVisible(true);
		}
	}
}
