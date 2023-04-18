package launcher;
import java.sql.SQLException;
import BD.DAOCambios;
import BD.ConexionBD;
import BD.DAOConsultas;
import tipos.DatosCliente;
import view.MainWindow;
import controller.GymController;
import controller.Controller;

public class Main {
    public static void main(String[] args) throws SQLException{
    	
    	Controller vistacontroller = new Controller();
    	MainWindow mainWindow = new MainWindow(vistacontroller);
    	mainWindow.setVisible(true);
    }
}
