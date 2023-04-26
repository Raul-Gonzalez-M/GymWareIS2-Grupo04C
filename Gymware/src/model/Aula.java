package model;

public class Aula {
    private int id;
    private String tipoActividad;
    
    public Aula(int id, String tipoActividad) {
    	this.id = id;
    	this.tipoActividad = tipoActividad;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoActividad() {
    	return this.tipoActividad;
    }
    
    public void setActividad(String tipoActividad) {
    	this.tipoActividad = tipoActividad;
    }
}
