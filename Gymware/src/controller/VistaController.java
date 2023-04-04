package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.ConexionBD;
import view.MainWindow;

public class VistaController {

    private ConexionBD conexionBD;
    private MainWindow mainWindow;
    
    public VistaController(){
    	mainWindow = new MainWindow(this);
    	start();
    }
    
    public void start() {
    	mainWindow.setVisible(true);
    }
    
    public boolean verificarCredenciales(String usuario, String contrasena) {
        Connection connection = conexionBD.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean resultado = false;

        try {
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND contrasena = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contrasena);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                resultado = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return resultado;
    }

}

