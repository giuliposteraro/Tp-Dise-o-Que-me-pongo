import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.clima.ClimaMock;
import domain.color.EColor;
import domain.eventos.Evento;
import domain.guardarropa.Atuendo;
import domain.guardarropa.Guardarropa;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.sugerencias.EstadoSugerencia;
import domain.sugerencias.Sugerencia;
import domain.sugerencias.Sugeridor;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
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
	Evento evento;
	Sugeridor s;
	Atuendo a;
	
	@Before
	public void crearPrendas() {
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		remera2 = c.crear();
		
		c.setTipo(RepoTipos.BUZO);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.BLANCO);
		buzo = c.crear();
		
		c.setTipo(RepoTipos.CAMPERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.AZUL, EColor.NEGRO);
		campera = c.crear();
		
		c.setTipo(RepoTipos.PANTALON);
		c.setTela(ETela.JEAN);
		c.setColor(EColor.AZUL, EColor.NINGUNO);
		pantalon = c.crear();
		
		c.setTipo(RepoTipos.ZAPATILLAS);
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();
		
		c.setTipo(RepoTipos.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();
		
		
		
		usuario = new Usuario(new UsuarioPremium());
		guardarropa = usuario.crearGuardarropa();
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(buzo, guardarropa);
		usuario.agregarPrenda(campera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		a = new Atuendo(Arrays.asList(remera, buzo, pantalon, zapatillas, reloj));
		evento = new Evento(usuario, guardarropa, LocalDate.now(), "", "");
		s = new Sugeridor(evento, new ClimaMock());
	}
	
	@Test
	public void calificandoUnaSugerenciaVariaLaToleranciaAlFrio() {
		Sugerencia sug = new Sugerencia(a,evento);
		usuario.agregarSugerencia(sug);
		usuario.revisarSugerencia(sug,EstadoSugerencia.ACEPTADA);
		sug.setCalificacion(2);
		assertEquals(2.0, usuario.getToleranciaAlFrio(),0.001);
	}
	
	@Test
	public void obtenerElNivelDeAbrigoDeUnAtuendo() {				
		assertEquals(15.0, a.getNivelAbrigo().doubleValue(), 0.001);
	}
	
	@Test
	public void obtenerElNivelDeAbrigoDeUnaSugerencia() {
		Atuendo a = new Atuendo(Arrays.asList(remera, buzo, pantalon, zapatillas, reloj));
		Sugerencia s = new Sugerencia(a, evento);
		assertEquals(15.0, s.getNivelAbrigo().doubleValue(), 0.001);
	}
		
	@Test
	public void generarSugerenciasParaEstaConfiguracionGenera8Sugerencias() {
		s.generarSugerencias();
		
		assertEquals(8, usuario.getSugerenciasPendientes().size());
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
