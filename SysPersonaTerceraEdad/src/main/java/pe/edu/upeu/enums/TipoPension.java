package pe.edu.upeu.enums;

public enum TipoPension {
    IMSS("IMSS"),
    ISSSTE("ISSSTE"),
    BIENESTAR("Bienestar"),
    PARTICULAR("Particular"),
    NINGUNA("Ninguna");

    private final String descripcion;

    TipoPension(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}