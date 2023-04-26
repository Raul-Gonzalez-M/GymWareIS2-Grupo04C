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

	public void verificarCredenciales(String DNI, String password) {
		Usuario u = gymcontroller.verificarCredenciales(DNI, password);
	}

	public Usuario obtenerClientePorId(String DNI) {
        return gymcontroller.obtenerUsuarioPorDNI(DNI);
    }
	
	public List<Encuesta> getEncuestas() {
        return gymcontroller.obtenerEncuestas();
    }

	public boolean registrarUsuario(String DNI, String nombre, String apellidos, String password, String fechaActual, double saldo){
		Cliente user = new Cliente(DNI, nombre, password, fechaActual, saldo);
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

    public List<Actividad> getListaActividades() {
        return gymcontroller.getListaActividades();
    }

    public boolean inscribirActividad(Cliente cliente, Actividad actividad) {
        return gymcontroller.inscribirActividad(cliente, actividad);
    }

	public void addEncuesta(Encuesta encuesta){
		gymcontroller.agregarEncuesta(encuesta.getDNI(), encuesta.getFecha(), encuesta.getSatisfaccion(), encuesta.getCambios(), encuesta.getParticipa());
	}
    
//    public Actividad getActividad(int selectedRow) {
//        return gymcontroller.getActividad(selectedRow);
//    }
//
//    public void addEncuesta(Encuesta encuesta) {
//        gymcontroller.addEncuesta(encuesta);
//    }
//
//    public Encuesta[] getEncuestas() {
//        return gymcontroller.getEncuestas();
//    }
//
//    public void eliminarMaterial(String id) {
//        gymcontroller.eliminarMaterial(id);
//    }
//
//    public Material[] obtenerMateriales() {
//        return gymcontroller.obtenerMateriales();
//    }

}

