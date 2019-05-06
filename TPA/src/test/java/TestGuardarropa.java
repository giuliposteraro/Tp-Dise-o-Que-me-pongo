import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.ConstructorPrenda;
import domain.EColor;
import domain.ETela;
import domain.Guardarropa;
import domain.Prenda;
import domain.Tipo;

import exceptions.*;

public class TestGuardarropa {

	@Parameter
	Prenda remera;
	Prenda remera2;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Guardarropa guardarropa;
	
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
	}
	
	@Test
	public void agregarUnaPrenda() {
		guardarropa.agregarPrenda(remera);
		assertTrue(guardarropa.tienePrenda(remera));
	}
	
	@Test
	public void unGuardarropaConCuatroPrendasGeneraUnAtuendoConEsasPrendas() {
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(zapatillas);
		guardarropa.agregarPrenda(reloj);
		
		List<Prenda> esperadas = Arrays.asList(remera, pantalon, zapatillas, reloj);
		
		assertEquals(esperadas, guardarropa.generarSugerencias().get(0).prendas());
	}
	
	@Test
	public void unGuardarropaConDosRemerasGeneraDosAtuendos() {
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(remera2);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(zapatillas);
		guardarropa.agregarPrenda(reloj);
		
		assertEquals(2, guardarropa.generarSugerencias().size());
	}
	
	@Test(expected = NoSePuedeGenerarSugerencia.class)
	public void siNoHayAlgunaCategoriaFalla() {
		guardarropa.agregarPrenda(reloj);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(zapatillas);

		guardarropa.generarSugerencias();
	}
	
}
