package model;

public class Aula {
    private int id;
    private Actividad actividad;
    
    public Aula(int id, Actividad actividad) {
    	this.id = id;
    	this.actividad = actividad;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actividad getActividad() {
    	return this.actividad;
    }
    
    public void setActividad(Actividad actividad) {
    	this.actividad = actividad;
    }
}
