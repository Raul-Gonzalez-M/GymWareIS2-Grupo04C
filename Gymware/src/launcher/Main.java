package launcher;
import java.sql.SQLException;
import BD.CambiosBD;
import BD.ConexionBD;
import BD.ConsultasBD;
import tipos.DatosCliente;
import view.MainWindow;
import controller.GymController;
import controller.VistaController;

public class Main {
    public static void main(String[] args) throws SQLException{
    	
    	ConexionBD conexionBD = new ConexionBD();
    	CambiosBD bd_cambios = new CambiosBD(conexionBD);
    	ConsultasBD bd_consulta = new ConsultasBD(conexionBD);
    	
    	VistaController vistacontroller = new VistaController(conexionBD);
    	GymController gymcontroller = new GymController(bd_cambios, bd_consulta);
    	MainWindow mainWindow = new MainWindow(vistacontroller, gymcontroller);
    	mainWindow.setVisible(true);
    }
}
