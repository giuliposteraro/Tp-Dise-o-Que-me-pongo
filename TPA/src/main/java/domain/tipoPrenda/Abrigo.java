package domain.tipoPrenda;

import java.util.List;
import java.util.Optional;

public class Abrigo extends Superior {
	private Optional<Superior> prendaAbajo = Optional.empty();
	
	public Abrigo(List<ETela> unasTelas, int capa) {
		super(unasTelas);
		this.categoria = ECategoria.ABRIGO;
		this.nivelAbrigo = 5.0;
		this.capa = capa;
	}
	
	//TODO Preguntar
	
	public void ponerSobre(Superior otraPrenda) {
		if(otraPrenda.puededeAbrigarseCon(this)) {
			this.prendaAbajo = Optional.of(otraPrenda);
		}
		else {
			//FIXME: chequear excepcion o no
		}
	}
	
	@Override
	public Double getNivelAbrigo(){
		return this.nivelAbrigo + this.prendaAbajo.map(prenda -> prenda.getNivelAbrigo()).orElse(0.0);
	}
}
