package ar.com.jumperinformatica.gui.controller;

import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.FacturaEgreso;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;
import ar.com.jumperinformatica.gui.controller.listeners.AceptarListener;
import ar.com.jumperinformatica.gui.controller.listeners.BuscarComitenteListener;
import ar.com.jumperinformatica.gui.controller.listeners.CambioTablaDetalleListener;
import ar.com.jumperinformatica.gui.controller.listeners.CancelarListener;
import ar.com.jumperinformatica.gui.controller.listeners.DetalleFacturaDefaultKeyListener;
import ar.com.jumperinformatica.gui.view.FacturaView;

public abstract class FacturaController  implements AMController{
	private FacturacionBean facturacionBean;
	
	public void mostrarBuscarComitente(){
		try{
			this.actualizarModelo();
			AdminComitenteController locAdminComitenteController = new AdminComitenteController(this.getVista());
			locAdminComitenteController.mostarSeleccionComitente();
			
			Comitente locComitente = locAdminComitenteController.getBusquedaModel().getSelected();
			
			if (locComitente!=null){
				this.getModel().setComitente(locComitente);
			}
			else{
				if (this.getModel().getComitente()!=null){
					this.getModel().setComitente(this.refrescarComitente());
				}
			}
			
			this.actualizarVista();
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getVista(), e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	protected Comitente refrescarComitente() throws LogicaException {
		return this.facturacionBean.refrescarComitente(this.getModel().getComitente());
	}
	
	public FacturacionBean getFacturacionBean() {
		return facturacionBean;
	}

	public void setFacturacionBean(FacturacionBean facturacionBean) {
		this.facturacionBean = facturacionBean;
	}
	
	@Override
	public void cancelar() {
		this.getVista().dispose();
	}
	@Override
	public void aceptar() {
		try{
			if (JOptionPane.showConfirmDialog(this.getVista(), "¿Está seguro que desea grabar la factura?","Guardar Factura",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				this.actualizarModelo();
				this.addFactura(this.getModel());
				this.getVista().dispose();
				if (this.getModel() instanceof FacturaEgreso){
					this.imprimirFactura();
				}
			}
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getVista(), e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Ejecuta el método del facturacionBean
	 * @param pFactura
	 * @throws LogicaException
	 */
	protected void addFactura(Factura pFactura) throws LogicaException{
		this.facturacionBean.addFactura(pFactura);
	}
	
	protected void inicializarEventos(){
		this.getVista().getTblDetalleFactura().addKeyListener(new DetalleFacturaDefaultKeyListener(this.getVista().getTblDetalleFactura()));
		this.getVista().getBtnCancelar().addActionListener(new CancelarListener(this.getVista()));
		this.getVista().getBtnAceptar().addActionListener(new AceptarListener(this));
		this.getVista().getBtnBuscarComitente().addActionListener(new BuscarComitenteListener(this));
		this.getVista().getTblDetalleFactura().getModel().addTableModelListener(new CambioTablaDetalleListener(this.getModel(),this));
	}
	
	public abstract Factura getModel();
	public abstract FacturaView getVista();
	
	private void imprimirFactura() throws LogicaException{
		AdminFacturaEgresoController.imprimirFactura(this.getModel());
	}
}
