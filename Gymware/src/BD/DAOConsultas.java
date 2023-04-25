package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Actividad;
import model.Aula;
import model.Cliente;
import model.Encuesta;
import model.Material;
import model.Personal;
import model.Usuario;
import model.Venta;
import tipos.DatosCliente;
import tipos.DatosMaterial;

public class DAOConsultas implements Interface_DAOConsultas{
    private ConexionBD bd;

    public DAOConsultas() {
		this.bd = new ConexionBD();
    }
    
    private ResultSet executeQueryAux(String query) throws SQLException {
    	try(Statement stmt = bd.getConnection().createStatement()){
    		return stmt.executeQuery(query);
    	}
    }
    public Usuario verificarCredenciales(String DNI, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        
        try {
            connection = bd.getConnection();
            String query = "SELECT * FROM usuarios WHERE DNI = ? AND Contraseña = ?";
            String participantesQuery = "SELECT actividad.* FROM actividad INNER JOIN participantes ON actividad.Id = participantes.Id_actividad WHERE participantes.DNICliente = ?";
            String capacidadQuery = "SELECT Capacidad FROM Aula WHERE Id = ?";
            String clienteQuery = "SELECT * FROM cliente WHERE DNI = ?";
            
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, DNI);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String tipoUsuario = resultSet.getString("TipoUsuario");
                if (tipoUsuario.equals("cliente")) {

                    PreparedStatement clienteStatement = connection.prepareStatement(clienteQuery);
                    clienteStatement.setString(1, DNI);
                    ResultSet clienteResult = clienteStatement.executeQuery();
                    if (clienteResult.next()) {
                        String nombre = clienteResult.getString("Nombre");
                        String apellidos = clienteResult.getString("Apellidos");
                        String fechaAlta = clienteResult.getString("FechaAlta");
                        double saldo = clienteResult.getDouble("Saldo");
                        usuario = new Cliente(DNI, nombre,apellidos, password, fechaAlta, saldo);
                    }
                    //Obtiene las actividades
                    PreparedStatement participantesStatement = connection.prepareStatement(participantesQuery);
                    participantesStatement.setString(1, usuario.getDNI());
                    ResultSet participantesResult = participantesStatement.executeQuery();
                    while (participantesResult.next()) {
        	        	int id = participantesResult.getInt("Id");
        	            String nombre = participantesResult.getString("Nombre");
        	            String horario = participantesResult.getString("Horario");
        	            String dniProfesor = participantesResult.getString("Nombre_profesor");
        	            int idAula = participantesResult.getInt("Id_Aula");
        	            int plazas = participantesResult.getInt("PlazasDisponibles");
        	            int capacidad = 0;
        	            try (PreparedStatement capacidadStatement = connection.prepareStatement(capacidadQuery)) {
        	                capacidadStatement.setInt(1, idAula);
        	                try (ResultSet capacidadResult = capacidadStatement.executeQuery()) {
        	                    if (capacidadResult.next()) {
        	                        capacidad = capacidadResult.getInt("Capacidad");
        	                    }
        	                }
        	            }
        	            Aula aula = new Aula.Builder().withId(idAula).withCapacidad(capacidad).build();
                        Actividad actividad = new Actividad.ActividadBuilder(id, nombre, horario, dniProfesor, aula,plazas).build();   
                        Cliente cliente = (Cliente) usuario;
                        cliente.getActividades().add(actividad);

                    }
                } else if (tipoUsuario.equals("personal")) {
                    String nombre = resultSet.getString("Nombre");
                    usuario = new Personal(DNI, nombre, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return usuario;
    }
    
    public boolean existeDni(String dni){
        String query = "SELECT COUNT(*) FROM usuarios WHERE DNI = ?";
        try (PreparedStatement st = bd.getConnection().prepareStatement(query)) {
            st.setString(1, dni);
            ResultSet rs = st.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    

	/*
	 * Devuelve la lista de todas las actividades
	 * ()
	 */
	public Actividad[] obtenerListaActividades() throws SQLException {
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    ArrayList<Actividad> actividades = new ArrayList<>();

	    try {
	        connection = bd.getConnection();
	        String query = "SELECT * FROM Actividad";
	        preparedStatement = connection.prepareStatement(query);
	        resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	        	int id = resultSet.getInt("Id");
	            String nombre = resultSet.getString("Nombre");
	            String horario = resultSet.getString("Horario");
	            String dniProfesor = resultSet.getString("Nombre_profesor");
	            int idAula = resultSet.getInt("Id_Aula");
	            int plazas = resultSet.getInt("PlazasDisponibles");
	            // Consultar la capacidad del aula
	            int capacidad = 0;
	            String capacidadQuery = "SELECT Capacidad FROM Aula WHERE Id = ?";
	            try (PreparedStatement capacidadStatement = connection.prepareStatement(capacidadQuery)) {
	                capacidadStatement.setInt(1, idAula);
	                try (ResultSet capacidadResult = capacidadStatement.executeQuery()) {
	                    if (capacidadResult.next()) {
	                        capacidad = capacidadResult.getInt("Capacidad");
	                    }
	                }
	            }

	            Aula aula = new Aula.Builder().withId(idAula).withCapacidad(capacidad).build();

	            ArrayList<Cliente> participantes = new ArrayList<>();

	            Actividad actividad = new Actividad.ActividadBuilder(id, nombre, horario, dniProfesor, aula, plazas).setParticipantes(participantes).build();

	            actividades.add(actividad);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    return actividades.toArray(new Actividad[0]);
	}

	/*
	 * Devuelve una encuesta segun el DNI del cliente y la fecha cuando al realizo
	 * ()
	 */
	public Encuesta obtenerEncuestaPorDNI(String DNI, String fecha) throws SQLException {
		String query = "SELECT * "
					 + "FROM Encuesta "
					 + "WHERE ((DNI = '" + DNI + "') AND (Fecha = '" + fecha + "'));";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next(); 
	
}
