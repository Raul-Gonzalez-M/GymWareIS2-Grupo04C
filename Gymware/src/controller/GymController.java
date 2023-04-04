package controller;

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
    public void agregarActividad(String nombre, LocalDateTime fechaInicio, LocalDateTime fechaFin, String descripcion) {
        Actividad nuevaActividad = new Actividad(nombre, fechaInicio, fechaFin, descripcion);
        cambios.insertarActividad(nuevaActividad); // se inserta la nueva actividad en la BD
    }
    
    public void eliminarActividad(Actividad actividad) {
    	cambios.eliminarActividad(actividad); // se elimina la actividad de la BD
    }
    
    public List<Actividad> obtenerActividades() {
        return consulta.obtenerActividades(); // se obtienen todas las actividades de la BD
    }
    
    public Actividad obtenerActividadPorNombre(String nombre) {
        return consulta.obtenerActividadPorNombre(nombre); // se busca la actividad en la BD según su nombre
    }
    
    public void actualizarActividad(Actividad actividad) {
        cambios.actualizarActividad(actividad); // se actualiza la información de la actividad en la BD
    }
    
    /*
     * AULAS
     */
    public void agregarAula(int id, int capacidadMaxima, String nombre) {
        Aula nuevaAula = new Aula(id, capacidadMaxima, nombre);
        cambios.insertarAula(nuevaAula); // se inserta la nueva aula en la BD
    }
    
    public void eliminarAula(Aula aula) {
    	cambios.eliminarAula(aula); // se elimina la aula de la BD
    }
    
    public List<Aula> obtenerAulas() {
        return consulta.obtenerAulas(); // se obtienen todas las aulas de la BD
    }
    
    public Aula obtenerAulaPorId(int id) {
        return consulta.obtenerAulaPorId(id); // se busca la aula en la BD según su ID
    }
    
    public void agregarClaseAAula(int idAula, Actividad actividad) {
        Aula aula = obtenerAulaPorId(idAula);
        if (aula != null) {
            aula.agregarClase(actividad);
            cambios.actualizarAula(aula); // se actualiza la información de la aula en la BD
        }
    }
    
    public void eliminarClaseDeAula(int idAula, Actividad actividad) {
        Aula aula = obtenerAulaPorId(idAula);
        if (aula != null) {
            aula.eliminarClase(actividad);
            cambios.actualizarAula(aula); // se actualiza la información de la aula en la BD
        }
    }
    
    /*
     * CLIENTE
     */
    public void agregarCliente(String DNI,String nombre, String apellido, int edad, String correoElectronico, String contrasena, int idCliente, double peso, double altura, double saldo) {
        Cliente nuevoCliente = new Cliente(DNI,nombre, apellido, edad, correoElectronico, contrasena, idCliente, peso, altura,saldo);
        cambios.insertarCliente(DNI, nombre, contrasena, saldo); // inserta el nuevo cliente en la BD
    }

    public void eliminarCliente(Cliente cliente) {
        cambios.eliminarCliente(cliente); // elimina el cliente de la BD
    }

    public List<Cliente> obtenerClientes() {
        return consulta.obtenerClientes(); // obtiene todos los clientes de la BD
    }

    public Cliente obtenerClientePorId(int idCliente) {
        return consulta.obtenerClientePorId(idCliente); // busca el cliente en la BD según su ID
    }

    public void actualizarCliente(Cliente cliente) {
        cambios.actualizarCliente(cliente); // actualiza la información del cliente en la BD
    }

    /*
     * ENCUESTA
     */
    public void agregarEncuesta(String titulo, List<Pregunta> preguntas) {
        Encuesta nuevaEncuesta = new Encuesta(titulo, preguntas);
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
