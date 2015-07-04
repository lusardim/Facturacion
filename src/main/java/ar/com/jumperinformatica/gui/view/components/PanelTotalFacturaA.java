package ar.com.jumperinformatica.gui.view.components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class PanelTotalFacturaA extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -498012563329892957L;
	private static final Color COLOR_FRENTE_LABELS = new Color(0, 90, 122);
	private static final Font FUENTE_LABELS = Font.decode("Tahoma 11 12 11 12 Bold");
	
    private JLabel lblIva;
    private JLabel lblSubTotal;
    private JLabel lblTotal;
    private JTextField tfSubtotal;
    private JTextField tfTotal;
    private JTextField tfTotalIva;
	
	public PanelTotalFacturaA() {
        initComponents();
    }

    private void initComponents() {

        tfSubtotal = new JTextField();
        tfTotalIva = new JTextField();
        tfTotal = new JTextField();
        lblSubTotal = new JLabel();
        lblIva = new JLabel();
        lblTotal = new JLabel();


        this.setBackground(Color.WHITE); 
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", 
        			javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
        			javax.swing.border.TitledBorder.DEFAULT_POSITION, 
        			new Font("DejaVu Sans", 0, 13),
        			new Color(0, 90, 122)));
    
        lblSubTotal.setFont(FUENTE_LABELS); // NOI18N
        lblSubTotal.setForeground(COLOR_FRENTE_LABELS); // NOI18N
        lblSubTotal.setText("Subtotal"); // NOI18N
        lblSubTotal.setAlignmentX(5.0F);
        lblSubTotal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        
        lblIva.setFont(FUENTE_LABELS); 
        lblIva.setForeground(COLOR_FRENTE_LABELS); 
        lblIva.setText("IVA Insc. 21%"); 

        lblTotal.setFont(FUENTE_LABELS); // NOI18N
        lblTotal.setForeground(COLOR_FRENTE_LABELS); // NOI18N
        lblTotal.setText("TOTAL"); // NOI18N
        lblTotal.setName("lblTotal"); // NOI18N

        javax.swing.GroupLayout pnlTotalesLayout = new javax.swing.GroupLayout(this);
        this.setLayout(pnlTotalesLayout);
        pnlTotalesLayout.setHorizontalGroup(
            pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalesLayout.createSequentialGroup()
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlTotalesLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(lblSubTotal)))
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTotalesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlTotalesLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblIva)
                        .addGap(49, 49, 49)
                        .addComponent(lblTotal)))
                .addGap(24, 24, 24))
        );
        pnlTotalesLayout.setVerticalGroup(
            pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTotalesLayout.createSequentialGroup()
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSubTotal)
                    .addComponent(lblIva)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlTotalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTotalIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }

	public JTextField getTfSubtotal() {
		return tfSubtotal;
	}

	public JTextField getTfTotal() {
		return tfTotal;
	}

	public JTextField getTfTotalIva() {
		return tfTotalIva;
	}

}