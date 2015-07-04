package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;

import com.toedter.calendar.JDateChooser;

public class ReporteComitenteView extends JDialog {

	private static final long serialVersionUID = -351130062829837496L;

	private static final Font FUENTE_LABELS = Font.decode("Tahoma 11 Bold");
	private static final Color FRENTE_LABELS = new Color(0, 90, 122);
	
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JComboBox cboPeriodo;
    private JDateChooser dcDesde;
    private JDateChooser dcHasta;
    private JLabel lblDesde;
    private JLabel lblHasta;
    private JLabel lblPeriodo;
    private JPanel pnlPrincipal;
    
	public ReporteComitenteView(Dialog parent) {
        super(parent);
        this.setModal(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(parent);
    }

    private void initComponents() {
        pnlPrincipal = new JPanel();
        lblPeriodo = new JLabel();
        lblDesde = new JLabel();
        lblHasta = new JLabel();
        btnAceptar = new JButton();
        btnCancelar = new JButton();
        cboPeriodo = new JComboBox();
        dcDesde = new com.toedter.calendar.JDateChooser();
        dcHasta = new com.toedter.calendar.JDateChooser();
        
        pnlPrincipal.setBackground(Color.WHITE); 

        lblPeriodo.setFont(FUENTE_LABELS); // NOI18N
        lblPeriodo.setForeground(FRENTE_LABELS); // NOI18N
        lblPeriodo.setText("Período"); // NOI18N

        lblDesde.setFont(FUENTE_LABELS); 
        lblDesde.setForeground(FRENTE_LABELS); 
        lblDesde.setText("Desde"); 

        lblHasta.setFont(FUENTE_LABELS); // NOI18N
        lblHasta.setForeground(FRENTE_LABELS); // NOI18N
        lblHasta.setText("Hasta"); // NOI18N


		btnAceptar.setFont(FUENTE_LABELS); 
		btnAceptar.setForeground(FRENTE_LABELS); 
		btnAceptar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_ACEPTAR)); 
		btnAceptar.setText("Aceptar"); 

		btnCancelar.setFont(FUENTE_LABELS); 
		btnCancelar.setForeground(FRENTE_LABELS); 
		btnCancelar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BOTON_CANCELAR)); 
		btnCancelar.setText("Cancelar"); 


        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblDesde)
                            .addComponent(lblPeriodo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                                .addComponent(dcDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(10, 10, 10)
                                .addComponent(lblHasta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dcHasta, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                            .addComponent(cboPeriodo, 0, 221, Short.MAX_VALUE))))
                .addGap(48, 48, 48))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPeriodo)
                    .addComponent(cboPeriodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDesde)
                            .addComponent(dcDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(dcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHasta))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }

    public JButton getBtnAceptar() {
		return btnAceptar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}

	public JComboBox getCboPeriodo() {
		return cboPeriodo;
	}

	public JDateChooser getDcDesde() {
		return dcDesde;
	}

	public JDateChooser getDcHasta() {
		return dcHasta;
	}


}
