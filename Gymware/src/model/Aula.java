package model;

public class Aula {
    private int id;
    private int capacidad;

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
