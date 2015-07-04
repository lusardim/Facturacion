package ar.com.jumperinformatica.gui.view.components;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.text.MaskFormatter;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

import com.toedter.calendar.JDateChooser;

public class CabeceraFactura extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 54654650654064065L;

	private static final Font FUENTE_TITULO_FACTURA = Font.decode("Tahoma 36-Bold-36");
	private static final Font FUENTE_LABELS = Font.decode("Tahoma 11-Bold-11");
	private static final Color COLOR_LABELS = new Color(0, 90, 122);
	
    private JDateChooser dcFecha;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblImagenTitulo;
    private javax.swing.JLabel lblNumeroFactura;
    private JFormattedTextField tfNumeroFactura;

    public CabeceraFactura() throws ParseException{
    	super();
    	this.initComponents();
    }
    
    private void initComponents() throws ParseException {

    	tfNumeroFactura = new JFormattedTextField(new MaskFormatter("####-########"));
    	lblImagenTitulo = new javax.swing.JLabel();
    	dcFecha = new com.toedter.calendar.JDateChooser();
    	lblNumeroFactura = new javax.swing.JLabel();
    	lblFecha = new javax.swing.JLabel();

    	
    	this.setBackground(Color.WHITE); // NOI18N
    	this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 90, 122))); // NOI18N
    	this.setName("pnlCabecera"); // NOI18N

    	lblImagenTitulo.setFont(FUENTE_TITULO_FACTURA); 
    	
    	lblImagenTitulo.setForeground(COLOR_LABELS); 
    	lblImagenTitulo.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.FACTURA_TITULO)); 

    	lblNumeroFactura.setFont(FUENTE_LABELS); 
    	lblNumeroFactura.setForeground(COLOR_LABELS); 
    	lblNumeroFactura.setText("Factura Nº");

    	
    	lblFecha.setFont(FUENTE_LABELS); 
    	lblFecha.setForeground(COLOR_LABELS); 
    	lblFecha.setText("Fecha"); 

    	javax.swing.GroupLayout pnlCabeceraLayout = new javax.swing.GroupLayout(this);
    	this.setLayout(pnlCabeceraLayout);
    	pnlCabeceraLayout.setHorizontalGroup(
    			pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    			.addGroup(pnlCabeceraLayout.createSequentialGroup()
    					.addGap(29, 29, 29)
    					.addComponent(lblImagenTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
    					.addGap(58, 58, 58)
    					.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
    							.addComponent(lblNumeroFactura)
    							.addComponent(lblFecha)
    							)
    							.addGap(18, 18, 18)
    							//.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    							.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    									.addGroup(pnlCabeceraLayout.createSequentialGroup()
    											.addGap(211, 211, 211)
    											.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)))    											    											.addGroup(pnlCabeceraLayout.createSequentialGroup()
    													.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    													.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
    																	.addComponent(dcFecha, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
    																	.addComponent(tfNumeroFactura, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE))
    																	.addGap(23, 23, 23)))
    																	.addGap(64, 64, 64))
    	);
    	pnlCabeceraLayout.setVerticalGroup(
    			pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    			.addGroup(pnlCabeceraLayout.createSequentialGroup()
    					.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
    							.addComponent(lblImagenTitulo, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
    							.addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlCabeceraLayout.createSequentialGroup()
    									.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
    											.addComponent(tfNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
    											.addComponent(lblNumeroFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
    											.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
    											.addGroup(pnlCabeceraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
    													.addComponent(lblFecha)
    													.addComponent(dcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
    													.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    	);

    }
    
    public void setTituloFactura(String pTitulo){
    	this.lblImagenTitulo.setText(pTitulo);
    }

	public JDateChooser getDcFecha() {
		return dcFecha;
	}

	public void setDcFecha(JDateChooser dcFecha) {
		this.dcFecha = dcFecha;
	}

	public JFormattedTextField getTfNumeroFactura() {
		return tfNumeroFactura;
	}

	public void setTfNumeroFactura(JFormattedTextField tfNumeroFactura) {
		this.tfNumeroFactura = tfNumeroFactura;
	}

}
