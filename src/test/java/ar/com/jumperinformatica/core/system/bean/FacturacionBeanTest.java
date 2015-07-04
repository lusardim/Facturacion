package ar.com.jumperinformatica.core.system.bean;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.jumperinformatica.core.enums.TipoFactura;
import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.system.bean.FacturacionBean;

public class FacturacionBeanTest {
	private static FacturacionBean facturacionBean;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		facturacionBean = new FacturacionBean(); 
	}

	@Test
	public void testAddFactura() {
		fail("Not yet implemented");
	}

	@Test
	public void testAnularFactura() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindListaFacturasIngreso() throws Exception {
		try {
			System.out.println(facturacionBean.findListaFacturasEgreso(null, null));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Test
	public void testFindListaFacturasEgreso() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetListaParametrosBusqueda() {
		fail("Not yet implemented");
	}

	@Test
	public void getSiguienteNumeroFacturaEgreso(){
		try {
			System.out.println(facturacionBean.getSiguienteNumeroFacturaEgreso(TipoFactura.A));
		} catch (LogicaException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testFindListaFacturasEgresoConParametros(){
		try{
			List<TipoFactura> listaTipoList = new ArrayList<TipoFactura>();
			listaTipoList.add(TipoFactura.A);
			listaTipoList.add(TipoFactura.B);
			//System.out.println(facturacionBean.findListaFacturasEgreso(new Date(2000,1,1),new Date(2009,1,1),listaTipoList ));
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}
