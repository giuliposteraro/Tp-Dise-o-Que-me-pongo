package domain;
import domain.clima.ClimaOW;
import domain.eventos.*;
import domain.usuario.RepositorioUsuarios;
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
	private RepositorioEventos repoEventos = new RepositorioEventos();
	private RepositorioUsuarios repoUsuarios = new RepositorioUsuarios();
	private ProveedorClima proveedor = new ClimaOW();
	
	private Config() {
		
	}
	
	public RepositorioEventos getRepositorioEventos() {
		return repoEventos;
	}
	
	public RepositorioUsuarios getRepositorioUsuarios() {
		return repoUsuarios;
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
	
	public void setRepositorioEventos(RepositorioEventos repo) {
		this.repoEventos = repo;
	}
	
	public void setRepositorioUsuarios(RepositorioUsuarios repo) {
		this.repoUsuarios = repo;
	}
	
	public void setCapacidadMaxima(int cap) {
		this.capacidadMaxima = cap;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	public void setProveedor(ProveedorClima proveedor) {
		 this.proveedor = proveedor; 
	}

}
