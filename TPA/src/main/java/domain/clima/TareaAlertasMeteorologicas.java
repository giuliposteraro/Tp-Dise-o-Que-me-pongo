package domain.clima;

import java.util.List;
import java.util.TimerTask;

import domain.Config;

public class TareaAlertasMeteorologicas extends TimerTask {
	@Override
	public void run() {
		System.out.println("Verificando alertas meteorologicas...");

		if(hayAlertaMeteorologica()) {
			System.out.println("Alerta encontrada! Notificando usuarios...");
			Config.instance().getRepositorioUsuarios().notificarAlertaMeteorologica();
		}
	}

	private Boolean hayAlertaMeteorologica() {
		List<ECondicionClimatica> condiciones = Config.instance().getProveedor().getWeatherConditions();

		return condiciones.stream().anyMatch(c -> c.esAlerta());
	}

}
