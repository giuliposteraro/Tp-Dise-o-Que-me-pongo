import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.color.EColor;
import domain.guardarropa.Atuendo;
import domain.guardarropa.ConstructorAtuendo;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import exceptions.ParametrosNoValidos;

public class TestAtuendo {

	@Parameter
	Prenda remera;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Set<Prenda> prendas;
	
	@Before
	public void init() {
		ConstructorPrenda c = new ConstructorPrenda();

		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();

		c.setTipo(RepoTipos.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();

		c.setTipo(RepoTipos.ZAPATILLAS);
		c.setTela(ETela.HILO);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();

		c.setTipo(RepoTipos.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();
		
		prendas = new HashSet<Prenda>();
		prendas.addAll(Arrays.asList(remera, pantalon, zapatillas, reloj));
	}
	
	@Test
	public void unConjuntoDePrendasEsValido() {
		assertTrue(Atuendo.esAtuendoValido(prendas));
	}
	
	@Test
	public void unConjuntoDePrendasNoEsValido() {
		prendas.remove(pantalon);
		assertFalse(Atuendo.esAtuendoValido(prendas));
	}
	
	@Test(expected = ParametrosNoValidos.class)
	public void constructorNoPuedeCrearAtuendoSiNoEsValido() {
		prendas.remove(pantalon);
		ConstructorAtuendo cA = new ConstructorAtuendo();
		cA.agregarPrendas(prendas);
		cA.crear();
	}
	
}