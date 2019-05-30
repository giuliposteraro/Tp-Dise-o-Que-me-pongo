import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import domain.color.EColor;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.tipoPrenda.*;
import exceptions.*;

public class TestPrenda {

	ConstructorPrenda c;
	
	@Before
	public void crearConstructorPrenda() {
		c = new ConstructorPrenda();
	}
	
	@Test
	public void saberDeQueTipoEsUnaPrenda() {
		c.setTipo(RepoTipos.BUZO);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(RepoTipos.BUZO, p.getTipo());
	}
	
	@Test
	public void saberDeQueCategoriaEsUnaPrenda() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ECategoria.SUPERIOR, p.getCategoria());
	}
	
	@Test
	public void saberDeQueTelaEsUnaPrenda() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		Prenda p = c.crear();
		assertEquals(ETela.ALGODON, p.getTela());
	}
	
	@Test
	public void cargarUnaImagen() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.setImagen("/home/mauro/2019-ju-ma-group-03/TPA/img/remera-azul.png");
		Prenda p = c.crear();
		assertNotNull(p.getImagen());
	}
	
	@Test(expected = NoSePudoCargarLaImagen.class)
	public void cargarUnaImagenInexistenteFalla() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.setImagen("/home/mauro/2019-ju-ma-group-03/TPA/img/a.png");
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTipoFalla() {
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorFalla() {
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinColorPrimarioFalla() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.NINGUNO, EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaConDosColoresIgualesFalla() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.ROJO);
		c.setTela(ETela.ALGODON);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void crearPrendaSinTelaFalla() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.crear();
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void unaPrendaConUnaTelaInconsistenteFalla() {
		c.setTipo(RepoTipos.REMERA);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		c.setTela(ETela.CUERO);
		c.crear();
	}
}
