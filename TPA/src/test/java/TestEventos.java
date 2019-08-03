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

	@Before
	public void crearObj() {
		repo = new RepositorioEventos();
		usuario = new Usuario(new UsuarioPremium());
		guardarropa = usuario.crearGuardarropa();
		fecha = LocalDate.of(2019, 10, 22);
		fecha2 = LocalDate.now();
		fecha3=LocalDate.of(2019, 5, 29);

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
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha, "Boliche", "Fiesta"));
		assertEquals(1, repo.eventos.size());
	}

	@Test
	public void detectaCorrectamenteSiEsProximoCasoPositivo() {
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha2, "Boliche", "Fiesta"));
		assertEquals(1, repo.proximosEventos().size());
	}

	@Test
	public void detectaCorrectamenteSiEsProximoCasoNegativo() {
		repo.agregarEvento(new Evento(usuario, guardarropa, fecha, "Boliche", "Fiesta"));
		assertEquals(0, repo.proximosEventos().size());
	}

	@Test
	public void chequearSugerenciasUsuarioTrasAgregarEvento() {
		Config.instance().setProveedor(new ClimaMock());
		evento1 = new Evento(usuario, guardarropa, fecha2, "Boliche", "Party");
		evento1.sugerir();
		assertEquals(2, usuario.getSugerenciasPendientes().size());
	}
}