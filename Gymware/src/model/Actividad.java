package model;

import java.util.ArrayList;

public class Actividad {
<<<<<<< HEAD
	private int id_actividad;
=======
	private final int MAX_PLAZAS = 25;
>>>>>>> 92f038a (aa)
    private String nombre;
    private String horario;
    private String DNIProfesor;
    private Aula aula;
<<<<<<< HEAD
    private int plazasDisponibles;
	private ArrayList<Cliente> participantes;

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
=======
	private List<String> participantes;

    private Actividad(String nombre, String horario, String DNIProfesor, Aula aula) {
        this.nombre = nombre;
        this.horario = horario;
        this.DNIProfesor = DNIProfesor;
        this.aula = aula;
		this.participantes = new ArrayList<>();
>>>>>>> 92f038a (aa)
    }
    
    public String getDNIProfesor() {
    	return this.DNIProfesor;
    }
    
<<<<<<< HEAD
    public void incPlazasDisponibles(int n) {
    	this.plazasDisponibles += n;
    }
    
    public int getId() {
    	return id_actividad;
=======
    public void setDNIProfesor(String dni) {
    	this.DNIProfesor = dni;
>>>>>>> 92f038a (aa)
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

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

<<<<<<< HEAD
	public boolean aniadirParticipante(Cliente user){ //Devuelve true si se le ha podido añadir y false en caso contrario
		if(participantes.size() < plazasDisponibles){
=======
	public boolean aniadirParticipante(String user){ //Devuelve true si se le ha podido añadir y false en caso contrario
		if(participantes.size() < MAX_PLAZAS){
>>>>>>> 92f038a (aa)
			participantes.add(user);
			return true;
		}
		return false;
	}

<<<<<<< HEAD
	public ArrayList<Cliente> getParticipantes(){
=======
	public List<String> getParticipantes(){
>>>>>>> 92f038a (aa)
		return participantes;
	}

	public boolean borrarParticipante(Cliente cliente){
		for(String c : participantes){
            if(c.equals(cliente.getDNI())){
                participantes.remove(cliente.getDNI());
                return true;
            }
        }
		return false;
	}
<<<<<<< HEAD
	
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
=======
>>>>>>> 92f038a (aa)
}

