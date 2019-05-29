import static org.junit.Assert.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import domain.eventos.*;
import domain.prenda.ConstructorPrenda;
import domain.color.EColor;
import domain.tipoPrenda.ETela;
import domain.Config;
import domain.Guardarropa;
import domain.prenda.*;
import domain.tipoPrenda.TipoPrenda;
import domain.usuario.*;
import domain.GuardarropaIlimitado;
import domain.GuardarropaLimitado;
import domain.TipoGuardarropa;
//import domain.usuario.UsuarioGratuito;
//import domain.usuario.UsuarioPremium;

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
	GuardarropaLimitado grLimitado; 
	
	@Before
	public void crearObj() {
		repo = Config.instance().getRepositorioEventos();
		usuario = new Usuario(new UsuarioPremium());
		guardarropa = usuario.crearGuardarropa();
		fecha = LocalDate.of(2019,10,22);
		fecha2 = LocalDate.of(2019,5,29);
		evento1 = new Evento(usuario,guardarropa,fecha,"Mi casa","Cumple Juan");
		evento2 = new Evento(usuario,guardarropa,fecha2,"Boliche","Fiesta");
	}
	
	@Test
	
	public void agregarEvento() {
		repo.agregarEvento(evento1);
		assertEquals(1,repo.eventos.size());
	}
	
	@Test
	
	public void detectaCorrectamenteSiEsProximoCasoPositivo() {
		repo.agregarEvento(evento2);
		assertEquals(1,repo.proximosEventos().size());
	}
	
	@Test
	public void detectaCorrectamenteSiEsProximoCasoNegativo() {
		repo.agregarEvento(evento1);
		assertEquals(0,repo.proximosEventos().size());
	}
	
	public void chequearSugerenciasUsuarioTrasAgregarEvento() {
		
		
	}
	
	
	
}