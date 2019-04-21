package domain;

public class Atuendo {
	
	Prenda superior;
	Prenda inferior;
	Prenda calzado;
	Prenda accesorio;
	
	public void setSuperior(Prenda unSuperior) {
		if(unSuperior().getCategoria() = ECategoria.SUPERIOR) {
				this.superior = unSuperior;
		}	
	}
	
	public void setInferior(Prenda unInferior) {
		if(unInferior().getCategoria() = ECategoria.INFERIOR) {
				this.inferior = unInferior;
		}	
	}

	public void setCalzado(Prenda unCalzado) {
		if(unCalzado().getCategoria() = ECategoria.CALZADO) {
			this.calzado = unCalzado;
		}
	}
	
	public void setAccesorio(Prenda unAccesorio) {
		if(unAccesorio().getCategoria() = ECategoria.ACCESORIO) {
			this.accesorio = unAccesorio;
		}
	}
}
