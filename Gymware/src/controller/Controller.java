package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

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

	public boolean verificarCredenciales(String DNI, String password) {
		return gymcontroller.verificarCredenciales(DNI, password);
	}

	public Usuario obtenerClientePorDNI(String DNI) {
        return gymcontroller.obtenerUsuarioPorDNI(DNI);
    }
	
	public List<Encuesta> getEncuestas() {
        return gymcontroller.obtenerEncuestas();
    }

	public boolean registrarUsuario(String DNI, String nombre, String password, String fecha, double saldo){
		Cliente user = new Cliente(DNI, nombre, password, fecha, saldo);
		try {
			gymcontroller.agregarCliente(user);
		}
		catch(SQLException e){
			e.printStackTrace();
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

    public List<Actividad> getListaActividades() {
        return gymcontroller.getListaActividades();
    }

    public boolean inscribirActividad(Cliente cliente, Actividad actividad) {
        return gymcontroller.inscribirActividad(cliente, actividad);
    }

	public void addEncuesta(Encuesta encuesta){
		gymcontroller.agregarEncuesta(encuesta.getDNI(), encuesta.getFecha(), encuesta.getSatisfaccion(), encuesta.getCambios(), encuesta.getParticipa());
	}

	public List<Actividad> getListaActividades(String dni) {
		try {
			return gymcontroller.getListaActividades(dni);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

