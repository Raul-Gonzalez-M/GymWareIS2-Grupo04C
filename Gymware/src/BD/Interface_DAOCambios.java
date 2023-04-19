package BD;

import java.sql.SQLException;

import model.Actividad;
import model.Aula;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Personal;
import model.Venta;

public interface Interface_DAOCambios {
	
	public void insertarCliente(Cliente user) throws SQLException;
	public boolean existeDni(String dni) throws SQLException;
	public void insertarPersonal(String DNI, String nombre, String contrasenya) throws SQLException;
	public void eliminarCliente(Cliente cliente) throws SQLException;
	public void actualizarCliente(Cliente cliente) throws SQLException;
	public void eliminarPersonal(Personal personal) throws SQLException;
	public void actualizarPersonal(Personal personal) throws SQLException;
	public void insertarAula(Aula nuevaAula) throws SQLException;
	public void eliminarAula(Aula aula) throws SQLException;
	public void actualizarAula(Aula aula) throws SQLException;
	public void insertarActividad(Actividad nuevaActividad) throws SQLException;
	public void eliminarActividad(Actividad actividad) throws SQLException;
	public void actualizarActividad(Actividad actividad) throws SQLException;
	public void guardarCompra(String nombre, String DNI, String fecha, int cantidad) throws SQLException;
	public void eliminarUnidades(String nombre, int unidades) throws SQLException;
	public void reponerUnidades(String nombre, int unidades) throws SQLException;
	public void cambiarPrecio(String nombre, double precio) throws SQLException;
	public void insertarMaterial(Material nuevoMaterial) throws SQLException;
	public void eliminarMaterial(String nombre) throws SQLException;
	public void actualizarMaterial(Material material) throws SQLException;
	public void insertarVenta(Venta nuevaVenta) throws SQLException;
	public void eliminarVenta(Venta venta) throws SQLException;
	public void actualizarVenta(Venta venta) throws SQLException;
	public void insertarEncuesta(Encuesta nuevaEncuesta) throws SQLException;
	public void eliminarEncuesta(Encuesta encuesta) throws SQLException;
	public void actualizarEncuesta(Encuesta encuesta) throws SQLException;
}
