package ar.com.jumperinformatica.gui.controller;

import java.awt.Dialog;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.FacturaEgreso;
import ar.com.jumperinformatica.core.persistent.TipoIva;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;
import ar.com.jumperinformatica.gui.model.DetalleFacturaTableModel;
import ar.com.jumperinformatica.gui.view.FacturaAView;
import ar.com.jumperinformatica.gui.view.FacturaView;

public class FacturaAController extends FacturaController implements AMController{
	private Factura modelo;
	private FacturaAView vista;
	private DetalleFacturaTableModel modeloTabla;
	
	public FacturaAController(Factura pFactura, FacturacionBean pFacturacionBean, Dialog pPadre) throws ParseException, LogicaException{
		this.modelo = pFactura;
		this.modelo.setTipoFactura(TipoFactura.A);
		this.vista = new FacturaAView(pPadre);
		this.modeloTabla = new DetalleFacturaTableModel(this.modelo);
		this.vista.getTblDetalleFactura().setModel(this.modeloTabla);
		this.setFacturacionBean(pFacturacionBean);
		if (pFactura instanceof FacturaEgreso){
			this.vista.setTitle("Factura Egreso A");
			this.modelo.setNumeroFactura(this.getFacturacionBean().getSiguienteNumeroFacturaEgreso(TipoFactura.A));
		}
		else{
			this.vista.setTitle("Factura Ingreso A");
			this.modelo.setNumeroFactura(this.getFacturacionBean().getSiguienteNumeroFacturaIngreso(TipoFactura.A));
		}
		this.vista.getCboIva().setModel(new DefaultComboBoxModel(this.getFacturacionBean().getListaTiposIva().toArray()));
		this.inicializarEventos();
		this.actualizarVista();
	}
	
	

	@Override
	protected void inicializarEventos() {
		super.inicializarEventos();
		this.vista.getCboIva().addActionListener(new CambioComboIvaListener(this));
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
		BigDecimal subtotal = null;
		BigDecimal totalIva = null;
		TipoIva tipoIva = null;
	
		DecimalFormat locFormato = (DecimalFormat)DecimalFormat.getNumberInstance();
		locFormato.setParseBigDecimal(true);
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
		if (!this.vista.getTfSubtotal().getText().isEmpty()){
			try{
				subtotal = (BigDecimal)locFormato.parse(this.vista.getTfSubtotal().getText());
			}
			catch(ParseException e){
				e.printStackTrace();
				throw new LogicaException("El valor del subtotal debe ser un número válido");
			}
		}
		
		if (!this.vista.getTfTotalIva().getText().isEmpty()){
			try{
				totalIva = (BigDecimal)locFormato.parse(this.vista.getTfTotalIva().getText());
			}
			catch(ParseException e){
				e.printStackTrace();
				throw new LogicaException("El valor del iva debe ser un número válido");
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
		this.modelo.setSubtotal(subtotal);
		this.modelo.setTotalIva(totalIva);
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
		if (this.modelo.getSubtotal()==null){
			this.vista.getTfSubtotal().setText("");
		}
		else{
			this.vista.getTfSubtotal().setText(locFormato.format(this.modelo.getSubtotal()));
		}
		if (this.modelo.getTotalIva()==null){
			this.vista.getTfTotalIva().setText("");
		}
		else{
			this.vista.getTfTotalIva().setText(locFormato.format(this.modelo.getTotalIva()));
		}
		this.vista.getCboIva().setSelectedItem(this.modelo.getTipoIva());
		this.actualizarVistaComitente();
	}

	
	/**
	 * Actualiza el valor del iva a partir de 
	 */
	public void actualizarIva() {
		TipoIva locTipoIva = (TipoIva)this.vista.getCboIva().getSelectedItem();
		if (locTipoIva!=null){
			this.modelo.setTipoIva(locTipoIva);
			this.modelo.calcularTotalIva();
			this.modelo.calcularTotal();
			this.actualizarVista();
		}
	}
	
	@Override
	public FacturaView getVista() {
		return this.vista;
	}


	@Override
	public Factura getModel() {
		return this.modelo;
	}

	
	public static void main(String[] args) throws HeadlessException, ParseException, LogicaException{
		FacturaAController controller = new FacturaAController(new FacturaEgreso(),new FacturacionBean(),new JDialog(new JFrame()));
		controller.getVista().addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(1);
			}
		});
		controller.mostrar();
	}
}


class CambioComboIvaListener implements ActionListener{
	private FacturaAController controller;
	public CambioComboIvaListener(FacturaAController pController){
		this.controller = pController;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
			//FIXME ACA!!!!
		//if (this.controller.getModel().getTipoIva().equals(this.controller.getVista))
		this.controller.actualizarIva();
	}
}