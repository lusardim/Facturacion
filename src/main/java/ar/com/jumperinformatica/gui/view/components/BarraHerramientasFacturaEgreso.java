package ar.com.jumperinformatica.gui.view.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class BarraHerramientasFacturaEgreso extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6361500513813723228L;
	private final static Color COLOR_FONDO = new Color(211,211,211); 
	private final static Font FUENTE_FACTURAS = Font.decode("Tahoma-Bold-20");
	private static final Color COLOR_FUENTE_FACTURAS = new Color(0, 142, 36);
		
	
	
	private JButton btnFacturaA;
	private JButton btnFacturaB;
	private JButton btnEliminar;
	private JButton btnReportes;
	private JButton btnImprimir;
	
	public BarraHerramientasFacturaEgreso(){
		super();
		this.initComponents();
	}

	private void initComponents() {
		btnFacturaA = new JButton();
		btnFacturaB = new JButton();
		btnEliminar = new JButton();
		btnReportes = new JButton();
		btnImprimir = new JButton();
		this.setBackground(COLOR_FONDO);
		
		
		btnFacturaA.setText("A");
		btnFacturaA.setFont(FUENTE_FACTURAS);
		btnFacturaA.setBackground(COLOR_FONDO);
		btnFacturaA.setForeground(COLOR_FUENTE_FACTURAS);
		btnFacturaA.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_AGREGAR));
		Dimension locDimension = btnFacturaA.getPreferredSize();
		
		btnFacturaB.setText("B");
		btnFacturaB.setFont(FUENTE_FACTURAS);
		btnFacturaB.setBackground(COLOR_FONDO);
		btnFacturaB.setForeground(COLOR_FUENTE_FACTURAS);
		btnFacturaB.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_AGREGAR));
		btnFacturaB.setPreferredSize(locDimension);
		
		btnEliminar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_ELIMINAR));
		btnEliminar.setBackground(COLOR_FONDO);
		btnEliminar.setPreferredSize(locDimension);
		
		btnReportes.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_REPORTES));
		btnReportes.setBackground(COLOR_FONDO);
		btnReportes.setPreferredSize(locDimension);
		
		btnImprimir.setBackground(COLOR_FONDO);
		btnImprimir.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_IMPRIMIR));
		btnImprimir.setPreferredSize(locDimension);
		
		this.add(btnFacturaA);
		this.add(btnFacturaB);
		this.add(btnEliminar);
		this.add(btnReportes);
		this.add(btnImprimir);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setFloatable(false);
		
	}

	
	public JButton getBtnFacturaA() {
		return btnFacturaA;
	}

	public JButton getBtnFacturaB() {
		return btnFacturaB;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnReportes() {
		return btnReportes;
	}

	public JButton getBtnImprimir() {
		return btnImprimir;
	}
}
