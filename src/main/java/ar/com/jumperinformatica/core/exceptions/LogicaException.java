package ar.com.jumperinformatica.core.exceptions;


/**
 * Error de lógica
 * @author mal
 *
 */
public class LogicaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5500629459856601574L;

	public LogicaException(String cadena){
		super(cadena);
	}
}	
