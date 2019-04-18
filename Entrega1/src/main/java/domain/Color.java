package domain;

public class Color {
	
	EColor colorPrimario;
	EColor colorSecundario;
	
	public Color(EColor colorPrimario, EColor colorSecundario) {
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}
	
	public Boolean esValido() {
		return colorPrimario != EColor.NINGUNO && colorPrimario != colorSecundario;
	}
	
	
}
