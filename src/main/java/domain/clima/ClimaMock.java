package domain.clima;

import java.util.Arrays;
import java.util.List;

public class ClimaMock implements ProveedorClima{
	
	private Double temp;
	private ECondicionClimatica condition;
	
	public ClimaMock(Double temp, ECondicionClimatica condition) {
		this.temp = temp;
		this.condition = condition;
	}
	
	public Double getTemp() {
		return temp;
	}
	
	public List<ECondicionClimatica> getWeatherConditions() {
		return Arrays.asList(condition);
	}
}
