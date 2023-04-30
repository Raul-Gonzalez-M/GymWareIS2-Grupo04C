package factories;

import model.Material;
import model.Pesa;

public class PesaBuilder extends Builder<Material>{

	public PesaBuilder() {
		super("Pesa");
	}

	@Override
	protected Material createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada) {
		if(precio <= 0)
			throw new IllegalArgumentException("El precio debe ser mayor que cero.");
		
		return new Pesa(id, nombre, precio, cantidad_disponible, actividad_asociada);
	}

}
