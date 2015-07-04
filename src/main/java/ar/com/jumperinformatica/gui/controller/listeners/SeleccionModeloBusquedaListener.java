package ar.com.jumperinformatica.gui.controller.listeners;

import javax.swing.DefaultListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.com.jumperinformatica.gui.model.AbstractBusquedaModel;

public class SeleccionModeloBusquedaListener implements ListSelectionListener {

	private AbstractBusquedaModel<?> modelo;
	
	public SeleccionModeloBusquedaListener(AbstractBusquedaModel<?> pModelo){
		this.modelo = pModelo;
	}
	@Override
	public void valueChanged(ListSelectionEvent e) {
		DefaultListSelectionModel locModeloSelection = (DefaultListSelectionModel)e.getSource();

		if ((!locModeloSelection.isSelectionEmpty())&&(e.getValueIsAdjusting())){
			int minIndex = locModeloSelection.getMinSelectionIndex();
			int maxIndex = locModeloSelection.getMaxSelectionIndex();
			for (int i = minIndex; i <= maxIndex; i++) {
				if (locModeloSelection.isSelectedIndex(i)) {
					modelo.setSelectedIndex(i);
				}
			}
		}
	}



}
