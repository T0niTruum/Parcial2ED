package co.edu.uniquindio.estructuras.model;

/**
 * Representa un equipo de fútbol (u otro deporte).
 * Usada en el Punto 4 con el Árbol Binario de Búsqueda.
 */
public class Equipo implements Comparable<Equipo> {

    private String nombre;
    private String ciudad;
    private int titulos;

    public Equipo(String nombre, String ciudad, int titulos) {
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.titulos = titulos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getTitulos() {
        return titulos;
    }

    public void setTitulos(int titulos) {
        this.titulos = titulos;
    }

    /**
     * Comparación por nombre del equipo (clave del ABB).
     */
    @Override
    public int compareTo(Equipo otro) {
        return this.nombre.compareToIgnoreCase(otro.nombre);
    }

    @Override
    public String toString() {
        return "Equipo{nombre='" + nombre + "', ciudad='" + ciudad + "', titulos=" + titulos + "}";
    }
}
