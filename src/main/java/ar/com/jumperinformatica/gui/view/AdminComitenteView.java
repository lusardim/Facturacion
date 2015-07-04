package ar.com.jumperinformatica.gui.view;

import java.awt.BorderLayout;
import java.awt.Dialog;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ar.com.jumperinformatica.gui.controller.interfaces.AdminView;
import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;
import ar.com.jumperinformatica.gui.view.components.BarraHerramientasABMPanel;
import ar.com.jumperinformatica.gui.view.components.BusquedaPanel;

public class AdminComitenteView extends JDialog implements AdminView{
	
	
	private static final long serialVersionUID = 2096221907697730677L;
	private BarraHerramientasABMPanel barraHerramientas;
	private BusquedaPanel panelBusqueda;
	
	private JButton btnReportesComitentes;

	public AdminComitenteView(JFrame padre){
		super(padre);
		this.configurar();
		this.setLocationRelativeTo(padre);
	}
	
	public AdminComitenteView(Dialog pPadre){
		super(pPadre);
		this.configurar();
		this.setLocationRelativeTo(pPadre);
	}
	
	
	private void configurar() {
		this.setModal(true);
		this.setTitle("Administración de comitentes");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponents();
		this.setResizable(true);
		this.pack();

	}

	private void initComponents() {
		barraHerramientas = new BarraHerramientasABMPanel();
		panelBusqueda = new BusquedaPanel();
		btnReportesComitentes = new JButton();
		
		barraHerramientas.addButton(btnReportesComitentes,Recursos.BARRA_ABM_REPORTES);
		
		this.setLayout(new BorderLayout());
		
		this.add(barraHerramientas,BorderLayout.NORTH);
		this.add(panelBusqueda,BorderLayout.CENTER);
		
		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_COMITENTES));
	}

	
	public JButton getBotonAgregar() {
		return barraHerramientas.getBotonAgregar();
	}

	public JButton getBotonEliminar() {
		return barraHerramientas.getBotonEliminar();
	}

	public JButton getBotonModificar() {
		return barraHerramientas.getBotonModificar();
	}


	public BusquedaPanel getPanelBusqueda() {
		return panelBusqueda;
	}

	public JButton getBtnReportesComitentes() {
		return btnReportesComitentes;
	}
	
}
