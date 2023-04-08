package model;

public class Venta {
    
    private String nombreMaterial;
    private String DNI;
    private String fecha;
    private int cantidad;
    
    public Venta(String nombreMaterial, String DNI, String fecha, int cantidad) {
        this.nombreMaterial = nombreMaterial;
        this.DNI = DNI;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }
    
    public String getNombreMaterial() {
		return nombreMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta").append(" realizada el ").append(fecha).append(" por el cliente con DNI ").append(DNI).append("\n");
        sb.append("Detalles de la venta:\n");
        sb.append("Producto: ").append(nombreMaterial).append(".\n");
        sb.append("Unidades: ").append(cantidad).append(".\n");
        sb.append("Total de la venta: $");
        return sb.toString();
    }
}
