package factories;

public interface Factory <T>{

	public T createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada);
}
