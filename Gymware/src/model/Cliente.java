package model;

import java.awt.Component;

public class Cliente extends Usuario {
	private String fechaAlta;
    private double saldo;
    private String apellidos;
    
    public Cliente(String DNI,String nombre, String apellidos, String contrasena, String fechaAlta, double saldo) {
        super(DNI,nombre,contrasena);
        this.apellidos = apellidos;
        this.fechaAlta = fechaAlta;
        this.saldo = saldo;
    }

	public String getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(String fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
    
}