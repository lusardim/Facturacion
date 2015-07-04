package ar.com.jumperinformatica.gui.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.com.jumperinformatica.core.enums.EstadoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Factura;
import ar.com.jumperinformatica.core.persistent.FacturaIngreso;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.listeners.RefrescarModeloWindowListener;
import ar.com.jumperinformatica.gui.model.BusquedaFacturaIngresoModel;
import ar.com.jumperinformatica.gui.view.AdminFacturaIngresoView;

public class AdminFacturaIngresoController{
	private BusquedaFacturaIngresoModel modelo;
	private AdminFacturaIngresoView vista;
	private FacturacionBean facturacionBean;
	
	public AdminFacturaIngresoController(JFrame pPrincipal) throws LogicaException{
		this.modelo = new BusquedaFacturaIngresoModel();
		this.vista = new AdminFacturaIngresoView(pPrincipal);
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
		this.vista.getBtnFacturaC().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mostrarAgregarFacturaC();
			}
		});
		this.vista.getBtnEliminar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eliminarFactura();
			}
		});
		this.getVista().getBtnReportes().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaReportes();
			}
		});
	}

	public void refrescarVista() {
		if (this.getBusquedaModel().getSelected()==null){
			this.getVista().getBtnEliminar().setEnabled(false);
		}
		else{
			if (this.getBusquedaModel().getSelected().getEstadoFactura()!=EstadoFactura.ANULADA){
				this.getVista().getBtnEliminar().setEnabled(true);
			}
		}
	}

	public BusquedaFacturaIngresoModel getBusquedaModel() {
		return this.modelo;
	}

	public AdminFacturaIngresoView getVista() {
		return this.vista;
	}

	public void mostrar() {
		this.getVista().setVisible(true);	
		
	}
	
	private void mostrarAgregarFacturaA() {
		try{
			FacturaAController locFacturaIngresoAController = new FacturaAController(new FacturaIngreso(),this.facturacionBean,this.vista);
			locFacturaIngresoAController.mostrar();
			locFacturaIngresoAController.getVista().addWindowListener(new RefrescarModeloWindowListener(this.modelo));
			
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
	
	private void mostrarAgregarFacturaC() {
		try{
			FacturaCController locFacturaIngresoCController = new FacturaCController(this.facturacionBean,this.vista);
			locFacturaIngresoCController.mostrar();
			locFacturaIngresoCController.getVista().addWindowListener(new RefrescarModeloWindowListener(this.modelo));
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,"No se ha podido mostrar la ventana de facturación, Reinicie la aplicación y pruebe nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void mostrarVentanaReportes() {
		try{
			ReporteFacturacionIngresoController locReporteFacturacionIngresoController = new ReporteFacturacionIngresoController(this.getVista(),this.facturacionBean);
			locReporteFacturacionIngresoController.mostrar();
		}	
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista,"No se ha podido mostrar la ventana de reportes de facturación, Reinicie la aplicación y pruebe nuevamente.", "Error",JOptionPane.ERROR_MESSAGE);
		}
	}
}
