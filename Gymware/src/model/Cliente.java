package model;

import java.awt.Component;
import java.util.ArrayList;

public class Cliente extends Usuario {
	private String fechaAlta;
    private double saldo;
    private String apellidos;
    private ArrayList<Actividad> actividades;
    
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

	public void inscribirActividad(Actividad actividad) {
	    if (actividades.contains(actividad)) {
	        System.out.println("Ya está inscrito en esta actividad");
	        return;
	    }
	    if (actividad.getPlazasDisponibles() == 0) {
	        System.out.println("Lo siento, no quedan plazas disponibles para esta actividad");
	        return;
	    }
	    actividades.add(actividad);
	    actividad.setPlazasDisponibles(actividad.getPlazasDisponibles() - 1);
	}

	public ArrayList<Actividad> getActividades() {
	    return actividades;
	}

    
}