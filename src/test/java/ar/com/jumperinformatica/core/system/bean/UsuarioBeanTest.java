package ar.com.jumperinformatica.core.system.bean;

import static org.junit.Assert.fail;

import org.junit.BeforeClass;
import org.junit.Test;

import ar.com.jumperinformatica.core.exceptions.LogicaException;
import ar.com.jumperinformatica.core.persistent.Usuario;
import ar.com.jumperinformatica.core.system.bean.UsuarioBean;

public class UsuarioBeanTest {
	private static UsuarioBean usuarioBean;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		usuarioBean = new UsuarioBean();
	}

	@Test
	public void testAddUsuario() throws LogicaException {
		Usuario locUsuario = new Usuario();
		locUsuario.setClave("caca");
		locUsuario.setNombre("pepe");
		usuarioBean.addUsuario(locUsuario);
	}

	@Test
	public void testUpdateUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteUsuario() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindListaUsuarios() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetListaParametrosBusqueda() {
		fail("Not yet implemented");
	}

}
