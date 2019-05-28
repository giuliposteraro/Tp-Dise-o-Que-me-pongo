package domain.tipoPrenda;

import java.util.List;

public class Accesorio extends TipoPrenda {
	public Accesorio(List<ETela> unasTelas) {
		super(unasTelas, 0.0);
		this.categoria = ECategoria.ACCESORIO;
	}
}
