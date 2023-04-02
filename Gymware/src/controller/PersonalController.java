package controller;

import java.util.List;
import model.Gimnasio;
import model.Material;

public class PersonalController {
    
    private final Gimnasio gymModel;
    
    public PersonalController(Gimnasio gymModel) {
        this.gymModel = gymModel;
    }
    
    public void agregarProducto(String nombre, double precio, int cantidad) {
    	Material producto = new Material(nombre, precio, cantidad);
        InventarioService inventarioService = gymModel.getInventarioService();
        inventarioService.agregarProducto(producto);
    }
    
    public boolean realizarVenta(int idCliente, String nombreProducto, int cantidad) {
    	Material producto = gymModel.getInventarioService().buscarProducto(nombreProducto);
        if (producto != null && producto.getCantidad() >= cantidad) {
            double precioTotal = producto.getPrecio() * cantidad;
            Venta venta = new Venta(idCliente, nombreProducto, cantidad, precioTotal);
            VentaService ventaService = gymModel.getVentaService();
            ventaService.registrarVenta(venta);
            gymModel.getInventarioService().actualizarCantidadProducto(producto, -cantidad);
            return true;
        }
        return false;
    }
    
    public List<Producto> listarProductos() {
        InventarioService inventarioService = gymModel.getInventarioService();
        return inventarioService.listarProductos();
    }
    
    public List<Venta> listarVentas() {
        VentaService ventaService = gymModel.getVentaService();
        return ventaService.listarVentas();
    }

    public void agregarClase(LocalDate fecha, String horaInicio, String horaFin, String descripcion, String nombreInstructor) {
        Clase clase = new Clase(fecha, horaInicio, horaFin, descripcion, nombreInstructor);
        ClaseService claseService = gymModel.getClaseService();
        claseService.agregarClase(clase);
    }
    
    public List<Clase> listarClases() {
        ClaseService claseService = gymModel.getClaseService();
        return claseService.listarClases();
    }
    
}
