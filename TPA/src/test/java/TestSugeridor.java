import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.color.EColor;
import domain.guardarropa.Atuendo;
import domain.guardarropa.Guardarropa;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.prenda.RepoPrendas;
import domain.sugerencias.Sugerencia;
import domain.sugerencias.Sugeridor;
import domain.tipoPrenda.ETela;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;

public class TestSugeridor {

	
	@Parameter
	Prenda remera;
	Prenda remera2;
	Prenda buzo;
	Prenda campera;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Usuario usuario;
	Guardarropa guardarropa;
	Sugeridor s;
	
	@Before
	public void crearPrendas() {
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(RepoPrendas.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(RepoPrendas.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		remera2 = c.crear();
		
		c.setTipo(RepoPrendas.BUZO);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.BLANCO);
		buzo = c.crear();
		
		c.setTipo(RepoPrendas.CAMPERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.AZUL, EColor.NEGRO);
		campera = c.crear();
		
		c.setTipo(RepoPrendas.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();
		
		c.setTipo(RepoPrendas.ZAPATILLAS);
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();
		
		c.setTipo(RepoPrendas.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();
		
		usuario = new Usuario(new UsuarioPremium());
		guardarropa = usuario.crearGuardarropa();
		usuario.agregarPrenda(Prenda.SIN_ABRIGO, guardarropa);
		usuario.agregarPrenda(Prenda.SIN_ACCESORIO, guardarropa);
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(buzo, guardarropa);
		usuario.agregarPrenda(campera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		s = new Sugeridor(usuario, guardarropa);
	}
	
	@Test
	public void obtenerElNivelDeAbrigoDeUnAtuendo() {
		
		Atuendo a = new Atuendo(Arrays.asList(remera, buzo, pantalon, zapatillas, reloj));
				
		assertEquals(15.0, a.getNivelAbrigo().doubleValue(), 0.001);
	}
	
	@Test
	public void obtenerElNivelDeAbrigoDeUnaSugerencia() {
		
		Atuendo a = new Atuendo(Arrays.asList(remera, buzo, pantalon, zapatillas, reloj));
		
		Sugerencia s = new Sugerencia(a, usuario);
		
		assertEquals(15.0, s.getNivelAbrigo().doubleValue(), 0.001);
	}
	
	@Test
	public void obtenerElCoeficienteDeAbrigo() {
		
		guardarropa.agregarPrenda(campera);
		
		campera.ponerSobre(buzo);
		
		Atuendo a = new Atuendo(Arrays.asList(remera, campera, pantalon, zapatillas, reloj));
		
		Sugerencia s = new Sugerencia(a, usuario);

//		System.out.println(s.coeficienteDeAbrigo(12.0));
		
		assertTrue(s.coeficienteDeAbrigo(20.0).doubleValue() >= 0.0 && s.coeficienteDeAbrigo(20.0).doubleValue() <= 1.0);
	}
	
	@Test
	public void generarSugerenciasParaEstaConfiguracionDevuelveMaximaCantidadDeSugerencias() {
		
		s.generarSugerencias();
		
		assertEquals(5, usuario.getSugerenciasPendientes().size());
	}
	
	@Test
	public void combinarAbrigos() {
		Set<Prenda> abr = new HashSet<Prenda>();
		abr.add(buzo);
		abr.add(campera);
		abr.add(Prenda.SIN_ABRIGO);
		Set<Prenda> r = s.obtenerCombinacionesDeAbrigos(abr);
		assertEquals(4, r.size());
	}
}
