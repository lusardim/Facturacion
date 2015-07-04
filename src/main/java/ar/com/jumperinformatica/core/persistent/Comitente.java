package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import ar.com.jumperinformatica.core.enums.EstadoComitente;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@Table(name="comitente")
public class Comitente implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1991541328017665841L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idComitente;
	
	@Basic(optional=false)
	private String nombre;
	private String cuit;
	private String domicilio;
	@Enumerated(EnumType.STRING)
	private EstadoComitente estadoComitente=EstadoComitente.ACTIVO;
	
	@OneToMany(mappedBy="comitente")
	private List<Factura> listaFacturas;
	
	public List<Factura> getListaFacturas() {
		return listaFacturas;
	}
	public void setListaFacturas(List<Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}
	public EstadoComitente getEstadoComitente() {
		return estadoComitente;
	}
	public void setEstadoComitente(EstadoComitente estado) {
		this.estadoComitente = estado;
	}
	public Long getIdComitente() {
		return idComitente;
	}
	public void setIdComitente(Long idComitente) {
		this.idComitente = idComitente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	

	@Override
	public String toString() {
		String locRetorno = "["+((this.getCuit()!=null)?this.getCuit():"Sin cuit")+"]";
		locRetorno+=" "+((this.getNombre()!=null)?this.getNombre():"");
		return locRetorno;
	}
	
	
}
