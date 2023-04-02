package BD;

import java.sql.*;

public class ConexionBD {
	static String DRIVER = "com.mysql.cj.jdbc.Driver"; /*com.mysql.jdbc.Driver */
    static String BD = "bd";
    static String login = "admin";
    static String password = "1234";
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
			e.printStackTrace();
		}
    }

    public Connection getConnection(){
        return connection;
    }

    public void desconectar(){
        connection = null;
    }
}
