package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class AMComitenteView extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2764865720154849896L;
	
	private static final Font FUENTE_LABELS = Font.decode("Tahoma-Bold-11");
	private static final Color COLOR_LABELS = new Color(0, 90, 122);
	
	private static final Font FUENTE_BOTONES = Font.decode("Tahoma-Bold-11");
	private static final Color COLOR_FRENTE_BOTONES = new Color(0, 90, 122);
	
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JLabel lblNombre;
	private javax.swing.JLabel lblCuit;
	private javax.swing.JLabel lblDomicilio;
	private javax.swing.JPanel pnlPrincipal;
	private javax.swing.JTextField tfNombre;
	private JFormattedTextField tfCuit;
	private javax.swing.JTextField tfDomicilio;


	public AMComitenteView(Dialog parent) throws ParseException {
		super(parent);
		initComponents();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setModal(true);
		this.setLocationRelativeTo(parent);
	}

	
	private void initComponents() throws ParseException {
		pnlPrincipal = new javax.swing.JPanel();
		lblNombre = new javax.swing.JLabel();
		lblCuit = new javax.swing.JLabel();
		lblDomicilio = new javax.swing.JLabel();
		tfNombre = new javax.swing.JTextField();
		tfDomicilio = new javax.swing.JTextField();
		btnAceptar = new javax.swing.JButton();
		btnCancelar = new javax.swing.JButton();
	
		tfCuit = new JFormattedTextField(new MaskFormatter("##'-########'-#"));

		lblNombre.setFont(FUENTE_LABELS); 
		lblNombre.setForeground(COLOR_LABELS); 
		lblNombre.setText("Nombre"); 

		lblCuit.setFont(FUENTE_LABELS); 
		lblCuit.setForeground(COLOR_LABELS); 
		lblCuit.setText("CUIT"); 

		lblDomicilio.setFont(FUENTE_LABELS); 
		lblDomicilio.setForeground(COLOR_LABELS); 
		lblDomicilio.setText("Domicilio"); 


		btnAceptar.setBackground(Color.WHITE);
		btnAceptar.setForeground(COLOR_FRENTE_BOTONES);
		btnAceptar.setFont(FUENTE_BOTONES); 
		btnAceptar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_ACEPTAR)); 
		btnAceptar.setText("Aceptar"); 

		btnCancelar.setFont(FUENTE_BOTONES); 
		btnCancelar.setForeground(COLOR_FRENTE_BOTONES); 
		btnCancelar.setBackground(Color.WHITE);
		btnCancelar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_CANCELAR)); 
		btnCancelar.setText("Cancelar"); 
		

		 javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnlPrincipal);
	        pnlPrincipal.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGap(39, 39, 39)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addComponent(lblCuit)
	                    .addComponent(lblNombre)
	                    .addComponent(lblDomicilio))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                    .addGroup(jPanel1Layout.createSequentialGroup()
	                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
	                        .addGap(43, 43, 43)
	                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
	                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                        .addComponent(tfNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
	                        .addComponent(tfCuit, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)
	                        .addComponent(tfDomicilio, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
	                .addGap(30, 30, 30))
	        );
	        jPanel1Layout.setVerticalGroup(
	            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(jPanel1Layout.createSequentialGroup()
	                .addGap(22, 22, 22)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(lblNombre)
	                    .addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(lblCuit)
	                    .addComponent(tfCuit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(lblDomicilio)
	                    .addComponent(tfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addContainerGap(22, Short.MAX_VALUE))
	        );

		
		this.add(pnlPrincipal);
		pack();
		this.setModal(true);
		this.setResizable(false);
		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_COMITENTES));
	}                        

	public javax.swing.JButton getBtnAceptar() {
		return btnAceptar;
	}


	public javax.swing.JButton getBtnCancelar() {
		return btnCancelar;
	}


	public javax.swing.JTextField getTfNombre() {
		return tfNombre;
	}


	public JFormattedTextField getTfCuit() {
		return tfCuit;
	}


	public javax.swing.JTextField getTfDomicilio() {
		return tfDomicilio;
	}



}
