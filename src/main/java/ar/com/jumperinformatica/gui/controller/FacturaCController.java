package ar.com.jumperinformatica.gui.controller;

import java.awt.Dialog;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

import ar.com.jumperinformatica.core.enums.EstadoComitente;
import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.FacturaIngreso;
import ar.com.jumperinformatica.core.persistent.TipoIva;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.listeners.SeleccionComitenteFacturaCFocusListener;
import ar.com.jumperinformatica.gui.controller.listeners.TotalesFocusListener;
import ar.com.jumperinformatica.gui.model.DetalleFacturaTableModel;
import ar.com.jumperinformatica.gui.view.FacturaCView;
import ar.com.jumperinformatica.gui.view.FacturaView;

public class FacturaCController extends FacturaController {
	private FacturaIngreso modelo;
	private FacturaCView vista;
	private DetalleFacturaTableModel modeloTabla;
	
	private boolean comitenteSeleccionado;
	
	public static void main(String[] args) throws  ParseException, LogicaException{
		FacturacionBean locFacturacionBean = new FacturacionBean();
		FacturaCController locController = new FacturaCController(locFacturacionBean,new JDialog(new JFrame()));
		locController.mostrar();
	}
	
	public FacturaCController(FacturacionBean pFacturacionBean, Dialog pPadre) throws ParseException, LogicaException{
		this.modelo = new FacturaIngreso();
		this.modelo.setTipoFactura(TipoFactura.C);
		this.vista = new FacturaCView(pPadre);
		this.vista.setTitle("Factura Ingreso C");
		this.modeloTabla = new DetalleFacturaTableModel(this.modelo);
		this.vista.getTblDetalleFactura().setModel(this.modeloTabla);
		this.setFacturacionBean(pFacturacionBean);
		this.modelo.setNumeroFactura(this.getFacturacionBean().getSiguienteNumeroFacturaIngreso(TipoFactura.C));
		this.vista.getCboIva().setModel(new DefaultComboBoxModel(this.getFacturacionBean().getListaTiposIva().toArray()));
		this.inicializarEventos();
		this.actualizarVista();
	}
	
	@Override
	protected void addFactura(Factura factura) throws LogicaException {
		this.getFacturacionBean().addFacturaC(factura);
	}

	@Override
	protected void inicializarEventos() {
		super.inicializarEventos();
		FocusListener locTotalesFocusListener = new TotalesFocusListener(this);
		this.vista.getTfTotal().addFocusListener(locTotalesFocusListener);
		this.vista.getTfDomicilio().addFocusListener(new SeleccionComitenteFacturaCFocusListener(this));
		this.vista.getTfNombre().addFocusListener(new SeleccionComitenteFacturaCFocusListener(this));
	}

	public void mostrar(){
		this.vista.setVisible(true);
	}
	
	@Override
	public void actualizarModelo() throws LogicaException {
		String condicionVenta = null;
		Date fecha = Calendar.getInstance().getTime();
		String numeroFactura = null;
		Integer numeroRemito = null;
		String obra  = null;
		BigDecimal totalFactura  = null;
		TipoIva tipoIva = null;
	
		NumberFormat locFormato = NumberFormat.getNumberInstance();
		if (!this.vista.getTfCondicionVenta().getText().trim().isEmpty()){
			condicionVenta = this.vista.getTfCondicionVenta().getText().trim();
		}
		
		if (this.vista.getDcFecha().getDate()!=null){
			fecha = this.vista.getDcFecha().getDate();
		}
		
		if (!this.vista.getTfNumeroFactura().getText().trim().isEmpty()){
			if (this.vista.getTfNumeroFactura().isEditValid()){
				numeroFactura = this.vista.getTfNumeroFactura().getText();
			}
		}
		
		
		if (!this.vista.getTfRemito().getText().trim().isEmpty()){
			try{
				numeroRemito = Integer.valueOf(this.vista.getTfRemito().getText());
			}
			catch(NumberFormatException e){
				throw new LogicaException("El número de remito debe ser un número entero");
			}
		}
		
		if (!this.vista.getTfObra().getText().isEmpty()){			
			obra = this.vista.getTfObra().getText().trim();
		}
		
		if (!this.vista.getTfTotal().getText().isEmpty()){
			try{
				totalFactura = (BigDecimal)locFormato.parse(this.vista.getTfTotal().getText());
			} catch (ParseException e) {
				e.printStackTrace();
				throw new LogicaException("El total de la factura debe ser un número válido");
			}
		}
		
		if (this.vista.getCboIva().getSelectedItem()!=null){
			tipoIva = (TipoIva)this.vista.getCboIva().getSelectedItem();
		}
		
		this.modelo.setCondicionVenta(condicionVenta);
		this.modelo.setFecha(fecha);
		this.modelo.setNumeroFactura(numeroFactura);
		this.modelo.setNumeroRemito(numeroRemito);
		this.modelo.setObra(obra);
		this.modelo.setTotalFactura(totalFactura);
		this.modelo.setTipoIva(tipoIva);
		
		if (!this.comitenteSeleccionado){
			Comitente locComitente;
			if (modelo.getComitente()==null){
				locComitente = new Comitente();
			}
			else{
				locComitente = modelo.getComitente();
			}
			locComitente.setNombre(this.getVista().getTfNombre().getText().trim());
			locComitente.setDomicilio(this.getVista().getTfDomicilio().getText().trim());
			locComitente.setCuit(null);
			locComitente.setEstadoComitente(EstadoComitente.INACTIVO);
			modelo.setComitente(locComitente);
		}
	}

