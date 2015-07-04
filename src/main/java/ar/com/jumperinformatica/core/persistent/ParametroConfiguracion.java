package ar.com.jumperinformatica.core.persistent;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParametroConfiguracion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7748547787123517082L;
	
	@Id
	private String nombre;
	@Basic(optional=false)
	private String valor;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	@Override
	public String toString() {
		return this.getNombre()+":["+this.getValor()+"]";
	}
}
