package ar.com.jumperinformatica.gui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.FacturaIngreso;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;

public class BusquedaFacturaIngresoModel extends AbstractBusquedaModel<FacturaIngreso> {

	private FacturacionBean facturacionBean;
	private FacturaTableModel modeloTabla;
	
	public BusquedaFacturaIngresoModel() throws LogicaException {
		this.facturacionBean = new FacturacionBean();
		List<ParametroBusqueda> locListaParametrosBusqueda = FacturacionBean.getListaParametrosBusqueda();
		this.setModeloCombo(new DefaultComboBoxModel(locListaParametrosBusqueda.toArray()));
		this.modeloTabla = new FacturaTableModel();
		this.setModeloTabla(this.modeloTabla);
		this.modeloTabla.setListaFacturas(this.facturacionBean.findListaFacturasIngreso(null, null));
	}

	@Override
	public FacturaIngreso getSelected() {
		try{
			FacturaTableModel locTableModelo = ((FacturaTableModel)this.getModeloTabla());
			
			if (this.getSelectedIndex()!=-1){ 
				return (FacturaIngreso)locTableModelo.getListaFacturas().get(this.getSelectedIndex());
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
	public void setSelected(FacturaIngreso seleccionado) {
		this.setSelectedIndex(this.modeloTabla.getListaFacturas().indexOf(seleccionado));
	}

	@Override
	public void fireModeloActualizado() throws LogicaException {
		this.modeloTabla.setListaFacturas(this.facturacionBean
					.findListaFacturasIngreso(this.getParametroBusqueda(), 
								(ParametroBusqueda)this.getModeloCombo().getSelectedItem()));
	}

}
