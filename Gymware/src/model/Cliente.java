package model;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        this.actividades = new ArrayList<Actividad>();
    }

	public Cliente(String DNI, String nombre, String contrasena, String fechaAlta, double saldo) {
        super(DNI,nombre,contrasena);
        this.apellidos = "";
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

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean inscribirActividad(Actividad actividad) {
		for (Actividad a : actividades) {
	        if (a.getId() == actividad.getId()) {
	        	JOptionPane.showMessageDialog(null, "Ya esta inscrito a esta actividad", "Error", JOptionPane.ERROR_MESSAGE);
	            return false;
	        }
	    }
	    if (actividad.getPlazasDisponibles() == 0) {
	    	JOptionPane.showMessageDialog(null, "Lo siento, no quedan plazas disponibles para esta actividad", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	    actividades.add(actividad);
	    actividad.setPlazasDisponibles(actividad.getPlazasDisponibles() - 1);
	    return true;
	}

	public ArrayList<Actividad> getActividades() {
	    return actividades;
	}

    
}