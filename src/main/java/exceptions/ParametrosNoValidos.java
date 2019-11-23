package exceptions;

public class ParametrosNoValidos extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ParametrosNoValidos(String mensaje){
		super(mensaje);
	}
}
