package BD;

import java.sql.*;

import javax.swing.JOptionPane;

public class ConexionBD {
	static String DRIVER = "com.mysql.cj.jdbc.Driver"; /*com.mysql.jdbc.Driver */
    static String BD = "gymware";
    static String login = "root";
    static String password = "Gymware_2023!";
    static String url = "jdbc:mysql://localhost:3306/"+BD;

    Connection connection = null;

    public ConexionBD() {
        try{
        	Class.forName(DRIVER);
            connection = DriverManager.getConnection(url, login, password);

        } catch(SQLException ex) {
            connection = null;
            ex.printStackTrace();
            System.out.println("SQLException : " + ex.getMessage());
            System.out.println("SQLState : " + ex.getSQLState());
            System.out.println("VendorError : " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
    }

    public Connection getConnection(){
        return connection;
    }

    public void desconectar(){
        connection = null;
    }
}
