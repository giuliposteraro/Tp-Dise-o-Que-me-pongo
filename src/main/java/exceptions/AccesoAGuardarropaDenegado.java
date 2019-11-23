package exceptions;

public class AccesoAGuardarropaDenegado extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public AccesoAGuardarropaDenegado(String mensaje) {
		super(mensaje);
	}
}
