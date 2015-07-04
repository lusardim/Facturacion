package ar.com.jumperinformatica.gui.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;
import ar.com.jumperinformatica.gui.view.components.BarraHerramientasFacturaEgreso;
import ar.com.jumperinformatica.gui.view.components.BusquedaPanel;

public class AdminFacturaEgresoView extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -48474812665646848L;
	private BarraHerramientasFacturaEgreso barraHerramientas;
	private BusquedaPanel panelBusqueda;
	
	public AdminFacturaEgresoView(JFrame pPadre){
		super(pPadre);
		this.setModal(true);
		this.setTitle("Administración de facturas de egreso");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.initComponents();
		this.setResizable(true);
		this.pack();
		this.setLocationRelativeTo(pPadre);
	}


	private void initComponents() {
		barraHerramientas = new BarraHerramientasFacturaEgreso();
		panelBusqueda = new BusquedaPanel();

		this.setLayout(new BorderLayout());

		this.add(barraHerramientas,BorderLayout.NORTH);
		this.add(panelBusqueda,BorderLayout.CENTER);

		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_FACTURA_EGRESO));
	}

	public JButton getBtnEliminar() {
		return barraHerramientas.getBtnEliminar();
	}

	public JButton getBtnFacturaA() {
		return barraHerramientas.getBtnFacturaA();
	}

	public JButton getBtnFacturaB() {
		return barraHerramientas.getBtnFacturaB();
	}

	public JButton getBtnImprimir() {
		return barraHerramientas.getBtnImprimir();
	}

	public JButton getBtnReportes() {
		return barraHerramientas.getBtnReportes();
	}

	public BusquedaPanel getPanelBusqueda() {
		return panelBusqueda;
	}


}
