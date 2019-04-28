package domain;

import java.util.Arrays;

import exceptions.*;

public class ConstructorPrenda {
	
	// Mismos atributos que Prenda
	Tipo tipo;
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

	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(EColor colorPrimario, EColor colorSecundario) {
		this.color = new Color(colorPrimario, colorSecundario);
	}
	public ETela getTela() {
		return tela;
	}
	public void setTela(ETela tela) {
		this.tela = tela;
	}
	
}
