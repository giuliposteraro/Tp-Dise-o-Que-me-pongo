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
	public Prenda p;
	
	@Before
	public void crearBuzo() throws Exception {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.BUZO);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		p = c.crear();
	}
	
	@Test
	public void agregarPrendaAGuardarropa() throws Exception {
		Guardarropa g = new Guardarropa();
		g.agregarPrenda(p);
		assertTrue(g.contains(p));
	}

}
