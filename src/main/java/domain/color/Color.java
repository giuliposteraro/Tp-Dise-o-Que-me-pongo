package domain.color;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
public class Color {
	@Enumerated(EnumType.STRING)
	private EColor colorPrimario;
	@Enumerated(EnumType.STRING)
	private EColor colorSecundario;

	public Color(EColor colorPrimario, EColor colorSecundario) {
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}

	public Boolean esValido() {
		return !EColor.NINGUNO.equals(colorPrimario) && !colorPrimario.equals(colorSecundario);
	}

	private Color() {}

}
