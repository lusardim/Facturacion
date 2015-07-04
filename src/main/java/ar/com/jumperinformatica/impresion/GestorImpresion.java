package ar.com.jumperinformatica.impresion;

import ar.com.jumperinformatica.core.persistent.GestorPersistencia;
import ar.com.jumperinformatica.gui.enums.Reportes;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JRViewer;
import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public class GestorImpresion {
	
	
	public static void imprimirDirecto(Reportes pReporte, Map<String, Object> pParametros) throws Exception{
		EntityManager entityManager = GestorPersistencia.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
        Session session = entityManager.unwrap(Session.class);
        session.doWork(connection -> {
            try (FileInputStream stream = new FileInputStream(pReporte.getArchivo()))
            {
                JasperPrint locPrint = JasperFillManager.fillReport(stream,
                        pParametros,connection);
                JasperPrintManager.printReport(locPrint, true);
            }
            catch(Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error", "Ha ocurrido un error al generar el reporte", JOptionPane.ERROR_MESSAGE);
            }
        });
	}
	
	
	public static void imprimirConViewer(Dialog pPadre, Reportes pReporte, Map<String, Object> pParametros) throws Exception
	{
		EntityManager entityManager = GestorPersistencia.getInstance().getEntityManager();
		entityManager.getTransaction().begin();
		Session sesion = entityManager.unwrap(Session.class);
		sesion.doWork(connection -> {
            try (FileInputStream locStream = new FileInputStream(pReporte.getArchivo()))
            {
                JasperPrint locPrint = JasperFillManager.fillReport(locStream,
                        pParametros,connection);

                JDialog locDialog = new JDialog(pPadre);
                JRViewer locViewer = new JRViewer(locPrint);

                locDialog.add(locViewer);
                locDialog.pack();
                locDialog.setModal(true);
                locDialog.setTitle(pReporte.getNombreReporte());
                locDialog.setVisible(true);
            }
            catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(pPadre, "Error", "Ha ocurrido un error al generar el reporte", JOptionPane.ERROR_MESSAGE);
            }
        });
	}
}
