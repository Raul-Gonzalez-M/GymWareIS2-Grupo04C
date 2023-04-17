package model;

import java.util.ArrayList;
import java.util.Date;

public class Gimnasio {
    private ArrayList<Usuario> usuarios;
    private ArrayList<Actividad> actividades;
    private ArrayList<Aula> aulas;
    private ArrayList<Encuesta> encuestas;
    private ArrayList<Material> materiales;
    private ArrayList<Venta> ventas;

    public Gimnasio() {
        this.usuarios = new ArrayList<>();
        this.actividades = new ArrayList<>();
        this.aulas = new ArrayList<>();
        this.encuestas = new ArrayList<>();
        this.materiales = new ArrayList<>();
        this.ventas = new ArrayList<>();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public ArrayList<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(ArrayList<Aula> aulas) {
        this.aulas = aulas;
    }

    public ArrayList<Encuesta> getEncuestas() {
        return encuestas;
    }

    public void setEncuestas(ArrayList<Encuesta> encuestas) {
        this.encuestas = encuestas;
    }

    public ArrayList<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(ArrayList<Material> materiales) {
        this.materiales = materiales;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }

    public void addUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    public void addActividad(Actividad actividad) {
        this.actividades.add(actividad);
    }

    public void addAula(Aula aula) {
        this.aulas.add(aula);
    }

    public void addEncuesta(Encuesta encuesta) {
        this.encuestas.add(encuesta);
    }

    public void addMaterial(Material material) {
        this.materiales.add(material);
    }

    public void addVenta(Venta venta) {
        this.ventas.add(venta);
    }

    public void removeUsuario(Usuario usuario) {
        this.usuarios.remove(usuario);
    }

    public void removeActividad(Actividad actividad) {
        this.actividades.remove(actividad);
    }

    public void removeAula(Aula aula) {
        this.aulas.remove(aula);
    }

    public void removeEncuesta(Encuesta encuesta) {
        this.encuestas.remove(encuesta);
    }

    public void removeMaterial(Material material) {
        this.materiales.remove(material);
    }

    public void removeVenta(Venta venta) {
        this.ventas.remove(venta);
    }

    public Usuario buscarUsuario(String DNI) {
        for (Usuario usuario : usuarios) {
            if (usuario.getDNI().equals(DNI)) {
                return usuario;
            }
        }
        return null;
    }

    public Actividad buscarActividad(String nombre) {
        for (Actividad actividad : actividades) {
            if (actividad.getNombre().equals(nombre)) {
                return actividad;
            }
        }
        return null;
    }

    public Aula buscarAula(int id) {
        for (Aula aula : aulas) {
            if (aula.getId() == id) {
                return aula;
            }
        }
        return null;
    }

	public void inscribirActividad(Cliente cliente, Actividad actividad) {
		if(!actividad.aniadirParticipante(cliente)){
            System.out.print("No has podido inscribirte en la actividad");  //Modificar para escribirlo en la interfaz gráfica
        }
	}

    public void borrarseActividad(Cliente cliente, Actividad actividad){
        actividad.borrarParticipante(cliente);
    }
}
