package ar.com.jumperinformatica.gui.controller;

import ar.com.jumperinformatica.gui.controller.interfaces.AdminView;
import ar.com.jumperinformatica.gui.model.AbstractBusquedaModel;

public abstract class AbstractAdminController {
	
	
	public void refrescarVista(){
		if (this.getBusquedaModel().getSelected()==null){
			this.getVista().getBotonModificar().setEnabled(false);
			this.getVista().getBotonEliminar().setEnabled(false);
		}
		else{
			this.getVista().getBotonModificar().setEnabled(true);
			this.getVista().getBotonEliminar().setEnabled(true);
		}
	}


	public abstract AbstractBusquedaModel<?> getBusquedaModel(); 

	public abstract AdminView getVista();
	
	/**
	 * muestra la ventana
	 */
	public void mostrar(){
		this.getVista().setVisible(true);
	}
}
