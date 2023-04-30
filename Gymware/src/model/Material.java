package model;

public class Material {
    private int id;
    private String nombre;
    private int precio;
    private int cantidad_disponible;
    private String actividad_asociada;
    
    
    public Material(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada){
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad_disponible = cantidad_disponible;
        this.actividad_asociada = actividad_asociada;
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

    public double getPrecio(){
        return this.precio;
    }

    public void setPrecio(int precio){
        this.precio = precio;
    }
    
    public double getCantidad_disponible(){
        return this.cantidad_disponible;
    }

    public void setCantidad_disponible(int cantidad_disponible){
        this.cantidad_disponible = cantidad_disponible;
    }
    
    public String getActividad_asociada(){
        return this.actividad_asociada;
    }

    public void setActividad_asociada(String marca){
        this.actividad_asociada = marca;
    }

}
