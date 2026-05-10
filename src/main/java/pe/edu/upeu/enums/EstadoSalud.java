package pe.edu.upeu.enums;

public enum EstadoSalud {
    BUENO("Bueno"),
    REGULAR("Regular"),
    DELICADO("Delicado"),
    CRITICO("Crítico");

    private final String descripcion;

    EstadoSalud(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}