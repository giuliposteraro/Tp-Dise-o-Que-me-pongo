package domain.usuario;

import domain.Guardarropa;

public class UsuarioGratuito implements TipoUsuario{
	
	public Boolean tieneLugarGuardarropa(Guardarropa guardarropa) {
		return guardarropa.tieneLugar();
	}
	
	
}
