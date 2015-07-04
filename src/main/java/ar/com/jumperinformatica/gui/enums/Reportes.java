package ar.com.jumperinformatica.gui.enums;

import java.io.File;

public enum Reportes {
	FACTURA_A("reportes/facturaA.jasper","Factura A"),
	FACTURA_B("reportes/facturaB.jasper","Factura B"),
	COMITENTE("reportes/Facturacion de Comitente.jasper", "Facturación de Comitente"),
	FACTURACION_EGRESO("reportes/Facturacion Egreso General.jasper", "Facturación Egreso General"),
	FACTURACION_INGRESO("reportes/Facturacion Ingreso General.jasper", "Facturación Ingreso General")
	
	;
	
	private File archivoReporte;
	private String nombreReporte;
	
	private Reportes(String pArchivoReportes,String pNombreReporte){
		this.archivoReporte = new File(pArchivoReportes);
		this.nombreReporte = pNombreReporte;
	}
	
	public File getArchivo(){
		return archivoReporte;
	}

	public String getNombreReporte() {
		return this.nombreReporte;
	}
	
}
