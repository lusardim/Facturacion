package ar.com.jumperinformatica.gui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.principal.Facturacion;


/**
 * 
 * @author Daiana Ernst
 *
 */
public class LoginView extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3368232420115703007L;
	public static final Color COLOR_BACKGROUND = new Color(0, 90, 122); 
	public static final Font FUENTE_LABELS = Font.decode("Tahoma 12-Bold-11");
	public static final Font FUENTE_BOTONES = Font.decode("Tahoma-Bold-11");
	
    private JButton btnCancelar;
    private JButton btnEntrar;
    public JPanel locPnlPrincipal;

    
    private JPasswordField tfClave;
   


	private JTextField tfUsuario;
                
    public LoginView(Frame parent, boolean modal) {
        super(parent);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        initComponents();
        this.setLocationRelativeTo(null);
    }

	private void initComponents() {
		this.setTitle("Jano - Sistema de Facturación");
		this.setIconImage(Facturacion.getInstance().getAdminRecursos().getImage(Recursos.LOGO_JANO));
    	JLabel locLblTituloEmpresa;
        JLabel locLblTituloJano;
        JLabel locLblUsuario;
        JLabel locLblClave;
        
        locPnlPrincipal = new JPanel();
        locLblTituloEmpresa = new JLabel();
        locLblUsuario = new JLabel();
        locLblClave = new JLabel();
        tfUsuario = new JTextField();
        btnCancelar = new JButton();
        tfClave = new JPasswordField();
        btnEntrar = new JButton();
        locLblTituloJano = new JLabel();

        locPnlPrincipal.setBackground(COLOR_BACKGROUND); 
        locLblTituloEmpresa.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.LOGIN_LOGO)); 

        locLblUsuario.setFont(FUENTE_LABELS); 
        locLblUsuario.setForeground(Color.WHITE); 
        locLblUsuario.setText("Usuario"); 

        
        locLblClave.setFont(FUENTE_LABELS); 
        locLblClave.setForeground(Color.WHITE); 
        locLblClave.setText("Contraseña"); 

        btnCancelar.setBackground(Color.WHITE); 
        btnCancelar.setFont(FUENTE_BOTONES); 
        btnCancelar.setForeground(COLOR_BACKGROUND); 
        btnCancelar.setText("Salir"); 

        btnEntrar.setBackground(Color.WHITE); 
        btnEntrar.setFont(FUENTE_BOTONES); 
        btnEntrar.setForeground(COLOR_BACKGROUND); 
        btnEntrar.setText("Entrar"); 

        locLblTituloJano.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.LOGIN_TITULO)); 

        
        GroupLayout layoutPnlPrincipal = new GroupLayout(locPnlPrincipal);
        locPnlPrincipal.setLayout(layoutPnlPrincipal);
        layoutPnlPrincipal.setHorizontalGroup(
            layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layoutPnlPrincipal.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(locLblUsuario)
                    .addComponent(locLblClave))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(tfClave)
                    .addComponent(tfUsuario, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE))
                .addContainerGap(66, GroupLayout.PREFERRED_SIZE))
            .addComponent(locLblTituloEmpresa, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layoutPnlPrincipal.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(btnEntrar)
                .addGap(36, 36, 36)
                .addComponent(btnCancelar, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(105, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, layoutPnlPrincipal.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(locLblTituloJano, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
        );
        layoutPnlPrincipal.setVerticalGroup(
            layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layoutPnlPrincipal.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(locLblTituloJano)
                .addGap(18, 18, 18)
                .addComponent(locLblTituloEmpresa)
                .addGap(29, 29, 29)
                .addGroup(layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locLblUsuario)
                    .addComponent(tfUsuario, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(locLblClave)
                    .addComponent(tfClave, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layoutPnlPrincipal.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrar)
                    .addComponent(btnCancelar)))
        );

        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(locPnlPrincipal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(locPnlPrincipal, GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
        );

        pack();
    }
	
	public JButton getBtnCancelar() {
		return btnCancelar;
	}


	public void setBtnCancelar(JButton bntCancelar) {
		this.btnCancelar = bntCancelar;
	}


	public JButton getBtnEntrar() {
		return btnEntrar;
	}


	public void setBtnEntrar(JButton btnEntrar) {
		this.btnEntrar = btnEntrar;
	}


	public JPasswordField getTfClave() {
		return tfClave;
	}


	public void setTfClave(JPasswordField tfClave) {
		this.tfClave = tfClave;
	}


	public JTextField getTfUsuario() {
		return tfUsuario;
	}


	public void setTfUsuario(JTextField tfUsuario) {
		this.tfUsuario = tfUsuario;
	}


}
