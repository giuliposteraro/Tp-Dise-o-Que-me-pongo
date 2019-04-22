package domain;
import java.util.*;

public class Usuario {
	
	Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();
	
	public void agregarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.add(guardarropa);
	}
	
	public void eliminarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.remove(guardarropa);
	}

	public Boolean tieneGuardarropa(Guardarropa g) {
		return guardarropas.contains(g);
	}
	
	public List<Atuendo> generarSugerencias(Guardarropa guardarropa) {
		return guardarropa.generarSugerencias();
	}
}
