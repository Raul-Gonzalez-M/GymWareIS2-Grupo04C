package model;

public class Usuario {
    private String DNI;
    private String nombre;
    private String contrasena;
    
    public Usuario(String DNI, String nombre, String contrasena) {
    	this.DNI = DNI;
        this.nombre = nombre;
        this.contrasena = contrasena;
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
    
    public static Usuario crearUsuario(String DNI, String nombre, String contrasena, String fechaAlta, String fechaBaja, double saldo) {
        if (fechaAlta != null && fechaBaja != null && saldo > 0) {
            return new Cliente(DNI, nombre, contrasena, fechaAlta, fechaBaja, saldo);
        } else {
            return new Personal(DNI, nombre, contrasena);
        }
    }

}