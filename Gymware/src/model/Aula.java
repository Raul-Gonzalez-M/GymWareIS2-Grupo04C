package model;

public class Aula {
    private int id;
    private Actividad actividad;

    public static class Builder {
        private int id;
        private Actividad actividad;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withActividad(Actividad actividad) {
            this.actividad = actividad;
            return this;
        }

        public Aula build() {
            Aula aula = new Aula();
            aula.setId(this.id);
            aula.setActividad(this.actividad);
            return aula;
        }
    }

    private Aula() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
}
