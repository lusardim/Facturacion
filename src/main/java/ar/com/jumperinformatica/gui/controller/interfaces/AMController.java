package ar.com.jumperinformatica.gui.controller.interfaces;

import javax.swing.JDialog;

import ar.com.jumperinformatica.core.exceptions.LogicaException;

public interface AMController {
	public void actualizarModelo() throws LogicaException;
	public void actualizarVista();
	public void aceptar();
	public void cancelar();
	public JDialog getVista();
	
}
