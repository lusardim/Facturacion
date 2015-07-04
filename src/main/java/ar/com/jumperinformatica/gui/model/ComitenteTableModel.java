package ar.com.jumperinformatica.gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.com.jumperinformatica.core.persistent.Comitente;

public class ComitenteTableModel extends AbstractTableModel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6951584239171411357L;
	private List<Comitente> listaComitentes;
	
	public List<Comitente> getListaComitentes() {
		return listaComitentes;
	}

	public void setListaComitentes(List<Comitente> listaComitentes) {
		this.listaComitentes = listaComitentes;
		this.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0: return "CUIT";
			case 1: return "Nombre";
			case 2: return "Domicilio";
			default: return null;
		}
	}
	@Override
	public int getColumnCount() {
		return 3;
	}

	@Override
	public int getRowCount() {
		return (this.listaComitentes==null)?0:this.listaComitentes.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Comitente locComitente = this.listaComitentes.get(rowIndex);
		switch(columnIndex){
			case 0: return locComitente.getCuit();
			case 1: return locComitente.getNombre();
			case 2: return locComitente.getDomicilio();
		}
		return null;
	}

}
