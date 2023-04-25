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
	
	public void inscribirActividad(Cliente cliente, Actividad actividad) throws SQLException{
		String query1 = "INSERT INTO participantes (Id_actividad,DNICliente) VALUES (?, ?);";
		String query2 = "UPDATE actividad SET PlazasDisponibles = PlazasDisponibles - 1 WHERE Id = ?";

	    try (PreparedStatement st1 = bd.getConnection().prepareStatement(query1);
	         PreparedStatement st2 = bd.getConnection().prepareStatement(query2)) {
	        bd.getConnection().setAutoCommit(false); 

	        st1.setInt(1, actividad.getId());
	        st1.setString(2, cliente.getDNI());
	        st1.executeUpdate();

	        st2.setInt(1, actividad.getId());
	        st2.executeUpdate();

	        bd.getConnection().commit(); 
	    } catch (SQLException e) {
	        bd.getConnection().rollback(); 
	        throw new SQLException("No se ha podido inscribir al cliente en la actividad.", e);
	    } finally {
	        bd.getConnection().setAutoCommit(true);
	    }
		
	}

	public void inscribirActividad(Cliente cliente, Actividad actividad) throws SQLException {
		String query = "INSERT INTO PARTICIPA "
				+ "VALUES('" + cliente.getDNI() + "', '" 
				+ actividad.getNombre() + "');";
		
		executeUpdate(query);
	}
	
	/*
	 * MATERIAL/VENTAS
	 */
	
	
	/*
	 * ENCUESTAS
	 */




}
