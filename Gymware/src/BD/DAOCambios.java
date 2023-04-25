package BD;

import java.sql.*;

import model.Actividad;
import model.Aula;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Personal;
import model.Venta;

public class DAOCambios{
	private ConexionBD bd;
	
	public DAOCambios(ConexionBD bd) {
		this.bd = bd;
	}
	
	/*
	 * Inserta un cliente con los datos pasados por parámetro, los cuales deben ser ya correctos
	 * (Probada)
	 */
	public void insertarCliente(String DNI, String nombre, String contrasenya, double saldo) throws SQLException{
		String query = "INSERT INTO Cliente values(?, ?, ?, curdate(), null, ?)";
		try ( PreparedStatement st = bd.getConnection().prepareStatement( query )) {
			st.setString(1, DNI);
			st.setString (2, nombre);
			st.setString (3, contrasenya);
			st.setDouble(4, saldo);
			st.executeUpdate ();
		}
	}
	
	/*
	 * Inserta un personal con los datos pasados por parámetro, los cuales deben ser ya correctos
	 * (Probada)
	 */
	public void insertarPersonal(String DNI, String nombre, String contrasenya) throws SQLException{
		String query = "INSERT INTO Cliente values('?', '?', '?')";
		try ( PreparedStatement st = bd.getConnection().prepareStatement( query )) {
			st.setString(1, DNI);
			st.setString (2, nombre);
			st.setString (3, contrasenya);
			st.executeUpdate ();
		}
	}
	
	/*
	 * Actualiza el historial de compras. Los datos deben ser correctos
	 * ()
	 */
	public void guardarCompra(String nombre, String DNI, String fecha, int cantidad) throws SQLException{
		String query = "INSERT INTO Compra_material VALUES('?', '?', '?' ,?)";
		
		try ( PreparedStatement st = bd.getConnection().prepareStatement( query )){
			st.setString(1, nombre);
			st.setString(2, DNI);
			st.setString(3, fecha);
			st.setDouble(4, cantidad);
			st.executeUpdate();
		}
	}
	
	/*
	 * Elimina el numero de unidades al material con dicho nombre.
	 * El numero de unidades debe ser menor o igual que el de unidades disponibles
	 * ()
	 */
	public void eliminarUnidades(String nombre, int unidades) throws SQLException {
		String query =    "UPDATE material"
						+ "SET Unidades = Unidades - " + unidades 
						+ " WHERE Nombre = " + nombre + ";";
		
		executeUpdate(query);
	}
	
	/*
	 * Incrementa el numero de unidades al material con dicho nombre.
	 * ()
	 */
	public void reponerUnidades(String nombre, int unidades) throws SQLException {
		String query =    "UPDATE material"
						+ "SET Unidades = Unidades + " + unidades 
						+ " WHERE Nombre = " + nombre + ";";
		
		executeUpdate(query);
	}
	
	/*
	 * Cambia a "precio" el precio del material con ese nombre
	 * ()
	 */
	public void cambiarPrecio(String nombre, double precio) throws SQLException {
		String query = "UPDATE Material"
				+ "SET Precio = " + precio +
				"WHERE Nombre = " + nombre + ";";
		
		executeUpdate(query);
	}
	
	/*
	 * Ejecuta una sentencia de insert, update o delete, con la consulta pasada como parametro
	 * (Probada)
	 */
	private void executeUpdate(String query) throws SQLException {
		try(PreparedStatement st = bd.getConnection().prepareStatement(query)){
			st.executeUpdate();
		}
	}

	/*
	 * inserta un aula nueva en la tabla
	 * ()
	 */
	public void insertarAula(Aula nuevaAula) throws SQLException {
		String query = "INSERT INTO Aula VALUES(" + nuevaAula.getId() + ", '" + nuevaAula.getActividad().getNombre() + "');";
		
		executeUpdate(query);
	}

	/*
	 * elimina el aula con el mismo numero que aula
	 * ()
	 */
	public void eliminarAula(Aula aula) throws SQLException {
		String query = "DELETE FROM Aula "
					 + "WHERE Numero = " + aula.getId() + ";";
		
		executeUpdate(query);
	}

	/*
	 * 
	 * 
	 */
	public void actualizarAula(Aula aula) throws SQLException {
		String query = "UPDATE TABLE Aula "
					 + "SET Actividad = " + aula.getActividad().getNombre() + " "
					 + "WHERE Numero = " + aula.getId() + ";";
		
		executeUpdate(query);
	}

	public void insertarActividad(Actividad nuevaActividad) throws SQLException {
		String query = "INSERT INTO Actividad VALUES('" 
					 + nuevaActividad.getNombre() + "', '" + nuevaActividad.getHorario() + "', '"
					 + nuevaActividad.getDNIProfesor() + "', " + nuevaActividad.getAula().getId() + ");";
		
		executeUpdate(query);
	}

	public void eliminarActividad(Actividad actividad) throws SQLException {
		String query = "DELETE FROM Actividad "
					 + "WHERE Nombre = '" + actividad.getNombre() + "';";
		
		executeUpdate(query);
	}

	public void actualizarActividad(Actividad actividad) throws SQLException {
		String query = "UPDATE TABLE Actividad "
				 	 + "SET Horario = '" + actividad.getHorario() + "', "
				 	 + "SET DNI_Profesor = '" + actividad.getDNIProfesor() + "', "
				 	 + "SET Aula = " + actividad.getAula().getId() + " "
				 	 + "WHERE Nombre = '" + actividad.getNombre() + "';";
		
		executeUpdate(query);
	}

