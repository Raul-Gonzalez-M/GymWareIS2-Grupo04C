package model;
import java.util.List;
import java.util.ArrayList;

public class Aula {
    
    private int capacidadMaxima;
    private int capacidadActual;
    private String nombre;
    private List<Actividad> clases;
    
    public Aula(int capacidadMaxima, String nombre) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = 0;
        this.nombre = nombre;
        this.clases = new ArrayList<>();
    }
    
    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }
    
    public int getCapacidadActual() {
        return capacidadActual;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public List<Actividad> getClases() {
        return clases;
    }
    
    public void agregarClase(Actividad clase) {
        if (capacidadActual < capacidadMaxima) {
            clases.add(clase);
            capacidadActual++;
        } else {
            throw new IllegalStateException("El aula está llena");
        }
    }
    
    public void eliminarClase(Actividad clase) {
        if (clases.remove(clase)) {
            capacidadActual--;
        }
    }
}
