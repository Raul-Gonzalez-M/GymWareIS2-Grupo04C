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
	public Actividad[] obtenerListaActividades() throws SQLException;

}
