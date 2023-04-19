package BD;

import java.sql.*;

import model.Actividad;
import model.Aula;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Personal;
import model.Usuario;
import model.Venta;

public class DAOCambios implements Interface_DAOCambios{
	private ConexionBD bd;
	
	public DAOCambios() {
		this.bd = new ConexionBD();
	}
	
	private void executeUpdate(String query) throws SQLException {
		try(PreparedStatement st = bd.getConnection().prepareStatement(query)){
			st.executeUpdate();
		}
	}
	
	/*
	 * USUARIOS
	 */
	
	public void insertarCliente(Cliente user) throws SQLException {
	    String query1 = "INSERT INTO usuarios values(?, ?, ?, ?)";
	    String query2 = "INSERT INTO cliente values(?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement st1 = bd.getConnection().prepareStatement(query1);
	            PreparedStatement st2 = bd.getConnection().prepareStatement(query2)) {

	        bd.getConnection().setAutoCommit(false); 
	        st1.setString(1, user.getDNI());
	        st1.setString(2, user.getNombre());
	        st1.setString(3, user.getContrasena());
	        st1.setString(4, "cliente");
	        st1.executeUpdate();

	        st2.setString(1, user.getDNI());
	        st2.setString(2, user.getNombre());
	        st2.setString(3, user.getApellidos());
	        st2.setString(4, user.getContrasena());
	        st2.setString(5, user.getFechaAlta());
	        st2.setDouble(6, user.getSaldo());
	        st2.executeUpdate();

	        bd.getConnection().commit(); 

	    } catch (SQLException e) {
	        bd.getConnection().rollback(); 
	        throw new SQLException("No se ha podido agregar el cliente.", e);
	    } finally {
	        bd.getConnection().setAutoCommit(true);
	    }
	}

	public boolean existeDni(String dni) throws SQLException {
	    String query = "SELECT COUNT(*) FROM usuarios WHERE DNI = ?";
	    try (PreparedStatement st = bd.getConnection().prepareStatement(query)) {
	        st.setString(1, dni);
	        ResultSet rs = st.executeQuery();
	        rs.next();
	        int count = rs.getInt(1);
	        return count > 0;
	    }
	}
	
	public void insertarPersonal(String DNI, String nombre, String contrasenya) throws SQLException{
		String query = "INSERT INTO usuario values(?, ?, ?, ?)";
		
		try ( PreparedStatement st = bd.getConnection().prepareStatement( query )) {
			st.setString(1, DNI);
			st.setString (2, nombre);
			st.setString (3, contrasenya);
	        st.setString (4, "personal");
			st.executeUpdate ();
		}
		
		query = "INSERT INTO personal values('?', '?', '?')";
		try ( PreparedStatement st = bd.getConnection().prepareStatement( query )) {
			st.setString(1, DNI);
			st.setString (2, nombre);
			st.setString (3, contrasenya);
			st.executeUpdate ();
		}
	}
	
	public void eliminarCliente(Cliente cliente) throws SQLException {
		String query = "DELETE FROM Cliente " + "WHERE DNI = '" + cliente.getDNI() + "';";
		
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
	

	public void eliminarPersonal(Personal personal) throws SQLException {
		String query = "DELETE FROM Personal " + "WHERE DNI = '" + personal.getDNI() + "';";

		executeUpdate(query);
	}

	public void actualizarPersonal(Personal personal) throws SQLException {
		String query = "UPDATE TABLE Personal "
				 	 + "SET Nombre = '" + personal.getNombre() + "', "
				 	 + "Contrasenya = '" + personal.getContrasena() + "' "
				 	 + "WHERE DNI = '" + personal.getDNI() + "';";
	
		executeUpdate(query);
	}
	/*
	 * ACTIVIDADES
	 */

	public void insertarAula(Aula nuevaAula) throws SQLException {
		String query = "INSERT INTO Aula VALUES(" + nuevaAula.getId() + ", '" + nuevaAula.getActividad().getNombre() + "');";
		
		executeUpdate(query);
	}


	public void eliminarAula(Aula aula) throws SQLException {
		String query = "DELETE FROM Aula " + "WHERE Numero = " + aula.getId() + ";";
		
		executeUpdate(query);
	}


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
	
	/*
	 * MATERIAL/VENTAS
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
	

	public void eliminarUnidades(String nombre, int unidades) throws SQLException {
		String query =    "UPDATE material"
						+ "SET Unidades = Unidades - " + unidades 
						+ " WHERE Nombre = " + nombre + ";";
		
		executeUpdate(query);
	}
	

	public void reponerUnidades(String nombre, int unidades) throws SQLException {
		String query =    "UPDATE material"
						+ "SET Unidades = Unidades + " + unidades 
						+ " WHERE Nombre = " + nombre + ";";
		
		executeUpdate(query);
	}
	

	public void cambiarPrecio(String nombre, double precio) throws SQLException {
		String query = "UPDATE Material"
				+ "SET Precio = " + precio +
				"WHERE Nombre = " + nombre + ";";
		
		executeUpdate(query);
	}
	

	public void insertarMaterial(Material nuevoMaterial) throws SQLException {
		String query = "INSERT INTO Material VALUES('" 
					  + nuevoMaterial.getNombre() + "', "
					  + nuevoMaterial.getPrecio() + ", "
					  + nuevoMaterial.getUnidades() + ");";
		
		executeUpdate(query);
	}

	public void eliminarMaterial(String nombre) throws SQLException {
		String query = "DELETE FROM Material "
					 + "WHERE Nombre = '" + nombre + "';";
	
		executeUpdate(query);
	}

	public void actualizarMaterial(Material material) throws SQLException {
		String query = "UPDATE TABLE Material "
					 + "SET Precio = " + material.getPrecio() + ", "
					 + "Unidades = " + material.getUnidades() + " "
					 + "WHERE Nombre = '" + material.getNombre() + "';";
		
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
	/*
	 * ENCUESTAS
	 */

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


}
