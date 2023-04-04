package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import BD.CambiosBD;
import BD.ConexionBD;
import BD.ConsultasBD;
import model.*;
import view.*;

public class GymController {
	
    private CambiosBD cambios;
    private ConsultasBD consulta;
    
    public GymController(CambiosBD bd_cambios,ConsultasBD bd_consulta) {
        this.cambios = bd_cambios;
        this.consulta = bd_consulta;
    }
    
    /*
     * AULAS
     */
    
    public void agregarAula(int id, int capacidadMaxima, String nombre) {
        Aula nuevaAula = new Aula(id, capacidadMaxima, nombre);
        cambios.insertarAula(nuevaAula); // se inserta la nueva aula en la BD
    }
    
    public void eliminarAula(Aula aula) {
    	cambios.eliminarAula(aula); // se elimina la aula de la BD
    }
    
    public List<Aula> obtenerAulas() {
        return consulta.obtenerAulas(); // se obtienen todas las aulas de la BD
    }
    
    public Aula obtenerAulaPorId(int id) {
        return consulta.obtenerAulaPorId(id); // se busca la aula en la BD según su ID
    }
    
    public void agregarClaseAAula(int idAula, Actividad actividad) {
        Aula aula = obtenerAulaPorId(idAula);
        if (aula != null) {
            aula.agregarClase(actividad);
            cambios.actualizarAula(aula); // se actualiza la información de la aula en la BD
        }
    }
    
    public void eliminarClaseDeAula(int idAula, Actividad actividad) {
        Aula aula = obtenerAulaPorId(idAula);
        if (aula != null) {
            aula.eliminarClase(actividad);
            cambios.actualizarAula(aula); // se actualiza la información de la aula en la BD
        }
    }
    

    

}
