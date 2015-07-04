package ar.com.jumperinformatica.gui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.FacturaEgreso;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;

public class BusquedaFacturaEgresoModel extends AbstractBusquedaModel<FacturaEgreso> {

	private FacturacionBean facturacionBean;
	private FacturaTableModel modeloTabla;
	
	public BusquedaFacturaEgresoModel() throws LogicaException {
		this.facturacionBean = new FacturacionBean();
		List<ParametroBusqueda> locListaParametrosBusqueda = FacturacionBean.getListaParametrosBusqueda();
		this.setModeloCombo(new DefaultComboBoxModel(locListaParametrosBusqueda.toArray()));
		this.modeloTabla = new FacturaTableModel();
		this.setModeloTabla(this.modeloTabla);
		this.modeloTabla.setListaFacturas(this.facturacionBean.findListaFacturasEgreso(null, null));
	}

	@Override
	public FacturaEgreso getSelected() {
		try{
			FacturaTableModel locTableModelo = ((FacturaTableModel)this.getModeloTabla());
			
			if (this.getSelectedIndex()!=-1){ 
				return (FacturaEgreso)locTableModelo.getListaFacturas().get(this.getSelectedIndex());
			}
			else{
				return null;
			}
		}
		catch(RuntimeException e){
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public void setSelected(FacturaEgreso seleccionado) {
		this.setSelectedIndex(this.modeloTabla.getListaFacturas().indexOf(seleccionado));
	}

	@Override
	public void fireModeloActualizado() throws LogicaException {
		this.modeloTabla.setListaFacturas(this.facturacionBean
					.findListaFacturasEgreso(this.getParametroBusqueda(), 
								(ParametroBusqueda)this.getModeloCombo().getSelectedItem()));
	}
}
