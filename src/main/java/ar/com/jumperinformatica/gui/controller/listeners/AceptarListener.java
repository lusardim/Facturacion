package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.jumperinformatica.gui.controller.interfaces.AMController;

public class AceptarListener implements ActionListener {
	private AMController controller;
	
	public AceptarListener(AMController pController){
		this.controller = pController;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		this.controller.aceptar();
	}

}
