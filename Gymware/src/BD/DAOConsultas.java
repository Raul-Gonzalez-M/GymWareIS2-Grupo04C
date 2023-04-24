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
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, DNI);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String tipoUsuario = resultSet.getString("TipoUsuario");
                if (tipoUsuario.equals("cliente")) {
                    String clienteQuery = "SELECT * FROM cliente WHERE DNI = ?";
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
     * Comprueba el login, al que se acede con el DNI y la contrasenya
     * (Probada)
     */
    public boolean accesoCorrecto(String DNI, String contrasenya) throws SQLException {
    	String query =    "SELECT contrasenya FROM CLIENTE WHERE DNI = '" + DNI
    					+ "' UNION"
    					+ " SELECT contrasenya FROM PERSONAL WHERE DNI = '" + DNI + "';";
    	
    	ResultSet rs = executeQueryAux(query);
    	if(rs.next() && rs.getString("Contrasenya").equals(contrasenya))
    		return true;
    	return false;
    }
    
    /*
     * Devuelve los datos de un cliente. Si no existe el cliente devuelve null (deberia existir y comprobarse fuera de la clase
     * () 
     */
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
    
    
    /*
     * Devuelve la lista de materiales que tienen un numero de unidades mayor o igual que el entero pasado como parametro
     * ()
     */
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

    /*
     * Devuelve un cliente por su DNI
     * ()
     */
	public Cliente obtenerClientePorId(String DNI) throws SQLException {
		String query = "SELECT * "
					 + "FROM Cliente "
					 + "WHERE DNI = '" + DNI + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		if(rs.next())
			return new Cliente(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")
				         , rs.getString("FechaAlta"), rs.getString("fechaBaja"), rs.getDouble("Saldo"));
		return null;
	}
	
	/*
	 * Devuelve la lista con todos los clientes
	 * ()
	 */
	public List<Cliente> obtenerClientes() throws SQLException {
		List<Cliente> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Cliente;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Cliente(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")
				         , rs.getString("FechaAlta"), rs.getString("fechaBaja"), rs.getDouble("Saldo")));
		}
		
		return ret;
	}

	
	/*
	 * Devuelve un aula segun su id
	 * ()
	 */
	public Aula obtenerAulaPorId(int id) throws SQLException {
		String query = "SELECT * "
				+ "FROM Aula "
				+ "WHERE Numero = " + id + ";";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next();
		
		String aux = rs.getString("Actividad");
		Actividad act = obtenerActividadPorNombre(aux);
		
		return new Aula(rs.getInt("Numero"), act);
	}

	/*
	 * Devuelve la lista de todas las aulas
	 * ()
	 */
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

	/*
	 * Devuelve una actividad por su nombre
	 * ()
	 */
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
	            String nombre = resultSet.getString("Nombre");
	            String horario = resultSet.getString("Horario");
	            String dniProfesor = resultSet.getString("DNI_Profesor");
	            int idAula = resultSet.getInt("Id_Aula");

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

	            Aula aula = new Aula.Builder()
	                    .withId(idAula)
	                    .withCapacidad(capacidad)
	                    .build();

	            ArrayList<Cliente> participantes = new ArrayList<>();

	            Actividad actividad = new Actividad.ActividadBuilder(nombre, horario, dniProfesor, aula)
	                    .setParticipantes(participantes)
	                    .build();

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
	public Encuesta obtenerEncuestaPorTitulo(String DNI, String fecha) throws SQLException {
		String query = "SELECT * "
					 + "FROM Encuesta "
					 + "WHERE ((DNI = '" + DNI + "') AND (Fecha = '" + fecha + "'));";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next(); 
	
		return new Encuesta(rs.getString("DNI"), rs.getString("Fecha"), rs.getInt("Satisfaccion")
				, rs.getString("Cambios"), rs.getString("Participa"));
	}

	/*
	 * Devuelve la lista de todas las encuestas
	 * ()
	 */
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

	/*
	 * Devuelve un material segun su nombre
	 * ()
	 */
	public Material obtenerMaterialPorNombre(String nombre) throws SQLException {
		String query = "SELECT * "
					 + "FROM Material "
					 + "WHERE Nombre = '" + nombre + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		rs.next();
		
		return new Material(rs.getString("Nombre"), rs.getDouble("Precio"), rs.getInt("Unidades"));
	}

	/*
	 * Devuelve la lista de todos los materiales
	 * ()
	 */
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

	/*
	 * Devuelve un miembro del personal segun su dni
	 * ()
	 */
	public Personal obtenerPersonalPorDNI(String DNI) throws SQLException {
		String query = "SELECT * "
					 + "FROM Personal "
					 + "WHERE DNI = '" + DNI + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		if(rs.next())
			return new Personal(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya"));
		return null;
	}

	/*
	 * Devuelve la lista de todos los miembros del personal
	 * ()
	 */
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
	
	/*
	 * Obtiene el usuario por DNI, independientemente de si es cliente o usuario
	 * ()
	 */
	public Usuario obtenerUsuarioPorDNI(String DNI) throws SQLException {
		Usuario ret = obtenerClientePorId(DNI);
		if(ret != null) {
			return ret;
		}
		return obtenerPersonalPorDNI(DNI);
	}

	/*
	 * Se obtiene una venta concreta segun el dni del cliente, el nombre del material y la fecha cuando se realizo
	 * ()
	 */
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

	/*
	 * Obtiene todas las ventas de un determinado cliente
	 * ()
	 */
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
	
	/*
	 * Devuelve todas las ventas de todos los clientes
	 * ()
	 */
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

	@Override
	public List<Actividad> obtenerActividades() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
}
