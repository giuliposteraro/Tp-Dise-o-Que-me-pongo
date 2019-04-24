import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.ConstructorPrenda;
import domain.EColor;
import domain.ETela;
import domain.Guardarropa;
import domain.Prenda;
import domain.Tipo;

public class TestGuardarropa {

	@Parameter(0)
	public Prenda remera;
	public Prenda pantalon;
	public Prenda zapatillas;
	public Prenda reloj;
	
	@Before
	public void crearBuzo() throws Exception {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(Tipo.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();
		
		c.setTipo(Tipo.ZAPATILLAS);
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		reloj = c.crear();
		
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
	
	

}
