package controller;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import BD.CambiosBD;
import BD.ConsultasBD;
import model.*;

public class GymController {
	
    private CambiosBD cambios;
    private ConsultasBD consulta;
    
    public GymController(CambiosBD bd_cambios,ConsultasBD bd_consulta) {
        this.cambios = bd_cambios;
        this.consulta = bd_consulta;
    }
    
    /*
     * ACTIVIDAD
     */
    public void agregarActividad(String nombre, String horario, String DNIProfesor, Aula aula) {
        Actividad nuevaActividad = new Actividad(nombre, horario, DNIProfesor, aula);
        cambios.insertarActividad(nuevaActividad); // se inserta la nueva actividad en la BD
    }
    
    public void eliminarActividad(Actividad actividad) {
    	cambios.eliminarActividad(actividad); // se elimina la actividad de la BD
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
        cambios.actualizarActividad(actividad); // se actualiza la información de la actividad en la BD
    }
    
    /*
     * AULAS
     */
    public void agregarAula(int id, Actividad actividad) {
        Aula nuevaAula = new Aula(id, actividad);
        cambios.insertarAula(nuevaAula); // se inserta la nueva aula en la BD
    }
    
    public void eliminarAula(Aula aula) {
    	cambios.eliminarAula(aula); // se elimina la aula de la BD
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
     * CLIENTE
     */
    public void agregarCliente(String DNI, String nombre, String contrasena, String fechaAlta, String fechaBaja, double saldo) {
        Cliente nuevoCliente = new Cliente(DNI,nombre, contrasena, fechaAlta, fechaBaja, saldo);
        try {
			cambios.insertarCliente(DNI, nombre, contrasena, saldo); // inserta el nuevo cliente en la BD
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
    }

    public void eliminarCliente(Cliente cliente) {
        cambios.eliminarCliente(cliente); // elimina el cliente de la BD
    }

    public List<Cliente> obtenerClientes() {
        try {
			return consulta.obtenerClientes(); // obtiene todos los clientes de la BD
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} 
    }

    public Cliente obtenerClientePorId(String DNI) {
        try {
			return consulta.obtenerClientePorId(DNI); // busca el cliente en la BD según su ID
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		} 
    }

    public void actualizarCliente(Cliente cliente) {
        cambios.actualizarCliente(cliente); // actualiza la información del cliente en la BD
    }

    /*
     * ENCUESTA
     */
    public void agregarEncuesta(String DNI, String fecha, int satisfaccion, String cambios, String participa) {
        Encuesta nuevaEncuesta = new Encuesta(DNI, fecha, satisfaccion, cambios, participa);
        cambios.insertarEncuesta(nuevaEncuesta); // se inserta la nueva encuesta en la BD
    }

    public void eliminarEncuesta(Encuesta encuesta) {
        cambios.eliminarEncuesta(encuesta); // se elimina la encuesta de la BD
    }

    public List<Encuesta> obtenerEncuestas() {
        return consulta.obtenerEncuestas(); // se obtienen todas las encuestas de la BD
    }

    public Encuesta obtenerEncuestaPorTitulo(String titulo) {
        return consulta.obtenerEncuestaPorTitulo(titulo); // se busca la encuesta en la BD según su título
    }

    public void actualizarEncuesta(Encuesta encuesta) {
        cambios.actualizarEncuesta(encuesta); // se actualiza la información de la encuesta en la BD
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
        cambios.insertarMaterial(nuevoMaterial); // inserta el nuevo material en la BD
    }

    public void eliminarMaterial(Material material) {
        cambios.eliminarMaterial(material); // elimina el material de la BD
    }

    public List<Material> obtenerMateriales() {
        return consulta.obtenerMateriales(); // obtiene todos los materiales de la BD
    }

    public Material obtenerMaterialPorNombre(String nombre) {
        return consulta.obtenerMaterialPorNombre(nombre); // busca el material en la BD según su nombre
    }

    public void actualizarMaterial(Material material) {
        cambios.actualizarMaterial(material); // actualiza la información del material en la BD
    }
    /*
     * PERSONAL
     */
    public void agregarPersonal(String DNI, String nombre, String apellido, int edad, String correoElectronico, String contrasena, int idPersonal, String puesto) {
        Personal nuevoPersonal = new Personal(DNI, nombre, apellido, edad, correoElectronico, contrasena, idPersonal, puesto);
        cambios.insertarPersonal(nuevoPersonal); // se inserta el nuevo personal en la BD
    }

    public void eliminarPersonal(Personal personal) {
        cambios.eliminarPersonal(personal); // se elimina el personal de la BD
    }

    public List<Personal> obtenerPersonal() {
        return consulta.obtenerPersonal(); // se obtiene todo el personal de la BD
    }

    public Personal obtenerPersonalPorDNI(String DNI) {
        return consulta.obtenerPersonalPorDNI(DNI); // se busca el personal en la BD según su DNI
    }

    public void actualizarPersonal(Personal personal) {
        cambios.actualizarPersonal(personal); // se actualiza la información del personal en la BD
    }
    /*
     * USUARIO
     */
    public Usuario obtenerUsuarioPorDNI(String DNI) {
        return consulta.obtenerUsuarioPorDNI(DNI); // se busca el usuario en la BD según su DNI
    }

    /*
     * VENTA
     */
    public void agregarVenta(LocalDateTime fecha, Cliente cliente, List<Producto> productos, double total) {
        Venta nuevaVenta = new Venta(fecha, cliente, productos, total);
        cambios.insertarVenta(nuevaVenta); // se inserta la nueva venta en la BD
    }
    
    public void eliminarVenta(Venta venta) {
        cambios.eliminarVenta(venta); // se elimina la venta de la BD
    }
    
    public List<Venta> obtenerVentas() {
        return consulta.obtenerVentas(); // se obtienen todas las ventas de la BD
    }
    
    public Venta obtenerVentaPorId(int id) {
        return consulta.obtenerVentaPorId(id); // se busca la venta en la BD según su ID
    }
    
    public void actualizarVenta(Venta venta) {
        cambios.actualizarVenta(venta); // se actualiza la información de la venta en la BD
    }

}
