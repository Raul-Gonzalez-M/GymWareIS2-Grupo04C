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
	
	protected abstract T createInstance(String name, double precio);
}
