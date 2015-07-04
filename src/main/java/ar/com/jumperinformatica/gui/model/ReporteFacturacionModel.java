package ar.com.jumperinformatica.gui.model;

import java.util.Calendar;
import java.util.Date;

import ar.com.jumperinformatica.gui.enums.Periodo;

public class ReporteFacturacionModel {
	private Date fechaDesde;
	private Date fechaHasta;
	private Periodo periodo;
	private ParametroTipoFactura tiposFacturas;
	
	public Date getFechaDesde() {
		if (this.fechaDesde == null){
			this.fechaDesde = Calendar.getInstance().getTime();
		}
		return fechaDesde;
	}
	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
		this.calcularFechaHasta();
	}
	private void calcularFechaHasta() {
		if (periodo != null){
			this.setFechaHasta(this.periodo.getProximaFecha(this.getFechaDesde()));
		}
	}
	public Date getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
		if (this.fechaHasta == null){
			this.calcularFechaHasta();
		}
	}
	public Periodo getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
		this.calcularFechaHasta();
	}
	public ParametroTipoFactura getTiposFacturas() {
		return tiposFacturas;
	}
	public void setTiposFacturas(ParametroTipoFactura tiposFacturas) {
		this.tiposFacturas = tiposFacturas;
	}
	
}
