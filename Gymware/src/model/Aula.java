package model;

public class Aula {
    private int id;
    private String Actividad;
    
    public Aula(int id, String tipoActividad) {
    	this.id = id;
    	this.Actividad = tipoActividad;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActividad() {
    	return this.Actividad;
    }
    
    public void setActividad(String tipoActividad) {
    	this.Actividad = tipoActividad;
    }
}
