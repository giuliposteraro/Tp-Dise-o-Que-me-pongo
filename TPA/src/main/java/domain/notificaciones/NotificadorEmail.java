package domain.notificaciones;

import domain.eventos.Evento;

public class NotificadorEmail implements INotificador {

	//	private String direccion;
	//	private IMailService mailService;
	//	
	//	public NotificadorEmail(String direccion, IMailService mailService) {
	//		this.direccion = direccion;
	//	}

	@Override
	public void notificarSugerencia(Evento evento) {
		enviarMail("¡Nuevas sugerencias para " + evento.getMotivo() + "!",
				"Los atuendos sugeridos para " + evento.getMotivo() + " ya están disponibles en la sección \"Sugerencias\"." + "");
	}

	@Override
	public void notificarAlertaMeteorologica() {
		enviarMail("¡Alerta meterológica! Revisa tus sugerencias.",
				"El pronóstico para hoy ha cambiado, revisa tus sugerencias para elegir el mejor atuendo.");
	}

	private void enviarMail(String asunto, String texto) {
		// mailService.send(direccion, asunto, texto);
	}

}
