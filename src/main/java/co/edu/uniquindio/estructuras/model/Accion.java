package co.edu.uniquindio.estructuras.model;

/**
 * Representa una acción registrada en el historial.
 * Usada en el Punto 2 junto con la Pila propia.
 */
public class Accion {

    private String descripcion;
    private long timestamp;

    public Accion(String descripcion) {
        this.descripcion = descripcion;
        this.timestamp = System.currentTimeMillis();
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Accion{descripcion='" + descripcion + "', timestamp=" + timestamp + "}";
    }
}
