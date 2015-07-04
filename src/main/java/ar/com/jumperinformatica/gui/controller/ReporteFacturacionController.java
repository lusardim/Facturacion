package ar.com.jumperinformatica.gui.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;
import ar.com.jumperinformatica.gui.controller.listeners.AceptarListener;
import ar.com.jumperinformatica.gui.controller.listeners.CancelarListener;
import ar.com.jumperinformatica.gui.enums.Periodo;
import ar.com.jumperinformatica.gui.model.ParametroTipoFactura;
import ar.com.jumperinformatica.gui.model.ReporteFacturacionModel;
import ar.com.jumperinformatica.gui.view.ReporteFacturacionView;

public abstract class ReporteFacturacionController implements AMController{

	private ReporteFacturacionView vista;
	private ReporteFacturacionModel modelo;
	private FacturacionBean facturacionBean;	
	
	public ReporteFacturacionController(JDialog pPadre, FacturacionBean pFacturacionBean) {
		this.vista = new ReporteFacturacionView(pPadre);
		this.init(pFacturacionBean);
		this.setParametrosVista();
	}
	
	protected abstract void setParametrosVista(); 

	private void init(FacturacionBean pFacturacionBean) {
		try{
			this.facturacionBean = pFacturacionBean;
			this.initModels();
			this.initListeners();
			this.getVista().setTitle(this.toString());
			this.actualizarModelo();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public ReporteFacturacionController(Frame pPadre, FacturacionBean pFacturacionBean) {
		this.vista = new ReporteFacturacionView(pPadre);
		this.init(pFacturacionBean);
	}
	
	public FacturacionBean getFacturacionBean() {
		return facturacionBean;
	}

	private void initListeners() {
		this.getVista().getBtnAceptar().addActionListener(new AceptarListener(this));
		this.getVista().getBtnCancelar().addActionListener(new CancelarListener(this.vista));
		this.getVista().getCboPeriodo().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarPeriodo();
			}
		});
		this.getVista().getDcDesde().addPropertyChangeListener("date",new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				actualizarFechaDesde();
			}
		});
	}

	private void actualizarFechaDesde(){
		this.modelo.setFechaDesde(this.getVista().getDcDesde().getDate());
		this.modelo.setFechaHasta(this.modelo.getPeriodo().getProximaFecha(this.modelo.getFechaDesde()));
		this.getVista().getDcHasta().setDate(this.modelo.getFechaHasta());
	}

	private void actualizarPeriodo(){
		this.modelo.setPeriodo((Periodo)this.getVista().getCboPeriodo().getSelectedItem());
		this.modelo.setFechaHasta(this.modelo.getPeriodo().getProximaFecha(this.modelo.getFechaDesde()));
		this.vista.getDcHasta().setDate(this.modelo.getFechaHasta());
	}

	protected void initModels() {
		this.modelo = new ReporteFacturacionModel();
		this.vista.getCboPeriodo().setModel(new DefaultComboBoxModel(Periodo.values()));
	}

	@Override
	public void actualizarModelo() throws LogicaException {
		this.modelo.setFechaDesde(this.getVista().getDcDesde().getDate());
		this.modelo.setPeriodo((Periodo)this.getVista().getCboPeriodo().getSelectedItem());
		this.modelo.setTiposFacturas((ParametroTipoFactura)this.getVista().getCboTipoFactura().getSelectedItem());
		this.modelo.setFechaHasta(this.getVista().getDcHasta().getDate());
	}

	@Override
	public void actualizarVista() {
		this.getVista().getCboPeriodo().setSelectedItem(this.modelo.getPeriodo());
		this.getVista().getCboTipoFactura().setSelectedItem(this.modelo.getTiposFacturas());
		this.getVista().getDcDesde().setDate(this.modelo.getFechaDesde());
		this.getVista().getDcHasta().setDate(this.modelo.getFechaHasta());
	}

	
	protected void validarModelo() throws LogicaException{
		if (this.modelo.getFechaDesde()==null){
			throw new LogicaException("Debe seleccionar una fecha de inicio");
		}
		if (this.modelo.getFechaHasta()==null){
			throw new LogicaException("Debe seleccionar una fecha de finalización");
		}
		if (this.modelo.getTiposFacturas()== null){
			throw new LogicaException("Debe seleccionar un tipo de factura");
		}
		
	}
	@Override
	public void cancelar() {
		this.vista.dispose();
	}

	@Override
	public ReporteFacturacionView getVista() {
		return this.vista;
	}

	public void mostrar(){
		this.vista.setVisible(true);
	}

	public ReporteFacturacionModel getModelo() {
		return modelo;
	}
}
