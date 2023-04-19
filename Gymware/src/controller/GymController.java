package controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import BD.DAOCambios;
import BD.DAOConsultas;
import model.*;

public class GymController {
	
    private DAOCambios cambios;
    private DAOConsultas consulta;
    private Usuario user;
    
    public GymController() {
        this.cambios = new DAOCambios();
        this.consulta = new DAOConsultas();
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
			cambios.insertarCliente(user);
		} catch (SQLException e) {
			throw new SQLException("No se ha podido agregar el cliente.", e);
		} 
    }
    
    public boolean existeDni(String DNI) {
        return consulta.existeDni(DNI);
    } 
    
    public void eliminarCliente(Cliente cliente) {
        try {
			cambios.eliminarCliente(cliente);
		} catch (SQLException e) {

			e.printStackTrace();
		}
    }

    public List<Cliente> obtenerClientes() {
        try {
			return consulta.obtenerClientes(); 
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} 
    }

    public Cliente obtenerClientePorId(String DNI) {
        try {
			return consulta.obtenerClientePorId(DNI);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} 
    }

    public void actualizarCliente(Cliente cliente) {
        try {
			cambios.actualizarCliente(cliente);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public Usuario verificarCredenciales(String DNI, String password) {
    	return consulta.verificarCredenciales(DNI, password);
    }
    /*
     * ACTIVIDAD
     */
    public void agregarActividad(String nombre, String horario, String DNIProfesor, Aula aula) {
        Actividad nuevaActividad = new Actividad(nombre, horario, DNIProfesor, aula);
        try {
			cambios.insertarActividad(nuevaActividad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se inserta la nueva actividad en la BD
    }
    
    public void eliminarActividad(Actividad actividad) {
    	try {
			cambios.eliminarActividad(actividad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se elimina la actividad de la BD
    }
    
    public List<Actividad> obtenerActividades() {
    	try {
    		return consulta.obtenerActividades(); // se obtienen todas las actividades de la BD
    	} catch (SQLException e) {
    		e.printStackTrace();
    		System.out.println(e.getMessage());
    		return null;
    	}
    }
    
    public Actividad obtenerActividadPorNombre(String nombre) {
        try {
			return consulta.obtenerActividadPorNombre(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
    }
    
    public void actualizarActividad(Actividad actividad) {
        try {
			cambios.actualizarActividad(actividad);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se actualiza la información de la actividad en la BD
    }
    
    /*
     * AULAS
     */
    public void agregarAula(int id, Actividad actividad) {
        Aula nuevaAula = new Aula(id, actividad);
        try {
			cambios.insertarAula(nuevaAula);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se inserta la nueva aula en la BD
    }
    
    public void eliminarAula(Aula aula) {
    	try {
			cambios.eliminarAula(aula);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se elimina la aula de la BD
    }
    
    public List<Aula> obtenerAulas() {
        try {
			return consulta.obtenerAulas();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
    }
    
    public Aula obtenerAulaPorId(int id) {
        try {
			return consulta.obtenerAulaPorId(id); // se busca la aula en la BD según su ID
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} 
    }
    
    

    /*
     * ENCUESTA
     */
    public void agregarEncuesta(String DNI, String fecha, int satisfaccion, String cambios, String participa) {
        Encuesta nuevaEncuesta = new Encuesta(DNI, fecha, satisfaccion, cambios, participa);
        cambios.insertarEncuesta(nuevaEncuesta);
    }

    public void eliminarEncuesta(Encuesta encuesta) {
        try {
			cambios.eliminarEncuesta(encuesta);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
    }

    public List<Encuesta> obtenerEncuestas() {
        try {
			return consulta.obtenerEncuestas();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
    }

    public Encuesta obtenerEncuestaPorTitulo(String titulo) {
        return consulta.obtenerEncuestaPorTitulo(titulo); 
    }

    public void actualizarEncuesta(Encuesta encuesta) {
        try {
			cambios.actualizarEncuesta(encuesta);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} 
    }

    public void responderEncuesta(Encuesta encuesta, Cliente cliente, List<Respuesta> respuestas) {
        // Agrega las respuestas del cliente a la encuesta
        encuesta.agregarRespuestas(cliente, respuestas);
        cambios.actualizarEncuesta(encuesta); // se actualiza la información de la encuesta en la BD
    }

    public List<Respuesta> obtenerRespuestasDeEncuestaPorCliente(Encuesta encuesta, Cliente cliente) {
        return consulta.obtenerRespuestasDeEncuestaPorCliente(encuesta, cliente); // obtiene las respuestas de un cliente específico para una encuesta
    }

    /*
     * MATERIAL
     */
    public void agregarMaterial(String nombre, double precio) {
        Material nuevoMaterial = new Material(nombre, precio);
        try {
			cambios.insertarMaterial(nuevoMaterial);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // inserta el nuevo material en la BD
    }

    public void eliminarMaterial(String nombre) {
        try {
			cambios.eliminarMaterial(nombre);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // elimina el material de la BD
    }

    public List<Material> obtenerMateriales() {
        try {
			return consulta.obtenerMateriales();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // obtiene todos los materiales de la BD
    }

    public Material obtenerMaterialPorNombre(String nombre) {
        try {
			return consulta.obtenerMaterialPorNombre(nombre);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;;
		} // busca el material en la BD según su nombre
    }

    public void actualizarMaterial(Material material) {
        try {
			cambios.actualizarMaterial(material);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // actualiza la información del material en la BD
    }
    /*
     * PERSONAL
     */
    public void agregarPersonal(String DNI, String nombre, String apellido, int edad, String correoElectronico, String contrasena, int idPersonal, String puesto) {
        Personal nuevoPersonal = new Personal(DNI, nombre, apellido);
        try {
			cambios.insertarPersonal(nuevoPersonal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se inserta el nuevo personal en la BD
    }

    public void eliminarPersonal(Personal personal) {
        try {
			cambios.eliminarPersonal(personal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se elimina el personal de la BD
    }

    public List<Personal> obtenerPersonal() {
        try {
			return consulta.obtenerPersonal();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // se obtiene todo el personal de la BD
    }

    public Personal obtenerPersonalPorDNI(String DNI) {
        try {
			return consulta.obtenerPersonalPorDNI(DNI);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // se busca el personal en la BD según su DNI
    }

    public void actualizarPersonal(Personal personal) {
        try {
			cambios.actualizarPersonal(personal);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se actualiza la información del personal en la BD
    }
    /*
     * USUARIO
     */
    public Usuario obtenerUsuarioPorDNI(String DNI) {
        try {
			return consulta.obtenerUsuarioPorDNI(DNI);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // se busca el usuario en la BD según su DNI

    }

    /*
     * VENTA
     */
    public void agregarVenta(LocalDateTime fecha, Cliente cliente, List<Producto> productos, double total) {
        Venta nuevaVenta = new Venta(fecha, cliente, productos, total);
        cambios.insertarVenta(nuevaVenta); // se inserta la nueva venta en la BD
    }
    
    public void eliminarVenta(Venta venta) {
        try {
			cambios.eliminarVenta(venta);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // se elimina la venta de la BD
    }
    
    public List<Venta> obtenerVentas() {
        try {
			return consulta.obtenerVentas();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} // se obtienen todas las ventas de la BD

    }
    
    public Venta obtenerVentaPorId(int id) {
        return consulta.obtenerVentaPorId(id); // se busca la venta en la BD según su ID
    }
    
    public void actualizarVenta(Venta venta) {
        try {
			cambios.actualizarVenta(venta);
		} catch (SQLException e) {
			e.printStackTrace();
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


	public void agregarMaterial(Object material) {
		// TODO Auto-generated method stub
		
	}



}