	public void eliminarCliente(Cliente cliente) throws SQLException {
		String query = "DELETE FROM Cliente "
				+ "WHERE DNI = '" + cliente.getDNI() + "';";
		
		executeUpdate(query);
	}

	public void actualizarCliente(Cliente cliente) throws SQLException {
		String query = "UPDATE TABLE Cliente "
					 + "SET Nombre = '" + cliente.getNombre() + "', "
					 + "Contrasenya = '" + cliente.getContrasena() + "', "
					 + "FechaAlta = date'" + cliente.getFechaAlta() + "', "
					 + "Saldo = " + cliente.getSaldo() + " "
					 + "WHERE DNI = '" + cliente.getDNI() + "';";
		
		executeUpdate(query);
	}

	public void insertarEncuesta(Encuesta nuevaEncuesta) throws SQLException {
		String query = "INSERT INTO Encuesta VALUES('"
					  + nuevaEncuesta.getDNI() + "', '"
					  + nuevaEncuesta.getFecha() + "', "
					  + nuevaEncuesta.getSatisfaccion() + ", '"
					  + nuevaEncuesta.getCambios() + "', '"
					  + nuevaEncuesta.getParticipa() + "');";
		
		executeUpdate(query);
	}

	public void eliminarEncuesta(Encuesta encuesta) throws SQLException {
		String query = "DELETE FROM Encuesta "
					 + "WHERE DNI = '" + encuesta.getDNI() + "' AND "
					 + "Fecha = date'" + encuesta.getFecha() + "';";
		
		executeUpdate(query);
	}

	public void actualizarEncuesta(Encuesta encuesta) throws SQLException {
		String query = "UPDATE TABLE Encuesta "
				 + "SET Satisfaccion = " + encuesta.getSatisfaccion() + ", "
				 + "Cambios = '" + encuesta.getCambios() + "', "
				 + "Participa = '" + encuesta.getParticipa() + "' "
				 + "WHERE DNI = '" + encuesta.getDNI() + "' AND "
				 + "Fecha = '" + encuesta.getFecha() + "';";
		
		executeUpdate(query);
	}

	public void insertarMaterial(Material nuevoMaterial) throws SQLException {
		String query = "INSERT INTO Material VALUES('" 
					  + nuevoMaterial.getNombre() + "', "
					  + nuevoMaterial.getPrecio() + ", "
					  + nuevoMaterial.getUnidades() + ");";
		
		executeUpdate(query);
	}

	public void eliminarMaterial(Material material) throws SQLException {
		String query = "DELETE FROM Material "
					 + "WHERE Nombre = '" + material.getNombre() + "';";
	
		executeUpdate(query);
	}

	public void actualizarMaterial(Material material) throws SQLException {
		String query = "UPDATE TABLE Material "
					 + "SET Precio = " + material.getPrecio() + ", "
					 + "Unidades = " + material.getUnidades() + " "
					 + "WHERE Nombre = '" + material.getNombre() + "';";
		
		executeUpdate(query);
	}

	public void insertarPersonal(Personal nuevoPersonal) throws SQLException {
		String query = "INSERT INTO Personal VALUES('"
				+ nuevoPersonal.getDNI() + "', '"
				+ nuevoPersonal.getNombre() + "', '"
				+ nuevoPersonal.getContrasena() + "');";
		
		executeUpdate(query);
	}

	public void eliminarPersonal(Personal personal) throws SQLException {
		String query = "DELETE FROM Personal "
				 + "WHERE DNI = '" + personal.getDNI() + "';";

		executeUpdate(query);
	}

	public void actualizarPersonal(Personal personal) throws SQLException {
		String query = "UPDATE TABLE Personal "
				 	 + "SET Nombre = '" + personal.getNombre() + "', "
				 	 + "Contrasenya = '" + personal.getContrasena() + "' "
				 	 + "WHERE DNI = '" + personal.getDNI() + "';";
	
		executeUpdate(query);
	}

	public void insertarVenta(Venta nuevaVenta) throws SQLException {
		String query = "INSERT INTO compra_material VALUES('"
				+ nuevaVenta.getNombreMaterial() + "', '"
				+ nuevaVenta.getDNI() + "', '"
				+ nuevaVenta.getFecha() + "', "
				+ nuevaVenta.getCantidad() + ");";
		
		executeUpdate(query);
	}

	public void eliminarVenta(Venta venta) throws SQLException {
		String query = "DELETE FROM compra_material "
					 + "WHERE Nombre = '" + venta.getNombreMaterial() + "' AND "
					 + "DNI = '" + venta.getDNI() + "';";

		executeUpdate(query);
	}

	public void actualizarVenta(Venta venta) throws SQLException {
		String query = "UPDATE TABLE compra_material "
				 	 + "SET Fecha = date'" + venta.getFecha() + "', "
				 	 + "Cantidad = " + venta.getCantidad() + " "
				 	 + "WHERE DNI = '" + venta.getDNI() + "' AND"
				 	 		+ "Nombre = '" + venta.getNombreMaterial() + "';";
	
		executeUpdate(query);
	}

	public void inscribirActividad(Cliente cliente, Actividad actividad) throws SQLException {
		String query = "INSERT INTO PARTICIPA "
				+ "VALUES('" + cliente.getDNI() + "', '" 
				+ actividad.getNombre() + "');";
		
		executeUpdate(query);
	}
	
}
