package ar.com.jumperinformatica.gui.model;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.com.jumperinformatica.core.persistent.Usuario;


public class UsuarioTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5954812016090807847L;
	private List<Usuario> listaUsuarios;

	public UsuarioTableModel(){
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public int getRowCount() {
		return (this.listaUsuarios!=null)?this.listaUsuarios.size():0;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex){
			case 0: return this.listaUsuarios.get(rowIndex).getNombrePersona();
			case 1: return this.listaUsuarios.get(rowIndex).getNombre();
			default: return null;
		}
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
		this.fireTableDataChanged();
	}

	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0: return "Nombre";
			case 1: return "Nombre de usuario";
		}
		return super.getColumnName(column);
	}
}
