package domain.tipoPrenda;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TipoPrenda {
	@Id @GeneratedValue
	private Long id_tipo;
	@Enumerated(EnumType.STRING)
	protected ECategoria categoria;
	
	protected List<ETela> telasValidas;
	protected Double nivelAbrigo;
	
	protected TipoPrenda(List<ETela> unasTelas, Double nivelAbrigo) {
		this.telasValidas = unasTelas;
		this.nivelAbrigo = nivelAbrigo;
	}
	
	public Boolean esTelaValida(ETela tela) {
		return this.telasValidas.contains(tela);
	}
	
	public ECategoria getCategoria() {
		return  this.categoria;
	}

	public Double getNivelAbrigo() {
		return nivelAbrigo;
	}
	
	public boolean puedeAbrigarseCon(TipoPrenda tipoPrenda) {
		return false;
	}

	public int getCapa() {
		return 0;
	}	
}
