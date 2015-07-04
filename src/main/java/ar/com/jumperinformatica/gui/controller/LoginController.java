package ar.com.jumperinformatica.gui.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.system.bean.LoginBean;
import ar.com.jumperinformatica.core.system.eventos.UsuarioLogeadoListener;
import ar.com.jumperinformatica.gui.controller.listeners.CancelarListener;
import ar.com.jumperinformatica.gui.view.LoginView;

public class LoginController {
	private LoginView view;
	private LoginBean model;
	


	public LoginController(Frame pOwner, LoginBean modelo){
		this.view = new LoginView(pOwner, true);
		this.model = modelo;
		
		this.inicializarEventos();
		this.actualizarVista();
		this.view.setVisible(true);
	}
	
	public LoginController(Frame pOwner){
		this(pOwner,new LoginBean());
	}
	
	/**
	 * Actualiza los datos de la vista según el usuario loggeado
	 */
	public void actualizarVista() {
		Usuario locUsuario = this.model.getUsuario();
		if (locUsuario!=null){
			this.view.getTfUsuario().setText(locUsuario.getNombre());
			this.view.getTfClave().setText(locUsuario.getClave());
		}
		else{
			this.view.getTfUsuario().setText("");
			this.view.getTfClave().setText("");
		}
	}

	
	
	private void inicializarEventos() {
		this.view.getBtnEntrar().addActionListener(new LoginActionListener(this));
		this.model.addUsuarioLoggeadoListener(new ModeloActualizadoListener(this));
		this.view.getBtnCancelar().addActionListener(new CancelarListener(this.view));
		LoginKeyListener locKeyListener = new LoginKeyListener(this);
		this.view.getTfUsuario().addKeyListener(locKeyListener);
		this.view.getTfClave().addKeyListener(locKeyListener);
	}
	
	
	public LoginView getView() {
		return view;
	}
	
	
	/**
	 * Realiza el login al sistema
	 * @throws LogicaException
	 */
	public void login(){
		final LoginBean modelo = this.model;
		final LoginView vista = this.view;
		
		Runnable locLoginThread  = 	new Runnable(){
					@Override
					public void run() {
						try{
							//TODO MOSTRAR VENTANA DE CARGA
							
							String locPassword = new String(vista.getTfClave().getPassword()).trim();
							String locNombreUsuario = vista.getTfUsuario().getText().trim();
							vista.getBtnEntrar().setEnabled(false);
							modelo.login(locNombreUsuario, locPassword);
							vista.dispose();
						}
						catch(LogicaException e){
							e.printStackTrace();
							JOptionPane.showMessageDialog(vista,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
							vista.getBtnEntrar().setEnabled(true);
						}
						catch(Exception e){
							e.printStackTrace();
							JOptionPane.showMessageDialog(vista,"Ha ocurrido un error al intentar ingresar al sistema. Por favor cierrelo e intente nuevamente."
										,"Error",JOptionPane.ERROR_MESSAGE);
							vista.getBtnEntrar().setEnabled(true);
						}
					}
		};
		
		
		Thread locThread = new  Thread(locLoginThread);
		locThread.start();
	}
	
	public LoginBean getModel() {
		return model;
	}
}



class LoginKeyListener extends KeyAdapter{
	private LoginController controller;

	public LoginKeyListener(LoginController controller){
		this.controller = controller;
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER){
			controller.login();
		}
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			controller.getView().dispose();
		}
	}
	
}


class LoginActionListener implements ActionListener{
	private LoginController controller;

	public LoginActionListener(LoginController controller){
		this.controller = controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent pAction) {
		controller.login();
	}
}


class ModeloActualizadoListener implements UsuarioLogeadoListener{
	
	private LoginController controller;
	
	public ModeloActualizadoListener(LoginController pController){
		this.controller = pController;
	}
	
	@Override
	public void usuarioLogeado(Usuario usuarioAnterior,	Usuario usuarioNuevo) {
		this.controller.actualizarVista();
	}
}