package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name="tipo_iva")
public class TipoIva implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 405059976414703161L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idTipoIva;
	@Basic(optional=false)
	private String descripcionIva;
	@Basic(optional=false)
	@Column(nullable = false, columnDefinition = "float")
	private BigDecimal porcentaje;
	
	public Long getIdTipoIva() {
		return idTipoIva;
	}
	public void setIdTipoIva(Long idTipoIva) {
		this.idTipoIva = idTipoIva;
	}

	public String getDescripcionIva() {
		return descripcionIva;
	}
	public void setDescripcionIva(String descripcionIva) {
		this.descripcionIva = descripcionIva;
	}
	public BigDecimal getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(BigDecimal porcentaje) {
		this.porcentaje = porcentaje;
	}
	
	@Override
	public String toString() {
		return this.getDescripcionIva();
	}
}
