package ar.com.jumperinformatica.gui.controller;

import java.awt.Dialog;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.system.bean.UsuarioBean;
import ar.com.jumperinformatica.gui.controller.interfaces.AMController;
import ar.com.jumperinformatica.gui.controller.listeners.AceptarListener;
import ar.com.jumperinformatica.gui.controller.listeners.CancelarListener;
import ar.com.jumperinformatica.gui.controller.listeners.DefaultKeyListener;
import ar.com.jumperinformatica.gui.view.AMUsuarioView;

public class AMUsuarioController implements AMController{
	private Usuario modelo;
	private UsuarioBean usuarioBean;
	private AMUsuarioView vista;
	
	private boolean agrego;
	
	public AMUsuarioController(Dialog pPadre){
		this.vista = new AMUsuarioView(pPadre);
		this.vista.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.inicializarEventos();
		
	}
	
	private void inicializarEventos() {
		this.vista.getBtnAceptar().addActionListener(new AceptarListener(this));
		this.vista.getBtnCancelar().addActionListener(new CancelarListener(this.vista));
		this.vista.addKeyListener(new DefaultKeyListener(this));
		this.vista.getTfNombre().addKeyListener(new DefaultKeyListener(this));
		this.vista.getTfUsuario().addKeyListener(new DefaultKeyListener(this));
		this.vista.getPfClave().addKeyListener(new DefaultKeyListener(this));
		this.vista.getPfRepitaClave().addKeyListener(new DefaultKeyListener(this));
	}

	public AMUsuarioController(Dialog pPadre, Usuario pModel){
		this(pPadre);
		this.modelo = pModel;
		this.actualizarVista();
	}
	
	
	@Override
	public void aceptar() {
		try{
			if (JOptionPane.showConfirmDialog(this.vista, "¿Está seguro que desea guardar los datos del usuario?"
						,this.getVista().getTitle(),
						JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
				if (this.modelo==null){
					this.modelo = new Usuario();
					this.actualizarModelo();
					this.getUsuarioBean().addUsuario(this.modelo);
				}
				else{
					this.actualizarModelo();
					this.getUsuarioBean().updateUsuario(this.modelo);
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
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar agregar un usuario. Intente nuevamente más tarde.","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void actualizarModelo() throws LogicaException{
		this.validoClaves();
		String locClave = null;
		String locNombreUsuario = null;
		String locNombrePersona = null;
		if (!new String(this.vista.getPfClave().getPassword()).trim().equals("")) {
			locClave = new String(this.vista.getPfClave().getPassword());
		}
		
		if (!this.vista.getTfUsuario().getText().trim().equals("")){
			locNombreUsuario = this.vista.getTfUsuario().getText().trim();
		}
		
		if (!this.vista.getTfNombre().getText().trim().equals("")){
			locNombrePersona = this.vista.getTfNombre().getText().trim();
		}
		
		this.modelo.setClave(locClave);
		this.modelo.setNombrePersona(locNombrePersona);
		this.modelo.setNombre(locNombreUsuario);
	}

	private void validoClaves() throws LogicaException {
		String locClaveIngresada = new String(this.vista.getPfClave().getPassword());
		String locRepiteClave = new String(this.vista.getPfRepitaClave().getPassword());
		
		if (!locClaveIngresada.equals(locRepiteClave)){
			throw new LogicaException("Las claves ingresadas no coinciden, verifique los datos ingresados");
		}
	}

	@Override
	public void actualizarVista() {
		this.vista.getTfNombre().setText(((this.modelo.getNombrePersona()==null)?"":this.modelo.getNombrePersona()));
		this.vista.getTfUsuario().setText((this.modelo.getNombre()==null)?"":this.modelo.getNombre());
		
		String locClaves = (this.modelo.getClave()==null)?"":this.modelo.getClave();
		this.vista.getPfClave().setText(locClaves);
		this.vista.getPfRepitaClave().setText(locClaves);
	}

	@Override
	public void cancelar() {
		this.getVista().dispose();
	}
	
	public void setUsuarioBean(UsuarioBean usuarioBean) {
		this.usuarioBean = usuarioBean;
		
	}
	
	public AMUsuarioView getVista() {
		return this.vista;
	}
	
	public boolean mostrar() {
		this.vista.setVisible(true);
		return agrego;
	}

	public UsuarioBean getUsuarioBean() {
		return usuarioBean;
	}

}
