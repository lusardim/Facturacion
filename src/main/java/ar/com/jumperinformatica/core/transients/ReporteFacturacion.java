package ar.com.jumperinformatica.core.transients;

import ar.com.jumperinformatica.core.enums.TipoFactura;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReporteFacturacion {
	private String dtype;
	private String periodo;
	private Timestamp fechaDesde;
	private Timestamp fechaHasta;
	private BigDecimal totalIva;
	private BigDecimal subtotal;
	private BigDecimal total;
	private TipoFactura tipoFactura;
	
	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public ReporteFacturacion(){
	}
	
	public ReporteFacturacion(BigDecimal subtotal, BigDecimal totalIva,BigDecimal total) {
		this.total = total;
		this.totalIva = totalIva;
		this.subtotal = subtotal;
	}
	
	public String getDtype() {
		return dtype;
	}
	public void setDtype(String dtype) {
		this.dtype = dtype;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Timestamp getFechaDesde() {
		return fechaDesde;
	}
	
	public void setFechaDesde(Date fechaDesde) {
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(fechaDesde);
		locCalendar.set(Calendar.HOUR, 0);
		this.setFechaDesde(new Timestamp(locCalendar.getTimeInMillis()));
	}
	
	public void setFechaHasta(Date fechaHasta) {
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(fechaHasta);
		locCalendar.set(Calendar.HOUR, 0);
		this.setFechaHasta(new Timestamp(locCalendar.getTimeInMillis()));
	}
	
	public void setFechaDesde(Timestamp fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public Timestamp getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(Timestamp fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public BigDecimal getTotalIva() {
		return totalIva;
	}
	public void setTotalIva(BigDecimal totalIva) {
		this.totalIva = totalIva;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
	public Map<String,Object> getListaParametros(){
		getTotal().setScale(2);
		getSubtotal().setScale(2);
		getTotalIva().setScale(2);

		Map<String,Object> listaParametros = new HashMap<String, Object>();
		
		listaParametros.put("P_DTYPE", this.getDtype());
		listaParametros.put("P_periodo", this.getPeriodo());
		listaParametros.put("P_fechaDesde", this.getFechaDesde());
		listaParametros.put("P_fechaHasta", this.getFechaHasta());
		listaParametros.put("P_totalIva", this.getTotalIva().floatValue());
		listaParametros.put("P_subtotal", this.getSubtotal().floatValue());
		listaParametros.put("P_total", this.getTotal().floatValue());
		
		String locTipoFactura=null;
		if (this.tipoFactura != null){
			locTipoFactura = this.getTipoFactura().toString();
		}
		else{
			locTipoFactura = "otro";
		}
		listaParametros.put("P_tipoFactura", locTipoFactura);
		return listaParametros;
	}


	
}
