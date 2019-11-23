package exceptions;

public class NoSePuedenCompartirPrendas extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public NoSePuedenCompartirPrendas(String mensaje){
		super(mensaje);
	}
}
