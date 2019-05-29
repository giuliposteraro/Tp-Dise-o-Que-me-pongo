package domain;

public class UsuarioPremium implements TipoUsuario{
	
	public Boolean tieneLugarGuardarropa(Guardarropa guardarropa) {
		return true;
	}
	
	public void crearGuardarropa(Guardarropa guardarropa) {
		return new GuardarropaIlimitado();
	}
	
}
