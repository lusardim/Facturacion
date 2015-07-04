package ar.com.jumperinformatica.gui.view;

import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class CargaGUI extends JWindow{
	
	private List<Icon> listaIconos;
	private Icon iconoDescanso;
	private Timer timer;
	private int idIconoActual = -1;
	private JLabel lblIconos; 
	
	public static void main(String[] args){
		JFrame locFrame = new JFrame();
		CargaGUI locCargaGUI = new CargaGUI(locFrame);
		locCargaGUI.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(1);
			}
		});
		locCargaGUI.setVisible(true);
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2965957094554660662L;

	public CargaGUI(Frame owner){
		super(owner);
		this.loadImages();
		this.initComponents();
		this.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e) {
				iniciar();
			}
		});
	}
	
	private void loadImages(){
		this.listaIconos = new ArrayList<Icon>();
		URL locUrl = this.getClass().getClassLoader().getResource("resources/busyicons/");
		try {
			File locFile = new File(locUrl.toURI());
			if (locFile.isDirectory()){
				for (File cadaArchivo : locFile.listFiles()){
					if (cadaArchivo.getName().contains("busy")){
						//TODO
						//this.listaIconos.add(new ImageIcon(cadaArchivo.geta));
					}
				}
			}
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		this.iconoDescanso = new ImageIcon(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("resources/busyicons/idle-icon.png")));
	}
	
	private void initComponents(){
		FlowLayout locFlowLayout = new FlowLayout(FlowLayout.LEADING);
		this.setLayout(locFlowLayout);
		
		this.lblIconos = new JLabel();
		lblIconos.setIcon(this.iconoDescanso);
		this.add(lblIconos);
		this.pack();
	}
	
	public void iniciar(){
		this.getTimer().start();
	}
	
	
	public Timer getTimer() {
		if (timer == null){
			timer = new Timer(5000, new CambiarIconoListener(this));
		}
		return timer;
	}
	
	public int getIdIconoActual() {
		return idIconoActual;
	}
	
	public JLabel getLblIconos() {
		return lblIconos;
	}
	public List<Icon> getListaIconos() {
		return listaIconos;
	}
}


class CambiarIconoListener implements ActionListener{
	
	private CargaGUI vista;
	
	public CambiarIconoListener(CargaGUI vista) {
		this.vista = vista;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		SwingUtilities.invokeLater(new Runnable(){
			@Override
			public void run() {
				System.out.println("LALLSLALD");
				int locIdIconoActual = vista.getIdIconoActual();
				locIdIconoActual=(locIdIconoActual+1)%vista.getListaIconos().size();
				vista.getLblIconos().setIcon(vista.getListaIconos().get(locIdIconoActual));
				vista.repaint();		
			}
		});
		
	}
}