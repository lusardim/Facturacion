package ar.com.jumperinformatica.gui.view;

import java.awt.BorderLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;

import ar.com.jumperinformatica.gui.controller.interfaces.AdminView;
import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;
import ar.com.jumperinformatica.gui.view.components.BarraHerramientasABMPanel;
import ar.com.jumperinformatica.gui.view.components.BusquedaPanel;

public class AdminUsuarioView extends JDialog implements AdminView{
	/**
	 * 
	 */
	private static final long serialVersionUID = -118336536865170313L;
	private BarraHerramientasABMPanel barraHerramientas;
	private BusquedaPanel panelBusqueda;

	public AdminUsuarioView(Frame pPadre){
		super(pPadre);
		this.setModal(true);
		this.setTitle("Administración de usuarios");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponents();
		this.setResizable(true);
		this.pack();
		this.setLocationRelativeTo(pPadre);
	}

	private void initComponents() {
		this.barraHerramientas = new BarraHerramientasABMPanel();
		this.panelBusqueda = new BusquedaPanel();
		this.setLayout(new BorderLayout());
		
		this.add(this.barraHerramientas,BorderLayout.NORTH);
		this.add(this.panelBusqueda, BorderLayout.CENTER);
		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_UTILIDADES_USUARIOS));
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
}
