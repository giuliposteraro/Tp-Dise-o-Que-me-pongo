package domain.TipoPrenda;

import java.util.List;
import domain.ECategoria;
import domain.ETela;

public class Superior extends TipoPrenda {
	protected int nivelAbrigo = 0;
	
	public Superior(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.SUPERIOR;
		this.telasValidas = unasTelas;
	}
	
	protected int getNivelAbrigo() {
		return this.nivelAbrigo;
	}
	
	protected boolean puededeAbrigarseCon(Superior prendaSuperior) {
		return this.nivelAbrigo < prendaSuperior.getNivelAbrigo();
	}
}
