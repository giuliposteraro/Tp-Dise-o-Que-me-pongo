import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.ConstructorPrenda;
import domain.ECategoria;
import domain.EColor;
import domain.ETela;
import domain.Prenda;
import domain.TipoPrenda;

import exceptions.*;

public class TestPrenda {

	ConstructorPrenda c;
	
	@Before
	public void crearConstructorPrenda() {
		c = new ConstructorPrenda();
	}
	
	@Test
	public void saberDeQueTipoEsUnaPrenda() {
		c.setTipo(TipoPrenda.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(TipoPrenda.REMERA, p.getTipo());
	}
	
	@Test
	public void saberDeQueCategoriaEsUnaPrenda() {
		c.setTipo(TipoPrenda.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ECategoria.SUPERIOR, p.getCategoria());
	}
	
	@Test
	public void saberDeQueTelaEsUnaPrenda() {
		c.setTipo(TipoPrenda.REMERA);
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
		c.setTipo(TipoPrenda.REMERA);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorPrimarioFalla() {
		c.setTipo(TipoPrenda.REMERA);
		c.setColor(EColor.NINGUNO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaConDosColoresIgualesFalla() {
		c.setTipo(TipoPrenda.REMERA);
		c.setColor(EColor.ROJO, EColor.ROJO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTelaFalla() {
		c.setTipo(TipoPrenda.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void unaPrendaConUnaTelaInconsistenteFalla() {
		c.setTipo(TipoPrenda.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.CUERO);
		c.crear();
	}
}
