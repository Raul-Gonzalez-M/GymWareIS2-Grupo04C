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
    
    public Usuario verificarCredenciales(String dni, String contrasena) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Usuario usuario = null;
        
        try {
            connection = conexionBD.getConnection();
            String query = "SELECT * FROM usuarios WHERE DNI = ? AND Contraseña = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, dni);
            preparedStatement.setString(2, contrasena);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String tipoUsuario = resultSet.getString("TipoUsuario");
                if (tipoUsuario.equals("cliente")) {
                    String clienteQuery = "SELECT * FROM cliente WHERE DNI = ?";
                    PreparedStatement clienteStatement = connection.prepareStatement(clienteQuery);
                    clienteStatement.setString(1, dni);
                    ResultSet clienteResult = clienteStatement.executeQuery();
                    if (clienteResult.next()) {
                        String nombre = clienteResult.getString("Nombre");
                        String fechaAlta = clienteResult.getString("FechaAlta");
                        double saldo = clienteResult.getDouble("Saldo");
                        usuario = new Cliente(dni, nombre, contrasena, fechaAlta, saldo);
                    }
                } else if (tipoUsuario.equals("personal")) {
                    String nombre = resultSet.getString("Nombre");
                    usuario = new Personal(dni, nombre, contrasena);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return usuario;
    }




}

