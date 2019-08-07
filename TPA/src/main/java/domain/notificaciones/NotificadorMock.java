package domain.notificaciones;

import domain.eventos.Evento;

public class NotificadorMock implements INotificador {

	public Boolean sugerenciasNotificadas = false;
	public Boolean alertaNotificada = false;
	
	@Override
	public void notificarSugerencia(Evento evento) {
		sugerenciasNotificadas = true;
	}

	@Override
	public void notificarAlertaMeteorologica() {
		alertaNotificada = true;
	}
}
