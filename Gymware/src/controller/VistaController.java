package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.ConexionBD;
import model.Cliente;
import model.Personal;
import model.Usuario;
import view.MainWindow;

public class VistaController {

    private ConexionBD conexionBD;
    
    public VistaController(ConexionBD conexionBD){
    	this.conexionBD = conexionBD;
    }
    
    public boolean verificarCredenciales(String usuario, String contrasena) {
        Connection connection = conexionBD.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        boolean resultado = false;

        try {
            String query = "SELECT * FROM usuarios WHERE DNI = ? AND contraseña = ?";
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
    
    public Usuario autenticarUsuario(String usuario, String contrasena) {
        Connection connection = conexionBD.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario resultado = null;

        try {
            String query = "SELECT * FROM usuarios WHERE DNI = ? AND contraseña = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, usuario);
            preparedStatement.setString(2, contrasena);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String DNI = resultSet.getString("DNI");
                String nombre = resultSet.getString("nombre");
                String fechaAlta = resultSet.getString("fechaAlta");
                String fechaBaja = resultSet.getString("fechaBaja");
                double saldo = resultSet.getDouble("saldo");
                String contrasenya = resultSet.getString("contraseña");
                if (fechaAlta != null && fechaBaja != null && saldo > 0) {
                    resultado = new Cliente(DNI, nombre, contrasenya, fechaAlta, fechaBaja, saldo);
                } else {
                    resultado = new Personal(DNI, nombre, contrasenya);
                }
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

