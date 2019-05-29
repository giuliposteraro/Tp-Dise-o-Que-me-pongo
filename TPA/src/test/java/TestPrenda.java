import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.ConstructorPrenda;
import domain.ECategoria;
import domain.EColor;
import domain.ETela;
import domain.Prenda;
import domain.Tipo;
import exceptions.*;

public class TestPrenda {

	ConstructorPrenda c;
	
	@Before
	public void crearConstructorPrenda() {
		c = new ConstructorPrenda();
	}
	
	@Test
	public void saberDeQueTipoEsUnaPrenda() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(Tipo.REMERA, p.getTipo());
	}
	
	@Test
	public void saberDeQueCategoriaEsUnaPrenda() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ECategoria.SUPERIOR, p.getCategoria());
	}
	
	@Test
	public void saberDeQueTelaEsUnaPrenda() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ETela.ALGODON, p.getTela());
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTipoFalla() {
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorFalla() {
		c.setTipo(Tipo.REMERA);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorPrimarioFalla() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.NINGUNO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaConDosColoresIgualesFalla() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.ROJO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTelaFalla() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void unaPrendaConUnaTelaInconsistenteFalla() {
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.CUERO);
		c.crear();
	}
}
