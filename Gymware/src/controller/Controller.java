package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BD.ConexionBD;
import model.*;
import view.*;

public class Controller {
	
    private ConexionBD conexionBD;
    
    public Controller() {
    	
    	//conexionBD = new ConexionBD();
    }


}
