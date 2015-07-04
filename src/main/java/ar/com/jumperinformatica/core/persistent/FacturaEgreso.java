package ar.com.jumperinformatica.core.persistent;

import javax.persistence.Entity;

@Entity
public class FacturaEgreso extends Factura{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5074431880981996917L;

	@Override
	public String toString() {
		return "Egreso "+super.toString();
	}
}
