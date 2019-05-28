package domain.tipoPrenda;

import java.util.List;

public class Inferior extends TipoPrenda {
		
	public Inferior(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.INFERIOR;
		this.telasValidas = unasTelas;
	}
}
