package domain;

import java.util.Arrays;

import domain.TipoPrenda.TipoPrenda;
import exceptions.*;

public class ConstructorPrenda {
	
	// Mismos atributos que Prenda
	TipoPrenda tipo;
	Color color;
	ETela tela;
	
	public Prenda crear() {
		validar();
		return new Prenda(tipo, tela, color);
	}
	
	private void validar() {
		if (Arrays.asList(tipo, color, tela).contains(null) || !color.esValido() || !tipo.esTelaValida(tela)) {
			throw new ParametrosNoValidos("Los parametros no son validos.");
		}
	}

	public void setTipo(TipoPrenda tipo) {
		this.tipo = tipo;
	}

	public void setColor(EColor colorPrimario, EColor colorSecundario) {
		this.color = new Color(colorPrimario, colorSecundario);
	}

	public void setTela(ETela tela) {
		this.tela = tela;
	}
	
}
