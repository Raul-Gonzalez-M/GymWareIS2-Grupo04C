package model;

public class Encuesta {
    
    private String DNI;
    private String fecha;
    private int satisfaccion;
    private String cambios;
    private String participa;

    public Encuesta(String DNI, String fecha, int satisfaccion, String cambios, String participa) {
        this.DNI = DNI;
        this.fecha = fecha;
        this.satisfaccion = satisfaccion;
        this.cambios = cambios;
        this.participa = participa;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getSatisfaccion() {
        return satisfaccion;
    }

    public void setSatisfaccion(int satisfaccion) {
        this.satisfaccion = satisfaccion;
    }

    public String getCambios() {
        return cambios;
    }

    public void setCambios(String cambios) {
        this.cambios = cambios;
    }

    public String getParticipa() {
        return participa;
    }

    public void setParticipa(String participa) {
        this.participa = participa;
    }

    public String getPregunta() {

        return null;
    }

    public String getRespuesta() {

        return null;
    }
}
