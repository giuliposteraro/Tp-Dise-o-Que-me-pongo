import static org.junit.Assert.*;

import org.junit.Test;

import domain.Guardarropa;
import domain.Usuario;

public class TestUsuario {

	@Test
	public void agregarUnGuardarropa() {
		Guardarropa g = new Guardarropa();
		Usuario u = new Usuario();
		u.agregarGuardarropa(g);
		assertTrue(u.tieneGuardarropa(g));
	}

}
