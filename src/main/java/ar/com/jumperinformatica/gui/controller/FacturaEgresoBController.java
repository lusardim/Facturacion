package ar.com.jumperinformatica.gui.controller;

import java.awt.Dialog;
import java.awt.event.FocusListener;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;

import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.FacturaEgreso;
import ar.com.jumperinformatica.core.persistent.TipoIva;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.listeners.TotalesFocusListener;
import ar.com.jumperinformatica.gui.model.DetalleFacturaTableModel;
import ar.com.jumperinformatica.gui.view.FacturaBView;
import ar.com.jumperinformatica.gui.view.FacturaView;

public class FacturaEgresoBController extends FacturaController {
	private FacturaEgreso modelo;
	private FacturaBView vista;
	private DetalleFacturaTableModel modeloTabla;
	
	public FacturaEgresoBController(FacturacionBean pFacturacionBean, Dialog pPadre) throws ParseException, LogicaException{
		this.modelo = new FacturaEgreso();
		this.modelo.setTipoFactura(TipoFactura.B);
		this.vista = new FacturaBView(pPadre);
		this.vista.setTitle("Factura Egreso B");
		this.modeloTabla = new DetalleFacturaTableModel(this.modelo);
		this.vista.getTblDetalleFactura().setModel(this.modeloTabla);
		this.setFacturacionBean(pFacturacionBean);
		this.modelo.setNumeroFactura(this.getFacturacionBean().getSiguienteNumeroFacturaEgreso(TipoFactura.B));
		this.vista.getCboIva().setModel(new DefaultComboBoxModel(this.getFacturacionBean().getListaTiposIva().toArray()));
		this.inicializarEventos();
		this.actualizarVista();
	}
	

	@Override
	protected void inicializarEventos() {
		super.inicializarEventos();
		FocusListener locTotalesFocusListener = new TotalesFocusListener(this);
		this.vista.getTfTotal().addFocusListener(locTotalesFocusListener);
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
		
	}

	private void actualizarVistaComitente() {
		Comitente locComitente = this.modelo.getComitente();
		if (locComitente!=null){
			if (locComitente.getCuit()!=null){
				this.vista.getTfCuit().setText(locComitente.getCuit());
			}
			else{
				this.vista.getTfCuit().setText("");
			}
			
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
			this.vista.getTfCuit().setText("");
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
	public FacturaEgreso getModel() {
		return this.modelo;
	}

}

