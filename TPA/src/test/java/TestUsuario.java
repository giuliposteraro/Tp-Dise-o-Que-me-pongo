import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Guardarropa;
import domain.color.EColor;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.Tipo;
import domain.usuario.Usuario;
import exceptions.*;

public class TestUsuario {

	Prenda remera;
	Prenda remera2;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Guardarropa guardarropa;
	Guardarropa guardarropa2;
	Usuario usuario;
	
	@Before
	public void crearPrendas() {
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
		
		guardarropa = new Guardarropa();
		guardarropa2 = new Guardarropa();
		usuario = new Usuario();
		
		usuario.agregarGuardarropa(guardarropa);
		usuario.agregarGuardarropa(guardarropa2);
	}
	
	@Test
	public void agregarUnGuardarropa() {
		assertTrue(usuario.tieneGuardarropa(guardarropa));
	}
	
	@Test
	public void seGeneraSugerenciaCorrectamente() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		
		
		assertEquals(1,usuario.generarSugerencias(guardarropa).size());
		
	}
	
	@Test(expected = NoSePuedeGenerarSugerencia.class)
	public void fallanSugerenciasSiFaltaPrenda() {
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		usuario.generarSugerencias(guardarropa);
	}
	
	@Test(expected = NoSePuedenCompartirPrendas.class)
	public void noSePuedeAgregarLaMismaPrendaADosGuardarropasDistintos() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(remera, guardarropa2);
	}
	
	@Test(expected = AccesoAGuardarropaDenegado.class)
	public void NoSePuedeAccederAUnGuardarropaAjeno() {
		Guardarropa guardarropa3 = new Guardarropa();
		usuario.agregarPrenda(remera, guardarropa3);
	}
}
