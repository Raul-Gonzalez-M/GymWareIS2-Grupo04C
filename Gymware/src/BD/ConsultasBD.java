package BD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controller.Respuesta;
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

public class ConsultasBD {
    private ConexionBD bd;

    public ConsultasBD(ConexionBD bd){
        this.bd = bd;
    }

    /*
     * Comprueba si el DNI es correcto, es decir, no existe ningun usuario con el mismo y es un DNI correcto (9 caracteres) 
     * (Probada)
     */
    public boolean DNIDisponible(String DNI){
        if(DNI.length() != 9)
            return false;

        String query =    "SELECT DNI FROM CLIENTE"
        				+ " UNION"
        				+ "	SELECT DNI FROM PERSONAL;";
        boolean ret = true;
        try(Statement stmt = bd.getConnection().createStatement()){
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next() && ret){
                if(rs.getString("DNI").equals(DNI))
                    ret = false;
            }
        } catch (SQLException e) {
        	System.out.println(e.getMessage());
			e.printStackTrace();
		}
        return ret;
    }
    
    /*
     * Comprueba el login, al que se acede con el DNI y la contrasenya
     * (Probada)
     */
    public boolean accesoCorrecto(String DNI, String contrasenya) throws SQLException {
    	String query =    "SELECT contrasenya FROM CLIENTE WHERE DNI = '" + DNI
    					+ "' UNION"
    					+ " SELECT contrasenya FROM PERSONAL WHERE DNI = '" + DNI + "';";
    	
    	try(Statement stmt = bd.getConnection().createStatement()){
    		ResultSet rs = stmt.executeQuery(query);
    		if(rs.next() && rs.getString("Contrasenya").equals(contrasenya))
    			return true;
    		return false;
    	}
    }
    
    public DatosCliente datosCliente(String DNI) throws SQLException {
    	String query =    "select DNI, Nombre, FechaAlta, FechaBaja, Saldo "
    					+ "from cliente "
    					+ "where dni = '" + DNI + "';";
    	
    	try(Statement stmt = bd.getConnection().createStatement()){
    		ResultSet rs =  stmt.executeQuery(query);
    		rs.next();
    		return new DatosCliente(rs.getString("DNI"), rs.getString("nombre"), rs.getString("FechaAlta"),
    					       			rs.getString("FechaBaja"), rs.getDouble("saldo"));
    	}
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
    	
    	try(Statement stmt = bd.getConnection().createStatement()){
    		ResultSet rs = stmt.executeQuery(query);
    		
    		while(rs.next()) {
    			DatosMaterial dm = new DatosMaterial(rs.getString("nombre"), rs.getDouble("precio"), rs.getInt("unidades"));
    			ret.add(dm);
    		}
    	}
    	
    	return ret;
    }

	public Aula obtenerAulaPorId(int id) {
		
		return null;
	}

	public List<Aula> obtenerAulas() {
		
		return null;
	}

	public List<Actividad> obtenerActividades() {
		
		return null;
	}

	public Actividad obtenerActividadPorNombre(String nombre) {
		
		return null;
	}

	public List<Cliente> obtenerClientes() {
		
		return null;
	}

	public Cliente obtenerClientePorId(int idCliente) {
		
		return null;
	}

	public List<Encuesta> obtenerEncuestas() {
		
		return null;
	}

	public Encuesta obtenerEncuestaPorTitulo(String titulo) {
		
		return null;
	}

	public List<Respuesta> obtenerRespuestasDeEncuestaPorCliente(Encuesta encuesta, Cliente cliente) {
		return null;
	}

	public List<Material> obtenerMateriales() {
		
		return null;
	}

	public Material obtenerMaterialPorNombre(String nombre) {
		
		return null;
	}

	public List<Personal> obtenerPersonal() {
		
		return null;
	}

	public Personal obtenerPersonalPorDNI(String dNI) {
		
		return null;
	}

	public Usuario obtenerUsuarioPorDNI(String dNI) {
		return null;
	}

	public List<Venta> obtenerVentas() {
		
		return null;
	}

	public Venta obtenerVentaPorId(int id) {
		
		return null;
	}
}
