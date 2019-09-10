package domain.tipoPrenda;

import java.util.List;

public class Superior extends TipoPrenda {
	protected int capa = 0;

	public Superior(List<ETela> unasTelas) {
		super(unasTelas, 2.0);
		this.categoria = ECategoria.SUPERIOR;
	}

	@Override
	public int getCapa() {
		return this.capa;
	}
}
