package ar.com.jumperinformatica.gui.enums;

import java.util.Calendar;
import java.util.Date;

public enum Periodo {
	DIARIO(Calendar.DATE,1), 
	SEMANAL(Calendar.WEEK_OF_MONTH,1), 
	QUINCENAL(Calendar.DATE,15), 
	MENSUAL(Calendar.MONTH,1), 
	BIMESTRAL(Calendar.MONTH,2), 
	TRIMESTRAL(Calendar.MONTH,3), 
	SEMESTRAL(Calendar.MONTH,6), 
	ANUAL(Calendar.YEAR,1), 
	OTRO(0,0)
	;
	
	private int variable;
	private int cantidad;
	
	private Periodo(int pVariable, int pCantidad){
		this.variable = pVariable;
		this.cantidad = pCantidad;
	}
	
	
	public Date getProximaFecha(Date pFecha){
		if (this==OTRO){
			return Calendar.getInstance().getTime();
		}
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.setTime(pFecha);
		locCalendar.add(variable, cantidad);
		locCalendar.add(Calendar.DATE, -1);
		return locCalendar.getTime();
	}
}
