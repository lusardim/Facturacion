package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class BackupView extends JDialog{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4213547379699438787L;
	private static final Font FUENTE = Font.decode("Tahoma 11 Bold");
	private static final Color COLOR_FRENTE_BOTONES = new Color(0, 90, 122);

	private JButton btnRealizarBackup;
	private JButton btnRestaurarBackup;

	public BackupView(Frame parent) {
		super(parent);
		initComponents();
		this.setModal(true);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(parent);
		this.setTitle("Administración de Copias de seguridad");
		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.PRINCIPAL_MENU_UTILIDADES_BACKUP));
	}

	private void initComponents() {

		JPanel pnlPrincipal = new JPanel();
		btnRealizarBackup = new JButton();
		btnRestaurarBackup = new JButton();

		this.setBackground(Color.WHITE);
		pnlPrincipal.setBackground(Color.WHITE);
		btnRealizarBackup.setBackground(Color.WHITE); 
		btnRealizarBackup.setFont(FUENTE); 
		btnRealizarBackup.setForeground(COLOR_FRENTE_BOTONES); 
		btnRealizarBackup.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BACKUP_HACER_BACKUP)); 
		btnRealizarBackup.setText("Hacer Backup"); 

		btnRestaurarBackup.setBackground(Color.WHITE);
		btnRestaurarBackup.setFont(FUENTE); 
		btnRestaurarBackup.setForeground(COLOR_FRENTE_BOTONES); 
		btnRestaurarBackup.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BACKUP_RESTAURAR_BACKUP)); 
		btnRestaurarBackup.setText("Restaurar Backup"); 

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(pnlPrincipal);
		pnlPrincipal.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(34, 34, 34)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addComponent(btnRestaurarBackup)
										.addContainerGap())
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(btnRealizarBackup, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
												.addGap(34, 34, 34))))
		);
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup()
						.addGap(30, 30, 30)
						.addComponent(btnRealizarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18)
						.addComponent(btnRestaurarBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(38, Short.MAX_VALUE))
		);

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

	public JButton getBtnRealizarBackup() {
		return btnRealizarBackup;
	}

	public JButton getBtnRestaurarBackup() {
		return btnRestaurarBackup;
	}                        

	
	
}
