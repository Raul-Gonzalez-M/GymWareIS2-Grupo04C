package src.launcher;

import java.sql.ResultSet;
import java.sql.SQLException;

import src.BD.CambiosBD;
import src.BD.ConexionBD;
import src.BD.ConsultasBD;
import src.tipos.DatosCliente;

public class Main {
    public static void main(String[] args) throws SQLException{
        ConexionBD bd = new ConexionBD();
        CambiosBD cambios = new CambiosBD(bd);
        ConsultasBD consultas = new ConsultasBD(bd);
        
        DatosCliente rs = consultas.datosCliente("11111111A");
        
        
        System.out.println(rs.DNI() + " " + rs.nombre() + " " + rs.FechaAlta()
        					+ " " + rs.FechaBaja() + " " + rs.saldo());
        
    }
    aaaa
}
