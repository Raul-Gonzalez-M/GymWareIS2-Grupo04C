package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
	private String fechaAlta;
    private double saldo;
    private List<Actividad> actividades;
    
    public Cliente(String DNI, String nombre, String contrasena, String fechaAlta, double saldo) {
        super(DNI,nombre,contrasena);
        this.fechaAlta = fechaAlta;
        this.saldo = saldo;
        this.actividades = new ArrayList<Actividad>();
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

	public List<Actividad> getActividades() {
	    return actividades;
	}

	public void setActividades(List<Actividad> listaActividades) {
		this.actividades = listaActividades;
	}

    
}