package model;

public class Actividad {
    private String nombre;
    private String horario;
    private String DNIProfesor;
    private Aula aula;
    private int plazasDisponibles;

    public Actividad(String nombre, String horario, String DNIProfesor, Aula aula) {
        this.nombre = nombre;
        this.horario = horario;
        this.DNIProfesor = DNIProfesor;
        this.aula = aula;
        this.plazasDisponibles = 30;
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
    
    public void decPlazasDisponibles(int n) {
    	incPlazasDisponibles(-n);
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

	public String getDNIProfesor() {
		return DNIProfesor;
	}

	public void setDNIProfesor(String dNIProfesor) {
		DNIProfesor = dNIProfesor;
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}
}
