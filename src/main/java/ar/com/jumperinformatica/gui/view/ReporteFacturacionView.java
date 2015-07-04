package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.model.PeriodoComboBoxModel;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class ReporteFacturacionView extends JDialog {

	private static final Font FUENTE = Font.decode("Tahoma 11 Bold");
	private static final Color COLOR = new Color(0, 90, 122);


	private static final long serialVersionUID = -8042478890891779952L;
	private javax.swing.JButton btnAceptar;
	private javax.swing.JButton btnCancelar;
	private javax.swing.JComboBox cboTipoFactura;
	private javax.swing.JComboBox cboPeriodo;
	private com.toedter.calendar.JDateChooser dcDesde;
	private com.toedter.calendar.JDateChooser dcHasta;
	private javax.swing.JLabel lblDesde;
	private javax.swing.JLabel lblHasta;
	private javax.swing.JLabel lblPeriodo;
	private javax.swing.JLabel lblTipoFactura;
	private javax.swing.JPanel pnlPrincipal;

	public ReporteFacturacionView(java.awt.Frame parent) {
		super(parent);
		this.init();
		this.setLocationRelativeTo(parent);
	}

	public ReporteFacturacionView(java.awt.Dialog parent) {
		super(parent);
		this.init();
		this.setLocationRelativeTo(parent);
	}

	private void init() {
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		initComponents();
		this.setModal(true);
		this.setResizable(false);
		this.cboPeriodo.setModel(new PeriodoComboBoxModel());
	}

	private void initComponents() {
		pnlPrincipal = new javax.swing.JPanel();
		lblPeriodo = new javax.swing.JLabel();
		lblDesde = new javax.swing.JLabel();
		lblHasta = new javax.swing.JLabel();
		btnAceptar = new javax.swing.JButton();
		btnCancelar = new javax.swing.JButton();
		cboPeriodo = new javax.swing.JComboBox();
		dcDesde = new com.toedter.calendar.JDateChooser();
		lblTipoFactura = new javax.swing.JLabel();
		cboTipoFactura = new javax.swing.JComboBox();
		dcHasta = new com.toedter.calendar.JDateChooser();



		pnlPrincipal.setBackground(Color.WHITE); 
		
		lblPeriodo.setFont(FUENTE); // NOI18N
		lblPeriodo.setForeground(COLOR); // NOI18N
		lblPeriodo.setText("Período"); // NOI18N

		lblDesde.setFont(FUENTE); // NOI18N
		lblDesde.setForeground(COLOR); // NOI18N
		lblDesde.setText("Desde"); // NOI18N

		lblHasta.setFont(FUENTE); // NOI18N
		lblHasta.setForeground(COLOR); // NOI18N
		lblHasta.setText("Hasta"); // NOI18N

		btnAceptar.setFont(FUENTE); 
		btnAceptar.setForeground(COLOR); 
		btnAceptar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_ACEPTAR)); 
		btnAceptar.setText("Aceptar"); 

		btnCancelar.setFont(FUENTE); 
		btnCancelar.setForeground(COLOR); 
		btnCancelar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_CANCELAR)); 
		btnCancelar.setText("Cancelar"); 

		lblTipoFactura.setFont(FUENTE); // NOI18N
		lblTipoFactura.setForeground(COLOR); // NOI18N
		lblTipoFactura.setText("Tipo Factura"); // NOI18N

		//FIXME VER ESTO
		cboTipoFactura.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura A", "Factura B", "Factura A y B" }));
		cboTipoFactura.setName("cbdTipoFactura"); // NOI18N

		dcHasta.setName("dcHasta"); // NOI18N

		javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
		pnlPrincipal.setLayout(pnlPrincipalLayout);
		pnlPrincipalLayout.setHorizontalGroup(
				pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlPrincipalLayout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(pnlPrincipalLayout.createSequentialGroup()
										.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
												.addComponent(lblTipoFactura)
												.addComponent(lblDesde))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(cboTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(31, 31, 31)
														.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addGroup(pnlPrincipalLayout.createSequentialGroup()
																		.addComponent(lblPeriodo)
																		.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(cboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addGroup(pnlPrincipalLayout.createSequentialGroup()
																				.addGap(8, 8, 8)
																				.addComponent(lblHasta)
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																				.addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
																				.addContainerGap())
		);
		pnlPrincipalLayout.setVerticalGroup(
				pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlPrincipalLayout.createSequentialGroup()
						.addGap(22, 22, 22)
						.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblTipoFactura)
								.addComponent(cboTipoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPeriodo)
								.addComponent(cboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
												.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(lblDesde)
														.addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(dcHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
														.addComponent(lblHasta))
														.addGap(29, 29, 29)
														.addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addContainerGap(31, Short.MAX_VALUE))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
		);

		pack();
	}// </editor-fold>

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				ReporteFacturacionView dialog = new ReporteFacturacionView(new javax.swing.JFrame());
				dialog.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				dialog.setVisible(true);
			}
		});
	}

	public javax.swing.JButton getBtnAceptar() {
		return btnAceptar;
	}

	public javax.swing.JButton getBtnCancelar() {
		return btnCancelar;
	}

	public javax.swing.JComboBox getCboPeriodo() {
		return cboPeriodo;
	}

	public com.toedter.calendar.JDateChooser getDcDesde() {
		return dcDesde;
	}

	public com.toedter.calendar.JDateChooser getDcHasta() {
		return dcHasta;
	}

	public javax.swing.JComboBox getCboTipoFactura() {
		return cboTipoFactura;
	}



}
