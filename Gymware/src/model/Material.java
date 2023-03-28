package model;

public class Material {
    private String nombre;
    private double precio;
    private int unidades;

    public Material(String nombre, double precio){
        this(nombre, precio, 0);
    }

    public Material(String nombre, double precio, int unidades){
        this.nombre = nombre;
        this.precio = precio;
        this.unidades = unidades;
    }

    public String getNombre(){
        return this.nombre;
    }

    public double getPrecio(){
        return this.precio;
    }

    public int getUnidades(){
        return this.unidades;
    }

    public void setPrecio(double precio){
        this.precio = precio;
    }

    public void setUnidades(int unidades){
        this.unidades = unidades;
    }

    public void incUnidades(){
        this.unidades++;
    }
}
