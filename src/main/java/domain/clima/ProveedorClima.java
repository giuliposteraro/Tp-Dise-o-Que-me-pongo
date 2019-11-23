package domain.clima;

import java.util.List;

public interface ProveedorClima {

	public Double getTemp();
	public List<ECondicionClimatica> getWeatherConditions();
	
}
