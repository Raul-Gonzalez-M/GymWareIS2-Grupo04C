package model;

public class Cliente extends Usuario {
    private int idCliente;
    private double peso;
    private double altura;
    
    public Cliente(String nombre, String apellido, int edad, String correoElectronico, String contrasena, int idCliente, double peso, double altura) {
        super(nombre, apellido, edad, correoElectronico, contrasena);
        this.idCliente = idCliente;
        this.peso = peso;
        this.altura = altura;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public double getPeso() {
        return peso;
    }
    
    public void setPeso(double peso) {
        this.peso = peso;
    }
    
    public double getAltura() {
        return altura;
    }
    
    public void setAltura(double altura) {
        this.altura = altura;
    }
}