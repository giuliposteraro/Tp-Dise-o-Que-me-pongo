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

	@Parameter(0)
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
	public void agregarUnaPrenda() {
		Guardarropa g = new Guardarropa();
		g.agregarPrenda(remera);
		assertTrue(g.tienePrenda(remera));
	}
	
	@Test
	public void unGuardarropaConCuatroPrendasGeneraUnAtuendoConEsasPrendas() throws NoSePuedeGenerarSugerencia {
		Guardarropa g = new Guardarropa();
		g.agregarPrenda(remera);
		g.agregarPrenda(pantalon);
		g.agregarPrenda(zapatillas);
		g.agregarPrenda(reloj);
		
		List<Prenda> esperadas = Arrays.asList(remera, pantalon, zapatillas, reloj);
		
		assertEquals(esperadas, g.generarSugerencias().get(0).prendas());
	}
	
	@Test
	public void unGuardarropaConDosRemerasGeneraDosAtuendos() throws NoSePuedeGenerarSugerencia {
		Guardarropa g = new Guardarropa();
		g.agregarPrenda(remera);
		g.agregarPrenda(remera2);
		g.agregarPrenda(pantalon);
		g.agregarPrenda(zapatillas);
		g.agregarPrenda(reloj);
		
		assertEquals(2, g.generarSugerencias().size());
	}
	
	@Test(expected = Exception.class)
	public void siNoHayAlgunaCategoriaFalla() throws NoSePuedeGenerarSugerencia{
		Guardarropa g = new Guardarropa();
		g.agregarPrenda(reloj);
		g.agregarPrenda(pantalon);
		g.agregarPrenda(zapatillas);

		g.generarSugerencias();
	}
	
}
