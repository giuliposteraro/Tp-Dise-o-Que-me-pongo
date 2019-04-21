package domain;
import java.util.*;

public class Usuario {
	
	HashSet<Guardarropa> guardarropas = new HashSet<Guardarropa>() ;
	
	public void agregarGuardarropa(Guardarropa nuevoGuardarropa) {
		this.guardarropas.add(nuevoGuardarropa);
	}
	
	public void eliminarGuardarropa(Guardarropa guardarropa) {
		this.guardarropas.remove(guardarropa);
	}

	public Boolean tieneGuardarropa(Guardarropa g) {
		return guardarropas.contains(g);
	}
}
