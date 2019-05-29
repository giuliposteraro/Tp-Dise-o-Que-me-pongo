import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import domain.Config;
import domain.color.EColor;
import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaIlimitado;
import domain.guardarropa.GuardarropaLimitado;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.prenda.RepoPrendas;
import domain.tipoPrenda.ETela;
import exceptions.CapacidadDelGuardarropaLlena;

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
	
		guardarropa = new Guardarropa(new GuardarropaIlimitado());
		guardarropa.agregarPrenda(Prenda.SIN_ABRIGO);
		guardarropa.agregarPrenda(Prenda.SIN_ACCESORIO);
	}


	@Test
	public void agregarUnaPrenda() {
		guardarropa.agregarPrenda(remera);
		assertTrue(guardarropa.tienePrenda(remera));
	}
	
	@Test 
	public void tieneLugarGuardarropaIlimitado() {
		guardarropa.agregarPrenda(buzo);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(remera);
		assertTrue(guardarropa.tieneLugar());
	}
	
	@Test 
	public void tieneLugarGuardarropaLimitado() {
		Config.instance().getCapacidadMaxima();
		guardarropa.setTipoGuardarropa(new GuardarropaLimitado());
		guardarropa.agregarPrenda(buzo);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(reloj);
		assertTrue(guardarropa.tieneLugar());
	}
	
	@Test(expected = CapacidadDelGuardarropaLlena.class)
	public void tieneLugarGuardarropaLimitadoConMasPrendas() {
		Config.instance().getCapacidadMaxima();
		guardarropa.setTipoGuardarropa(new GuardarropaLimitado());
		guardarropa.agregarPrenda(remera2);
		guardarropa.agregarPrenda(zapatillas);
		guardarropa.agregarPrenda(buzo);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(reloj);
		guardarropa.tieneLugar();
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
