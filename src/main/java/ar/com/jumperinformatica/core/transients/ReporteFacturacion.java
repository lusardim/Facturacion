package ar.com.jumperinformatica.core.transients;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import ar.com.jumperinformatica.core.enums.TipoFactura;

public class ReporteFacturacion {
	private String dtype;
	private String periodo;
	private Timestamp fechaDesde;
	private Timestamp fechaHasta;
	private Float totalIva;
	private Float subtotal;
	private Float total;
	private TipoFactura tipoFactura;
	
	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public ReporteFacturacion(){
	}
	
	public ReporteFacturacion(Double subtotal, Double totalIva,Double total){
		this.total = (total!=null)?total.floatValue():0;
		this.totalIva = (totalIva!=null)?totalIva.floatValue():0;
		this.subtotal = (subtotal!=null)?subtotal.floatValue():0;
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
	public Float getTotalIva() {
		return totalIva;
	}
	public void setTotalIva(Float totalIva) {
		this.totalIva = totalIva;
	}
	public Float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}
	public Float getTotal() {
		return total;
	}
	public void setTotal(Float total) {
		this.total = total;
	}
	
	public Map<String,Object> getListaParametros(){
		Map<String,Object> listaParametros = new HashMap<String, Object>();
		
		listaParametros.put("P_DTYPE", this.getDtype());
		listaParametros.put("P_periodo", this.getPeriodo());
		listaParametros.put("P_fechaDesde", this.getFechaDesde());
		listaParametros.put("P_fechaHasta", this.getFechaHasta());
		listaParametros.put("P_totalIva", this.getTotalIva());
		listaParametros.put("P_subtotal", this.getSubtotal());
		listaParametros.put("P_total", this.getTotal());
		
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
