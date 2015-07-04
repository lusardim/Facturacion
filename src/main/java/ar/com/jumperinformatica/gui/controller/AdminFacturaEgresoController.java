package ar.com.jumperinformatica.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.sf.jasperreports.engine.JRException;
import ar.com.jumperinformatica.core.enums.EstadoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.FacturaEgreso;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.listeners.RefrescarModeloWindowListener;
import ar.com.jumperinformatica.gui.enums.Reportes;
import ar.com.jumperinformatica.gui.model.BusquedaFacturaEgresoModel;
import ar.com.jumperinformatica.gui.view.AdminFacturaEgresoView;
import ar.com.jumperinformatica.impresion.GestorImpresion;

public class AdminFacturaEgresoController{
	private BusquedaFacturaEgresoModel modelo;
	private AdminFacturaEgresoView vista;
	private FacturacionBean facturacionBean;
	
	public AdminFacturaEgresoController(JFrame pPrincipal) throws LogicaException{
		this.modelo = new BusquedaFacturaEgresoModel();
		this.vista = new AdminFacturaEgresoView(pPrincipal);
		this.vista.getPanelBusqueda().setModel(this.modelo);
		this.facturacionBean = new FacturacionBean();
		this.refrescarVista();
		this.initListeners();
	}

	private void initListeners() {
		this.vista.getPanelBusqueda().addSeleccionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				refrescarVista();
			}
		});
		this.vista.getBtnFacturaA().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarAgregarFacturaA();	
			}

		});
		this.vista.getBtnFacturaB().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarAgregarFacturaB();
			}
		});
		this.vista.getBtnEliminar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminarFactura();
			}
		});
		this.vista.getBtnImprimir().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try{
					imprimirFactura(getBusquedaModel().getSelected());
				}
				catch(LogicaException e){
					JOptionPane.showMessageDialog(vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		this.vista.getBtnReportes().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaReportes();
			}
		});
	}

	public void refrescarVista() {
		if (this.getBusquedaModel().getSelected()==null){
			this.getVista().getBtnImprimir().setEnabled(false);
			this.getVista().getBtnEliminar().setEnabled(false);
		}
		else{
			this.getVista().getBtnImprimir().setEnabled(true);
			if (this.getBusquedaModel().getSelected().getEstadoFactura()!=EstadoFactura.ANULADA){
				this.getVista().getBtnEliminar().setEnabled(true);
			}
		}
	}

	public BusquedaFacturaEgresoModel getBusquedaModel() {
		return this.modelo;
	}

	public AdminFacturaEgresoView getVista() {
		return this.vista;
	}

	public void mostrar() {
		this.getVista().setVisible(true);	
		
	}
	
	private void mostrarAgregarFacturaA() {
		try{
			FacturaAController locFacturaEgresoAController = new FacturaAController(new FacturaEgreso(),this.facturacionBean,this.vista);
			locFacturaEgresoAController.mostrar();
			locFacturaEgresoAController.getVista().addWindowListener(new RefrescarModeloWindowListener(this.modelo));
			
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,"No se ha podido mostrar la ventana de facturación, Reinicie la aplicación y pruebe nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	private void eliminarFactura() {
		try{
			Factura locFactura = this.modelo.getSelected();
			if (JOptionPane.showConfirmDialog(this.vista, "¿Desea anular la factura "+locFactura+"?","Anular factura",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				this.facturacionBean.anularFactura(locFactura);
				this.getBusquedaModel().refrescar();
			}
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,e.getMessage(), "Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,"No se ha podido elimar la factura.", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void mostrarAgregarFacturaB() {
		try{
			FacturaEgresoBController locFacturaEgresoBController = new FacturaEgresoBController(this.facturacionBean,this.vista);
			locFacturaEgresoBController.mostrar();
			locFacturaEgresoBController.getVista().addWindowListener(new RefrescarModeloWindowListener(this.modelo));
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,"No se ha podido mostrar la ventana de facturación, Reinicie la aplicación y pruebe nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Imprime una factura A
	 * @param pFactura
	 * @throws FileNotFoundException
	 * @throws JRException
	 */
	public static void imprimirFactura(Factura pFactura) throws LogicaException{
		try{
			Map<String, Object> locParametros = new HashMap<String, Object>();
			locParametros.put("P_idFactura",pFactura.getIdFactura());
	
			switch(pFactura.getTipoFactura()){
				case A: GestorImpresion.imprimirDirecto(Reportes.FACTURA_A, locParametros);
						break;
				case B: GestorImpresion.imprimirDirecto(Reportes.FACTURA_B, locParametros);
						break;	
			}
		}
		catch(Exception e){
			e.printStackTrace();
			throw new LogicaException("No se ha podido imprimir el reporte de factura, Reinicie la aplicación e intente nuevamente.");
		}
	}
	

	private void mostrarVentanaReportes() {
		try{
			ReporteFacturacionEgresoController locReporteFacturacionEgresoController = new ReporteFacturacionEgresoController(this.getVista(),this.facturacionBean);
			locReporteFacturacionEgresoController.mostrar();
		}	
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,"No se ha podido mostrar la ventana de reportes de facturación, Reinicie la aplicación y pruebe nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
