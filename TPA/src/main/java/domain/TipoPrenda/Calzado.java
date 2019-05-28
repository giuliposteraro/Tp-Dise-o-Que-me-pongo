package domain.TipoPrenda;

import java.util.List;
import domain.ECategoria;
import domain.ETela;


public class Calzado extends TipoPrenda {
	public Calzado(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.CALZADO;
		this.telasValidas = unasTelas;
	}
}
