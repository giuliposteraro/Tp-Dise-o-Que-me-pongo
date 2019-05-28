import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.Atuendo;
import domain.ConstructorPrenda;
import domain.EColor;
import domain.ETela;
import domain.Guardarropa;
import domain.Prenda;
import domain.RepoPrendas;
import domain.Usuario;
import exceptions.*;

public class TestUsuario {

	Prenda remera;
	Prenda remera2;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Prenda campera;
	Guardarropa guardarropa;
	Guardarropa guardarropa2;
	Usuario usuario;
	
	@Before
	public void crearPrendas() {
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(RepoPrendas.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(RepoPrendas.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		remera2 = c.crear();
		
		c.setTipo(RepoPrendas.CAMPERA);
		c.setTela(ETela.CUERO);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		campera = c.crear();
		
		c.setTipo(RepoPrendas.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();
		
		c.setTipo(RepoPrendas.ZAPATILLAS);
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();
		
		c.setTipo(RepoPrendas.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();
		
		
		guardarropa = new Guardarropa();
		guardarropa.agregarPrenda(Atuendo.SIN_ABRIGO);
		guardarropa.agregarPrenda(Atuendo.SIN_ACCESORIO);
		guardarropa2 = new Guardarropa();
		guardarropa.agregarPrenda(Atuendo.SIN_ABRIGO);
		guardarropa.agregarPrenda(Atuendo.SIN_ACCESORIO);
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
		usuario.agregarPrenda(campera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		
		usuario.generarSugerencias(guardarropa);
		
		assertEquals(1, usuario.getSugerenciasPendientes().size());
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
