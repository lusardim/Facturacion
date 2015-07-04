package ar.com.jumperinformatica.gui.view;

import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

public abstract class FacturaView extends JDialog{
	
	public FacturaView(Dialog pPadre){
		super(pPadre);
	}
	private static final long serialVersionUID = -4094740079432408543L;
	public abstract JButton getBtnAceptar();
	public abstract JButton getBtnCancelar();
	public abstract JTable getTblDetalleFactura();
	public abstract JButton getBtnBuscarComitente();
	public abstract JDateChooser getDcFecha();
	public abstract JTextField getTfNumeroFactura();
	public abstract JTextField getTfCondicionVenta();
	public abstract JTextField getTfDomicilio();
	public abstract JTextField getTfNombre();
	public abstract JTextField getTfObra();
	public abstract JTextField getTfRemito();
	public abstract JTextField getTfTotal();
}
