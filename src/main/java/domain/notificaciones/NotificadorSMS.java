package domain.notificaciones;

import domain.eventos.Evento;

public class NotificadorSMS implements INotificador {

//	private String telefono;
//	private ISMSService smsService;
//	
//	public NotificadorSMS(String telefono, ISMSService smsService) {
//		this.telefono = telefono;
//		this.smsService = smsService;
//	}
	
	@Override
	public void notificarSugerencia(Evento evento) {
		enviarSMS("Nuevas sugerencias para " + evento.getMotivo() + " ya están disponibles en la sección \"Sugerencias\"." + "");
	}

	@Override
	public void notificarAlertaMeteorologica() {
		enviarSMS("Hemos detectado una alerta meteorológica para hoy, revisa tus sugerencias para elegir un atuendo adecuado.");
	}
	
	private void enviarSMS(String texto) {
		//smsService.send(telefono, texto);
	}

}
