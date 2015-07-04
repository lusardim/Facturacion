package ar.com.jumperinformatica.gui.controller.listeners;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.gui.controller.FacturaCController;

public class SeleccionComitenteFacturaCFocusListener implements FocusListener{
		
	private String valorAnterior;
		
		private FacturaCController controller;
		public SeleccionComitenteFacturaCFocusListener(FacturaCController pController){
			this.controller = pController;
		}
		@Override
		public void focusGained(FocusEvent arg0) {
			JTextField locTextField = (JTextField)arg0.getComponent();
			this.valorAnterior = locTextField.getText();
			locTextField.setSelectionStart(0);
			locTextField.setSelectionEnd(locTextField.getText().length());
		
		}

		@Override
		public void focusLost(FocusEvent arg0) {
			JTextField locTextField = (JTextField)arg0.getComponent();
			String locValorNuevo = locTextField.getText();
			try{
				if (!valorAnterior.equals(locValorNuevo)){
					if (this.controller.isComitenteSeleccionado()){
						if (JOptionPane.showConfirmDialog(this.controller.getVista(),
								"¿Está seguro que desea perder la selección del comitente", 
								"Confirmacion",
								JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
							this.controller.iniciarEdicionComitente(locTextField,locValorNuevo);
						}
						else{
							locTextField.setText(this.valorAnterior);
							this.controller.getVista().getTfNumeroFactura().requestFocus();
						}
					}
					else{
						this.controller.iniciarEdicionComitente(locTextField, locValorNuevo);
					}
				}
			}
			catch(LogicaException e){
				e.printStackTrace();
				locTextField.requestFocus();
			}
				
		}
}
