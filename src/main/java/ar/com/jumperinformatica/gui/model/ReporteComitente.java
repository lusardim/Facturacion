package ar.com.jumperinformatica.gui.model;

import java.util.Date;

import ar.com.jumperinformatica.core.persistent.Comitente;
import ar.com.jumperinformatica.gui.enums.Periodo;

public class ReporteComitente {
	private Date fechaDesde;
	private Date fechaHasta;
	private Periodo periodo;
	private Comitente comitente;
	
	public Comitente getComitente() {
		return comitente;
	}
	public void setComitente(Comitente comitente) {
		this.comitente = comitente;
	}
	public Date getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
		
	}
	
	public void recalcularFechaHasta() {
		if ((this.fechaDesde!=null) &&(this.periodo!=null)){
			this.fechaHasta = this.periodo.getProximaFecha(this.fechaDesde);
		}
	}
	
	
}	
