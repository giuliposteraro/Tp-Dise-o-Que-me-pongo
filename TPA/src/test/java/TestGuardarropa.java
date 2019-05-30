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
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;

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
		
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		remera2 = c.crear();
		
		c.setTipo(RepoTipos.BUZO);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.BLANCO);
		buzo = c.crear();
		
		c.setTipo(RepoTipos.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();
		
		c.setTipo(RepoTipos.ZAPATILLAS);
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();
		
		c.setTipo(RepoTipos.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();
	
		guardarropa = new Guardarropa(new GuardarropaIlimitado());
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
	
	public void NoTieneLugarGuardarropaLimitadoConMasPrendas() {
		guardarropa.setTipoGuardarropa(new GuardarropaLimitado());
		guardarropa.agregarPrenda(remera2);
		guardarropa.agregarPrenda(zapatillas);
		guardarropa.agregarPrenda(buzo);
		guardarropa.agregarPrenda(pantalon);
		guardarropa.agregarPrenda(reloj);
		assertFalse(guardarropa.tieneLugar());
	}
}
