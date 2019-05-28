package domain.tipoPrenda;

import java.util.List;


public class Calzado extends TipoPrenda {
	public Calzado(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.CALZADO;
		this.telasValidas = unasTelas;
	}
}
