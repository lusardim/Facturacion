package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.gui.model.AbstractBusquedaModel;

public class RefrescarModeloWindowListener extends WindowAdapter{
	
	private AbstractBusquedaModel<?> modelo;
	
	public RefrescarModeloWindowListener(AbstractBusquedaModel<?> pModelo){
		this.modelo = pModelo;
	}
	
	@Override
	public void windowClosed(WindowEvent arg0) {
		try{
			modelo.refrescar();	
		}
		catch(LogicaException e){
			e.printStackTrace();
		}
		
	}
}
