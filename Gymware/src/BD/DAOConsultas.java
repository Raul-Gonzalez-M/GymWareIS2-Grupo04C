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
    private Statement stmt;

    public DAOConsultas(ConexionBD bd) throws SQLException{
        this.bd = bd;
        this.stmt = bd.getConnection().createStatement();
    }

    public boolean DNIDisponible(String DNI) throws SQLException{
        if(DNI.length() != 9)
            return false;

        String query = "SELECT DNI FROM usuarios;";
        
        boolean ret = true;
	    ResultSet rs = executeQueryAux(query);
	    
	    while(rs.next() && ret){
	        if(rs.getString("DNI").equals(DNI))
	            ret = false;
	    }
        
        return ret;
    }
    
    private ResultSet executeQueryAux(String query) throws SQLException {
    	try{
    		return stmt.executeQuery(query);
    	} catch(SQLException e) {
    		e.printStackTrace();
    		return null;
    	}
    }
    
    public boolean verificarCredenciales(String DNI, String password) throws SQLException {
    	String query = "SELECT * "
    				 + "FROM Usuarios "
    				 + "WHERE DNI = '" + DNI + "' AND "
    				 + "Contrasenya = '" + password + "';";
    	
    	ResultSet rs = executeQueryAux(query);
    	
    	if(rs.next())
    		return true;
    	return false;
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
					 + "FROM Usuarios "
					 + "WHERE DNI = '" + DNI + "'"
					 		+ "AND TipoUsuario = 'Cliente';";
		
		ResultSet rs = executeQueryAux(query);
		
		if(rs.next())
			return new Cliente(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")
				         , rs.getString("FechaAlta"), rs.getDouble("Saldo"));
		return null;
	}

	public List<Cliente> obtenerClientes() throws SQLException {
		List<Cliente> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Usuarios "
					 + "WHERE TipoUsuario = 'Cliente'";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Cliente(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya")
				         , rs.getString("FechaAlta"),  rs.getDouble("Saldo")));
		}
		
		return ret;
	}

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
					 + "FROM Usuarios "
					 + "WHERE DNI = '" + DNI + "'"
					 + "AND TipoUsuario = 'Personal';";
		
		ResultSet rs = executeQueryAux(query);
		
		if(rs.next())
			return new Personal(rs.getString("DNI"), rs.getString("Nombre"), rs.getString("Contrasenya"));
		return null;
	}

	public List<Personal> obtenerPersonal() throws SQLException {
		List<Personal> ret = new ArrayList<Personal>();
		
		String query = "SELECT * "
					 + "FROM Usuarios"
					 + "WHERE TipoUsuario = 'Personal';";
		
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
		
		String query = "SELECT * "
				+ "FROM Participa"
				+ "WHERE Nombre_Actividad = '" + actividad.getNombre() + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(rs.getString("DNI_Cliente"));
		}
		
		
		return ret;
	}

	public List<Actividad> obtenerActividades() throws SQLException {
		List<Actividad> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Actividad;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Actividad(rs.getInt("id"), rs.getString("Nombre"), rs.getString("Horario"), rs.getString("DNI_Profesor")
					, rs.getInt("aula")));
		}
		
		return ret;
	}

	public List<Actividad> obtenerActividadPorNombre(String nombre) throws SQLException {
		List<Actividad> ret = new ArrayList<>();
		
		String query = "SELECT * "
				+ "FROM Actividad "
				+ "WHERE Nombre = '" + nombre + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Actividad(rs.getInt("id"), rs.getString("Nombre"), rs.getString("Horario"), rs.getString("DNI_Profesor")
					, rs.getInt("aula")));
		}
		
		return ret;
	}

	public List<Aula> obtenerAulas() throws SQLException {
		List<Aula> ret = new ArrayList<>();
		
		String query = "SELECT * "
					 + "FROM Aula;";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Aula(rs.getInt("Id"), rs.getString("Actividad")));
		}
		
		return ret;
	}

	public List<Actividad> obtenerActividadPorDNI(String dni) throws SQLException {
		List<Actividad> ret = new ArrayList<>();
		
		String query = "SELECT A.Id, A.Nombre, A.horario, A.Nombre_profesor, A.id_Aula "
					 + "FROM Actividad A JOIN participantes P ON A.Id = P.Id_Actividad "
					 + "WHERE P.DNICliente = '" + dni + "';";
		
		ResultSet rs = executeQueryAux(query);
		
		while(rs.next()) {
			ret.add(new Actividad(rs.getInt("id"), rs.getString("Nombre"), rs.getString("Horario"), rs.getString("DNI_Profesor")
					, rs.getInt("aula")));
		}
		
		return ret;
	}
}
