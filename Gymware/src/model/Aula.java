package model;

public class Aula {
    private int id;
    private int capacidad;

<<<<<<< HEAD
    private Aula(int id, Actividad actividad) {}
=======
    public static class Builder {
        private int id;
        private int capacidad;

        public Builder withId(int id) {
            this.id = id;
            return this;
        }

        public Builder withCapacidad(int capacidad) {
            this.capacidad = capacidad;
            return this;
        }

        public Aula build() {
            Aula aula = new Aula();
            aula.setId(this.id);
            aula.setCapacidad(this.capacidad);
            return aula;
        }
    }

    private Aula() {}
>>>>>>> 82da2e9af95ee7c4133a6a3b7cf310541d52cbf7

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
}
