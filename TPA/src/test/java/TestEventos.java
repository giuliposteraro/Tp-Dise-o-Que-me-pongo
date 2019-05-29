import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import domain.Evento;
import domain.RepositorioEventos;
import domain.JobEventos;import domain.ConstructorPrenda;
import domain.EColor;
import domain.ETela;
import domain.Guardarropa;
import domain.Prenda;
import domain.Tipo;

public class TestEventos {
	@Parameter
	
	RepositorioEventos repo;
	Evento evento1;
	Evento evento2;
	Guardarropa guardarropa;
	Prenda prenda1;
	Usuario usuario;
	
	@Before
	public void crearObj() {
		guardarropa = new Guardarropa();
		repo = new RepositorioEventos();
		usuario = new Usuario();
		evento1 = new Evento()
	}
	
	@Test
	
	public void agregarEvento() {
		evento
	}
	
	
}