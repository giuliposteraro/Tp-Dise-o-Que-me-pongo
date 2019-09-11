package domain.prenda;

import java.awt.image.BufferedImage;
import java.util.Optional;

import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import domain.color.Color;
import domain.color.EColor;
import domain.tipoPrenda.ECategoria;
import domain.tipoPrenda.ETela;
import domain.tipoPrenda.TipoPrenda;
import persistency.converters.TipoPrendaConverter;
import domain.tipoPrenda.RepoTipos;

//No instanciar directamente, usar ConstructorPrenda

@Entity
public class Prenda {
	@Id @GeneratedValue
	private Long id_prenda;
	@Convert(converter = TipoPrendaConverter.class)
	private TipoPrenda tipo;
	@Embedded
	private Color color;
	@Enumerated(EnumType.STRING)
	private ETela tela;
	private BufferedImage imagen;
	private Boolean enUso; // TODO cuando termina un evento poner en false.

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
		return tipo.getNivelAbrigo();
	}
}
