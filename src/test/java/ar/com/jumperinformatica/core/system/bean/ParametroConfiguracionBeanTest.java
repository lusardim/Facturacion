package ar.com.jumperinformatica.core.system.bean;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.jumperinformatica.core.persistent.ParametroConfiguracion;
import ar.com.jumperinformatica.core.system.bean.ConfiguracionBean;

public class ParametroConfiguracionBeanTest {
	private static ConfiguracionBean configuracionBean;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		configuracionBean = new ConfiguracionBean();
	}
	
	@Test
	public void testAddParametroConfiguracion(){
		try{
			ParametroConfiguracion locParametroConfiguracion = new ParametroConfiguracion();
			locParametroConfiguracion.setNombre("REALIZAR_BACKUP");
			locParametroConfiguracion.setValor("cmd /C start \"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\" " +
						"\"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\"");
			configuracionBean.setParametro(locParametroConfiguracion);
			
			
			locParametroConfiguracion.setNombre("RESTAURAR_BACKUP");
			locParametroConfiguracion.setValor("cmd /C start \"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\backup.bat\" " +
					"\"C:\\Archivos de programa\\MySQL\\MySQL Server 5.0\\bin\\restaurar.bat\" ");
			configuracionBean.setParametro(locParametroConfiguracion);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testGetParametroConfiguracion(){
		String[] locListaParametros = {null, "INEXISTENT", "REALIZAR_BACKUP","RESTAURAR_BACKUP"};
		
		for (String cadaParametro: locListaParametros){
			System.out.print("Nombre del Parámetro: "+cadaParametro);
			try{
				System.out.println("\tValor:"+configuracionBean.getParametro(cadaParametro));
			}
			catch(Exception e){
				System.out.println("\tError "+e.getMessage());
			}
		}
	}
}
