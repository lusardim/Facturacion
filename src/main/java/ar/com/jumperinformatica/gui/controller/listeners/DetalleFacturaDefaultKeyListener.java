package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;

import ar.com.jumperinformatica.gui.model.DetalleFacturaTableModel;

public class DetalleFacturaDefaultKeyListener extends KeyAdapter{

	private JTable tblDetalleFactura; 
	
	public DetalleFacturaDefaultKeyListener(JTable pTblDetalleFactura){
		this.tblDetalleFactura = pTblDetalleFactura;
	}
	
	public void keyPressed(KeyEvent e) {
		if (!tblDetalleFactura.isEditing()){
			if(e.getKeyCode() == KeyEvent.VK_DELETE){
				int locFilaSeleccionada = tblDetalleFactura.getSelectedRow();
				if (locFilaSeleccionada < tblDetalleFactura.getModel().getRowCount()-1){
					((DetalleFacturaTableModel)tblDetalleFactura.getModel()).removeRow(locFilaSeleccionada);
				}
			}
			//FIXME ASÍ ANDA, PERO NO LOGRO QUE BORRE LA CELDA AL INCIAR LA EDICIÓN
			/*else if (e.getKeyChar()!=KeyEvent.VK_UNDEFINED){
				
				JTextField locTextField = (JTextField)tblDetalleFactura.getEditorComponent();
				locTextField.setText("");
				System.out.println(locTextField.getText());
			}
			else{
				System.out.println("changos");
			}
			 */
		}
		
	}
	
}
