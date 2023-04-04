import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venta {
    
    private int idVenta;
    private LocalDate fechaVenta;
    private Cliente cliente;
    private List<Material> productos;
    private double totalVenta;
    
    public Venta(int idVenta, LocalDate fechaVenta, Cliente cliente) {
        this.idVenta = idVenta;
        this.fechaVenta = fechaVenta;
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.totalVenta = 0;
    }
    
    public int getIdVenta() {
        return idVenta;
    }
    
    public LocalDate getFechaVenta() {
        return fechaVenta;
    }
    
    public Cliente getCliente() {
        return cliente;
    }
    
    public List<Material> getProductos() {
        return productos;
    }
    
    public double getTotalVenta() {
        return totalVenta;
    }
    
    public void agregarProducto(Material producto) {
        productos.add(producto);
        totalVenta += producto.getPrecio();
    }
    
    public void eliminarProducto(Material producto) {
        productos.remove(producto);
        totalVenta -= producto.getPrecio();
    }
    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Venta #").append(idVenta).append(" realizada el ").append(fechaVenta.toString()).append(" por el cliente ").append(cliente.toString()).append("\n");
        sb.append("Detalles de la venta:\n");
        for (Material p : productos) {
            sb.append(p.toString()).append("\n");
        }
        sb.append("Total de la venta: $").append(String.format("%.2f", totalVenta));
        return sb.toString();
    }
}
