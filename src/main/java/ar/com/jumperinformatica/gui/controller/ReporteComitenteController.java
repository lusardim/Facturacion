package ar.com.jumperinformatica.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.system.bean.ComitenteBean;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;
import ar.com.jumperinformatica.gui.controller.listeners.CancelarListener;
import ar.com.jumperinformatica.gui.enums.Periodo;
import ar.com.jumperinformatica.gui.enums.Reportes;
import ar.com.jumperinformatica.gui.model.PeriodoComboBoxModel;
import ar.com.jumperinformatica.gui.model.ReporteComitente;
import ar.com.jumperinformatica.gui.view.ReporteComitenteView;
import ar.com.jumperinformatica.impresion.GestorImpresion;

public class ReporteComitenteController implements AMController {
	private ReporteComitenteView vista;
	private ReporteComitente modelo;
	private ComitenteBean comitenteBean;
	
	public ReporteComitenteController(JDialog pPadre,Comitente pComitente){
		this.vista = new ReporteComitenteView(pPadre);
		this.modelo = new ReporteComitente();
		this.modelo.setComitente(pComitente);
		this.initModels();
		this.initListeners();
		this.vista.setTitle("Reporte de comitente "+this.modelo.getComitente());
	}
	
	private void initModels() {
		this.vista.getCboPeriodo().setModel(new PeriodoComboBoxModel());
	}

	private void initListeners() {
		this.vista.getBtnAceptar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				aceptar();
			}
		});
		this.vista.getBtnCancelar().addActionListener(new CancelarListener(this.vista));
		this.vista.getCboPeriodo().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				actualizarFechas();				
			}
		});
		this.vista.getDcDesde().addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if (arg0.getPropertyName().equals("date")){
					actualizarFechas();
				}
			}
		});
	}

	public void mostrar(){
		this.vista.setVisible(true);
	}
	
	private void actualizarFechas() {
		try{
			this.actualizarModelo();
			this.modelo.recalcularFechaHasta();
			this.actualizarVista();
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(this.getVista(), e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	@Override
	public void aceptar() {
		try{
			this.actualizarModelo();
			this.validarModelo();
			Map<String, Object> locParametros = this.getParametrosReporte();
			GestorImpresion.imprimirConViewer(this.getVista(),Reportes.COMITENTE, locParametros);
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getVista(), e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.getVista(), 
					"Ha ocurrido un error al intentar mostrar el reporte. Reinicie la aplicación e intente nuevamente",
					"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void validarModelo() throws LogicaException{
		if (this.modelo.getFechaDesde()==null){
			throw new LogicaException("Debe seleccionar una fecha de inicio");
		}
		if (this.modelo.getFechaHasta()==null){
			throw new LogicaException("Debe seleccionar una fecha de finalización");
		}
		
	}

	private Map<String, Object> getParametrosReporte() throws LogicaException{
		Map<String,Object> locParametros = new HashMap<String, Object>();
		locParametros.put("P_periodo",this.modelo.getPeriodo().toString().toLowerCase());
		locParametros.put("P_fechaDesde", new Timestamp(this.modelo.getFechaDesde().getTime()));
		locParametros.put("P_fechaHasta", new Timestamp(this.modelo.getFechaHasta().getTime()));
		locParametros.put("P_idComitente", this.modelo.getComitente().getIdComitente());
		Float totalIva = 0f;
		Float subTotal = 0f;
		Float total = 0f;
		List<Factura> locFacturacion = this.comitenteBean
									.getFacturacionComitente(this.modelo.getComitente(), 
										this.modelo.getFechaDesde(), 
										this.modelo.getFechaHasta());
		
		for (Factura cadaFactura : locFacturacion){
			totalIva += cadaFactura.getTotalIva();
			subTotal += cadaFactura.getSubtotal();
			total +=cadaFactura.getTotalFactura();
		}
		locParametros.put("P_totalIva", totalIva);
		locParametros.put("P_subtotal", subTotal);
		locParametros.put("P_total", total);
		return locParametros;
	}

	@Override
	public void actualizarModelo() throws LogicaException {
		this.modelo.setPeriodo((Periodo)this.vista.getCboPeriodo().getSelectedItem());
		this.modelo.setFechaDesde(this.vista.getDcDesde().getDate());
		this.modelo.setFechaHasta(this.vista.getDcHasta().getDate());
	}

	@Override
	public void actualizarVista() {
		this.vista.getCboPeriodo().setSelectedItem(this.modelo.getPeriodo());
		this.vista.getDcDesde().setDate(this.modelo.getFechaDesde());
		this.vista.getDcHasta().setDate(this.modelo.getFechaHasta());
		
	}

	@Override
	public void cancelar() {
		this.vista.dispose();
	}

	@Override
	public ReporteComitenteView getVista() {
		return this.vista;
	}

	public ComitenteBean getComitenteBean() {
		if (comitenteBean==null){
			comitenteBean = new ComitenteBean(); 
		}
		return comitenteBean;
	}

	public void setComitenteBean(ComitenteBean comitenteBean) {
		this.comitenteBean = comitenteBean;
	}
}