import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.Atuendo;
import domain.Guardarropa;
import domain.color.EColor;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.prenda.RepoPrendas;
import domain.tipoPrenda.ETela;
import exceptions.*;

public class TestGuardarropa {

	@Parameter
	Prenda remera;
	Prenda remera2;
	Prenda buzo;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Guardarropa guardarropa;
	
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
		
		c.setTipo(RepoPrendas.BUZO);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.BLANCO);
		buzo = c.crear();
		
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
		guardarropa.agregarPrenda(Prenda.SIN_ABRIGO);
		guardarropa.agregarPrenda(Prenda.SIN_ACCESORIO);
	}
	
	@Test
	public void agregarUnaPrenda() {
		guardarropa.agregarPrenda(remera);
		assertTrue(guardarropa.tienePrenda(remera));
	}
	
	//TODO Actualizar Tests
	
//	@Test
//	public void unGuardarropaConCuatroPrendasPuedeGenerarMasDeUnAtuendoConEsasPrendas() {
//		guardarropa.agregarPrenda(remera);
//		guardarropa.agregarPrenda(buzo);
//		guardarropa.agregarPrenda(pantalon);
//		guardarropa.agregarPrenda(zapatillas);
//		guardarropa.agregarPrenda(reloj);
//		
//		assertEquals(4, guardarropa.generarSugerencias().size());
//	}
	
//	@Test
//	public void unGuardarropaConDosRemerasGeneraOchoAtuendos() {
//		guardarropa.agregarPrenda(remera);
//		guardarropa.agregarPrenda(remera2);
//		guardarropa.agregarPrenda(buzo);
//		guardarropa.agregarPrenda(pantalon);
//		guardarropa.agregarPrenda(zapatillas);
//		guardarropa.agregarPrenda(reloj);
//		
//		assertEquals(8, guardarropa.generarSugerencias().size());
//	}
	
//	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	
//	@Test
//	public void unGuardarropaConCuatroPrendasGeneraUnAtuendoConEsasPrendas() {
//		guardarropa.agregarPrenda(remera);
//		guardarropa.agregarPrenda(pantalon);
//		guardarropa.agregarPrenda(zapatillas);
//		guardarropa.agregarPrenda(reloj);
//		
//		List<Prenda> esperadas = Arrays.asList(remera, pantalon, zapatillas, reloj);
//		
//		assertEquals(esperadas, guardarropa.generarSugerencias().get(0).prendas());
//	}
//	
//	@Test
//	public void unGuardarropaConDosRemerasGeneraDosAtuendos() {
//		guardarropa.agregarPrenda(remera);
//		guardarropa.agregarPrenda(remera2);
//		guardarropa.agregarPrenda(pantalon);
//		guardarropa.agregarPrenda(zapatillas);
//		guardarropa.agregarPrenda(reloj);
//		
//		assertEquals(2, guardarropa.generarSugerencias().size());
//	}
//	
//	@Test(expected = NoSePuedeGenerarSugerencia.class)
//	public void siNoHayAlgunaCategoriaFalla() {
//		guardarropa.agregarPrenda(reloj);
//		guardarropa.agregarPrenda(pantalon);
//		guardarropa.agregarPrenda(zapatillas);
//
//		guardarropa.generarSugerencias();
//	}
	
}
