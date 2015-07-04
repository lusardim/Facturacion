package ar.com.jumperinformatica.gui.model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import ar.com.jumperinformatica.core.persistent.DetalleFactura;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;

public class DetalleFacturaTableModel extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6902856856346000669L;
		
	private Factura factura;
	
	public DetalleFacturaTableModel(Factura pFactura){
		super();
		if (pFactura==null){
			throw new IllegalArgumentException("La factura no puede ser nula");
		}
		this.factura = pFactura;
		this.factura.setDetalleFactura(new ArrayList<DetalleFactura>());
	}
	
	@Override
	public int getColumnCount() {
		return 5;
	}

	@Override
	public int getRowCount() {
		return this.factura.getDetalleFactura().size()+1;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (rowIndex<this.factura.getDetalleFactura().size()){
			DetalleFactura locDetalleFactura = this.factura.getDetalleFactura().get(rowIndex);
			switch(columnIndex){
				case 0: return locDetalleFactura.getItem();
				case 1: return locDetalleFactura.getDescripcion();
				case 2: return locDetalleFactura.getCantidad();
				case 3: return locDetalleFactura.getPrecio();
				case 4: return locDetalleFactura.getTotal();
			}
			return null;
		}
		else{
			return null;	
		}
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch(columnIndex){
			case 0: return Integer.class;
			case 1: return String.class;
			case 2: return Integer.class;
			case 3: return Float.class;
			case 4: return Float.class;
		}
		return super.getColumnClass(columnIndex);
	}
	
	@Override
	public String getColumnName(int column) {
		switch(column){
			case 0: return "Item";
			case 1: return "Descripcion";
			case 2: return "Cantidad";
			case 3: return "Precio Unitario";
			case 4: return "Total";
		}
		return super.getColumnName(column);
	}
	
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return true;
	}
	
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		DetalleFactura locDetalleFactura = this.getDetalleFacturaEditada(rowIndex);
		/*
		 * Solo permito 2 dígitos decimales en los detalles de facturas, por eso utilizo
		 * El number format, cosa que no le tire error pero que de prepo lo modifique 
		 */
		switch(columnIndex){
			case 0: locDetalleFactura.setItem(Integer.valueOf(value.toString())); break;
			case 1: locDetalleFactura.setDescripcion(value.toString().trim()); break;
			case 2: locDetalleFactura.setCantidad(Integer.valueOf(value.toString())); break;
			case 3: {
				Float valor = FacturacionBean.redondearADosCifras(value.toString());
				locDetalleFactura.setPrecio(valor); 
				break;
			}
			case 4: {
				Float valor = FacturacionBean.redondearADosCifras(value.toString());
				locDetalleFactura.setTotal(valor); 
				break;
			}
		}

		if (!this.factura.getDetalleFactura().contains(locDetalleFactura)){
			this.factura.getDetalleFactura().add(locDetalleFactura);
			locDetalleFactura.setFactura(this.factura);
			fireTableRowsInserted(rowIndex, rowIndex);
		}
		else{
			fireTableRowsUpdated(rowIndex, rowIndex);
		}
	}


	/**
	 * Obtiene el detalle de factura que está siendo editada
	 * @param rowIndex
	 * @return
	 */
	private DetalleFactura getDetalleFacturaEditada(int rowIndex) {
		if (rowIndex < this.factura.getDetalleFactura().size()){
			return this.factura.getDetalleFactura().get(rowIndex);
		}
		else{
			return new DetalleFactura();
		}
	}
	
	public void removeRow(int fila){
		this.factura.getDetalleFactura().remove(fila);
		this.fireTableRowsDeleted(fila, fila);
	}
}

