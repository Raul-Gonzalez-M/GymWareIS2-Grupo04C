package factories;

import model.Esterilla;
import model.Material;

public class EsterillaBuilder extends Builder<Material>{

	public EsterillaBuilder() {
		super("Esterilla");
	}

	protected Material createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Esterilla(id, nombre, precio, cantidad_disponible, actividad_asociada);
	}

}
