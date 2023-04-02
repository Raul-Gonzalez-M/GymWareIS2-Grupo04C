package launcher;
import java.sql.SQLException;
import BD.CambiosBD;
import BD.ConexionBD;
import BD.ConsultasBD;
import tipos.DatosCliente;
import view.MainWindow;
import controller.Controller;
import view.GimnasioApp;

public class Main {
    public static void main(String[] args) throws SQLException{
        //ConexionBD bd = new ConexionBD();
        //CambiosBD cambios = new CambiosBD(bd);
        //ConsultasBD consultas = new ConsultasBD(bd);
        //DatosCliente rs = consultas.datosCliente("11111111A");
        //System.out.println(rs.DNI() + " " + rs.nombre() + " " + rs.FechaAlta() + " " + rs.FechaBaja() + " " + rs.saldo());
        Controller controller = new Controller();
    }
}
