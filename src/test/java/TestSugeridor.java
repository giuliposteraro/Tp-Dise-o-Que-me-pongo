import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import domain.clima.ClimaMock;
import domain.clima.ECondicionClimatica;
import domain.color.EColor;
import domain.eventos.Evento;
import domain.eventos.EFrecuencia;
import domain.guardarropa.Atuendo;
import domain.guardarropa.Guardarropa;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.sugerencias.Calificacion;
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
		c.setTela(ETela.HILO);
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
		evento = new Evento(usuario, guardarropa, LocalDate.now(), "", "", EFrecuencia.UNICA);
		s = new Sugeridor(evento, new ClimaMock(20.0, ECondicionClimatica.CLEAR));

	}

	@Test
	public void generarSugerenciasParaEstaConfiguracionGenera8Sugerencias() {
		s.generarSugerencias();
		assertEquals(8, usuario.getSugerenciasPendientes().size());
	}

	@Test // TODO Testear que cambie el atuendo sugerido segun la tolerancia al frio
	public void calificandoUnaSugerenciaVariaLaToleranciaAlFrio() {
		Sugerencia sug = new Sugerencia(a, evento);
		usuario.agregarSugerencia(sug);
		usuario.aceptarSugerencia(sug);
		sug.setCalificacion(new Calificacion(2.0, 2.0, 2.0));
		assertEquals(3, usuario.getToleranciasAlFrio().size());
		assertEquals(2.0, usuario.getToleranciasAlFrio().get(0), 0.001);
	}
	
	@Test
	public void conVeinteGradosYcalificacionesFriolentasLaSugerenciaTieneAbrigo() {
		Sugerencia sug = new Sugerencia(a, evento);
		usuario.agregarSugerencia(sug);
		usuario.aceptarSugerencia(sug);
		a.setPrendasEnUso(false);
		sug.setCalificacion(new Calificacion(-5.0, 0.0, 0.0));
	
		s.generarSugerencias();
		
		Sugerencia sugerencia1 = usuario.getSugerenciasPendientes().stream()
		.max(Comparator.comparingDouble(s -> s.coeficienteDeAbrigo(20.0))).orElse(null);
		
		assertFalse(Atuendo.cantidadDeAbrigos(sugerencia1.getAtuendo().getPrendas())==0);
	}
	
	
}
