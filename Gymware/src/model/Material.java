package model;

public class Material {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int cantidad;

    public Material(int id, String nombre, String descripcion, double precio, int cantidad){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public String getDescripcion(){
        return this.descripcion;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public double getPrecio(){
        return this.precio;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public int getCantidad(){
        return this.cantidad;
    }

    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }

    public void incCantidad(){
        this.cantidad++;
    }
}
