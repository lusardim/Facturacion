package ar.com.jumperinformatica.gui.controller;

import java.awt.Dialog;
import java.awt.event.KeyListener;
import java.text.ParseException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.core.system.bean.ComitenteBean;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;
import ar.com.jumperinformatica.gui.controller.listeners.AceptarListener;
import ar.com.jumperinformatica.gui.controller.listeners.CancelarListener;
import ar.com.jumperinformatica.gui.controller.listeners.DefaultKeyListener;
import ar.com.jumperinformatica.gui.view.AMComitenteView;

public class AMComitenteController implements AMController{
	private AMComitenteView vista;
	private Comitente modelo;
	private ComitenteBean comitenteBean;
	
	private boolean agrego;
	
	public AMComitenteController(Dialog pPadre,Comitente pComitente) throws ParseException{
		this(pPadre);
		this.modelo = pComitente;
		this.actualizarVista();
	}
	
	public boolean mostrar(){
		this.vista.setVisible(true);
		return agrego;
	}
	
	public AMComitenteController(Dialog pPadre) throws ParseException{
		this.vista = new AMComitenteView(pPadre);
		this.vista.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.inicializarEventos();
	}
	
	
	private void inicializarEventos() {
		this.vista.getBtnCancelar().addActionListener(new CancelarListener(this.vista));
		this.vista.getBtnAceptar().addActionListener(new AceptarListener(this));
		KeyListener locKeyListener = new DefaultKeyListener(this); 
		this.vista.addKeyListener(locKeyListener);
		this.vista.getTfCuit().addKeyListener(locKeyListener);
		this.vista.getTfDomicilio().addKeyListener(locKeyListener);
		this.vista.getTfNombre().addKeyListener(locKeyListener);
	}

	/**
	 * refresca la vista a partir del modelo
	 */
	public void actualizarVista() {
		this.vista.getTfCuit().setText((this.modelo.getCuit()!=null)?this.modelo.getCuit():"");
		this.vista.getTfDomicilio().setText((this.modelo.getDomicilio()!=null)?this.modelo.getDomicilio():"");
		this.vista.getTfNombre().setText((this.modelo.getNombre()!=null)?this.modelo.getNombre():"");
	}

	
	/**
	 * refresca el modelo a partir de la vista
	 */
	public void actualizarModelo(){
		String locCuit=null;
		String locNombre=null;
		String locDomicilio=null;
		if (!this.vista.getTfCuit().getText().isEmpty()){
			if (this.vista.getTfCuit().isEditValid()){
				locCuit = this.vista.getTfCuit().getText().trim();
			}
		}
		if (!this.vista.getTfNombre().getText().isEmpty()){
			locNombre = this.vista.getTfNombre().getText().trim();
		}
		
		if (!this.vista.getTfDomicilio().getText().isEmpty()){
			locDomicilio = this.vista.getTfDomicilio().getText().trim();
		}
		
		this.modelo.setCuit(locCuit);
		this.modelo.setNombre(locNombre);
		this.modelo.setDomicilio(locDomicilio);
	}
	
	
	
	
	
	/**
	 * si el modelo es nulo, agrega un comitente, en caso contrario lo actualiza
	 */
	@Override
	public void aceptar(){
		try{
			if (JOptionPane.showConfirmDialog(this.vista, "¿Está seguro que desea guardar los datos del comitente?"
						,this.getVista().getTitle(),
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				if (this.modelo==null){
					this.modelo = new Comitente();
					this.actualizarModelo();
					this.getComitenteBean().addComitente(this.modelo);
				}
				else{
					this.actualizarModelo();
					this.getComitenteBean().updateComitente(this.modelo);
				}
				this.agrego = true;
				this.getVista().dispose();
			}
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar agregar un comitente. Intente nuevamente más tarde.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	
	
	public AMComitenteView getVista() {
		return vista;
	}

	public void setVista(AMComitenteView vista) {
		this.vista = vista;
	}

	public Comitente getModelo() {
		return modelo;
	}

	public void setModelo(Comitente modelo) {
		this.modelo = modelo;
	}

	public ComitenteBean getComitenteBean() {
		return comitenteBean;
	}

	public void setComitenteBean(ComitenteBean comitenteBean) {
		this.comitenteBean = comitenteBean;
	}

	

	@Override
	public void cancelar() {
		this.vista.dispose();		
	}

}
