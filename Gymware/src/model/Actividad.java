package model;

import java.util.ArrayList;

public class Actividad {
    private String nombre;
    private String horario;
    private String nombre_profesor;
    private Aula aula;
    private int plazasDisponibles;
	private ArrayList<Cliente> participantes;

    private Actividad(ActividadBuilder builder) {
        this.nombre = builder.nombre;
        this.horario = builder.horario;
        this.nombre_profesor = builder.nombre_profesor;
        this.aula = builder.aula;
        this.plazasDisponibles = builder.plazasDisponibles;
		this.participantes = builder.participantes;
    }

    public int getPlazasDisponibles() {
    	return this.plazasDisponibles;
    }
    
    public void setPlazasDisponibles(int n) {
    	this.plazasDisponibles = n;
    }
    
    public void incPlazasDisponibles(int n) {
    	this.plazasDisponibles += n;
    }
    
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getNombre_profesor() {
		return nombre_profesor;
	}

	public void setNombre_profesor(String dNIProfesor) {
		nombre_profesor = dNIProfesor;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public boolean aniadirParticipante(Cliente user){ //Devuelve true si se le ha podido añadir y false en caso contrario
		if(participantes.size() < plazasDisponibles){
			participantes.add(user);
			return true;
		}
		return false;
	}

	public ArrayList<Cliente> getParticipantes(){
		return participantes;
	}

	public boolean borrarParticipante(Cliente cliente){
		for(Cliente c : participantes){
            if(c.equals(cliente)){
                participantes.remove(cliente);
                return true;
            }
        }
		return false;
	}
	
	// Clase Builder
	public static class ActividadBuilder {
	    private String nombre;
	    private String horario;
	    private String nombre_profesor;
	    private Aula aula;
	    private int plazasDisponibles;
	    private ArrayList<Cliente> participantes;

	    public ActividadBuilder(String nombre, String horario, String nombre_profesor, Aula aula) {
	        this.nombre = nombre;
	        this.horario = horario;
	        this.nombre_profesor = nombre_profesor;
	        this.aula = aula;
	        this.plazasDisponibles = aula.getCapacidad();
			this.participantes = new ArrayList<Cliente>();
	    }

	    public ActividadBuilder setParticipantes(ArrayList<Cliente> participantes) {
	        this.participantes = participantes;
	        return this;
	    }
	    public Actividad build() {
	        return new Actividad(this);
	    }
	}
}

