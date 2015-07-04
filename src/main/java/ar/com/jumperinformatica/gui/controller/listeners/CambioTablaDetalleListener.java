package ar.com.jumperinformatica.gui.controller.listeners;

import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;

public class CambioTablaDetalleListener implements TableModelListener {

	private AMController controller;
	private Factura factura;
	
	public CambioTablaDetalleListener(Factura pFactura , AMController pController){
		this.controller = pController;
		this.factura = pFactura;
	}
	@Override
	public void tableChanged(TableModelEvent e) {
		try {
			this.controller.actualizarModelo();
			this.factura.calcularSubTotalDetalle();
			this.factura.calcularTotalIva();
			this.factura.calcularTotal();
			this.controller.actualizarVista();
		} catch (LogicaException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this.controller.getVista(), e1.getMessage(),
					"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
