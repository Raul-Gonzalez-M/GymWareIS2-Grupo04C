package factories;

import model.Material;
import model.Pole;

public class PoleBuilder extends Builder<Material>{

	public PoleBuilder() {
		super("Pole");
	}

	@Override
	protected Material createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Pole(id, nombre, precio, cantidad_disponible, actividad_asociada);
	}

}
