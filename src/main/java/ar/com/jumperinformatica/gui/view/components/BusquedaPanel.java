package ar.com.jumperinformatica.gui.view.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.transients.ParametroBusqueda;
import ar.com.jumperinformatica.gui.controller.listeners.SeleccionModeloBusquedaListener;
import ar.com.jumperinformatica.gui.enums.Recursos;
import ar.com.jumperinformatica.gui.model.AbstractBusquedaModel;
import ar.com.jumperinformatica.gui.model.BusquedaFacturaEgresoModel;
import ar.com.jumperinformatica.gui.principal.Facturacion;

import com.toedter.calendar.JDateChooser;

//FIXME falta hacer que cambie los eventos del combo al tfPArametro cuando lo necesite
//y que anden bien las búsquedas ::)
public class BusquedaPanel extends JPanel {

	private static final long serialVersionUID = 5304715246411775463L;
	public static final Color COLOR_FONDO = new Color(255, 255, 255);
	public static final Color COLOR_FRENTE = new Color(255, 255, 255);
	public static final Font FUENTE_LABELS = Font.decode("Tahoma 11 12 Bold");
	public static final Color COLOR_LABELS = new Color(0,90,122);
	
	private JComboBox cbCondicionBusqueda;
	private JLabel lblCondicionBusqueda;
	private JPanel pnlParametrosBusqueda;
	private JScrollPane spTabla;
	private JTable tblBusqueda;
	private JTextField tfParametroBusqueda;
	
	private JComboBox cboParametroBusqueda;
	
	private JDateChooser dcParametroBusqueda;
	
	private AbstractBusquedaModel<?> modelo;
	
