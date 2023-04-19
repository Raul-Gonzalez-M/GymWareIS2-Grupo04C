package controller;

import java.sql.SQLException;
import java.time.LocalDate;

import model.Actividad;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Usuario;


public class Controller {

    private GymController gymcontroller;
    
    public Controller(){
    	this.gymcontroller = new GymController();
    }

	public Usuario verificarCredenciales(String DNI, String password) {
		return gymcontroller.verificarCredenciales(DNI,password);
	}
	
	public boolean registrarUsuario(String DNI, String nombre, String apellidos, String password, String fechaActual,double saldo){
		Cliente user = new Cliente(DNI, nombre, apellidos, password, fechaActual, saldo);
		try {
			gymcontroller.agregarCliente(user);
		}
		catch(SQLException e){
			return false;
		}
		
		return true;
	}
	
	public boolean existeDni(String DNI) {
        return gymcontroller.existeDni(DNI);
    } 
	
    public void setUsuario(Usuario usuario) {
    	gymcontroller.setUsuario(usuario);
    }

	public Actividad[] getActividades() {
		// TODO Auto-generated method stub
		return null;
	}

	public void inscribirActividad(Cliente cliente, Actividad actividad) {
		// TODO Auto-generated method stub
		
	}

	public void addEncuesta(Encuesta encuesta) {
		// TODO Auto-generated method stub
		
	}

	public Encuesta[] getEncuestas() {
		// TODO Auto-generated method stub
		return null;
	}

	public void eliminarMaterial(String id) {
		// TODO Auto-generated method stub
		
	}

	public Material[] obtenerMateriales() {
		// TODO Auto-generated method stub
		return null;
	}








}

