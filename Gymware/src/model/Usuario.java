package model;

public class Usuario {
    private String DNI;
    private String nombre;
    private String apellidos;
    private String contrasena;
    private double saldo;
    
    public Usuario(String DNI, String nombre,String apellidos, String contrasena, double saldo) {
    	this.DNI = DNI;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.apellidos = apellidos;
        this.saldo = saldo;
    }
    
    public String getDNI() {
        return DNI;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getContrasena() {
        return contrasena;
    }
    
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    

}