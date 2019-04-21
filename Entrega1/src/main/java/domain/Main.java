package domain;

class Main {
	
	public static void main(String[] args) {
		
		Prenda p;
		
		ConstructorPrenda c = new ConstructorPrenda();
		
		c.setTipo(Tipo.REMERA);
		c.setColor(EColor.ROJO,EColor.NINGUNO);
		c.setTela(ETela.ALGODON);
		
		try {
			p = c.crear();
			System.out.println("La prenda se ha creado satisfactoriamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
