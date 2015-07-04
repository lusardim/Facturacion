package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ar.com.jumperinformatica.gui.controller.FacturaController;

public class BuscarComitenteListener implements ActionListener {
	private FacturaController controller;

	public BuscarComitenteListener(FacturaController pController){
		this.controller = pController;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.controller.mostrarBuscarComitente();

	}
}
