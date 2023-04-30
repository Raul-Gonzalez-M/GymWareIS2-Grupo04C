package factories;

import model.Churro;
import model.Material;

public class ChurroBuilder extends Builder<Material>{

	public ChurroBuilder() {
		super("Churro");
	}


	protected Material createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Churro(id, nombre, precio, cantidad_disponible, actividad_asociada);
	}
}
