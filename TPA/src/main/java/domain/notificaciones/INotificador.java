package domain.notificaciones;

import domain.eventos.Evento;

public interface INotificador {
	public void notificarSugerencia(Evento evento);
	public void notificarAlertaMeteorologica();
}
