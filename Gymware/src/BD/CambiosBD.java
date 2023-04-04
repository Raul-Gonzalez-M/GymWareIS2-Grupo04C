package BD;

import java.sql.*;

import model.Actividad;
import model.Aula;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Personal;
import model.Venta;

public class CambiosBD {
	private ConexionBD bd;
	
	public CambiosBD(ConexionBD bd) {
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
		String query = "INSERT INTO Cliente values(?, ?, ?)";
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
		String query = "INSERT INTO Compra_material VALUES(?, ?, ? ,?)";
		
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

	public void insertarAula(Aula nuevaAula) {
		
		
	}

	public void eliminarAula(Aula aula) {
		
		
	}

	public void actualizarAula(Aula aula) {
		
		
	}

	public void insertarActividad(Actividad nuevaActividad) {
		
		
	}

	public void eliminarActividad(Actividad actividad) {
		
		
	}

	public void actualizarActividad(Actividad actividad) {
		
		
	}

	public void eliminarCliente(Cliente cliente) {
		
	}

	public void actualizarCliente(Cliente cliente) {
		
		
	}

	public void insertarEncuesta(Encuesta nuevaEncuesta) {
		
		
	}

	public void eliminarEncuesta(Encuesta encuesta) {
		
		
	}

	public void actualizarEncuesta(Encuesta encuesta) {
		
		
	}

	public void insertarMaterial(Material nuevoMaterial) {
		
		
	}

	public void eliminarMaterial(Material material) {
		
		
	}

	public void actualizarMaterial(Material material) {
		
		
	}

	public void insertarPersonal(Personal nuevoPersonal) {
		
		
	}

	public void eliminarPersonal(Personal personal) {
		
		
	}

	public void actualizarPersonal(Personal personal) {
		
		
	}

	public void insertarVenta(Venta nuevaVenta) {
		
		
	}

	public void eliminarVenta(Venta venta) {
		
		
	}

	public void actualizarVenta(Venta venta) {
		
		
	}
	
}
