package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JDialog;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class AcercaDeView extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7523532227773426747L;
	private static final Color COLOR_TITULO_PANEL_INTERNO = new Color(0, 90, 122);
	private static final Color FRENTE_LABELS_NOMBRE = new Color(0, 90, 122);
	private static final Font FUENTE_LABELS_NOMBRE = Font.decode("Tahoma 11-Bold-11");
	private static final Font FUENTE_TITULO = Font.decode("Tahoma-Bold-12");
	private static final Font FUENTE_SUBTITULO = Font.decode("Tahoma 12-Bold-10");

	private javax.swing.JLabel lblDaiana;
	private javax.swing.JLabel lblMal;
	private javax.swing.JLabel lblMail;
	private javax.swing.JLabel lblAnio;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JLabel lblLogo;
	private javax.swing.JLabel lblTitulo;


	public AcercaDeView(java.awt.Frame parent) {
		super(parent);
		initComponents();
		this.setModal(true);
		this.setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
	}


	private void initComponents() {

		jPanel2 = new javax.swing.JPanel();
		jPanel1 = new javax.swing.JPanel();
		lblLogo = new javax.swing.JLabel();
		lblTitulo = new javax.swing.JLabel();
		lblDaiana = new javax.swing.JLabel();
		lblMal = new javax.swing.JLabel();
		lblMail = new javax.swing.JLabel();
		lblAnio = new javax.swing.JLabel();

		jPanel2.setBackground(Color.WHITE); 


		jPanel1.setBackground(Color.WHITE); 
		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", 
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
				javax.swing.border.TitledBorder.DEFAULT_POSITION, 
				new Font("Tahoma", 0, 11),
				COLOR_TITULO_PANEL_INTERNO   )); 


		lblLogo.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.ACERCADE_LOGO)); 

		lblTitulo.setFont(FUENTE_TITULO);
		lblTitulo.setForeground(FRENTE_LABELS_NOMBRE); 
		lblTitulo.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.ACERCADE_TITULO)); 

		lblDaiana.setFont(FUENTE_LABELS_NOMBRE);
		lblDaiana.setForeground(FRENTE_LABELS_NOMBRE); 
		lblDaiana.setText("Daiana M. Ernst"); 

		lblMal.setFont(FUENTE_LABELS_NOMBRE); 
		lblMal.setForeground(FRENTE_LABELS_NOMBRE); 
		lblMal.setText("Mariano A. Lusardi");

		lblMail.setFont(FUENTE_SUBTITULO);
		lblMail.setForeground(FRENTE_LABELS_NOMBRE); 
		lblMail.setText("jumper176@gmail.com"); 


		lblAnio.setFont(FUENTE_SUBTITULO); 
		lblAnio.setForeground(FRENTE_LABELS_NOMBRE); 
		lblAnio.setText("2008"); 

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
						.addGap(16, 16, 16)
						.addComponent(lblLogo)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblDaiana)
												.addComponent(lblMal)))
												.addGroup(jPanel1Layout.createSequentialGroup()
														.addGap(38, 38, 38)
														.addComponent(lblAnio))
														.addGroup(jPanel1Layout.createSequentialGroup()
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(lblMail)))
																.addGap(552, 552, 552))
																.addGroup(jPanel1Layout.createSequentialGroup()
																		.addGap(19, 19, 19)
																		.addComponent(lblTitulo)
																		.addContainerGap(502, Short.MAX_VALUE))
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(11, 11, 11)
						.addComponent(lblTitulo)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
										.addGap(18, 18, 18)
										.addComponent(lblDaiana)
										.addGap(2, 2, 2)
										.addComponent(lblMal)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(lblMail)
										.addGap(4, 4, 4)
										.addComponent(lblAnio))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(lblLogo)
												.addGap(18, 18, 18)))
												.addContainerGap())
		);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
		);

		pack();
		this.setTitle("Acerca de Jano. Sistema de Facturación");
	}


}
