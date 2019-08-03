package domain;
import domain.clima.ClimaOW;
import domain.eventos.*;
import domain.clima.ProveedorClima;

public class Config {
	
	// Singleton
	private static Config instance;
	
	public static Config instance() {
		if(instance == null) {
			instance = new Config();
		}
		return instance;
	}
	
	// Class
	private int capacidadMaxima = 20;
	private String ciudad = "Buenos Aires";
	private RepositorioEventos repo = new RepositorioEventos();
	private ProveedorClima proveedor = new ClimaOW();
	
	private Config() {
		
	}
	
	public RepositorioEventos getRepositorioEventos() {
		return repo;
	}
	
	public int getCapacidadMaxima() {
		return this.capacidadMaxima;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}
	
	public ProveedorClima getProveedor() {
		return proveedor;
	}
	
	public void setCapacidadMaxima(int cap) {
		this.capacidadMaxima = cap;
	}

	public void setProveedor(ProveedorClima proveedor) {
		 this.proveedor = proveedor; 
	}

}