	private void actualizarVistaComitente() {
		Comitente locComitente = this.modelo.getComitente();
		if (locComitente!=null){
			if (locComitente.getDomicilio()!=null){
				this.vista.getTfDomicilio().setText(locComitente.getDomicilio());
			}
			else{
				this.vista.getTfDomicilio().setText("");
			}
			
			if (locComitente.getNombre()!=null){
				this.vista.getTfNombre().setText(locComitente.getNombre());
			}
			else{
				this.vista.getTfNombre().setText("");
			}
		}
		else{
			this.vista.getTfDomicilio().setText("");
			this.vista.getTfNombre().setText("");
		}
	}


	@Override
	public void actualizarVista() {
		this.vista.getTfCondicionVenta().setText(this.modelo.getCondicionVenta());
		this.vista.getDcFecha().setDate(this.modelo.getFecha());
		this.vista.getTfNumeroFactura().setText(this.modelo.getNumeroFactura());
		
		NumberFormat locFormato = NumberFormat.getNumberInstance();
		locFormato.setMaximumFractionDigits(2);
		locFormato.setMinimumIntegerDigits(1);
		
		if (this.modelo.getNumeroRemito()!=null){
			this.vista.getTfRemito().setText(String.valueOf(this.modelo.getNumeroRemito()));
		}
		else{
			this.vista.getTfRemito().setText("");
		}
		this.vista.getTfObra().setText(this.modelo.getObra());			
		
		if (this.modelo.getTotalFactura()==null){
			this.vista.getTfTotal().setText("");
		}
		else{
			this.vista.getTfTotal().setText(locFormato.format(this.modelo.getTotalFactura()));
		}
		
		this.vista.getCboIva().setSelectedItem(this.modelo.getTipoIva());
		
		this.actualizarVistaComitente();
	}

	
	/**
	 * Actualiza el valor del iva a partir de 
	 */
	public void actualizarIva() {
		TipoIva locTipoIva = (TipoIva)this.vista.getCboIva().getSelectedItem();
		this.modelo.setTipoIva(locTipoIva);
		this.actualizarVista();
	}
	
	@Override
	public FacturaView getVista() {
		return this.vista;
	}


	@Override
	public FacturaIngreso getModel() {
		return this.modelo;
	}
	
	@Override
	public void mostrarBuscarComitente() {
		super.mostrarBuscarComitente();
		this.comitenteSeleccionado = true;
	}
	
	public void iniciarEdicionComitente(JTextField pFuente, String pValorNuevo) throws LogicaException{
		if (this.comitenteSeleccionado){
			this.comitenteSeleccionado = false;
			this.modelo.setComitente(null);
			this.actualizarVista();
			pFuente.setText(pValorNuevo);
			this.actualizarModelo();
		}
		else{
			pFuente.setText(pValorNuevo);
			this.actualizarModelo();
		}
		
	}

	public boolean isComitenteSeleccionado() {
		return comitenteSeleccionado;
	}

	public void setComitenteSeleccionado(boolean comitenteSeleccionado) {
		this.comitenteSeleccionado = comitenteSeleccionado;
	}

}


