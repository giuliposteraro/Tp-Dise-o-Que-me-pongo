package domain.tipoPrenda;

import java.util.List;

public class Superior extends TipoPrenda {
	protected int capa = 0;
	
	public Superior(List<ETela> unasTelas, String descripcion) {
		super(unasTelas, 2.0, descripcion);
		this.categoria = ECategoria.SUPERIOR;
	}

	@Override
	public int getCapa() {
		return this.capa;
	}
}
