import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import domain.Config;
import domain.clima.ClimaMock;
import domain.clima.ECondicionClimatica;
import domain.color.EColor;
import domain.guardarropa.Guardarropa;
import domain.guardarropa.GuardarropaIlimitado;
import domain.prenda.ConstructorPrenda;
import domain.prenda.Prenda;
import domain.sugerencias.EstadoSugerencia;
import domain.sugerencias.Sugerencia;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.RepoTipos;
import domain.usuario.Usuario;
import domain.usuario.UsuarioGratuito;
import domain.usuario.UsuarioPremium;
import exceptions.*;

public class TestUsuario {

	Prenda remera;
	Prenda remera2;
	Prenda pantalon;
	Prenda zapatillas;
	Prenda reloj;
	Prenda campera;
	Guardarropa guardarropa;
	Guardarropa guardarropa2;
	Usuario usuario;
	Usuario usuario2;
	
	@Before
	public void init() {
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.NEGRO, EColor.AZUL);
		remera = c.crear();
		
		c.setTipo(RepoTipos.REMERA);
		c.setTela(ETela.ALGODON);
		c.setColor(EColor.ROJO, EColor.NINGUNO);
		remera2 = c.crear();
		
		c.setTipo(RepoTipos.CAMPERA);
		c.setTela(ETela.CUERO);
		c.setColor(EColor.NEGRO, EColor.NINGUNO);
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
		usuario2 = new Usuario(new UsuarioGratuito());
		guardarropa = usuario.crearGuardarropa();
		guardarropa2 = usuario.crearGuardarropa();
		Config.instance().setProveedor(new ClimaMock(20.0, ECondicionClimatica.CLEAR));
	}
	
	@Test
	public void agregarUnGuardarropa() {
		assertTrue(usuario.tieneGuardarropa(guardarropa));
	}
	
	@Test
	public void seGeneraSugerenciaCorrectamente() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(campera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		
		usuario.generarSugerencias(guardarropa);
		
		assertEquals(4, usuario.getSugerenciasPendientes().size());
	}
	
	@Test
	public void aceptarUnaSugerencia() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		
		usuario.generarSugerencias(guardarropa);
		
		Sugerencia sugerencia = usuario.getSugerenciasPendientes().get(0);
		usuario.aceptarSugerencia(sugerencia);
		
		assertEquals(EstadoSugerencia.ACEPTADA, usuario.getSugerenciasRevisadas().get(0).getEstado());
	}
	
	@Test
	public void rechazarUnaSugerencia() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		
		usuario.generarSugerencias(guardarropa);
		
		Sugerencia sugerencia = usuario.getSugerenciasPendientes().get(0);
		usuario.rechazarSugerencia(sugerencia);
		
		assertEquals(EstadoSugerencia.RECHAZADA, usuario.getSugerenciasRevisadas().get(0).getEstado());
	}
	
	@Test
	public void deshacerLaUltimaAccion() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		
		usuario.generarSugerencias(guardarropa);
		
		Sugerencia sugerencia = usuario.getSugerenciasPendientes().get(0);
		usuario.rechazarSugerencia(sugerencia);
		usuario.deshacerUltimaSugerenciaRevisada();
		
		assertEquals(0, usuario.getSugerenciasRevisadas().size());
	}
	
	@Test(expected = NoSePuedeGenerarSugerencia.class)
	public void fallanSugerenciasSiFaltaPrenda() {
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.agregarPrenda(reloj, guardarropa);
		usuario.generarSugerencias(guardarropa);
	}
	
	@Test(expected = NoSePuedenCompartirPrendas.class)
	public void noSePuedeAgregarLaMismaPrendaADosGuardarropasDistintos() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(remera, guardarropa2);
	}
	
	@Test(expected = AccesoAGuardarropaDenegado.class)
	public void NoSePuedeAccederAUnGuardarropaAjeno() {
		Guardarropa guardarropa3 = new Guardarropa(new GuardarropaIlimitado());
		usuario.agregarPrenda(remera, guardarropa3);
	}
	
	@Test 
	public void compartirGuardarropaQueTieneConUsuario() {
		usuario.agregarGuardarropa(guardarropa);
		usuario.compartirGuardarropaCon(guardarropa, usuario2);	
		assertTrue(usuario2.tieneGuardarropa(guardarropa));
	}
	
	@Test(expected = AccesoAGuardarropaDenegado.class)
	public void compartirGuardarropasQueNoTieneConUsuario() {
		usuario2.compartirGuardarropaCon(guardarropa, usuario);
	}
	
	@Test
	public void lasPrendasEstanEnUso() {
		usuario.agregarPrenda(remera, guardarropa);
		usuario.agregarPrenda(pantalon, guardarropa);
		usuario.agregarPrenda(zapatillas, guardarropa);
		usuario.generarSugerencias(guardarropa);
		
		Sugerencia sugerencia = usuario.getSugerenciasPendientes().get(0);
		usuario.aceptarSugerencia(sugerencia);
		assertTrue(remera.getEnUso());
		assertTrue(pantalon.getEnUso());
		assertTrue(zapatillas.getEnUso());
	}
}
