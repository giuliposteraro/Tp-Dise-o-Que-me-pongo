package domain.tipoPrenda;

import java.util.List;

public class Inferior extends TipoPrenda {
		
	public Inferior(List<ETela> unasTelas) {
		super(unasTelas, 2.0);
		this.categoria = ECategoria.INFERIOR;
	}
}
