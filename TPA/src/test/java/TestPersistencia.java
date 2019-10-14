import static org.junit.Assert.assertNotNull;

import java.nio.charset.StandardCharsets;

import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;

import com.google.common.hash.Hashing;

import domain.color.EColor;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;


public class TestPersistencia extends AbstractPersistenceTest implements WithGlobalEntityManager {

	@Test
	public void contextUp() {
		assertNotNull(entityManager());
	}

	@Test
	public void contextUpWithTransaction() throws Exception {
		ConstructorPrenda c = new ConstructorPrenda();

		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		Prenda remera = c.crear();
		entityManager().persist(remera);
		withTransaction(() -> {
			remera.setEnUso(true);
		});
	}
	
	@Test
	public void crearUsuario() throws Exception {
		Usuario user = new Usuario(new UsuarioPremium());
		try {
			user.setUsername("damonte");
			String password = Hashing.sha256().hashString("nventi", StandardCharsets.UTF_8).toString();
			entityManager().persist(user);
			withTransaction(() -> {
				user.setPassword(password);
			});
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
