import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized.Parameter;

import domain.Config;
import domain.clima.ClimaMock;
import domain.clima.TareaAlertasMeteorologicas;
import domain.eventos.Evento;
import domain.eventos.Frecuencia;
import domain.guardarropa.Guardarropa;
import domain.notificaciones.NotificadorMock;
import domain.usuario.Usuario;
import domain.usuario.UsuarioPremium;

public class TestNotificaciones {
	
	@Parameter
	Usuario usuario;
	NotificadorMock notificador;
	Evento evento;
	
	@Before
	public void before() {
		usuario = new Usuario(new UsuarioPremium());
		notificador = new NotificadorMock();
		usuario.agregarNotificador(notificador);
		Guardarropa guardarropa = usuario.crearGuardarropa();
		evento = new Evento(usuario, guardarropa, LocalDate.now(), "", "Evento Random", Frecuencia.UNICA);
	}
	
	@Test
	public void testNotificacionSugerencias() {
		usuario.notificarSugerencias(evento);
		assertTrue(notificador.sugerenciasNotificadas);
	}
	
	@Test
	public void testNotificacionAlerta() {
		usuario.notificarAlertaMeteorologica();
		assertTrue(notificador.alertaNotificada);
	}
	
	@Test
	public void testNotificacionAlertaDesdeTareaNegativo() {
		Config.instance().setProveedor(new ClimaMock(10.0, "Clear"));
		new TareaAlertasMeteorologicas().run();
		assertFalse(notificador.alertaNotificada);
	}
	
	@Test
	public void testNotificacionAlertaDesdeTareaPositivo() {
		Config.instance().setProveedor(new ClimaMock(10.0, "Thunderstorm"));
		new TareaAlertasMeteorologicas().run();
		assertTrue(notificador.alertaNotificada);
	}
	
}
