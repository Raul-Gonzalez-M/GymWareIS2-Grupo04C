package model;

import java.util.List;

public class Personal extends Usuario {
	
    public Personal(String DNI,String nombre, String contrasena) {
        super(DNI, nombre, contrasena);
    }

	@Override
	public void setActividades(List<Actividad> listaActividades) {
	}
    
}