package ar.com.jumperinformatica.gui.view.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.model.BusquedaUsuarioModel;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class BarraHerramientasABMPanel extends JToolBar{
	
	private final Color COLOR_FONDO = new Color(211,211,211); 
	/**
	 * 
	 */
	private static final long serialVersionUID = -5352193275864395473L;
	private JButton botonAgregar;
	private JButton botonModificar;
	private JButton botonEliminar;
	private Dimension dimensionDefectoBotones;
	
	public BarraHerramientasABMPanel() {
		super();
		this.initComponents();
	}


	private void initComponents() {
		botonAgregar = new JButton();
		botonModificar = new JButton();
		botonEliminar = new JButton();
		dimensionDefectoBotones =  new Dimension(37, 29);
		
		botonAgregar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_AGREGAR));
		botonAgregar.setBackground(COLOR_FONDO);
		
		botonModificar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_MODIFICAR));
		botonModificar.setBackground(COLOR_FONDO);
		botonEliminar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BARRA_ABM_ELIMINAR));
		botonEliminar.setBackground(COLOR_FONDO);
		
		botonAgregar.setPreferredSize(dimensionDefectoBotones);
		botonEliminar.setPreferredSize(dimensionDefectoBotones);
		botonModificar.setPreferredSize(dimensionDefectoBotones);
		
		this.setBackground(COLOR_FONDO);
		this.add(botonAgregar);
		this.add(botonModificar);
		this.add(botonEliminar);
		
		this.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		this.setFloatable(false);
	}
	
	
	public static void main(String[] args) throws LogicaException{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JDialog locDialog = new JDialog();
		locDialog.setSize(800, 600);
		
				
		locDialog.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			
			}
		});
		locDialog.setLayout(new BorderLayout());
		locDialog.add(new BarraHerramientasABMPanel(),BorderLayout.NORTH);
		
		BusquedaPanel locBusquedaPanel = new BusquedaPanel();
		locBusquedaPanel.setModel(new BusquedaUsuarioModel());
		
		locDialog.add(locBusquedaPanel,BorderLayout.CENTER);

		locDialog.setVisible(true);
	}


	public JButton getBotonAgregar() {
		return botonAgregar;
	}


	public JButton getBotonModificar() {
		return botonModificar;
	}


	public JButton getBotonEliminar() {
		return botonEliminar;
	}


	public void addButton(JButton pBoton, Recursos pIcono) {
		pBoton.setBackground(COLOR_FONDO);
		pBoton.setPreferredSize(dimensionDefectoBotones);
		pBoton.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(pIcono));
		this.add(pBoton);
		
	}
}
