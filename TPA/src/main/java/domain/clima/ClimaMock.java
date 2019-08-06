package domain.clima;

import java.util.Arrays;
import java.util.List;

public class ClimaMock implements ProveedorClima{
	
	private Double temp;
	private String condition;
	
	public ClimaMock(Double temp, String condition) {
		this.temp = temp;
		this.condition = condition;
	}
	
	public Double getTemp() {
		return temp;
	}
	
	public List<String> getWeatherConditions() {
		return Arrays.asList(condition);
	}
}
