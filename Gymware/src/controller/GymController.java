package controller;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import BD.ConexionBD;
import BD.DAOCambios;
import BD.DAOConsultas;
import model.*;

public class GymController {
	
    private DAOCambios cambios;
    private DAOConsultas consulta;
    private Usuario user;
    private ConexionBD bd;
    
    public GymController() {
    	this.bd = new ConexionBD();
        this.cambios = new DAOCambios(bd);
        try {
			this.consulta = new DAOConsultas(bd);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
    }
    
    public Usuario getUsuario() {
    	return this.user;
    }
    
    public void setUsuario(Usuario usuario) {
    	this.user = usuario;
    }
    
    
    
    /*
     * USUARIO / CLIENTE / PERSONAL
     */
    public void agregarCliente(Cliente user) throws SQLException{
        try {
			cambios.insertarCliente(user.getDNI(), user.getNombre(), user.getContrasena(), user.getSaldo());
		} catch (SQLException e) {
			throw new SQLException("No se ha podido agregar el cliente.", e);
		} 
    }
    
    public boolean existeDni(String DNI) {
        try {
			return !consulta.DNIDisponible(DNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return false;
		}
    } 
    
    public void eliminarCliente(Cliente cliente) {
        try {
			cambios.eliminarCliente(cliente);
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
    }

    public List<Cliente> obtenerClientes() {
        try {
			return consulta.obtenerClientes(); 
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			System.out.println(e.getMessage());
			return null;
		} 
    }

    public Cliente obtenerClientePorId(String DNI) {
        try {
			return consulta.obtenerClientePorId(DNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			System.out.println(e.getMessage());
			return null;
		} 
    }

    public void actualizarCliente(Cliente cliente) {
        try {
			cambios.actualizarCliente(cliente);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
    }
    
    public boolean verificarCredenciales(String DNI, String password) {
    	try {
			return consulta.verificarCredenciales(DNI, password);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return false;
		}
    }
    /*
     * ACTIVIDAD
     */
    public boolean agregarActividad(String nombre, String horario, String DNIProfesor, int num_aula, List<String> participantes) {
        Actividad nuevaActividad = new Actividad(0, nombre, horario, DNIProfesor, num_aula, participantes);
        try {
			cambios.insertarActividad(nuevaActividad);
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return false;
		} 
    }
    
    public void eliminarActividad(Actividad actividad) {
    	try {
			cambios.eliminarActividad(actividad);
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, e.getMessage());	
		} 
    }

    
    public void actualizarActividad(Actividad actividad) {
        try {
			cambios.actualizarActividad(actividad);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} 
    }
    
    /*
     * AULAS
     */
    public void agregarAula(int id, String actividad) {
        Aula nuevaAula = new Aula(id, actividad);
        try {
			cambios.insertarAula(nuevaAula);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se inserta la nueva aula en la BD
    }
    
    public void eliminarAula(Aula aula) {
    	try {
			cambios.eliminarAula(aula);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se elimina la aula de la BD
    }
    
    public List<Aula> obtenerAulas() {
        try {
			return consulta.obtenerAulas();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			System.out.println(e.getMessage());
			return null;
		}
    }
    
    /*
     * ENCUESTA
     */
    public void agregarEncuesta(String DNI, int satisfaccion, String cambios, String participa) {
        Encuesta nuevaEncuesta = new Encuesta(DNI, null, satisfaccion, cambios, participa);
       try {
		this. cambios.insertarEncuesta(nuevaEncuesta);
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, e.getMessage());	
	}
    }

    public void eliminarEncuesta(Encuesta encuesta) {
        try {
			cambios.eliminarEncuesta(encuesta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} 
    }

    public List<Encuesta> obtenerEncuestas() {
        try {
			return consulta.obtenerEncuestas();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} 
    }

    public void actualizarEncuesta(Encuesta encuesta) {
        try {
			cambios.actualizarEncuesta(encuesta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} 
    }

    public void responderEncuesta(Cliente cliente) {
        //Encuesta encuesta = cliente.responderEncuesta();
        //cambios.actualizarEncuesta(encuesta); // se actualiza la información de la encuesta en la BD
    }

    public Encuesta obtenerRespuestasDeEncuestaPorCliente(String DNI, String fecha) {
        try {
			return consulta.obtenerEncuestaPorDNI(DNI, fecha);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // obtiene las respuestas de un cliente específico para una encuesta
    }

    /*
     * MATERIAL
     */
    public void agregarMaterial(int id, String nombre, int precio, int cantidad_disponible, String actividad_asociada) {
        Material nuevoMaterial = new Material(id, nombre, precio, cantidad_disponible, actividad_asociada);
        try {
			cambios.insertarMaterial(nuevoMaterial);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // inserta el nuevo material en la BD
    }

    public void eliminarMaterial(Material nombre) {
        try {
			cambios.eliminarMaterial(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // elimina el material de la BD
    }

    public List<Material> obtenerMateriales() {
        try {
			return consulta.obtenerMateriales();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // obtiene todos los materiales de la BD
    }

    public Material obtenerMaterialPorNombre(String nombre) {
        try {
			return consulta.obtenerMaterialPorNombre(nombre);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // busca el material en la BD según su nombre
    }

    public void actualizarMaterial(Material material) {
        try {
			cambios.actualizarMaterial(material);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // actualiza la información del material en la BD
    }
    /*
     * PERSONAL
     */
    public void agregarPersonal(String DNI, String nombre, String contrasena) {
        Personal nuevoPersonal = new Personal(DNI, nombre, contrasena);
        try {
			cambios.insertarPersonal(nuevoPersonal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se inserta el nuevo personal en la BD
    }

    public void eliminarPersonal(Personal personal) {
        try {
			cambios.eliminarPersonal(personal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se elimina el personal de la BD
    }

    public List<Personal> obtenerPersonal() {
        try {
			return consulta.obtenerPersonal();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // se obtiene todo el personal de la BD
    }

    public Personal obtenerPersonalPorDNI(String DNI) {
        try {
			return consulta.obtenerPersonalPorDNI(DNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // se busca el personal en la BD según su DNI
    }

    public void actualizarPersonal(Personal personal) {
        try {
			cambios.actualizarPersonal(personal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se actualiza la información del personal en la BD
    }
    /*
     * USUARIO
     */
    public Usuario obtenerUsuarioPorDNI(String DNI) {
        try {
			return consulta.obtenerUsuarioPorDNI(DNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // se busca el usuario en la BD según su DNI

    }

    /*
     * VENTA
     */
    public void agregarVenta(String nombreMaterial, String DNI, String fecha, int cantidad){
        Venta nuevaVenta = new Venta(nombreMaterial, DNI, fecha, cantidad);
        try {
			cambios.insertarVenta(nuevaVenta);
		} catch (SQLException e) {
			System.out.println("Compra fallida...");
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se inserta la nueva venta en la BD
    }
    
    public void eliminarVenta(Venta venta) {
        try {
			cambios.eliminarVenta(venta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se elimina la venta de la BD
    }
    
    public List<Venta> obtenerVentas() {
        try {
			return consulta.obtenerVentas();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		} // se obtienen todas las ventas de la BD

    }
    
    public List<Venta> obtenerVentaPorId(String DNI) throws SQLException {
        return consulta.obtenerVentasCliente(DNI); // se busca la venta en la BD según su ID
    }
    
    public void actualizarVenta(Venta venta) {
        try {
			cambios.actualizarVenta(venta);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		} // se actualiza la información de la venta en la BD
    }


	public Usuario[] obtenerUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}


	public void eliminarUsuario(String dni) {
		// TODO Auto-generated method stub
		
	}


	public void agregarMaterial(Material material) {
		// TODO Auto-generated method stub
		
	}


	public void agregarMaterial(String nombre, double precio, int unidades, String act_asociada) {
		try {
			cambios.agregarMaterial(nombre,precio,unidades,act_asociada);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
	}
	
	public List<Actividad> getListaActividadesPorDNI(String DNI) {
		try {
			return consulta.obtenerActividadPorDNI(DNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		}
	}
	
	public List<Actividad> getListaActividades() {
		try {
			return consulta.getListaActividades();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		}
	}
	public List<Actividad> getActNoInscrito(String DNI) {
		try {
			return consulta.getActNoInscrito(DNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
			return null;
		}
	}
	public boolean inscribirActividad(Cliente cliente, Actividad actividad) {
		try {
			if(actividad.aniadirParticipante(cliente.getDNI())){
				cambios.inscribirActividad(cliente, actividad);
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());	
		}
		return false;
	}

	public boolean borrarUsuarioActividad(Cliente cliente, Actividad actividad) {
		try {
			if(actividad.borrarParticipante(cliente)){
				cambios.borrarUsuarioActividad(cliente, actividad);
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
		return false;
	}

	public boolean borrarUsuarioTodasActividades(String viejoDNI) {
		try {
			cambios.borrarUsuarioTodasActividades(viejoDNI);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
		}
		return false;
	}
	
	public void setSaldo(Cliente cliente) {
		try {
			cambios.setSaldo(cliente);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
	}

	public void cambiarContrasenya(Cliente cliente, String nuevaContra) {
		try {
			cambios.cambiarContrasenya(cliente,nuevaContra);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
		
	}

	public void darBajaUsusario(String DNI) {
		try {
			cambios.darBajaUsusario(DNI);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
	}

	public List<Cliente> getListaClientes() {
		try {
			return consulta.getListaClientes();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
		return null;
	}

	public List<Material> getMaterialesDisponibles() {
		try {
			return consulta.getListaMateriales();
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
		return null;
	}

	public void updateMaterial(String valueAt) throws SQLException {
		cambios.actualizarMaterial(valueAt);
	}

	public void setSaldo(double saldo, String string) throws SQLException {
		cambios.setSaldo(saldo, string);
	}

	public void updateMaterial(int cantidad, String nombre) throws SQLException {
		cambios.updateMaterial(cantidad, nombre);
	}
	
	public void actualizaCliente(Cliente cliente) {
		try {
			cambios.actualizaCliente(cliente);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
			
		}
	}

	public String getProfesorPorNombre(String nombre) {
		try {
			return consulta.getProfesorPorNombre(nombre);
		}
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());		
		}
		return null;
	}



}
