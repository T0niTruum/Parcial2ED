package co.edu.uniquindio.estructuras.estructuras;

/**
 * Implementación propia de una Pila (Stack) genérica usando nodos enlazados.
 * No utiliza ninguna clase de java.util.
 *
 * @param <T> Tipo de dato almacenado en la pila.
 */
public class Pila<T> {

    // Nodo interno de la pila
    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> tope;
    private int tamanio;

    public Pila() {
        this.tope = null;
        this.tamanio = 0;
    }

    /**
     * Apila un elemento en la cima de la pila.
     *
     * @param dato Elemento a apilar.
     */
    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.siguiente = tope;
        tope = nuevo;
        tamanio++;
    }

    /**
     * Desapila y retorna el elemento en la cima.
     *
     * @return Elemento en la cima.
     * @throws RuntimeException si la pila está vacía.
     */
    public T pop() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía.");
        }
        T dato = tope.dato;
        tope = tope.siguiente;
        tamanio--;
        return dato;
    }

    /**
     * Retorna el elemento en la cima sin desapilarlo.
     *
     * @return Elemento en la cima.
     * @throws RuntimeException si la pila está vacía.
     */
    public T peek() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía.");
        }
        return tope.dato;
    }

    /**
     * Indica si la pila no tiene elementos.
     */
    public boolean estaVacia() {
        return tope == null;
    }

    /**
     * Retorna el número de elementos en la pila.
     */
    public int getTamanio() {
        return tamanio;
    }

    /**
     * Alias de getTamanio() — compatible con la interfaz esperada en Main.
     */
    public int size() {
        return tamanio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pila (tope -> fondo): [");
        Nodo<T> actual = tope;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) sb.append(", ");
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}
