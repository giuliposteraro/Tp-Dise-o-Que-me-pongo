package domain.clima;

import org.json.JSONException;

public class ClimaMock implements ProveedorClima{

	public Double getTemp() throws JSONException {
		return 10.0;
	}

}
