package domain;
import java.time.LocalDate; // import the LocalDate class

public interface ProveedorClima {

	public float getTemp(LocalDate fecha,String lugar);
	
}
