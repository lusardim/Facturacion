package ar.com.jumperinformatica.core.system.bean;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.system.bean.LoginBean;

public class LoginTest {
	
	private static LoginBean loginBean;
	
	@BeforeClass
	public static void setUp(){
		loginBean = new LoginBean(); 
	}
	
	
	@Test
	public void testGetUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUsuarioLoggeadoListener() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveUsuarioLoggeadoListener() {
		fail("Not yet implemented");
	}

	@Test
	public void testLogin() throws Exception {
		String[] locNombresUsuario = {null, "pepe", "usuario","","root"};
		String[] locClaves = {null, "", "pepe", "clave","root"};
		for (String cadaNombreUsuario : locNombresUsuario){
			for (String cadaClave : locClaves){
				System.out.println("Evanluado usuario: "+cadaNombreUsuario+" | clave:"+cadaClave );
				try{
					loginBean.login(cadaNombreUsuario, cadaClave);
					System.out.println(loginBean.getUsuario());
				}
				catch(LogicaException e){
					System.out.println("\t->Exception:"+e.getMessage());
				}
			}
		}
	}

	@Test
	public void testLogout() {
		fail("Not yet implemented");
	}

}
