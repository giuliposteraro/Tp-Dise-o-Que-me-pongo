package domain.color;

public class Color {
	
	EColor colorPrimario;
	EColor colorSecundario;
	
	public Color(EColor colorPrimario, EColor colorSecundario) {
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}
	
	public Boolean esValido() {
		return !EColor.NINGUNO.equals(colorPrimario) && !colorPrimario.equals(colorSecundario);
	}
	
	
}
