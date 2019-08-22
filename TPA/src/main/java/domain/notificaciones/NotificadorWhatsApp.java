package domain.notificaciones;

import domain.eventos.Evento;

public class NotificadorWhatsApp implements INotificador {

//	private String telefono;
//	private IWhatsAppService wppService;
//	
//	public NotificadorSMS(String telefono, IWhatsAppService wppService) {
//		this.telefono = telefono;
//		this.wppService = wppService;
//	}
	
	@Override
	public void notificarSugerencia(Evento evento) {
		enviarWhatsApp("Nuevas sugerencias para " + evento.getMotivo() + " ya están disponibles en la sección \"Sugerencias\"." + "");
	}

	@Override
	public void notificarAlertaMeteorologica() {
		enviarWhatsApp("Hemos detectado una alerta meteorológica para hoy, revisa tus sugerencias para elegir un atuendo adecuado.");
	}
	
	private void enviarWhatsApp(String texto) {
		//wppService.send(telefono, texto);
	}

}
