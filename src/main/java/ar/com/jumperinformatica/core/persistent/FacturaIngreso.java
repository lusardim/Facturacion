package ar.com.jumperinformatica.core.persistent;

import javax.persistence.Entity;

@Entity
public class FacturaIngreso extends Factura{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8434492722523163082L;

	@Override
	public String toString() {
		return "Ingreso "+super.toString();
	}
}
