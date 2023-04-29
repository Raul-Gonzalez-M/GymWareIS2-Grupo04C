package model;

import java.util.List;

public class Actividad {
	private int id;
	private final int MAX_PLAZAS = 30;
    private String nombre;
    private String horario;
    private String DNI_profesor;
    private int num_aula;
	private List<String> participantes;

    public Actividad(int id, String nombre, String horario, String DNI_profesor, int num_aula, List<String> participantes) {
    	this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.DNI_profesor = DNI_profesor;
        this.num_aula = num_aula;
		this.participantes = participantes;
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getDNIProfesor() {
    	return this.DNI_profesor;
    }
    
	public int getPlazasDisponibles(){
		return MAX_PLAZAS - participantes.size();
	}

    public void setDNIProfesor(String dni) {
    	this.DNI_profesor = dni;
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

	public int getNumAula() {
		return num_aula;
	}

	public void setNumAula(int aula) {
		this.num_aula = aula;
	}
	
	public void setParticipantes(List<String> participantes) {
	    this.participantes = participantes;
	}

	public int plazasDisponibles() {
		return (MAX_PLAZAS - participantes.size());
	}

	public boolean aniadirParticipante(String user){ //Devuelve true si se le ha podido añadir y false en caso contrario
		if(participantes.size() < MAX_PLAZAS && !participantes.contains(user)){
			participantes.add(user);
			return true;
		}
		return false;
	}

	public List<String> getParticipantes(){
		return participantes;
	}

	public boolean borrarParticipante(Cliente cliente){
		for(String c : participantes){
            if(c.equals(cliente.getNombre())){
                participantes.remove(cliente.getNombre());
                return true;
            }
        }
		return false;
	}
}

