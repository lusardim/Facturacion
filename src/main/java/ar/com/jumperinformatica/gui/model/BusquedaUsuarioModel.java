package ar.com.jumperinformatica.gui.model;

import java.util.List;

import javax.swing.DefaultComboBoxModel;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.system.bean.UsuarioBean;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;


public class BusquedaUsuarioModel extends AbstractBusquedaModel<Usuario> {
	
	private UsuarioBean usuarioBean;
	private UsuarioTableModel modeloTabla;
	
	public BusquedaUsuarioModel() throws LogicaException{
		this.usuarioBean = new UsuarioBean();
		List<ParametroBusqueda> locListaParametrosDeBusqueda = UsuarioBean.getListaParametrosBusqueda();
		System.out.println(locListaParametrosDeBusqueda);
		this.setModeloCombo(new DefaultComboBoxModel(locListaParametrosDeBusqueda.toArray()));
		this.modeloTabla = new UsuarioTableModel(); 
		this.setModeloTabla(this.modeloTabla);
		this.modeloTabla.setListaUsuarios(this.usuarioBean.findListaUsuarios(null, null));
	}
	
	
	@Override
	public void setSelected(Usuario seleccionado) {
		this.setSelectedIndex(this.modeloTabla.getListaUsuarios().indexOf(seleccionado));
	}


	@Override
	public Usuario getSelected() {
		try{
			UsuarioTableModel locTableModelo = ((UsuarioTableModel)this.getModeloTabla());
			
			if (this.getSelectedIndex()!=-1){ 
				return locTableModelo.getListaUsuarios().get(this.getSelectedIndex());
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
	public void fireModeloActualizado() throws LogicaException {
		this.modeloTabla.setListaUsuarios(this.usuarioBean
					.findListaUsuarios(this.getParametroBusqueda(), 
								(ParametroBusqueda)this.getModeloCombo().getSelectedItem()));
		
	}
	
	
	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}
	
}



