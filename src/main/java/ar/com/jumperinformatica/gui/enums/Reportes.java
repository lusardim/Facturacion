package ar.com.jumperinformatica.gui.enums;

import java.io.File;

public enum Reportes {
	FACTURA_A("reportes/facturaA.jasper","Factura A"),
	FACTURA_B("reportes/facturaB.jasper","Factura B"),
	COMITENTE("reportes/FacturacionComitente.jasper", "Facturación de Comitente"),
	FACTURACION_EGRESO("reportes/FacturacionEgresoGeneral.jasper", "Facturación Egreso General"),
	FACTURACION_INGRESO("reportes/FacturacionIngresoGeneral.jasper", "Facturación Ingreso General");
	
	private File archivoReporte;
	private String nombreReporte;
	
	Reportes(String pArchivoReportes, String pNombreReporte) {
		try {
			System.out.println(getClass().getClassLoader().getResource(pArchivoReportes));
			this.archivoReporte = new File(getClass().getClassLoader().getResource(pArchivoReportes).toURI());
			this.nombreReporte = pNombreReporte;
		}
		catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		//	throw new RuntimeException(e);
		}
	}
	
	public File getArchivo(){
		return archivoReporte;
	}

	public String getNombreReporte() {
		return this.nombreReporte;
	}
	
}
