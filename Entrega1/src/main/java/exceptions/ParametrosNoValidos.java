package exceptions;

public class ParametrosNoValidos extends RuntimeException {
	public ParametrosNoValidos(String mensaje){
		super(mensaje);
	}
}
