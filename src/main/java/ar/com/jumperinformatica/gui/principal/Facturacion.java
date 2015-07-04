package ar.com.jumperinformatica.gui.principal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;
import ar.com.jumperinformatica.gui.controller.AdminComitenteController;
import ar.com.jumperinformatica.gui.controller.AdminFacturaEgresoController;
import ar.com.jumperinformatica.gui.controller.AdminFacturaIngresoController;
import ar.com.jumperinformatica.gui.controller.AdminUsuarioController;
import ar.com.jumperinformatica.gui.controller.BackupController;
import ar.com.jumperinformatica.gui.controller.LoginController;
import ar.com.jumperinformatica.gui.controller.ReporteFacturacionEgresoController;
import ar.com.jumperinformatica.gui.controller.ReporteFacturacionIngresoController;
import ar.com.jumperinformatica.gui.controller.listeners.CambioUsuarioListener;
import ar.com.jumperinformatica.gui.controller.listeners.SalirAplicacionListener;
import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.view.AcercaDeView;
import ar.com.jumperinformatica.gui.view.PrincipalView;

public class Facturacion {
	private static Facturacion facturacion;
	private AdminRecursos adminRecursos;
	
	private PrincipalView principalView;
	
	
	public PrincipalView getPrincipalView() {
		if (this.principalView == null){
			this.principalView = new PrincipalView();
			this.principalView.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			this.inicializarEventos();
		}
		return principalView;
	}
	
	private void inicializarEventos() {
		this.principalView.addWindowListener(new SalirAplicacionListener());
		this.principalView.getMnuComitentes().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				mostrarVentanaAdministracionComitentes();
			}
		});
		this.principalView.getMiAcercaDe().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarAcercaDe();				
			}
		});
		this.principalView.getMiBackup().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarBackup();
			}
		});
		this.principalView.getMiUsuarios().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaAdministracionUsuarios();				
			}

		});
		this.principalView.getMnuFacturaEgreso().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				mostrarVentanaAdministracionFacturaEgreso();
			}
		});
	
		//reportes
		this.principalView.getMnuFacturaIngreso().addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e) {
				mostrarVentanaAdministracionFacturaIngreso();
			}
		});
		this.principalView.getMiFacturacionIngresoGeneral().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarReporteFacturacionIngresoGeneral();				
			}

		});
		this.principalView.getMiFacturacionEgresoGeneral().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarReporteFacturacionEgresoGeneral();
			}
		});
		this.principalView.getMiFacturaComitentes().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaAdministracionComitentes();
			}
		});
		this.principalView.getMiFacturacionEgresoIndividual().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaAdministracionFacturaEgreso();
			}
		});
	}

	public static Facturacion getInstance(){
		if (facturacion == null){
			facturacion = new Facturacion();
		}
		return facturacion;
	}


	/**
	 * Obtiene el administrador de recursos
	 * @return AdminRecursos
	 */
	public AdminRecursos getAdminRecursos() {
		if (this.adminRecursos == null){
			adminRecursos = new AdminRecursos();
		}
		return adminRecursos;
	}
	
	
	public static void main(String[] args){
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		LoginController locLogin = new LoginController(Facturacion.getInstance().principalView);
		locLogin.getModel().addUsuarioLoggeadoListener(new CambioUsuarioListener());
		
	}

	
	/**
	 * Abre la ventana de administración de comitentes
	 * @throws LogicaException
	 */
	public void mostrarVentanaAdministracionComitentes(){
		AdminComitenteController locAdminComitenteController = null; 
		try{
			locAdminComitenteController = new AdminComitenteController(this.getPrincipalView());
			locAdminComitenteController.mostrar();
			
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(
					(locAdminComitenteController!=null)?locAdminComitenteController.getVista():null, 
					e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	
	}

	/**
	 * Muestra la ventana de carga de las facturas de egreso
	 */
	public void mostrarVentanaAdministracionFacturaEgreso(){
		AdminFacturaEgresoController locAdminFacturaController = null;
		try{
			locAdminFacturaController = new AdminFacturaEgresoController(this.getPrincipalView());
			locAdminFacturaController.mostrar();
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(
					(locAdminFacturaController!=null)?locAdminFacturaController.getVista():null, 
					e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
		
	/**
	 * Muestra la ventana de facturacion ingreso
	 */
	private void mostrarVentanaAdministracionFacturaIngreso() {
		AdminFacturaIngresoController locAdminFacturaController = null;
		try{
			locAdminFacturaController = new AdminFacturaIngresoController(this.getPrincipalView());
			locAdminFacturaController.mostrar();
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(
					(locAdminFacturaController!=null)?locAdminFacturaController.getVista():null, 
					e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		
	}
	/**
	 * Muestra la ventana de administración de usuarios
	 */
	private void mostrarVentanaAdministracionUsuarios() {
		AdminUsuarioController locAdminUsuarioController = null; 
		try{
			locAdminUsuarioController = new AdminUsuarioController(this.getPrincipalView());
			locAdminUsuarioController.mostrar();
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(
					(locAdminUsuarioController!=null)?locAdminUsuarioController.getVista():null, 
					e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * Muestra la ventanad de acerca de
	 */
	private void mostrarAcercaDe() {
		AcercaDeView locAcercaDeView = new AcercaDeView(this.principalView);
		locAcercaDeView.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_UTILIDADES_ACERCADE));
		locAcercaDeView.setVisible(true);
	}
	
	
	/**
	 * Muestra la ventana de reporte de facturación egreso general
	 */
	private void mostrarReporteFacturacionEgresoGeneral() {
		ReporteFacturacionEgresoController locFacturacionEgresoController = new ReporteFacturacionEgresoController(this.principalView,new FacturacionBean());
		locFacturacionEgresoController.mostrar();

	}
	
	/**
	 * Muestra la ventana de reporte de facturación ingreso general
	 */
	private void mostrarReporteFacturacionIngresoGeneral() {
		ReporteFacturacionIngresoController locFacturacionEgresoController = new ReporteFacturacionIngresoController(this.principalView,new FacturacionBean());
		locFacturacionEgresoController.mostrar();
	}
	/**
	 * Muestra la ventana de backup
	 */
	private void mostrarBackup() {
		BackupController controller = new BackupController(this.principalView);
		controller.mostrar();
	}
	
	
	/**
	 * Sale del sistema
	 */
	public void salir(){
		if (JOptionPane.showConfirmDialog(this.getPrincipalView(), "¿Está seguro que desea salir del sistema?", this.getPrincipalView().getTitle(), JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
			System.exit(0);	
		}
	}
}
