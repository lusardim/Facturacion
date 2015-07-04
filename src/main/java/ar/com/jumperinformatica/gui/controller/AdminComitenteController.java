package ar.com.jumperinformatica.gui.controller;

import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.gui.model.BusquedaComitenteModel;
import ar.com.jumperinformatica.gui.view.AdminComitenteView;

public class AdminComitenteController extends AbstractAdminController {
	
	private BusquedaComitenteModel busquedaComitenteModel;
	private AdminComitenteView vista;
	
	public AdminComitenteController(JFrame pPrincipal) throws LogicaException{
		this.vista = new AdminComitenteView(pPrincipal);
		this.inicializar();
	}
	
	public AdminComitenteController(Dialog pPadre) throws LogicaException{
		this.vista = new AdminComitenteView(pPadre);
		this.inicializar();
	}
	
	private void inicializar() throws LogicaException{
		this.busquedaComitenteModel = new BusquedaComitenteModel();
		this.vista.getPanelBusqueda().setModel(this.busquedaComitenteModel);
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
		
		this.vista.getBotonAgregar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaAltaComitentes();	
			}
		});
		
		this.vista.getBotonModificar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaModificarComitentes();
			}
		});
		
		this.vista.getBotonEliminar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarComitente();
			}
		});
		
		this.vista.getBtnReportesComitentes().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				reporteComitente();				
			}
		});
	}

	public void mostrarVentanaAltaComitentes(){
		try{
			AMComitenteController locAMComitenteController = new AMComitenteController(this.vista);
			locAMComitenteController.setComitenteBean(this.busquedaComitenteModel.getComitenteBean());
			locAMComitenteController.getVista().setTitle("Alta de Comitentes");
			if (locAMComitenteController.mostrar()){
				this.busquedaComitenteModel.refrescar();
			}
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar mostrar la ventana de comitente. ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void mostrarVentanaModificarComitentes(){
		try{
			Comitente locComitente = this.busquedaComitenteModel.getSelected();
			AMComitenteController locAMComitenteController = new AMComitenteController(this.vista,locComitente);
			locAMComitenteController.setComitenteBean(this.busquedaComitenteModel.getComitenteBean());
			locAMComitenteController.getVista().setTitle("Modificación del Comitente "+locComitente);
		
			if (locAMComitenteController.mostrar()){
				this.busquedaComitenteModel.refrescar();
			}
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar mostrar la ventana de comitente. ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	private void eliminarComitente() {
		try{
			Comitente locComitente = this.busquedaComitenteModel.getSelected();
			if (JOptionPane.showConfirmDialog(this.getVista(), "Está seguro que desea eliminar el comitente "+locComitente, "Eliminar comitente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				this.busquedaComitenteModel.getComitenteBean().removeComitente(locComitente);
				this.busquedaComitenteModel.refrescar();
			}
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	/**
	 *  obtiene la vista
	 */
	public AdminComitenteView getVista() {
		return this.vista;
	}

	@Override
	public BusquedaComitenteModel getBusquedaModel() {
		return this.busquedaComitenteModel;
	}
	
	@Override
	public void refrescarVista() {
		super.refrescarVista();
		if (this.getBusquedaModel().getSelected()==null){
			this.getVista().getBtnReportesComitentes().setEnabled(false);
		}
		else{
			this.getVista().getBtnReportesComitentes().setEnabled(true);
		}
	}
	
	
	public void mostarSeleccionComitente(){
		this.vista.getPanelBusqueda().getTblBusqueda().addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2){
					vista.dispose();
				}
			}
		});
		this.mostrar();
	}
	
	private void reporteComitente() {
		Comitente locComitente = this.busquedaComitenteModel.getSelected();
		ReporteComitenteController locReporteComitenteController = new ReporteComitenteController(this.getVista(),locComitente);
		locReporteComitenteController.setComitenteBean(this.busquedaComitenteModel.getComitenteBean());
		locReporteComitenteController.mostrar();
	}

}
