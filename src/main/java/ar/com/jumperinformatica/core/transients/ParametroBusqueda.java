package ar.com.jumperinformatica.core.transients;

public class ParametroBusqueda {
	private String nombreAtributo;
	private String nombre;
	private Class<?> tipoAtributo;
	
	public Class<?> getTipoAtributo() {
		return tipoAtributo;
	}
	public void setTipoAtributo(Class<?> tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}
	public String getNombreAtributo() {
		return nombreAtributo;
	}
	public void setNombreAtributo(String nombreAtributo) {
		this.nombreAtributo = nombreAtributo;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}
}
