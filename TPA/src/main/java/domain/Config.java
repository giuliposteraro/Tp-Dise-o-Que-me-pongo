package domain;

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
	
	public int capacidadMaxima = 5;
	
	public int getCapacidadMaxima() {
		return this.capacidadMaxima;
	}
	
	public void setCapacidadMaxima(int cap) {
		this.capacidadMaxima = cap;
	}
	//Ubicacion ubicacion = CABA;
}
