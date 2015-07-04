package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="detallefactura")
public class DetalleFactura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 663101003195945218L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idDetalleFactura;
	private Integer item;
	
	@Basic(optional=true)
	private String descripcion = "";
	private Integer cantidad = 1;
	private BigDecimal precio = new BigDecimal(0);
	private BigDecimal total;
	
	@ManyToOne(optional=false)
	private Factura factura;
	
	public Long getIdDetalleFactura() {
		return idDetalleFactura;
	}
	public void setIdDetalleFactura(Long idDetalleFactura) {
		this.idDetalleFactura = idDetalleFactura;
	}
	
	public Factura getFactura() {
		return factura;
	}
	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Integer getItem() {
		return item;
	}
	public void setItem(Integer item) {
		this.item = item;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		if (cantidad<=0){
			throw new IllegalArgumentException("La cantidad del producto debe ser mayor a cero.");
		}
		this.cantidad = cantidad;
		this.calcularTotal();
	}
	
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
		this.calcularTotal();
	}

	public BigDecimal getTotal(){
		if (this.total==null){
			this.calcularTotal();
		}
		return this.total;
	}
	
	public void setTotal(BigDecimal pTotal){
		this.total=pTotal;
		BigDecimal cantidad = new BigDecimal(this.cantidad);
		BigDecimal total = precio.multiply(cantidad);

		if (!this.total.equals(total)){
			this.setPrecio(this.getTotal().divide(cantidad));
		}
	}
	
	private void calcularTotal() {
		BigDecimal cantidad = new BigDecimal(this.cantidad);
		this.setTotal(cantidad.multiply(precio));
	}

}
