package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;

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
	private String descripcion="";
	private Integer cantidad=1;
	private Float precio=0f;
	private Float total;
	
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
	
	public Float getPrecio() {
		return precio;
	}
	public void setPrecio(Float precio) {
		this.precio = precio;
		this.calcularTotal();
	}

	public Float getTotal(){
		if (this.total==null){
			this.calcularTotal();
		}
		return this.total;
	}
	
	public void setTotal(Float pTotal){
		this.total=pTotal;	
		if (!this.total.equals(this.getCantidad()* this.getPrecio())){
			this.setPrecio(this.getTotal()/this.getCantidad());
		}
	}
	
	private void calcularTotal() {
		this.setTotal(this.getCantidad()*this.getPrecio());
	}

}
