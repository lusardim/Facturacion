package ar.com.jumperinformatica.gui.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.gui.model.BusquedaUsuarioModel;
import ar.com.jumperinformatica.gui.view.AdminUsuarioView;

public class AdminUsuarioController extends AbstractAdminController{
	private BusquedaUsuarioModel busquedaUsuarioModel;
	private AdminUsuarioView vista;
	
	
	public AdminUsuarioController(Frame pPadre) throws LogicaException{
		this.busquedaUsuarioModel = new BusquedaUsuarioModel();
		this.vista = new AdminUsuarioView(pPadre);
		this.vista.getPanelBusqueda().setModel(this.busquedaUsuarioModel);
		this.refrescarVista();
		this.initListeners();
	}

	private void initListeners() {
		this.vista.getPanelBusqueda().addSeleccionListener(new ListSelectionListener(){
			@Override
			public void valueChanged(ListSelectionEvent e) {
				refrescarVista();
			}
		});
		
		this.vista.getBotonAgregar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaAltaUsuarios();	
			}
		});
		
		this.vista.getBotonModificar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mostrarVentanaModificarUsuarios();
			}
		});
		
		this.vista.getBotonEliminar().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				eliminarUsuario();
			}
		});
	}

	public void refrescarVista(){
		if (busquedaUsuarioModel.getSelected()==null){
			this.vista.getBotonModificar().setEnabled(false);
			this.vista.getBotonEliminar().setEnabled(false);
		}
		else{
			this.vista.getBotonModificar().setEnabled(true);
			this.vista.getBotonEliminar().setEnabled(true);
		}
	}


	@Override
	public BusquedaUsuarioModel getBusquedaModel() {
		return this.busquedaUsuarioModel;
	}


	@Override
	public AdminUsuarioView getVista() {
		return this.vista;
	}

	
	private void eliminarUsuario() {
		try{
			Usuario locUsuario = this.getBusquedaModel().getSelected();
			if (JOptionPane.showConfirmDialog(this.getVista(), "Está seguro que desea eliminar el usuario "+locUsuario, "Eliminar usuario", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				this.getBusquedaModel().getUsuarioBean().removeUsuario(locUsuario);
				this.getBusquedaModel().refrescar();
			}
		}
		catch(LogicaException e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	public void mostrarVentanaAltaUsuarios(){
		try{
			AMUsuarioController locAMComitenteController = new AMUsuarioController(this.vista);
			locAMComitenteController.setUsuarioBean(this.getBusquedaModel().getUsuarioBean());
			locAMComitenteController.getVista().setTitle("Alta de Usuarios");
			if (locAMComitenteController.mostrar()){
				this.getBusquedaModel().refrescar();
			}
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar mostrar la ventana de usuario. ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void mostrarVentanaModificarUsuarios(){
		try{
			Usuario locUsuario = this.getBusquedaModel().getSelected();
			AMUsuarioController locAMComitenteController = new AMUsuarioController(this.vista,locUsuario);
			locAMComitenteController.setUsuarioBean(this.getBusquedaModel().getUsuarioBean());
			locAMComitenteController.getVista().setTitle("Modificación del Usuario "+locUsuario);
			if (locAMComitenteController.mostrar()){
				this.getBusquedaModel().refrescar();
			}
		}
		catch(LogicaException e){
			JOptionPane.showMessageDialog(this.vista, e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar mostrar la ventana de usuario. ","Error",JOptionPane.ERROR_MESSAGE);
		}
	}

}
