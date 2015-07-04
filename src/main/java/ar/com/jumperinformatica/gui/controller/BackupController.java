package ar.com.jumperinformatica.gui.controller;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import ar.com.jumperinformatica.core.system.bean.ConfiguracionBean;
import ar.com.jumperinformatica.gui.view.BackupView;

public class BackupController {
	private BackupView vista;
	private ConfiguracionBean configuracion;
	
	public BackupController(Frame pPadre) {
		this.vista = new BackupView(pPadre);
		this.inicializarEventos();
		this.configuracion = new ConfiguracionBean();
	}
	
	private void inicializarEventos() {
		this.vista.getBtnRealizarBackup().addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				realizarBackup();
				
			}
		});
		
		this.vista.getBtnRestaurarBackup().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				restaurarBackup();
			}
		});
		
	}

	public void mostrar(){
		this.vista.setVisible(true);		
	}
	
	public void realizarBackup(){
		try{
			/*String runcmd="cmd /C start \"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\" " +
					"\"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\"";
			System.out.println(runcmd);
			*/
			
			String runcmd = this.configuracion.getParametro("REALIZAR_BACKUP").getValor();
			Process locProceso = Runtime.getRuntime().exec(runcmd);
			BufferedReader reader=new BufferedReader(new InputStreamReader(locProceso.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) 
			{ 
				System.out.println(line); 
				line=reader.readLine(); 
			} 
			JOptionPane.showMessageDialog(this.vista, "El archivo de resguardo se ha realizado correctamente.", "Backup",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar generar el archivo de resguardo", "Backup",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void restaurarBackup(){
		try{
			File locFile = new File("C:/Archivos de programa/Jano/backup");
			JFileChooser locFileChooser = new JFileChooser(locFile);
			locFileChooser.showOpenDialog(this.vista);
			if (locFileChooser.getSelectedFile()!=null){
				if (JOptionPane.showConfirmDialog(this.vista, 
						"¿El backup reemplazará todos los datos existentes. Confirma esta operación?",
						"Restaurar backup", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
				
					
				/*	String runcmd="cmd /C start \"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\" " +
						"\"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\restaurar.bat\" "+locFileChooser.getSelectedFile().getName();
					*/
					String runcmd = this.configuracion.getParametro("RESTAURAR_BACKUP").getValor();
					System.out.println(runcmd);
					Process locProceso = Runtime.getRuntime().exec(runcmd);
					BufferedReader reader=new BufferedReader(new InputStreamReader(locProceso.getInputStream())); 
					String line=reader.readLine(); 
					while(line!=null) 
					{ 
						System.out.println(line); 
						line=reader.readLine(); 
					} 
					JOptionPane.showMessageDialog(this.vista, "La restauración del backup se ha realizado correctamente.", "Backup",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar generar el archivo de resguardo", "Backup",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	/**
	 * @deprecated
	 */
	public void Backup(){
		try{
			File directorio = new File("C:/Archivos de programa/MySQL/MySQL Server 5.0/bin/");
			String[] parametros = {"hola"};
			String[] comandos = {"C:/Archivos de programa/MySQL/MySQL Server 5.0/bin/restaurar.bat",
					"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\facturacion_26082008.sql"
			};
			Process locProceso = Runtime.getRuntime().exec(comandos,parametros,directorio);
			BufferedReader reader=new BufferedReader(new InputStreamReader(locProceso.getInputStream())); 
			String line=reader.readLine(); 
			while(line!=null) 
			{ 
				System.out.println(line); 
				line=reader.readLine(); 
			} 
			JOptionPane.showMessageDialog(this.vista, "El archivo de resguardo se ha realizado correctamente.", "Backup",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(this.vista, "Ha ocurrido un error al intentar generar el archivo de resguardo", "Backup",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	
	public static void main(String[] args ){
		BackupController locBackupController = new BackupController(new JFrame());
		locBackupController.vista.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		locBackupController.mostrar();
	}

}
