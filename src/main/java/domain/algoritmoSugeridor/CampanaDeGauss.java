package domain.algoritmoSugeridor;

public class CampanaDeGauss implements IAlgoritmoSugeridor{
	public Double coeficienteDeAbrigo(Double nivelDeAbrigo, Double temp, Double toleranciaAlFrio) {
		return Math.pow(Math.E, (-Math.pow(nivelDeAbrigo - (30.0 - temp * 1.25) + toleranciaAlFrio, 2) / 10));
	}
}

