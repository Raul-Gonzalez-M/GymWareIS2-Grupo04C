package model;

public class Material {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int unidades;

    public Material(String nombre, double precio, int unidades){
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
    }
    
    public Material(String nombre, double precio) {
    	this(nombre, precio, 1);
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

    public int getUnidades(){
        return this.unidades;
    }

    public void setCantidad(int unidades){
        this.unidades = unidades;
    }

    public void incUnidades(){
        this.unidades++;
    }
}
