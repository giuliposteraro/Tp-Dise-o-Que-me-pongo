package domain.clima;

public enum ECondicionClimatica {
	THUNDERSTORM(true),
	DRIZZLE(false),
	RAIN(true),
	SNOW(true),
	MIST(false),
	TORNADO(true),
	CLEAR(false),
	CLOUDS(false);
	
	private Boolean esAlerta;
	
	ECondicionClimatica(Boolean esAlerta) {
		this.esAlerta = esAlerta;
	}

	public static ECondicionClimatica getCondicionClimatica(String s) {
		switch(s) {
			case "Thunderstorm": return THUNDERSTORM;
			case "Drizzle": return DRIZZLE;
			case "Rain": return RAIN;
			case "Snow": return SNOW;
			case "Mist": return MIST;
			case "Tornado": return TORNADO;
			case "Clear": return CLEAR;
			case "Clouds": return CLOUDS;
			default: return CLEAR;
		}
	}
	
	public Boolean esAlerta() {
		return esAlerta;
	}
}
