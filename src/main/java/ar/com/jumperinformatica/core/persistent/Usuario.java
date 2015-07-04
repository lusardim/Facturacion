package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries(
	{
		@NamedQuery(name="Usuario.login",
					query="select u from Usuario u " +
							"	where u.nombre = :nombreUsuario " +
							"      and u.clave = :clave"
		)
	
	}
)
@Table(name="usuario")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7829022190626627304L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long idUsuario;
	
	@Basic(optional=false)
	@Column(unique=true)
	private String nombre;
	@Basic(optional=false)
	private String clave;
	private String nombrePersona;
	
	public String getNombrePersona() {
		return nombrePersona;
	}
	public void setNombrePersona(String nombrePersona) {
		this.nombrePersona = nombrePersona;
	}
	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

	@Override
	public String toString() {
		return (this.getNombrePersona()!=null)?this.getNombrePersona():this.getNombre();
	}
}
