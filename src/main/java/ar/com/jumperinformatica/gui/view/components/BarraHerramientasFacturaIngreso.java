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

public class BarraHerramientasFacturaIngreso extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6361500513813723228L;
	private final static Color COLOR_FONDO = new Color(211,211,211); 
	private final static Font FUENTE_FACTURAS = Font.decode("Tahoma-Bold-20");
	private static final Color COLOR_FUENTE_FACTURAS = new Color(0, 142, 36);
		
	
	
	private JButton btnFacturaA;
	private JButton btnFacturaC;
	private JButton btnEliminar;
	private JButton btnReportes;
	
	public BarraHerramientasFacturaIngreso(){
		super();
		this.initComponents();
	}

	private void initComponents() {
		btnFacturaA = new JButton();
		btnFacturaC = new JButton();
		btnEliminar = new JButton();
		btnReportes = new JButton();
		this.setBackground(COLOR_FONDO);
		
		
		
		btnFacturaA.setText("A");
		btnFacturaA.setFont(FUENTE_FACTURAS);
		btnFacturaA.setBackground(COLOR_FONDO);
		btnFacturaA.setForeground(COLOR_FUENTE_FACTURAS);
		btnFacturaA.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_AGREGAR));
		Dimension locDimension = btnFacturaA.getPreferredSize();
		btnFacturaA.setPreferredSize(locDimension);
		
		btnFacturaC.setText("C");
		btnFacturaC.setFont(FUENTE_FACTURAS);
		btnFacturaC.setBackground(COLOR_FONDO);
		btnFacturaC.setForeground(COLOR_FUENTE_FACTURAS);
		btnFacturaC.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_AGREGAR));
		btnFacturaC.setPreferredSize(locDimension);
		
		btnEliminar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_ELIMINAR));
		btnEliminar.setBackground(COLOR_FONDO);
		btnEliminar.setPreferredSize(locDimension);
		
		btnReportes.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_REPORTES));
		btnReportes.setBackground(COLOR_FONDO);
		btnReportes.setPreferredSize(locDimension);
		
		this.add(btnFacturaA);
		this.add(btnFacturaC);
		this.add(btnEliminar);
		this.add(btnReportes);
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setFloatable(false);
		
	}

	
	public JButton getBtnFacturaA() {
		return btnFacturaA;
	}

	public JButton getBtnFacturaC() {
		return btnFacturaC;
	}

	public JButton getBtnEliminar() {
		return btnEliminar;
	}

	public JButton getBtnReportes() {
		return btnReportes;
	}
}
