package domain.tipoPrenda;

import java.util.List;

public class Accesorio extends TipoPrenda {
	public Accesorio(List<ETela> unasTelas) {
		super();
		this.categoria = ECategoria.ACCESORIO;
		this.telasValidas = unasTelas;
	}
}
