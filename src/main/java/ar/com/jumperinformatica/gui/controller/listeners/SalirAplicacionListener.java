package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ar.com.jumperinformatica.gui.principal.Facturacion;

public class SalirAplicacionListener extends WindowAdapter {
	
	@Override
	public void windowClosing(WindowEvent e) {
		System.out.println("caca");
		Facturacion.getInstance().salir();
	}


}
