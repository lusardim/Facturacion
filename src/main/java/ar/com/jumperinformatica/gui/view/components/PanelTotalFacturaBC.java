package ar.com.jumperinformatica.gui.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelTotalFacturaBC extends JPanel{
	
	private static final long serialVersionUID = -1155069084351224824L;
	private static final Color COLOR_FRENTE_LABELS = new Color(0, 90, 122);
	private static final Font FUENTE_LABELS = Font.decode("Tahoma 11 12 11 12 Bold");

	private JLabel lblTotal;
	private JTextField tfTotal;
	

	/** Creates new form pnlTotalFacturaB */
	public PanelTotalFacturaBC() {
		super();
		initComponents();
	}
	
	private void initComponents() {
		tfTotal = new JTextField();
		lblTotal = new JLabel();

		this.setBackground(Color.WHITE); 
		this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 0, 11), new Color(0, 90, 122))); // NOI18N

		lblTotal.setFont(FUENTE_LABELS); // NOI18N
		lblTotal.setForeground(COLOR_FRENTE_LABELS); // NOI18N
		lblTotal.setText("TOTAL $"); // NOI18N
		lblTotal.setName("lblTotal"); // NOI18N

		javax.swing.GroupLayout pnlTotalLayout = new javax.swing.GroupLayout(this);
		this.setLayout(pnlTotalLayout);
		pnlTotalLayout.setHorizontalGroup(
				pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnlTotalLayout.createSequentialGroup()
						.addGroup(pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(pnlTotalLayout.createSequentialGroup()
										.addContainerGap()
										.addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGroup(pnlTotalLayout.createSequentialGroup()
												.addGap(48, 48, 48)
												.addComponent(lblTotal)))
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnlTotalLayout.setVerticalGroup(
				pnlTotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalLayout.createSequentialGroup()
						.addComponent(lblTotal)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
		);
	}

	public JTextField getTfTotal() {
		return tfTotal;
	}

	
}
