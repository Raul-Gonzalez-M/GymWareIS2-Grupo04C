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

public class DAOConsultas {
    private ConexionBD bd;

    public DAOConsultas(ConexionBD bd){
        this.bd = bd;
    }

    public boolean DNIDisponible(String DNI) throws SQLException{
        if(DNI.length() != 9)
            return false;

        String query =    "SELECT DNI FROM CLIENTE"
        				+ " UNION"
        				+ "	SELECT DNI FROM PERSONAL;";
        
        boolean ret = true;
	    ResultSet rs = executeQueryAux(query);
	    while(rs.next() && ret){
	        if(rs.getString("DNI").equals(DNI))
	            ret = false;
	    }
        
        return ret;
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
                        usuario = new Cliente(DNI, nombre,apellidos, password, saldo);
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
        	            Aula aula = new Aula(capacidad, null);
                        Actividad actividad = new Actividad(id, nombre, horario, dniProfesor, aula,plazas);   
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
    
    public DatosCliente datosCliente(String DNI) throws SQLException {
    	String query =    "select DNI, Nombre, FechaAlta, FechaBaja, Saldo "
    					+ "from cliente "
    					+ "where dni = '" + DNI + "';";
    	
    	ResultSet rs =  executeQueryAux(query);
    	
    	if(rs.next())
    		return new DatosCliente(rs.getString("DNI"), rs.getString("nombre"), rs.getString("FechaAlta"),
    					       			rs.getString("FechaBaja"), rs.getDouble("saldo"));
    	else
    		return null;
    }
    
    
    public List<DatosMaterial> listaMateriales(int unidades) throws SQLException{
    	List<DatosMaterial> ret = new ArrayList<>();
    	
    	String query =    "SELECT Nombre, Precio, Unidades "
    					+ "FROM Material"
    					+ "WHERE unidades >= " + unidades + ";";
    	
    	ResultSet rs = executeQueryAux(query);
    		
    	while(rs.next()) {
    		DatosMaterial dm = new DatosMaterial(rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("unidades"));
    		ret.add(dm);
    	}
    	
    	return ret;
    }

	public Cliente obtenerClientePorId(String DNI) throws SQLException {
		String query = "SELECT * "
					 + "FROM Cliente "
					 + "WHERE DNI = '" + DNI + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		if(rs.next())
			return new Cliente(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")
				         , rs.getString("FechaAlta"), rs.getDouble("Saldo"));
		return null;
	}

	public List<Cliente> obtenerClientes() throws SQLException {
		List<Cliente> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Cliente;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Cliente(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")
				         , rs.getString("FechaAlta"),  rs.getDouble("Saldo")));
		}
		
