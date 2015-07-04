package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.jumperinformatica.core.enums.EstadoFactura;
import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="factura")
public abstract class Factura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8796225973456871526L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idFactura;
	
	private String condicionVenta;
	private Date fecha = Calendar.getInstance().getTime();
	private String numeroFactura;
	private Integer numeroRemito;
	private String obra;
	private Float totalFactura=0f;
	private Float subtotal=0f;
	private Float totalIva=0f;
	
	
	@Enumerated(EnumType.STRING)
	private EstadoFactura estadoFactura;

	@ManyToOne(optional=false)
	private TipoIva tipoIva;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="factura")
	private List<DetalleFactura> detalleFactura=new ArrayList<DetalleFactura>();
	
	@Enumerated(EnumType.STRING)
	private TipoFactura tipoFactura;

	@ManyToOne
	private Comitente comitente;
	
	public EstadoFactura getEstadoFactura() {
		return estadoFactura;
	}
	public void setEstadoFactura(EstadoFactura estado) {
		this.estadoFactura = estado;
	}
	
	public String getCondicionVenta() {
		return condicionVenta;
	}
	public List<DetalleFactura> getDetalleFactura() {
		return detalleFactura;
	}
	public Date getFecha() {
		return fecha;
	}
	public Long getIdFactura() {
		return idFactura;
	}
	
	public Integer getNumeroRemito() {
		return numeroRemito;
	}
	public String getObra() {
		return obra;
	}
	public TipoFactura getTipoFactura() {
		return tipoFactura;
	}
	public TipoIva getTipoIva() {
		return tipoIva;
	}

	public Float getTotalFactura() {
		return totalFactura;
	}

	public void setCondicionVenta(String condicionVenta) {
		this.condicionVenta = condicionVenta;
	}

	public void setDetalleFactura(List<DetalleFactura> detalleFactura) {
		this.detalleFactura = detalleFactura;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public void setNumeroRemito(Integer numeroRemito) {
		this.numeroRemito = numeroRemito;
	}

	public void setObra(String obra) {
		this.obra = obra;
	}

	public void setTipoFactura(TipoFactura tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public void setTipoIva(TipoIva tipoIva) {
		this.tipoIva = tipoIva;
	}

	public void setTotalFactura(Float pTotalFactura) {
		this.totalFactura = pTotalFactura;
	}

	public Comitente getComitente() {
		return comitente;
	}
	public void setComitente(Comitente comitente) {
		this.comitente = comitente;
	}
	
	public Float getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}
	
	public Float getTotalIva() {
		return totalIva;
	}
	public void setTotalIva(Float totalIva) {
		this.totalIva = totalIva;
	}

	
	/**
	 * Calcula el total de la factura
	 */
	public void calcularTotal(){
		System.out.println("Calcular total req = subtotal = "+this.subtotal+" total = "+this.totalIva);
		if ((this.subtotal!=null) && (this.totalIva!=null)){
			this.totalFactura = this.subtotal+this.totalIva;
			this.totalFactura = FacturacionBean.redondearADosCifras(this.totalFactura);
		}
		else{
			this.totalFactura = 0f;
		}
	}
	
	public void calcularSubTotalDetalle() {
		System.out.println("Calcular subtotal desde total requerido "+this.detalleFactura);
		float locSubtotal = 0f;
		for (DetalleFactura locDetalleFactura : this.detalleFactura){
			locSubtotal+=locDetalleFactura.getTotal();
		}
		this.subtotal = FacturacionBean.redondearADosCifras(locSubtotal);
		//this.subtotal= locSubtotal;
		
	}
	
	public void calcularTotalIva(){
		System.out.println("calcular totalIva req subtotal = "+this.subtotal+" tipoIva.porcentaje = "+((tipoIva!=null)?tipoIva.getPorcentaje():"sintipoiva"));
		if ((this.subtotal!=null) && (this.tipoIva !=null)){
			this.totalIva = this.getSubtotal()*(tipoIva.getPorcentaje()/100);
			this.totalIva = FacturacionBean.redondearADosCifras(this.totalIva);
		}
		else{
			this.totalIva = 0f;
		}
	}
	
	/**
	 * Calcula el subtotal de la factura a partir del total
	 */
	public void calcularSubTotalDesdeTotal() {
		System.out.println("calcular subtotal desde total requeridos totalFactura = "+this.totalFactura+" totalIva = "+this.totalIva);
		if ((this.totalFactura!=null) && (this.totalIva!=null)){
			this.subtotal = this.totalFactura-this.totalIva;
		}
		else {
			this.subtotal = 0f;
		}
	}
	
	@Override
	public String toString() {
		String locSalida = (this.tipoFactura==null)?"":("Tipo "+this.tipoFactura.toString());
		locSalida+=" Nº "+this.getNumeroFactura();
		return locSalida;
	}
}
