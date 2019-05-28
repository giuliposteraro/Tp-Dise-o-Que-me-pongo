package domain.tipoPrenda;

import java.util.List;

public class TipoPrenda {
	protected ECategoria categoria;
	protected List<ETela> telasValidas;
	
	protected TipoPrenda() {}
	
	public Boolean esTelaValida(ETela tela) {
		return this.telasValidas.contains(tela);
	}
	
	public ECategoria getCategoria() {
		return  this.categoria;
	}
	
}
