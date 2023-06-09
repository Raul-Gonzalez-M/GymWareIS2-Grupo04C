package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

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

	public Usuario obtenerUsuarioPorDNI(String DNI) {
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
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return false;
		}
		
		return true;
	}
	
	public void darBajaUsusario(String DNI) {
		gymcontroller.darBajaUsusario(DNI);
	}
	public void setSaldo(Cliente cliente) {
		gymcontroller.setSaldo(cliente);
	}
	
	public boolean existeDni(String DNI) {
        return gymcontroller.existeDni(DNI);
    } 
	
    public void setUsuario(Usuario usuario) {
    	gymcontroller.setUsuario(usuario);
    }

    public List<Actividad> obtenerActividadPorDNI(String DNI) {
        return gymcontroller.getListaActividadesPorDNI(DNI);
    }
	public List<Actividad> getListaActividades() {
		return gymcontroller.getListaActividades();
	}
	public List<Actividad> getActNoInscrito(String DNI) {
		return gymcontroller.getActNoInscrito(DNI);
	}
    public boolean inscribirActividad(Cliente cliente, Actividad actividad) {
        return gymcontroller.inscribirActividad(cliente, actividad);
    }

	public void addEncuesta(Encuesta encuesta){
		gymcontroller.agregarEncuesta(encuesta.getDNI(), encuesta.getSatisfaccion(), encuesta.getCambios(), encuesta.getParticipa());
	}

	public boolean borrarUsuarioActividad(Cliente cliente, Actividad actividad) {
		return gymcontroller.borrarUsuarioActividad(cliente,actividad);
	}
	
	public boolean borrarUsuarioTodasActividades(String viejoDNI) {
		return gymcontroller.borrarUsuarioTodasActividades(viejoDNI);
	}

	public void cambiarContrasenya(Cliente cliente, String nuevaContra) {
		gymcontroller.cambiarContrasenya(cliente,nuevaContra);
		
	}

	public List<Material> getMaterialesDisponibles() {
		return gymcontroller.getMaterialesDisponibles();
	}

	public List<Cliente> getListaClientes() {
		return gymcontroller.getListaClientes();
	}

	public void updateMaterial(String valueAt) {
		try {
			gymcontroller.updateMaterial(valueAt);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
	}
	public void agregarMaterial(String nombre, double precio, int unidades, String act_asociada) {
		gymcontroller.agregarMaterial(nombre,precio,unidades,act_asociada);
	}
		
	public void setSaldo(double saldo, String string) {
		try {
			gymcontroller.setSaldo(saldo, string);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
	}

	public void updateMaterial(int cantidad, String nombre) {
		try {
			gymcontroller.updateMaterial(cantidad, nombre);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
	}
	
	public void actualizarCliente(Cliente cliente) {
		gymcontroller.actualizaCliente(cliente);	
	}

	public boolean agregarActividad(String nombre, String DNIProfesor, String horaInicio, String horaFin, int idAula) {
		return gymcontroller.agregarActividad(nombre, horaInicio + "-" + horaFin , DNIProfesor, idAula, null);	
	}

	public String getProfesorPorNombre(String nombre) {
		return gymcontroller.getProfesorPorNombre(nombre);
	}


}