	/**
	 * @deprecated
	 * @param args
	 * @throws LogicaException
	 */
	public static void main(String[] args) throws LogicaException{
		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		BusquedaPanel locPnlBusqueda = new BusquedaPanel();
		JFrame locJframe = new JFrame();

	//	BusquedaUsuarioModel locBusquedaUsuarioModel = new BusquedaUsuarioModel();
//		locPnlBusqueda.setModel(locBusquedaUsuarioModel);
		
		BusquedaFacturaEgresoModel locModelo = new BusquedaFacturaEgresoModel();
		locPnlBusqueda.setModel(locModelo);
		JDialog locDialog = new JDialog(locJframe);

		locDialog.add(locPnlBusqueda);
		locDialog.pack();
		
		locDialog.setVisible(true);

	}
	
	
	public BusquedaPanel() {
		initComponents();
	}

	
	/**
	 * Inicializa la interfaz gráfica
	 */
	private void initComponents() {

		pnlParametrosBusqueda = new JPanel();
		JLabel lblBuscar = new JLabel();
		tfParametroBusqueda = new JTextField();
		lblCondicionBusqueda = new JLabel();
		cbCondicionBusqueda = new JComboBox();
		spTabla = new JScrollPane();
		tblBusqueda = new JTable();
		
		cboParametroBusqueda = new JComboBox();
				
		dcParametroBusqueda = new JDateChooser();
		
		setBackground(COLOR_FONDO); 
		setForeground(COLOR_FRENTE); 

		pnlParametrosBusqueda.setBackground(COLOR_FONDO); 
		pnlParametrosBusqueda.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new Font("Tahoma", 1, 11), new Color(0, 90, 122))); // NOI18N

		lblBuscar.setFont(FUENTE_LABELS); // NOI18N
		lblBuscar.setForeground(COLOR_LABELS); // NOI18N
		lblBuscar.setIcon(Facturacion.getInstance().getAdminRecursos().getIcon(Recursos.BUSQUEDA_ICONO_BUSCAR));
		lblBuscar.setText("Buscar:"); // NOI18N

		lblCondicionBusqueda.setFont(FUENTE_LABELS); // NOI18N
		lblCondicionBusqueda.setForeground(COLOR_LABELS); 
		lblCondicionBusqueda.setText("En:"); 
		
		
		GroupLayout jPanel2Layout = new GroupLayout(pnlParametrosBusqueda);
		pnlParametrosBusqueda.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(
				jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblBuscar, GroupLayout.PREFERRED_SIZE, lblBuscar.getPreferredSize().width, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(tfParametroBusqueda, GroupLayout.PREFERRED_SIZE, 274, Short.MAX_VALUE)
						.addComponent(cboParametroBusqueda, GroupLayout.PREFERRED_SIZE, 274, Short.MAX_VALUE)
						.addComponent(dcParametroBusqueda, GroupLayout.PREFERRED_SIZE, 274, Short.MAX_VALUE)
						.addGap(24, 24, 24)
						.addComponent(lblCondicionBusqueda, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(cbCondicionBusqueda, 0, 381, Short.MAX_VALUE)
						.addContainerGap())
		);
		jPanel2Layout.setVerticalGroup(
				jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(lblCondicionBusqueda)
						.addComponent(cbCondicionBusqueda, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblBuscar)
						.addComponent(tfParametroBusqueda, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(cboParametroBusqueda, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(dcParametroBusqueda, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
		);

		spTabla.setViewportView(tblBusqueda);

		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
						.addContainerGap()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addComponent(spTabla, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 841, Short.MAX_VALUE)
								.addComponent(pnlParametrosBusqueda, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap())
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addContainerGap()
						.addComponent(pnlParametrosBusqueda, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(spTabla, GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
						.addContainerGap())
		);
		
		
		cboParametroBusqueda.setVisible(false);
		dcParametroBusqueda.setVisible(false);
	}



	/**
	 * Setea el modelo en la tabla
	 * @param pModelo
	 */
	public void setModel(final AbstractBusquedaModel<?> pModelo){
		this.modelo = pModelo;
		this.cbCondicionBusqueda.setModel(pModelo.getModeloCombo());
		this.tblBusqueda.setModel(pModelo.getModeloTabla());
		
		//Seteo los listeners para mantener actualizada la vista
		this.tblBusqueda.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.tblBusqueda.getSelectionModel().addListSelectionListener(new SeleccionModeloBusquedaListener(pModelo));
		
		//TODO MOVER DE ACÁ
		this.cbCondicionBusqueda.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				cambiarCondicionBusqueda();
			}
		});
		
		this.getTfParametroBusqueda().getDocument().addDocumentListener(new DocumentListener(){
			@Override
			public void changedUpdate(DocumentEvent e) {
				refrescarModelo();
			}
			@Override
			public void insertUpdate(DocumentEvent e) {
				refrescarModelo();
			}
			@Override
			public void removeUpdate(DocumentEvent e) {
				refrescarModelo();
			}
		});

	}
	
	
	/**
	 * Cambia el parámetro de búsqueda basándose en lo seleccionado en la condición de búsqueda
	 */
	private void cambiarCondicionBusqueda() {
		Class<?> tipoAtributo = ((ParametroBusqueda)cbCondicionBusqueda.getSelectedItem()).getTipoAtributo();
		if (tipoAtributo.getSuperclass().equals(Enum.class)){
			this.cambiarCondicionBusquedaAEnumeracion(tipoAtributo);
		}
		else if (tipoAtributo.equals(Date.class)){
			this.cambiarCondicionBusquedaADate();
		}
		else{
			this.cambiarCondicionBusquedaAString();
		}
		BusquedaPanel.this.repaint();
		refrescarModelo();
		
	}
	
	
	private void cambiarCondicionBusquedaADate() {
		tfParametroBusqueda.setVisible(false);
		tfParametroBusqueda.setText("");
		cboParametroBusqueda.setVisible(false);
		cboParametroBusqueda.setSelectedItem(null);
		this.dcParametroBusqueda.setVisible(true);
		
		dcParametroBusqueda.addPropertyChangeListener(new PropertyChangeListener(){
			@Override
			public void propertyChange(PropertyChangeEvent arg0) {
				if (arg0.getPropertyName().equals("date")){
					refrescarModelo();
				}
			}
		});
	}


	/**
	 * Cambia la condición de búsqueda desde una enumeración a un string
	 */
	private void cambiarCondicionBusquedaAString() {
		tfParametroBusqueda.setVisible(true);
		cboParametroBusqueda.setVisible(false);
		cboParametroBusqueda.setSelectedItem(null);
		this.dcParametroBusqueda.setDate(null);
		this.dcParametroBusqueda.setVisible(false);
		
		//Elimino los action listeners
		if (cboParametroBusqueda.getActionListeners().length>0){
			cboParametroBusqueda.removeActionListener(cboParametroBusqueda.getActionListeners()[0]);
		}
	}

	/**
	 * Modifica la condición de búsqueda, desde un String o un número a una enumeración
	 * para ello reemplaza el selector del text field
	 * @param pClaseAtributo
	 */
	private void cambiarCondicionBusquedaAEnumeracion(Class<?> pClaseAtributo) {
		tfParametroBusqueda.setVisible(false);
		tfParametroBusqueda.setText("");
		cboParametroBusqueda.setVisible(true);
		this.dcParametroBusqueda.setVisible(false);
		this.dcParametroBusqueda.setDate(null);
		ComboBoxModel locModeloCombo = this.getComboModelDesdeEnumeracion(pClaseAtributo);
		cboParametroBusqueda.setModel(locModeloCombo);

		cboParametroBusqueda.addActionListener(new ActionListener(){
			@Override
  			public void actionPerformed(ActionEvent e) {
				refrescarModelo();
			}
		});
		
	}

	/**
	 * Obtiene el modelo del combo box utilizado en la búsqueda
	 * @param pClaseEnumerada es la clase cuya enumeración debe mostrarse. Se agrega un registro nulo
	 * @return
	 */
	private ComboBoxModel getComboModelDesdeEnumeracion(Class<?> pClaseEnumerada) {
		Object[] locEnuObjects = pClaseEnumerada.getEnumConstants();
		Vector<Object> locVector = new Vector<Object>();
		locVector.add(null);
		Collections.addAll(locVector, locEnuObjects);
		return new DefaultComboBoxModel(locVector);
	}


	/**
	 * refresca el modelo y actualiza las vistas
	 */
	public void refrescarModelo(){
		try{
			this.modelo.setSelectedIndex(-1);
			if (this.getTfParametroBusqueda().isVisible()){
				String valor = this.getTfParametroBusqueda().getText().trim();
				this.modelo.setParametroBusqueda(valor.equals("")?null:valor);
			}
			else if (this.cboParametroBusqueda.isVisible()){
				Object valor = this.cboParametroBusqueda.getSelectedItem();
				this.modelo.setParametroBusqueda(valor);
			}
			else{
				Date valor = this.dcParametroBusqueda.getDate();
				this.modelo.setParametroBusqueda(valor);
			}
				
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	/**
	 * Agrega un listener de seleccion
	 * @param pSelectionListener
	 */
	public void addSeleccionListener(ListSelectionListener pSelectionListener){
		this.tblBusqueda.getSelectionModel().addListSelectionListener(pSelectionListener);
	}
	
	///---------------GETERS Y SETERS
	public JLabel getLblCondicionBusqueda() {
		return lblCondicionBusqueda;
	}


	public void setLblCondicionBusqueda(JLabel lblCondicionBusqueda) {
		this.lblCondicionBusqueda = lblCondicionBusqueda;
	}


	public JTable getTblBusqueda() {
		return tblBusqueda;
	}


	public void setTblBusqueda(JTable tblBusqueda) {
		this.tblBusqueda = tblBusqueda;
	}


	public JTextField getTfParametroBusqueda() {
		return tfParametroBusqueda;
	}


	public void setTfParametroBusqueda(JTextField tfParametroBusqueda) {
		this.tfParametroBusqueda = tfParametroBusqueda;
	}


	public JComboBox getCbCondicionBusqueda() {
		return cbCondicionBusqueda;
	}


	public void setCbCondicionBusqueda(JComboBox cbCondicionBusqueda) {
		this.cbCondicionBusqueda = cbCondicionBusqueda;
	}

}
