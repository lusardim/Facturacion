package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import ar.com.jumperinformatica.gui.controller.interfaces.AMController;

public class DefaultKeyListener extends KeyAdapter{
	
	
	private AMController controller;
	public DefaultKeyListener(AMController pController) {
		this.controller = pController;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			this.controller.aceptar();
		}
		else if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			this.controller.cancelar();
		}
	}
}
