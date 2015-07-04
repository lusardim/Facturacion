package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;
import ar.com.jumperinformatica.gui.view.components.CabeceraFactura;
import ar.com.jumperinformatica.gui.view.components.CuerpoFacturaA;
import ar.com.jumperinformatica.gui.view.components.PanelTotalFacturaA;

import com.toedter.calendar.JDateChooser;

public class FacturaAView extends FacturaView{

	private static final Color COLOR_LABELS = new Color(0,90,122);
	private static final Font FUENTE_BOTONES = Font.decode("Tahoma 11 12 Bold");
	
	private static final long serialVersionUID = 3718770051500791694L;
	private CabeceraFactura pnlCabecera;
	private PanelTotalFacturaA pnlTotal;
	private CuerpoFacturaA pnlCuerpoFactura;
	
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JTable tblDetalleFactura;
	
	public FacturaAView(Dialog pPadre) throws ParseException{
		super(pPadre);
		this.initComponents();
		this.setLocationRelativeTo(pPadre);
	}
	
	private void initComponents() throws ParseException{
		pnlCabecera = new CabeceraFactura();
		pnlTotal = new PanelTotalFacturaA();
		this.pnlCuerpoFactura = new CuerpoFacturaA();
		this.btnAceptar = new JButton();
		this.btnCancelar = new JButton();
		tblDetalleFactura = new JTable();
		JScrollPane panelTabla = new JScrollPane(tblDetalleFactura);
		JPanel principal = new JPanel();
		
		this.pnlCabecera.setTituloFactura("A");
		
		principal.setBackground(Color.WHITE);
		
		this.btnAceptar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_ACEPTAR));
		this.btnAceptar.setForeground(COLOR_LABELS);
		this.btnAceptar.setFont(FUENTE_BOTONES);
		this.btnAceptar.setText("Aceptar");
		
		
		this.btnCancelar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_CANCELAR));
		this.btnCancelar.setForeground(COLOR_LABELS);
		this.btnCancelar.setFont(FUENTE_BOTONES);
		this.btnCancelar.setText("Cancelar");
		
		this.pnlCuerpoFactura.getTfNombre().setEditable(false);
		this.pnlCuerpoFactura.getTfCuit().setEditable(false);
		this.pnlCuerpoFactura.getTfDomicilio().setEditable(false);
		
		this.tblDetalleFactura.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
	    javax.swing.GroupLayout pnlFacturaLayout = new javax.swing.GroupLayout(principal);
	    principal.setLayout(pnlFacturaLayout);
        pnlFacturaLayout.setHorizontalGroup(
            pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacturaLayout.createSequentialGroup()
                        .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacturaLayout.createSequentialGroup()
                        .addComponent(pnlCabecera, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlFacturaLayout.createSequentialGroup()
                        .addComponent(pnlCuerpoFactura, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(pnlFacturaLayout.createSequentialGroup()
                        .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        pnlFacturaLayout.setVerticalGroup(
            pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFacturaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCuerpoFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                
                .addComponent(panelTabla, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                
                .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlFacturaLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pnlTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlFacturaLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(pnlFacturaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                          .addGap(21,21,21))
                
        );
        
        this.add(principal);
        pack();
	}
	

	public CabeceraFactura getPnlCabecera() {
		return pnlCabecera;
	}


	public PanelTotalFacturaA getPnlTotal() {
		return pnlTotal;
	}


	public CuerpoFacturaA getPnlCuerpoFactura() {
		return pnlCuerpoFactura;
	}


	public JButton getBtnAceptar() {
		return btnAceptar;
	}


	public JButton getBtnCancelar() {
		return btnCancelar;
	}


	public JTable getTblDetalleFactura() {
		return tblDetalleFactura;
	}


	public JButton getBtnBuscarComitente() {
		return pnlCuerpoFactura.getBtnBuscarComitente();
	}


	public JDateChooser getDcFecha() {
		return pnlCabecera.getDcFecha();
	}


	public JFormattedTextField getTfNumeroFactura() {
		return pnlCabecera.getTfNumeroFactura();
	}


	public JComboBox getCboIva() {
		return pnlCuerpoFactura.getCboIva();
	}


	public JTextField getTfCondicionVenta() {
		return pnlCuerpoFactura.getTfCondicionVenta();
	}


	public JFormattedTextField getTfCuit() {
		return pnlCuerpoFactura.getTfCuit();
	}


	public JTextField getTfDomicilio() {
		return pnlCuerpoFactura.getTfDomicilio();
	}


	public JTextField getTfNombre() {
		return pnlCuerpoFactura.getTfNombre();
	}


	public JTextField getTfObra() {
		return pnlCuerpoFactura.getTfObra();
	}


	public JTextField getTfRemito() {
		return pnlCuerpoFactura.getTfRemito();
	}
	
	public JTextField getTfSubtotal() {
		return pnlTotal.getTfSubtotal();
	}


	public JTextField getTfTotal() {
		return pnlTotal.getTfTotal();
	}


	public JTextField getTfTotalIva() {
		return pnlTotal.getTfTotalIva();
	}


}
