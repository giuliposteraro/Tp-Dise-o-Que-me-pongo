import static org.junit.Assert.assertNotNull;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.google.common.hash.Hashing;

import domain.color.EColor;
import domain.eventos.EFrecuencia;
import domain.eventos.Evento;
import domain.guardarropa.Guardarropa;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;


public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {
	
	@Parameter
	Prenda remera;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	LocalDate fecha;

	
	@Before
	public void beforeEach() {
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
	}
	
	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}
	
	@Test
	public void crearUsuario() {
		Usuario user = new Usuario(new UsuarioPremium());
		try {
			String password = Hashing.sha256().hashString("nventi", StandardCharsets.UTF_8).toString();
			user.setUsername("damonte");
			Guardarropa g1 = user.crearGuardarropa();
			Guardarropa g2 = user.crearGuardarropa();
			List<Guardarropa> gs = new ArrayList<Guardarropa>();
			fecha = LocalDate.of(2023, 8, 4);
			Evento evento = new Evento(user, g1, fecha, "Boliche", "Fiesta", EFrecuencia.UNICA);
			for(int i = 0; i < 10; i++) {
				gs.add(user.crearGuardarropa());
			}
			entityManager().persist(user);
			entityManager().persist(evento);
			withTransaction(() -> {
				user.setPassword(password);
			});
			withTransaction(() -> {
				g1.setNombre("El Guardarropas");
				g2.setNombre("Let's go G2");
				gs.forEach(g -> g.setNombre("Un Guardarropa"));
				g1.agregarPrenda(remera);
				g1.agregarPrenda(pantalon);
				g1.agregarPrenda(zapatillas);
				g1.agregarPrenda(reloj);
			});
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
