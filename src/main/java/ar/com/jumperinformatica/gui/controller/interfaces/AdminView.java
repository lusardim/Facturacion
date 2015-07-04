package ar.com.jumperinformatica.gui.controller.interfaces;

import javax.swing.JButton;

public interface AdminView {
	public JButton getBotonModificar();
	public JButton getBotonAgregar();
	public JButton getBotonEliminar();
	public void setVisible(boolean pVisible);
}
