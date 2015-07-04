package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;

import javax.swing.JDialog;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class AMUsuarioView extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4892668889606955440L;
	private static final Font FUENTE_BOTONES = Font.decode("Tahoma 11 Bold");
	private static final Color FRENTE_BOTONES = new Color(0, 90, 122);

	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JPanel pnlPrincipal;
	private javax.swing.JLabel lblClave;
	private javax.swing.JLabel lblNombre;
	private javax.swing.JLabel lblRepitaClave;
	private javax.swing.JLabel lblUsuario;
	private javax.swing.JPasswordField pfClave;
	private javax.swing.JPasswordField pfRepitaClave;
	private javax.swing.JTextField tfNombre;
	private javax.swing.JTextField tfUsuario;


	public AMUsuarioView(Dialog pPadre) {
		super(pPadre);
		this.setModal(true);
		initComponents();
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(pPadre);
		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_UTILIDADES_USUARIOS));
	}


	private void initComponents() {

		pnlPrincipal = new javax.swing.JPanel();
		lblUsuario = new javax.swing.JLabel();
		lblClave = new javax.swing.JLabel();
		lblNombre = new javax.swing.JLabel();
		tfUsuario = new javax.swing.JTextField();
		tfNombre = new javax.swing.JTextField();
		btnAceptar = new javax.swing.JButton();
		btnCancelar = new javax.swing.JButton();
		pfClave = new javax.swing.JPasswordField();
		pfRepitaClave = new javax.swing.JPasswordField();
		lblRepitaClave = new javax.swing.JLabel();


		pnlPrincipal.setBackground(Color.WHITE); 

		lblUsuario.setFont(FUENTE_BOTONES); 
		lblUsuario.setForeground(FRENTE_BOTONES); 
		lblUsuario.setText("Usuario"); 

		lblClave.setFont(FUENTE_BOTONES); 
		lblClave.setForeground(FRENTE_BOTONES); 
		lblClave.setText("Contraseña"); 

		lblNombre.setFont(FUENTE_BOTONES); 
		lblNombre.setForeground(FRENTE_BOTONES); 
		lblNombre.setText("Nombre"); 


		lblRepitaClave.setFont(FUENTE_BOTONES); 
		lblRepitaClave.setForeground(FRENTE_BOTONES); 
		lblRepitaClave.setText("Repita contraseña"); 

		btnAceptar.setFont(FUENTE_BOTONES); 
		btnAceptar.setForeground(FRENTE_BOTONES); 
		btnAceptar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_ACEPTAR)); 
		btnAceptar.setText("Aceptar"); 

		btnCancelar.setFont(FUENTE_BOTONES); 
		btnCancelar.setForeground(FRENTE_BOTONES); 
		btnCancelar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_CANCELAR));
		btnCancelar.setText("Cancelar"); 


		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(pnlPrincipal);
		pnlPrincipal.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(layout.createSequentialGroup()
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(layout.createSequentialGroup()
														.addGap(20, 20, 20)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(lblClave)
																.addComponent(lblUsuario)
																.addComponent(lblNombre)
																.addComponent(lblRepitaClave))
												)
										)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(pfClave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
												.addComponent(pfRepitaClave, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
												.addComponent(tfNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
												.addComponent(tfUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)))
												.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
														.addGap(48, 48, 48)
														.addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(43, 43, 43)
														.addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(21, 21, 21)))
														.addGap(25, 25, 25))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblUsuario)
								.addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblClave)
										.addComponent(pfClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblRepitaClave, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(pfRepitaClave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblNombre)
														.addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(37, 37, 37)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addContainerGap())
		);

		javax.swing.GroupLayout layoutPrincipal = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layoutPrincipal);
		layoutPrincipal.setHorizontalGroup(
				layoutPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layoutPrincipal.setVerticalGroup(
				layoutPrincipal.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		pack();
	}


	public javax.swing.JButton getBtnAceptar() {
		return btnAceptar;
	}


	public javax.swing.JButton getBtnCancelar() {
		return btnCancelar;
	}


	public javax.swing.JPasswordField getPfClave() {
		return pfClave;
	}


	public javax.swing.JPasswordField getPfRepitaClave() {
		return pfRepitaClave;
	}


	public javax.swing.JTextField getTfNombre() {
		return tfNombre;
	}


	public javax.swing.JTextField getTfUsuario() {
		return tfUsuario;
	}


	
}
