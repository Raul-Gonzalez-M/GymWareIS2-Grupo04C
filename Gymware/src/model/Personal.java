package model;

public class Personal extends Usuario {
    private int idPersonal;
    private String puesto;
    
    public Personal(String nombre, String apellido, int edad, String correoElectronico, String contrasena, int idPersonal, String puesto) {
        super(nombre, apellido, edad, correoElectronico, contrasena);
        this.idPersonal = idPersonal;
        this.puesto = puesto;
    }
    
    
    public int getIdPersonal() {
        return idPersonal;
    }
    
    public void setIdPersonal(int idPersonal) {
        this.idPersonal = idPersonal;
    }
    
    public String getPuesto() {
        return puesto;
    }
    
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}