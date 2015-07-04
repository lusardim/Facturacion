package ar.com.jumperinformatica.gui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.system.bean.ComitenteBean;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;

public class BusquedaComitenteModel extends AbstractBusquedaModel<Comitente>{

	private ComitenteBean comitenteBean;
	
	
	public BusquedaComitenteModel() throws LogicaException{
		this.comitenteBean = new ComitenteBean();
		List<ParametroBusqueda> locListaParametrosDeBusqueda = ComitenteBean.getListaParametrosBusqueda();
		this.setModeloCombo(new DefaultComboBoxModel(locListaParametrosDeBusqueda.toArray()));
		
		ComitenteTableModel locModeloTabla = new ComitenteTableModel();
		this.setModeloTabla(locModeloTabla);
		locModeloTabla.setListaComitentes(this.comitenteBean.findListaComitentes(null, null));
	}
	
	@Override
	protected void fireModeloActualizado() throws LogicaException {
		((ComitenteTableModel)this.getModeloTabla()).setListaComitentes(this.comitenteBean.
							findListaComitentes(this.getParametroBusqueda(), 
									(ParametroBusqueda)this.getModeloCombo().getSelectedItem()));
	}

	@Override
	public Comitente getSelected() {
		try{
			ComitenteTableModel locTableModelo = ((ComitenteTableModel)this.getModeloTabla());
			
			if (this.getSelectedIndex()!=-1){ 
				return locTableModelo.getListaComitentes().get(this.getSelectedIndex());
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
	public void setSelected(Comitente pSeleccionado) {
		this.setSelectedIndex(((ComitenteTableModel)this.getModeloTabla()).getListaComitentes().indexOf(pSeleccionado));
	}

	public ComitenteBean getComitenteBean() {
		return comitenteBean;
	}

}
