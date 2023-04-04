package model;

public class Encuesta {
    
    private int idEncuesta;
    private int idUsuario;
    private String respuesta;

    public Encuesta(int idEncuesta, int idUsuario, String respuesta) {
        this.idEncuesta = idEncuesta;
        this.idUsuario = idUsuario;
        this.respuesta = respuesta;
    }

    public int getIdEncuesta() {
        return idEncuesta;
    }

    public void setIdEncuesta(int idEncuesta) {
        this.idEncuesta = idEncuesta;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String toString() {
        return "Encuesta [idEncuesta=" + idEncuesta + ", idUsuario=" + idUsuario + ", respuesta=" + respuesta + "]";
    }
    
}
