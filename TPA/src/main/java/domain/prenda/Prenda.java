package domain.prenda;

import java.awt.image.BufferedImage;
import java.util.Optional;

import domain.color.Color;
import domain.tipoPrenda.ECategoria;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.TipoPrenda;

//No instanciar directamente, usar ConstructorPrenda
public class Prenda {

	private TipoPrenda tipo;
	private Color color;
	private ETela tela;
	private BufferedImage imagen;
	private Boolean enUso; // TODO cuando termina un evento poner en false.
	private Optional<Prenda> prendaAbajo = Optional.empty();

	public Prenda(TipoPrenda tipo, ETela tela, Color color, BufferedImage imagen) {
		this.tipo = tipo;
		this.tela = tela;
		this.color = color;
		this.imagen = imagen;
		this.enUso = false;
	}

	public Boolean getEnUso() {
		return enUso;
	}

	public void setEnUso(Boolean enUso) {
		this.enUso = enUso;
		this.prendaAbajo.ifPresent(p -> p.setEnUso(enUso));
	}

	public TipoPrenda getTipo() {
		return tipo;
	}

	public Color getColor() {
		return color;
	}

	public ETela getTela() {
		return tela;
	}

	public BufferedImage getImagen() {
		return imagen;
	}

	public ECategoria getCategoria() {
		return tipo.getCategoria();
	}

	public Double getNivelAbrigo() {
		return tipo.getNivelAbrigo() + this.prendaAbajo.map(prenda -> prenda.getNivelAbrigo()).orElse(0.0);
	}

	public Prenda ponerSobre(Prenda otraPrenda) {
		Prenda resultadoPrenda = new Prenda(tipo, tela, color, imagen);
		if (otraPrenda.puededeAbrigarseCon(resultadoPrenda)) {
			resultadoPrenda.prendaAbajo = Optional.of(otraPrenda);
			return resultadoPrenda;
		}
		return null;
	}

	protected boolean puededeAbrigarseCon(Prenda otraPrenda) {
		return tipo.puedeAbrigarseCon(otraPrenda.getTipo());
	}

	public Boolean esSuperior() {
		return this.tipo.getCategoria().equals(ECategoria.SUPERIOR);
	}

	public Boolean esInferior() {
		return this.tipo.getCategoria().equals(ECategoria.INFERIOR);
	}

	public Boolean esCalzado() {
		return this.tipo.getCategoria().equals(ECategoria.CALZADO);
	}

	public Boolean esAccesorio() {
		return this.tipo.getCategoria().equals(ECategoria.ACCESORIO);
	}

	public Boolean esAbrigo() {
		return this.tipo.getCategoria().equals(ECategoria.ABRIGO);
	}
}
