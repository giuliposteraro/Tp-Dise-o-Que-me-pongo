package domain.tipoPrenda;

import java.util.List;

public class Calzado extends TipoPrenda {
	public Calzado(List<ETela> unasTelas) {
		super(unasTelas, 1.0);
		this.categoria = ECategoria.CALZADO;
	}
}
