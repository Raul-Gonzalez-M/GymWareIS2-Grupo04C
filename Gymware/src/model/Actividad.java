package model;

import java.util.ArrayList;
import java.util.List;

public class Actividad {
	private int id;
	private final int MAX_PLAZAS = 30;
    private String nombre;
    private String horario;
    private String Nombre_profesor;
    private Aula aula;
	private List<String> participantes;

    public Actividad(int id, String nombre, String horario, String Nombre_profesor, Aula aula, int plazas) {
    	this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.Nombre_profesor = Nombre_profesor;
        this.aula = aula;
		this.participantes = new ArrayList<>();
    }
    
    public int getId() {
    	return this.id;
    }
    
    public String getNombre_profesor() {
    	return this.Nombre_profesor;
    }
    
	public int getPlazasDisponibles(){
		return MAX_PLAZAS - participantes.size();
	}

    public void setNombre_profesor(String dni) {
    	this.Nombre_profesor = dni;
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
            if(c.equals(cliente.getDNI())){
                participantes.remove(cliente.getDNI());
                return true;
            }
        }
		return false;
	}
}

