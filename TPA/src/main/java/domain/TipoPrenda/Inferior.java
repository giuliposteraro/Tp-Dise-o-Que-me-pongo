package domain.TipoPrenda;

import java.util.List;

import domain.ECategoria;
import domain.ETela;

public class Inferior extends TipoPrenda {
		
	public Inferior(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.INFERIOR;
		this.telasValidas = unasTelas;
	}
}
