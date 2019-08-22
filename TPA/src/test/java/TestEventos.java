import static org.junit.Assert.*;
import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import domain.eventos.*;
import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaLimitado;
import domain.Config;
import domain.clima.ClimaMock;
import domain.color.EColor;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import domain.prenda.*;
import domain.sugerencias.Sugeridor;
import domain.usuario.*;
import java.time.temporal.ChronoUnit;

public class TestEventos {
	
	@Parameter
	RepositorioEventos repo;
	Evento evento1;
	Evento evento2;
	Guardarropa guardarropa;
	Prenda prenda1;
	Usuario usuario;
	LocalDate fecha;
	LocalDate fecha2;
	LocalDate fecha3;
	GuardarropaLimitado grLimitado; 
	Prenda remera;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Sugeridor s;
	TareaSugerenciaEventos tareaRevision;

	@Before
	public void crearObj() {
		repo = new RepositorioEventos();
		usuario = new Usuario(new UsuarioPremium());
		guardarropa = usuario.crearGuardarropa();
		fecha = LocalDate.of(2023, 8, 4);
		fecha2 = LocalDate.now();
		tareaRevision = new TareaSugerenciaEventos();
		fecha3 = LocalDate.now().plus(1,ChronoUnit.DAYS);

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
		c.setTela(ETela.TELA);
		c.setColor(EColor.BLANCO, EColor.NEGRO);
		zapatillas = c.crear();

		c.setTipo(RepoTipos.RELOJ);
		c.setTela(ETela.NINGUNA);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
		reloj = c.crear();

		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
	}

	@Test
	public void agregarEvento() {
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha, "Boliche", "Fiesta", Frecuencia.UNICA));
		assertEquals(1, repo.eventos().size());
	}

	@Test
	public void detectaCorrectamenteSiEsProximoCasoPositivo() {
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha2, "Boliche", "Fiesta",Frecuencia.UNICA));
		assertEquals(1, repo.proximosEventos().size());
	}

	@Test
	public void detectaCorrectamenteSiEsProximoCasoNegativo() {
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha, "Boliche", "Fiesta",Frecuencia.UNICA));
		assertEquals(0, repo.proximosEventos().size());
	}

	@Test
	public void chequearSugerenciasUsuarioTrasAgregarEvento() {
	  Config.instance().setProveedor(new ClimaMock(20.0, "Clear"));
    evento1 = new Evento(usuario, guardarropa, fecha2, "Boliche", "Party", Frecuencia.UNICA); 
	  evento1.sugerir();
	  assertEquals(2, usuario.getSugerenciasPendientes().size());
	}
	
	@Test
	public void chequearQueSeRealicenLasSugerenciasAlEjecutarElJob() {
		repo = Config.instance().getRepositorioEventos();
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha2, "Boliche", "Fiesta", Frecuencia.UNICA));
		tareaRevision.run();
		assertEquals(2, usuario.getSugerenciasPendientes().size());
	}
	
	@Test
	public void chequeoEventoUnico() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.UNICA);
		repo.agregarEvento(event);
		tareaRevision.run();
		assertFalse(event.pendiente());	
	}
	
	@Test
	public void chequeoCambioDeFechaDiaria() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.DIARIA);
		repo.agregarEvento(event);
		tareaRevision.run();
		assertEquals(fecha3.plus(1,ChronoUnit.DAYS),event.fecha());	
	}	
	
	@Test
	public void chequeoCambioDeFechaSemanal() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.SEMANAL);
		repo.agregarEvento(event);
		tareaRevision.run();
		assertEquals(fecha3.plus(1,ChronoUnit.WEEKS),event.fecha());	
	}	
	
	@Test
	public void chequeoCambioDeFechaMensual() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.MENSUAL);
		repo.agregarEvento(event);
		tareaRevision.run();
		assertEquals(fecha3.plus(1,ChronoUnit.MONTHS),event.fecha());	
	}	
	
	@Test
	public void chequeoCambioDeFechaAnual() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.ANUAL);
		repo.agregarEvento(event);
		tareaRevision.run();
		assertEquals(fecha3.plus(1,ChronoUnit.YEARS),event.fecha());	
	}	
	
	@Test
	public void chequeoEstadoPendiente() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.DIARIA);
		repo.agregarEvento(event);
		tareaRevision.run();
		assertTrue(event.pendiente());	
	}	
	
	@Test
	public void chequeoProximosEventosTrasCarga() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.DIARIA);
		Evento event2 = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.SEMANAL);
		repo.agregarEvento(event);
		repo.agregarEvento(event2);
		assertEquals(2,repo.proximosEventos().size());	
	}	
	
	@Test
	public void chequeoProximosEventosTrasJob() {
		repo = Config.instance().getRepositorioEventos();
		Evento event = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.DIARIA);
		Evento event2 = new Evento(usuario, guardarropa, fecha3, "Boliche", "Fiesta", Frecuencia.SEMANAL);
		repo.agregarEvento(event);
		repo.agregarEvento(event2);
		tareaRevision.run();
		assertEquals(0,repo.proximosEventos().size());	
	}	
	
}