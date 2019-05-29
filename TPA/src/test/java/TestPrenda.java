import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

<<<<<<< HEAD
import domain.ConstructorPrenda;
import domain.ECategoria;
import domain.EColor;
import domain.ETela;
import domain.Prenda;
import domain.Tipo;
=======
import domain.color.EColor;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.prenda.RepoPrendas;
import domain.tipoPrenda.*;
>>>>>>> master
import exceptions.*;

public class TestPrenda {

	ConstructorPrenda c;
	
	@Before
	public void crearConstructorPrenda() {
		c = new ConstructorPrenda();
	}
	
	@Test
	public void saberDeQueTipoEsUnaPrenda() {
		c.setTipo(RepoPrendas.BUZO);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(RepoPrendas.BUZO, p.getTipo());
	}
	
	@Test
	public void saberDeQueCategoriaEsUnaPrenda() {
		c.setTipo(RepoPrendas.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ECategoria.SUPERIOR, p.getCategoria());
	}
	
	@Test
	public void saberDeQueTelaEsUnaPrenda() {
		c.setTipo(RepoPrendas.REMERA);
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
		c.setTipo(RepoPrendas.REMERA);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorPrimarioFalla() {
		c.setTipo(RepoPrendas.REMERA);
		c.setColor(EColor.NINGUNO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaConDosColoresIgualesFalla() {
		c.setTipo(RepoPrendas.REMERA);
		c.setColor(EColor.ROJO, EColor.ROJO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTelaFalla() {
		c.setTipo(RepoPrendas.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void unaPrendaConUnaTelaInconsistenteFalla() {
		c.setTipo(RepoPrendas.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.CUERO);
		c.crear();
	}
}
