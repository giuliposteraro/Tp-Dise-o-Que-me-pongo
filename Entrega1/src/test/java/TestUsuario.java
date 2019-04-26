import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.ConstructorPrenda;
import domain.EColor;
import domain.ETela;
import domain.Guardarropa;
import domain.Prenda;
import domain.Tipo;
import domain.Usuario;

import exceptions.*;

public class TestUsuario {

	public Prenda remera;
	public Prenda remera2;
	public Prenda pantalon;
	public Prenda zapatillas;
	public Prenda reloj;
	
	@Before
	public void crearPrendas() throws Exception {
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(Tipo.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(Tipo.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		remera2 = c.crear();
		
		c.setTipo(Tipo.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();
		
		c.setTipo(Tipo.ZAPATILLAS);
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();
		
		c.setTipo(Tipo.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();
	}
	
	@Test
	public void agregarUnGuardarropa() {
		Guardarropa g = new Guardarropa();
		Usuario u = new Usuario();
		u.agregarGuardarropa(g);
		assertTrue(u.tieneGuardarropa(g));
	}
	
	@Test
	public void seGeneraSugerenciaCorrectamente() throws NoSePuedeGenerarSugerencia {
		Guardarropa g = new Guardarropa();
		Usuario u = new Usuario();
		
		g.agregarPrenda(remera);
		g.agregarPrenda(pantalon);
		g.agregarPrenda(zapatillas);
		g.agregarPrenda(reloj);
		u.agregarGuardarropa(g);
		
		assertEquals(1,u.generarSugerencias(g).size());
		
	}

	@Test(expected = Exception.class)
	public void fallanSugerenciasSiFaltaPrenda() throws NoSePuedeGenerarSugerencia {
		Guardarropa g = new Guardarropa();
		Usuario u = new Usuario();
	
		g.agregarPrenda(pantalon);
		g.agregarPrenda(zapatillas);
		g.agregarPrenda(reloj);
		u.agregarGuardarropa(g);
		u.generarSugerencias(g);
	}
}
