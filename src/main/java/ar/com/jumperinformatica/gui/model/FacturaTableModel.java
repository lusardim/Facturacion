package ar.com.jumperinformatica.gui.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ar.com.jumperinformatica.core.persistent.Factura;

public class FacturaTableModel extends AbstractTableModel {
	
	private static final long serialVersionUID = 8338159886050840617L;

	private List<? extends Factura> listaFacturas;

	
	public FacturaTableModel(){
		super();
	}
	
	
	@Override
	public int getColumnCount() {
		return 7;
	}

	@Override
	public int getRowCount() {
		return this.getListaFacturas().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Factura locFactura = this.listaFacturas.get(rowIndex);
		switch(columnIndex){
			case 0: return locFactura.getTipoFactura();
			case 1: return SimpleDateFormat
						.getDateInstance(DateFormat.SHORT)
						.format(locFactura.getFecha());
			case 2: return locFactura.getNumeroFactura();
			case 3: return locFactura.getComitente();
			case 4: return locFactura.getSubtotal();
			case 5: return locFactura.getTotalIva();
			case 6: return locFactura.getTotalFactura();
		}
		return null;
	}
	
	public List<? extends Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(List<? extends Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
		this.fireTableDataChanged();
	}

	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0: return "Tipo";
			case 1: return "Fecha";
			case 2: return "Nº Factura";
			case 3: return "Comitente";
			case 4: return "Subtotal";
			case 5: return "Total I.V.A.";
			case 6: return "TOTAL";
		}
		return super.getColumnName(column);
	}
	
	
	
	
}
