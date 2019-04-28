import static org.junit.Assert.*;

import org.junit.Test;

import domain.ConstructorPrenda;
import domain.ECategoria;
import domain.EColor;
import domain.ETela;
import domain.Prenda;
import domain.Tipo;

import exceptions.*;

public class TestPrenda {

	@Test
	public void saberDeQueTipoEsUnaPrenda() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(Tipo.REMERA, p.getTipo());
	}
	
	@Test
	public void saberDeQueCategoriaEsUnaPrenda() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ECategoria.SUPERIOR, p.getCategoria());
	}
	
	@Test
	public void saberDeQueTelaEsUnaPrenda() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ETela.ALGODON, p.getTela());
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTipoFalla() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorFalla() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorPrimarioFalla() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.NINGUNO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaConDosColoresIgualesFalla() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.ROJO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTelaFalla() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void unaPrendaConUnaTelaInconsistenteFalla() {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.CUERO);
		c.crear();
	}
}
