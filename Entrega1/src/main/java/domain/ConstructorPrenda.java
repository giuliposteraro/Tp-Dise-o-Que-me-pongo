package domain;

public class ConstructorPrenda {
	
	
	// Mismos atributos que Prenda
	Tipo tipo;
	Color color;
	ETela tela;
	
	public Prenda crear() throws Exception {
		validar();
		return new Prenda(tipo, tela, color);
	}
	
	public void validar() throws Exception {
		if (tipo == null || !color.esValido() || !tipo.esTelaValida(tela)) {
			throw new Exception("Los parametros no son validos.");
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