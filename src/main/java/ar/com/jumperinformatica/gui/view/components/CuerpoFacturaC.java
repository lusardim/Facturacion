package ar.com.jumperinformatica.gui.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JPanel;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

public class CuerpoFacturaC extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5106442597900644085L;
	private static final Font FUENTE_LABELS = Font.decode("Tahoma 11 11 12 Bold");
	private static final Color COLOR_FRENTE_LABELS = new Color(0,90,122);


	private javax.swing.JButton btnBuscarComitente;
	private javax.swing.JComboBox cboIva;
	private javax.swing.JLabel lblCondicionVenta;
	private javax.swing.JLabel lblDomicilio;
	private javax.swing.JLabel lblIva;
	private javax.swing.JLabel lblNombre;
	private javax.swing.JLabel lblObra;
	private javax.swing.JLabel lblRemito;
	private javax.swing.JTextField tfCondicionVenta;
	private javax.swing.JTextField tfDomicilio;
	private javax.swing.JTextField tfNombre;
	private javax.swing.JTextField tfObra;
	private javax.swing.JTextField tfRemito;


	public CuerpoFacturaC() {
		super();
		initComponents();
	}

	/** This method is called from within the constructor to
	 * initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is
	 * always regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		tfNombre = new javax.swing.JTextField();
		cboIva = new javax.swing.JComboBox();
		tfDomicilio = new javax.swing.JTextField();
		btnBuscarComitente = new javax.swing.JButton();
		tfObra = new javax.swing.JTextField();
		tfCondicionVenta = new javax.swing.JTextField();
		lblNombre = new javax.swing.JLabel();
		lblDomicilio = new javax.swing.JLabel();
		lblIva = new javax.swing.JLabel();
		lblCondicionVenta = new javax.swing.JLabel();
		tfRemito = new javax.swing.JTextField();
		lblRemito = new javax.swing.JLabel();
		lblObra = new javax.swing.JLabel();


		this.setBackground(Color.WHITE); 
		this.setBorder(
				javax.swing.BorderFactory.createTitledBorder(null, "", 
						javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
						javax.swing.border.TitledBorder.DEFAULT_POSITION, 
						new Font("Tahoma", 1, 11),
						new Color(0, 90, 122)));

		btnBuscarComitente.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.FACTURA_BUSCAR_COMITENTE));

		lblNombre.setFont(FUENTE_LABELS); // NOI18N
		lblNombre.setForeground(COLOR_FRENTE_LABELS); // NOI18N
		lblNombre.setText("Señor (es)"); // NOI18N

		lblDomicilio.setFont(FUENTE_LABELS); // NOI18N
		lblDomicilio.setForeground(COLOR_FRENTE_LABELS); // NOI18N
		lblDomicilio.setText("Domicilio"); // NOI18N

		lblIva.setFont(FUENTE_LABELS); // NOI18N
		lblIva.setForeground(COLOR_FRENTE_LABELS); // NOI18N
		lblIva.setText("I.V.A."); // NOI18N

		lblCondicionVenta.setFont(FUENTE_LABELS); // NOI18N
		lblCondicionVenta.setForeground(COLOR_FRENTE_LABELS); // NOI18N
		lblCondicionVenta.setText("Condic. Vta."); 

		lblRemito.setFont(FUENTE_LABELS);
		lblRemito.setForeground(COLOR_FRENTE_LABELS); 
		lblRemito.setText("Remito Nº"); 

		lblObra.setFont(FUENTE_LABELS); 
		lblObra.setForeground(COLOR_FRENTE_LABELS); 
		lblObra.setText("Obra"); 

		javax.swing.GroupLayout pnlCuerpoLayout = new javax.swing.GroupLayout(this);
		this.setLayout(pnlCuerpoLayout);
		pnlCuerpoLayout.setHorizontalGroup(
				pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlCuerpoLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addComponent(lblNombre)
								.addComponent(lblDomicilio)
								.addComponent(lblIva)
								.addComponent(lblCondicionVenta))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(pnlCuerpoLayout.createSequentialGroup()
												.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(cboIva, javax.swing.GroupLayout.Alignment.LEADING, 0, 282, Short.MAX_VALUE)
														.addComponent(tfCondicionVenta, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
														.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
														.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																.addComponent(lblObra)
																.addComponent(lblRemito))
																.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																		.addGroup(pnlCuerpoLayout.createSequentialGroup()
																				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																				.addComponent(tfRemito, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
																				.addComponent(tfObra, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
																				.addGroup(pnlCuerpoLayout.createSequentialGroup()
																						.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
																								.addComponent(tfDomicilio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
																								.addComponent(tfNombre, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE))
																								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																								.addComponent(btnBuscarComitente, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
																								.addGap(1, 1, 1)))
																								.addGap(20, 20, 20))
		);
		pnlCuerpoLayout.setVerticalGroup(
				pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlCuerpoLayout.createSequentialGroup()
						.addContainerGap()
						.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlCuerpoLayout.createSequentialGroup()
										.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(lblNombre)
												.addComponent(tfNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(lblDomicilio)
														.addComponent(tfDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(btnBuscarComitente, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGap(9, 9, 9)
														.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																.addComponent(lblIva)
																.addComponent(cboIva, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
																.addComponent(lblRemito)
																.addComponent(tfRemito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																.addGap(12, 12, 12)
																.addGroup(pnlCuerpoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(tfCondicionVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblCondicionVenta)
																		.addComponent(lblObra)
																		.addComponent(tfObra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addContainerGap(16, Short.MAX_VALUE))
		);
	}

	public javax.swing.JComboBox getCboIva() {
		return cboIva;
	}

	public void setCboIva(javax.swing.JComboBox cboIva) {
		this.cboIva = cboIva;
	}

	public javax.swing.JTextField getTfDomicilio() {
		return tfDomicilio;
	}

	public void setTfDomicilio(javax.swing.JTextField tfDomicilio) {
		this.tfDomicilio = tfDomicilio;
	}

	public javax.swing.JTextField getTfNombre() {
		return tfNombre;
	}

	public void setTfNombre(javax.swing.JTextField tfNombre) {
		this.tfNombre = tfNombre;
	}

	public javax.swing.JTextField getTfObra() {
		return tfObra;
	}

	public void setTfObra(javax.swing.JTextField tfObra) {
		this.tfObra = tfObra;
	}

	public javax.swing.JTextField getTfRemito() {
		return tfRemito;
	}

	public void setTfRemito(javax.swing.JTextField tfRemito) {
		this.tfRemito = tfRemito;
	}

	public javax.swing.JButton getBtnBuscarComitente() {
		return btnBuscarComitente;
	}

	public javax.swing.JTextField getTfCondicionVenta() {
		return tfCondicionVenta;
	}

}
