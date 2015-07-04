package ar.com.jumperinformatica.gui.model;

import javax.swing.ComboBoxModel;
import javax.swing.table.TableModel;

public interface BusquedaModel<T> {
	
	public TableModel getModeloTabla();
	public Object getParametroBusqueda();
	public ComboBoxModel getModeloCombo();
	public T getSelected();
	public void setSelected(T pSeleccionado);
	
}
