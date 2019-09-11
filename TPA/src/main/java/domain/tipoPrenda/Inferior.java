package domain.tipoPrenda;

import java.util.List;

public class Inferior extends TipoPrenda {
		
	public Inferior(List<ETela> unasTelas, String descripcion) {
		super(unasTelas, 2.0, descripcion);
		this.categoria = ECategoria.INFERIOR;
	}
}
