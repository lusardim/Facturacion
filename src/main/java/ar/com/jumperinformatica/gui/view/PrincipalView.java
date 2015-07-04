package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class PrincipalView extends JFrame {

		/**
	 * 
	 */
	private static final long serialVersionUID = -8108080175676403808L;
		public static final Color COLOR_FONDO = Color.WHITE;
		public static final Color PANEL_PRINCIPAL_FRENTE = Color.WHITE;
		
		public static final Color MENU_BAR_FONDO = new Color(211, 211, 211);
		public static final Color MENU_BAR_FRENTE = new Color(0, 90, 122);
		
		public static final Font MENU_BAR_FONT = Font.getFont("Tahoma-Bold-11");
	
		
		private JMenuItem miFacturacionEgresoIndividual;
	    private JMenuItem miFacturacionEgresoGeneral;
	    private JMenuItem miFacturacionIngresoGeneral;
	    private JMenuItem miUsuarios;
	    private JMenuItem miBackup;
	    private JMenuItem miAyuda;
	    private JMenuItem miAcercaDe;
	    private javax.swing.JLabel lblLogoEmpresa;
	    private javax.swing.JMenuBar mbBarraMenus;
	    private JMenuItem miFacturaComitentes;
	    private JMenu mnuComitentes;
	    private JMenu mnuFacturaEgreso;
	    private JMenu mnuFacturaIngreso;
	    private JMenu mnuReportes;
	    private JMenu mnuUtilidades;
	    private javax.swing.JPanel pnlPrincipal;
                   
	
	    public PrincipalView() {
	        initComponents();
	        this.setState(JFrame.MAXIMIZED_BOTH);
	        //TODO PRÓXIMA VERSION!!!
	        this.miAyuda.setVisible(false);
	      //  this.setLocationRelativeTo(null);
	    }
          
	    private void initComponents() {
	    	this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.LOGO_JANO));
	        pnlPrincipal = new javax.swing.JPanel();
	        lblLogoEmpresa = new javax.swing.JLabel();
	        mbBarraMenus = new javax.swing.JMenuBar();
	        
	        mnuComitentes = new JMenu();
	        
	        
	        mnuFacturaIngreso = new JMenu();
	        mnuFacturaEgreso = new JMenu();
	        mnuReportes = new JMenu();
	        miFacturaComitentes = new JMenuItem();
	        miFacturacionEgresoIndividual = new JMenuItem();
	        miFacturacionEgresoGeneral = new JMenuItem();
	        mnuUtilidades = new JMenu();
	        miUsuarios = new JMenuItem();
	        miBackup = new JMenuItem();
	        miAyuda = new JMenuItem();
	        miAcercaDe = new JMenuItem();
	        miFacturacionIngresoGeneral = new JMenuItem();
	        
	        this.setTitle("Sistema de Facturación v1.07");

	        pnlPrincipal.setBackground(COLOR_FONDO);
	        pnlPrincipal.setForeground(PANEL_PRINCIPAL_FRENTE);

	        lblLogoEmpresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        lblLogoEmpresa.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_LOGO)); 

	        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
	        pnlPrincipal.setLayout(pnlPrincipalLayout);
	        pnlPrincipalLayout.setHorizontalGroup(
	            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
	                .addGap(211, 211, 211)
	                .addComponent(lblLogoEmpresa, javax.swing.GroupLayout.DEFAULT_SIZE, 814, Short.MAX_VALUE)
	                .addGap(61, 61, 61))
	        );
	        pnlPrincipalLayout.setVerticalGroup(
	            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlPrincipalLayout.createSequentialGroup()
	                .addGap(238, 238, 238)
	                .addComponent(lblLogoEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, 257, Short.MAX_VALUE)
	                .addGap(213, 213, 213))
	        );

	        mbBarraMenus.setBackground(MENU_BAR_FONDO); 
	        mbBarraMenus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

	        mnuComitentes.setBackground(MENU_BAR_FONDO);
	        mnuComitentes.setForeground(MENU_BAR_FRENTE); 
	        mnuComitentes.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_COMITENTES));
	        mnuComitentes.setText("Comitentes"); 
	        mnuComitentes.setFont(MENU_BAR_FONT); 
	        mbBarraMenus.add(mnuComitentes);

	        mnuFacturaIngreso.setBackground(MENU_BAR_FONDO);
	        mnuFacturaIngreso.setForeground(MENU_BAR_FRENTE); 
	        mnuFacturaIngreso.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_FACTURA_INGRESO)); 
	        mnuFacturaIngreso.setText("Facturación Ingreso"); 
	        mnuFacturaIngreso.setFont(MENU_BAR_FONT); 
	        mbBarraMenus.add(mnuFacturaIngreso);

	        mnuFacturaEgreso.setBackground(MENU_BAR_FONDO); 
	        mnuFacturaEgreso.setForeground(MENU_BAR_FRENTE); 
	        mnuFacturaEgreso.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_FACTURA_EGRESO)); 
	        mnuFacturaEgreso.setText("Facturación Egreso"); 
	        mnuFacturaEgreso.setFont(MENU_BAR_FONT); 
	        mbBarraMenus.add(mnuFacturaEgreso);

	        mnuReportes.setBackground(MENU_BAR_FONDO); 
	        mnuReportes.setForeground(MENU_BAR_FRENTE);
	        mnuReportes.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_REPORTES)); 
	        mnuReportes.setText("Reportes"); 
	        mnuReportes.setFont(MENU_BAR_FONT); 

	        miFacturaComitentes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
	        miFacturaComitentes.setFont(MENU_BAR_FONT); 
	        miFacturaComitentes.setForeground(MENU_BAR_FRENTE); 
	        miFacturaComitentes.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_REPORTES_FACTURA_COMITENTES));
	        miFacturaComitentes.setText("Facturación de Comitente"); 
	        mnuReportes.add(miFacturaComitentes);
	        
	        miFacturacionIngresoGeneral.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
	        miFacturacionIngresoGeneral.setFont(MENU_BAR_FONT); 
	        miFacturacionIngresoGeneral.setForeground(MENU_BAR_FRENTE); 
	        miFacturacionIngresoGeneral.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_REPORTES_FACTURA_INGRESO_INDIVIDUAL));
	        miFacturacionIngresoGeneral.setText("Facturación Ingreso General"); 
	        mnuReportes.add(miFacturacionIngresoGeneral);

	        miFacturacionEgresoIndividual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
	        miFacturacionEgresoIndividual.setFont(MENU_BAR_FONT); 
	        miFacturacionEgresoIndividual.setForeground(MENU_BAR_FRENTE); 
	        miFacturacionEgresoIndividual.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_REPORTES_FACTURACION_EGRESO_INDIVIDUAL)); 
	        miFacturacionEgresoIndividual.setText("Facturación Egreso Individual"); 
	        mnuReportes.add(miFacturacionEgresoIndividual);

	        miFacturacionEgresoGeneral.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
	        miFacturacionEgresoGeneral.setFont(MENU_BAR_FONT); 
	        miFacturacionEgresoGeneral.setForeground(MENU_BAR_FRENTE); 
	        miFacturacionEgresoGeneral.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_REPORTES_FACTURACION_EGRESO_GENERAL)); 
	        miFacturacionEgresoGeneral.setText("Facturación Egreso General"); 
	        mnuReportes.add(miFacturacionEgresoGeneral);

	        
	        mbBarraMenus.add(mnuReportes);

	        mnuUtilidades.setBackground(MENU_BAR_FONDO); 
	        mnuUtilidades.setForeground(MENU_BAR_FRENTE);
	        mnuUtilidades.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_UTILIDADES)); 
	        mnuUtilidades.setText("Utilidades"); 
	        mnuUtilidades.setFont(MENU_BAR_FONT); 
	        

	        miUsuarios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
	        miUsuarios.setFont(MENU_BAR_FONT); 
	        miUsuarios.setForeground(MENU_BAR_FRENTE); 
	        miUsuarios.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_UTILIDADES_USUARIOS)); 
	        miUsuarios.setText("Usuarios"); 
	        mnuUtilidades.add(miUsuarios);

	        miBackup.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
	        miBackup.setFont(MENU_BAR_FONT); // NOI18N
	        miBackup.setForeground(MENU_BAR_FRENTE); // NOI18N
	        miBackup.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_UTILIDADES_BACKUP)); 
	        miBackup.setText("Backup"); 
	        mnuUtilidades.add(miBackup);

	        miAyuda.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
	        miAyuda.setFont(MENU_BAR_FONT); 
	        miAyuda.setForeground(MENU_BAR_FRENTE); 
	        miAyuda.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_UTILIDADES_AYUDA)); 
	        miAyuda.setText("Ayuda"); 
	        mnuUtilidades.add(miAyuda);

	        miAcercaDe.setFont(MENU_BAR_FONT); 
	        miAcercaDe.setForeground(MENU_BAR_FRENTE); 
	        miAcercaDe.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.PRINCIPAL_MENU_UTILIDADES_ACERCADE)); 
	        miAcercaDe.setText("Acerca de...");
	        mnuUtilidades.add(miAcercaDe);

	        mbBarraMenus.add(mnuUtilidades);

	        setJMenuBar(mbBarraMenus);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	        );

	        pack();
	    }

		public JMenu getMnuComitentes() {
			return mnuComitentes;
		}

		public JMenuItem getMiAcercaDe() {
			return miAcercaDe;
		}

		public JMenuItem getMiBackup() {
			return miBackup;
		}

		public JMenuItem getMiUsuarios() {
			return miUsuarios;
		}

		public JMenu getMnuFacturaEgreso() {
			return mnuFacturaEgreso;
		}

		public JMenu getMnuFacturaIngreso() {
			return mnuFacturaIngreso;
		}

		public JMenuItem getMiFacturacionEgresoIndividual() {
			return miFacturacionEgresoIndividual;
		}

		public JMenuItem getMiFacturacionEgresoGeneral() {
			return miFacturacionEgresoGeneral;
		}

		public JMenuItem getMiFacturaComitentes() {
			return miFacturaComitentes;
		}

		public JMenuItem getMiFacturacionIngresoGeneral() {
			return miFacturacionIngresoGeneral;
		}             

}
