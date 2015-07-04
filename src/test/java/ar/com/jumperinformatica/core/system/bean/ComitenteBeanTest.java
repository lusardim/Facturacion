package ar.com.jumperinformatica.core.system.bean;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Comitente;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;


public class ComitenteBeanTest {
	private ComitenteBean comitenteBean;
	
	@Before
	public void setUp() throws Exception {
		comitenteBean = new ComitenteBean();
	}

	
	@Test
	public void testAddComitente() throws LogicaException {
		Comitente[] locComitentes = new Comitente[4];
		locComitentes[0] = new Comitente();
		locComitentes[0].setCuit("20-30782653-3");
		locComitentes[0].setDomicilio("Las calandrias 2333");
		locComitentes[0].setNombre("lalalal");
		
		locComitentes[1] = new Comitente();
		locComitentes[1].setCuit(null);
		locComitentes[1].setEstadoComitente(null);
		locComitentes[1].setNombre(null);
		locComitentes[1].setDomicilio(null);
		
		
		locComitentes[2] = new Comitente();
		locComitentes[2].setCuit("algo raro");
		locComitentes[2].setNombre(null);
		locComitentes[2].setDomicilio("Un domicilio");
		
		
		locComitentes[3] = new Comitente();
		locComitentes[3].setCuit("20-30432240-2");
		locComitentes[3].setNombre("Pepe el pepón con eÑe");
		locComitentes[3].setDomicilio("domicilio domiciliario");
		
		for (Comitente cadaComitente : locComitentes){
			try{
				System.out.println("Agregando el comitente "+cadaComitente);
				comitenteBean.addComitente(cadaComitente);
			}
			catch(LogicaException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Test
	public void testUpdateComitente() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveComitente() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetFacturacionComitente() throws Exception{
		Comitente locComitente = comitenteBean.findListaComitentes(null, null).get(0);
		Calendar locCalendar = Calendar.getInstance();
		locCalendar.add(Calendar.DAY_OF_YEAR, -100);
		Date locFechaDesde = locCalendar.getTime();
		locCalendar=Calendar.getInstance();
		locCalendar.add(Calendar.DAY_OF_YEAR, 12);
		Date locFechaHasta = locCalendar.getTime();
		System.out.println(comitenteBean.getFacturacionComitente(locComitente, locFechaDesde,locFechaHasta));
	}
}
