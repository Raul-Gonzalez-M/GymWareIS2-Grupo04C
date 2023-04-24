package BD;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Actividad;
import model.Aula;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Personal;
import model.Venta;

public interface Interface_DAOCambios {
	
	public void insertarCliente(Cliente user) throws SQLException ;
	public boolean existeDni(String dni) throws SQLException;
	public void insertarPersonal(String DNI, String nombre, String contrasenya) throws SQLException;
	public void eliminarCliente(Cliente cliente) throws SQLException;
	public void actualizarCliente(Cliente cliente) throws SQLException;
	public void eliminarPersonal(Personal personal) throws SQLException;
	public void actualizarPersonal(Personal personal) throws SQLException;
	public void inscribirActividad(Cliente cliente, Actividad actividad) throws SQLException;
}
