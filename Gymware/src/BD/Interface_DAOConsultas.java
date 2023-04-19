package BD;

import java.sql.SQLException;
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

public interface Interface_DAOConsultas {
	
    public Usuario verificarCredenciales(String DNI, String password);
    public boolean existeDni(String dni);
    public boolean accesoCorrecto(String DNI, String contrasenya) throws SQLException;
    public DatosCliente datosCliente(String DNI) throws SQLException;
    public List<DatosMaterial> listaMateriales(int unidades) throws SQLException;
	public Cliente obtenerClientePorId(String DNI) throws SQLException;
	public List<Cliente> obtenerClientes() throws SQLException;
	public Aula obtenerAulaPorId(int id) throws SQLException;
	public List<Aula> obtenerAulas() throws SQLException;
	public Actividad obtenerActividadPorNombre(String nombre) throws SQLException;
	public List<Actividad> obtenerActividades() throws SQLException;
	public Encuesta obtenerEncuestaPorTitulo(String DNI, String fecha) throws SQLException;
	public List<Encuesta> obtenerEncuestas() throws SQLException;
	public Material obtenerMaterialPorNombre(String nombre) throws SQLException;
	public List<Material> obtenerMateriales() throws SQLException;
	public Personal obtenerPersonalPorDNI(String DNI) throws SQLException;
	public List<Personal> obtenerPersonal() throws SQLException;
	public Usuario obtenerUsuarioPorDNI(String DNI) throws SQLException;
	public Venta obtenerVenta(String nombre, String DNI, String fecha) throws SQLException;
	public List<Venta> obtenerVentasCliente(String DNI) throws SQLException;

	public List<Venta> obtenerVentas() throws SQLException;
}
