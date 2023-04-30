package factories;

public abstract class Builder<T> {

	String tag;
	
	public Builder(String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
	
	public String toString() {
		return this.tag;
	}
	
	protected abstract T createInstance(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada);
}
