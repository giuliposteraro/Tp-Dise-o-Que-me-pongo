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
	public void saberDeQueTipoEsUnaPrenda() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(Tipo.REMERA, p.getTipo());
	}
	
	@Test
	public void saberDeQueCategoriaEsUnaPrenda() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ECategoria.SUPERIOR, p.getCategoria());
	}
	
	@Test
	public void saberDeQueTelaEsUnaPrenda() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ETela.ALGODON, p.getTela());
	}
	
	@Test(expected = Exception.class)
	public void crearPrendaSinTipoFalla() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = Exception.class)
	public void crearPrendaSinColorFalla() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = Exception.class)
	public void crearPrendaSinColorPrimarioFalla() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.NINGUNO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = Exception.class)
	public void crearPrendaConDosColoresIgualesFalla() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.ROJO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = Exception.class)
	public void crearPrendaSinTelaFalla() throws ParametrosNoValidos {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.crear();
	}
	
	@Test(expected = Exception.class)
	public void unaPrendaConUnaTelaInconsistenteFalla() throws Exception {
		ConstructorPrenda c = new ConstructorPrenda();
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.CUERO);
		c.crear();
	}
}
