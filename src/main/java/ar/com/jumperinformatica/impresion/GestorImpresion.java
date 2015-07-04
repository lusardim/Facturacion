package ar.com.jumperinformatica.impresion;

import java.awt.Dialog;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Map;

import javax.swing.JDialog;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JRViewer;
import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.gui.enums.Reportes;

public class GestorImpresion {
	
	
	public static void imprimirDirecto(Reportes pReporte, Map<String, Object> pParametros) throws Exception{
		Connection locConection = GestorPersistencia.getInstance().getConnection();
		FileInputStream locStream = null;
		try{
			locStream = new FileInputStream(pReporte.getArchivo());
			JasperPrint locPrint = JasperFillManager.fillReport(locStream, 
					pParametros,locConection);

			JasperPrintManager.printReport(locPrint, true);
			
		}
		finally{
			if (locStream!=null){
				locStream.close();
			}
			locConection.close();
		}
	}
	
	
	public static void imprimirConViewer(Dialog pPadre,Reportes pReporte, Map<String, Object> pParametros) throws Exception{
		Connection locConection = GestorPersistencia.getInstance().getConnection();
		FileInputStream locStream = null;
		try{
			locStream = new FileInputStream(pReporte.getArchivo());
			JasperPrint locPrint = JasperFillManager.fillReport(locStream, 
					pParametros,locConection);
		
			JDialog locDialog = new JDialog(pPadre);
			JRViewer locViewer = new JRViewer(locPrint);
			locDialog.add(locViewer);
			locDialog.pack();
			locDialog.setModal(true);
			locDialog.setTitle(pReporte.getNombreReporte());
			locDialog.setVisible(true);
		}
		finally{
			if (locStream!=null){
				locStream.close();
			}
			locConection.close();
		}
	}
}
