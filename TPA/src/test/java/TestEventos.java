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
	GuardarropaLimitado grLimitado; 
	UsuarioPremium premium;
	
	@Before
	public void crearObj() {
		guardarropa = new Guardarropa(grLimitado);
		repo = new RepositorioEventos();
		usuario = new Usuario(premium);
		fecha = LocalDate.of(2019,10,22);
		evento1 = new Evento(usuario,guardarropa,fecha,"Mi casa","Cumple Juan");
		
	}
	
	@Test
	
	public void agregarEvento() {
		repo.agregarEvento(evento1);
		assertEquals(1,repo.eventos.size());
	}
	
	
}