		return ret;
	}

	
	/*public Aula obtenerAulaPorId(int id) throws SQLException {

		String query = "SELECT * "
				+ "FROM Aula "
				+ "WHERE Numero = " + id + ";";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next();
		
		String aux = rs.getString("Actividad");
		Actividad act = obtenerActividadPorNombre(aux);
		
		return new Aula(rs.getInt("Numero"), act);
	}


	public List<Aula> obtenerAulas() throws SQLException {
		List<Aula> ret = new ArrayList<>();
		String query= "SELECT * "
				+ "FROM Aula;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			String aux = rs.getString("Actividad");
			Actividad act = obtenerActividadPorNombre(aux);
			
			ret.add(new Aula(rs.getInt("Numero"), act));
		}
		
		return ret;
	}


	public Actividad obtenerActividadPorNombre(String nombre) throws SQLException {
		String query = "SELECT * "
					 + "FROM Actividad "
					 + "WHERE Nombre = '" + nombre + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next();
		
		int aux = rs.getInt("Aula");
		Aula aula = obtenerAulaPorId(aux);
		
		return new Actividad(rs.getString("Nombre"), rs.getString("Horario"), rs.getString("DNI_Profesor"), aula);
	}


	public List<Actividad> obtenerActividades() throws SQLException {
		List<Actividad> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Actividad;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			int aux = rs.getInt("Aula");
			Aula aula = obtenerAulaPorId(aux);
			
			ret.add(new Actividad(rs.getString("Nombre"), rs.getString("Horario"), rs.getString("DNI_Profesor"), aula));
		}
		
		return ret;
	}*/

	public Encuesta obtenerEncuestaPorDNI(String DNI, String fecha) throws SQLException {
		String query = "SELECT * "
					 + "FROM Encuesta "
					 + "WHERE ((DNI = '" + DNI + "') AND (Fecha = '" + fecha + "'));";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next(); 
	
		return new Encuesta(rs.getString("DNI"), rs.getString("Fecha"), rs.getInt("Satisfaccion")
				, rs.getString("Cambios"), rs.getString("Participa"));
	}

	public List<Encuesta> obtenerEncuestas() throws SQLException {
		List<Encuesta> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Encuesta;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Encuesta(rs.getString("DNI"), rs.getString("Fecha"), rs.getInt("Satisfaccion")
				, rs.getString("Cambios"), rs.getString("Participa")));
		}
		
		return ret;
	}

	public Material obtenerMaterialPorNombre(String nombre) throws SQLException {
		String query = "SELECT * "
					 + "FROM Material "
					 + "WHERE Nombre = '" + nombre + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next();
		
		return new Material(rs.getString("Nombre"), rs.getDouble("Precio"), rs.getInt("Unidades"));
	}

	public List<Material> obtenerMateriales() throws SQLException {
		List<Material> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Material;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Material(rs.getString("Nombre"), rs.getDouble("Precio"), rs.getInt("Unidades")));
		}
		
		return ret;
	}

	public Personal obtenerPersonalPorDNI(String DNI) throws SQLException {
		String query = "SELECT * "
					 + "FROM Personal "
					 + "WHERE DNI = '" + DNI + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		if(rs.next())
			return new Personal(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya"));
		return null;
	}

	public List<Personal> obtenerPersonal() throws SQLException {
		List<Personal> ret = new ArrayList<Personal>();
		
		String query = "SELECT * "
					 + "FROM Personal;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Personal(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")));
		}
		
		return ret;
	}
	
	public Usuario obtenerUsuarioPorDNI(String DNI) throws SQLException {
		Usuario ret = obtenerClientePorId(DNI);
		if(ret != null) {
			return ret;
		}
		return obtenerPersonalPorDNI(DNI);
	}

	public Venta obtenerVenta(String nombre, String DNI, String fecha) throws SQLException {
		String query = "SELECT * "
					 + "FROM compra_material "
					 + "WHERE (Nombre = '" + nombre + "') AND "
					  	   + "(DNI = '" + DNI + "') AND "
					       + "(Fecha = '" + fecha + "');";
		ResultSet rs = executeQueryAux(query);
		
		rs.next();
		
		return new Venta(rs.getString("Nombre"), rs.getString("DNI"), rs.getString("Fecha"), rs.getInt("Cantidad"));
	}

	public List<Venta> obtenerVentasCliente(String DNI) throws SQLException {
		List<Venta> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM compra_material "
					 + "WHERE DNI = '" + DNI + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Venta(rs.getString("Nombre"), rs.getString("DNI"), rs.getString("Fecha"), rs.getInt("Cantidad")));
		}
		
		return ret;
	}
	
	public List<Venta> obtenerVentas() throws SQLException{
		List<Venta> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM compra_material;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Venta(rs.getString("Nombre"), rs.getString("DNI"), rs.getString("Fecha"), rs.getInt("Cantidad")));
		}
		
		return ret;
	}
	
	public List<String> inscritosActividad(Actividad actividad) throws SQLException{
		List<String> ret = new ArrayList<>();
		
		String query = "SELECT *"
				+ "FROM Participa"
				+ "WHERE Nombre_Actividad = '" + actividad.getNombre() + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(rs.getString("DNI_Cliente"));
		}
		
		
		return ret;
	}
}
