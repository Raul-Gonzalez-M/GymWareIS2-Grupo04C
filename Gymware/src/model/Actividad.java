package model;

import java.util.ArrayList;
import java.util.List;

public class Actividad {
	private final int MAX_PLAZAS = 25;
	private int id_actividad;
    private String nombre;
    private String horario;
    private String nombre_profesor;
    private Aula aula;
	private List<Cliente> participantes;

    private Actividad(String nombre, String horario, String DNIProfesor, Aula aula) {
        this.nombre = nombre;
        this.horario = horario;
        this.DNIProfesor = DNIProfesor;
        this.aula = aula;
		this.participantes = new ArrayList<>();
=======
    private Actividad(ActividadBuilder builder) {
    	this.id_actividad = builder.id_actividad;
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
    
    public int getId() {
    	return id_actividad;
>>>>>>> 82da2e9af95ee7c4133a6a3b7cf310541d52cbf7
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
	
	public int plazasDisponibles() {
		return (MAX_PLAZAS - participantes.size());
	}

	public boolean aniadirParticipante(Cliente user){ //Devuelve true si se le ha podido añadir y false en caso contrario
		if(participantes.size() < MAX_PLAZAS){
			participantes.add(user);
			return true;
		}
		return false;
	}

	public List<Cliente> getParticipantes(){
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
		private int id_actividad;
	    private String nombre;
	    private String horario;
	    private String nombre_profesor;
	    private Aula aula;
	    private int plazasDisponibles;
	    private ArrayList<Cliente> participantes;

	    public ActividadBuilder(int id_actividad, String nombre, String horario, String nombre_profesor, Aula aula , int plazasDisponibles) {
	    	this.id_actividad = id_actividad;
	        this.nombre = nombre;
	        this.horario = horario;
	        this.nombre_profesor = nombre_profesor;
	        this.aula = aula;
	        this.plazasDisponibles = plazasDisponibles;
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
>>>>>>> 82da2e9af95ee7c4133a6a3b7cf310541d52cbf7
}

