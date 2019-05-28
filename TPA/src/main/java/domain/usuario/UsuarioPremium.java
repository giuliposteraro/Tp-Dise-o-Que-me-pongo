package domain.usuario;

import domain.Guardarropa;

public class UsuarioPremium implements TipoUsuario{
	
	public Boolean tieneLugarGuardarropa(Guardarropa guardarropa) {
		return true;
	}
	
}
