package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;

public class TotalesFocusListener extends FocusAdapter {

	private AMController controller;
	
	public TotalesFocusListener(AMController pController) {
		this.controller = pController;
	}

	@Override
	public void focusLost(FocusEvent e) {
		try{
			this.controller.actualizarModelo();
			this.controller.actualizarVista();
		}
		catch(LogicaException ex){
			ex.printStackTrace();
			JOptionPane.showMessageDialog(this.controller.getVista(), ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception ex){
			e.getComponent().requestFocus();
		}
	}

}
