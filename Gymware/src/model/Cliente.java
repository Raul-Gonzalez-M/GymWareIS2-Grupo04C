package model;

import java.awt.Component;

public class Cliente extends Usuario {
	private String fechaAlta;
    private double saldo;
    
    public Cliente(String DNI,String nombre, String contrasena, String fechaAlta, double saldo) {
        super(DNI,nombre,contrasena);
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

	public Component getActividades() {
		// TODO Auto-generated method stub
		return null;
	}
    
}