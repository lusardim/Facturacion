package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelarListener implements ActionListener {
	
	private Window vista;
	
	public CancelarListener(Window pVista){
		this.vista = pVista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.vista.dispose();
	}

}
