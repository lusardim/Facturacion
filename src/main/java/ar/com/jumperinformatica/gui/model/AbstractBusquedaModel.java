package ar.com.jumperinformatica.gui.model;

import javax.swing.ComboBoxModel;
import javax.swing.table.TableModel;

import ar.com.jumperinformatica.core.exceptions.LogicaException;

public abstract class AbstractBusquedaModel<T> implements BusquedaModel<T>{
	
	
	private TableModel modeloTabla;
	private ComboBoxModel modeloCombo;
	private Object parametroBusqueda;
	private int selectedIndex=-1;
		
	
	public int getSelectedIndex() {
		return selectedIndex;
	}
	public void setSelectedIndex(int selectedIndex) {
		this.selectedIndex = selectedIndex;
	}
	
	public ComboBoxModel getModeloCombo() {
		return modeloCombo;
	}
	public void setModeloCombo(ComboBoxModel modeloCombo) {
		this.modeloCombo = modeloCombo;
	}
	public AbstractBusquedaModel(){
		
	}
	public void setModeloTabla(TableModel modeloTabla) {
		this.modeloTabla = modeloTabla;
	}

	public void setParametroBusqueda(Object parametroBusqueda) throws LogicaException {
		this.parametroBusqueda = parametroBusqueda;
		this.fireModeloActualizado();
	}

	
	
	protected abstract void fireModeloActualizado() throws LogicaException;
	
	@Override
	public TableModel getModeloTabla() {
		return this.modeloTabla;
	}


	@Override
	public Object getParametroBusqueda() {
		return this.parametroBusqueda;
	}
	
	public void refrescar() throws LogicaException {
		this.setSelectedIndex(-1);
		this.fireModeloActualizado();
	}
}
