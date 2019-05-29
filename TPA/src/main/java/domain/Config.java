package domain;
import domain.clima.ClimaOW;
import domain.clima.ProveedorClima;

public class Config {
	
	// Singleton
	
	static Config instance;
	
	public static Config instance() {
		if(instance == null) {
			instance = new Config();
		}
		
		return instance;
	}
	
	// Class
	
	int capacidadMaxima = 20;
	String ciudad = "Buenos Aires";
	
	public int getCapacidadMaxima() {
		return this.capacidadMaxima;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public ProveedorClima getProveedor() {
		return new ClimaOW();
	}
	
	
	public void setCapacidadMaxima(int cap) {
		this.capacidadMaxima = cap;
	}

}
