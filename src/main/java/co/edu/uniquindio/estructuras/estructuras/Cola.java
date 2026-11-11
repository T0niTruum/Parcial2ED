package co.edu.uniquindio.estructuras.estructuras;

public class Cola<T> {

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamanio;

    public Cola() {
        frente = null;
        fin = null;
        tamanio = 0;
    }

    // Agrega un elemento al final de la cola
    public void encolar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.siguiente = nuevo;
        }
        fin = nuevo;
        tamanio++;
    }

    // Saca y retorna el elemento del frente
    public T desencolar() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía.");
        }
        T dato = frente.dato;
        frente = frente.siguiente;
        if (frente == null) {
            fin = null;
        }
        tamanio--;
        return dato;
    }

    // Retorna el elemento del frente sin sacarlo
    public T verFrente() {
        if (estaVacia()) {
            throw new RuntimeException("La cola está vacía.");
        }
        return frente.dato;
    }

    public boolean estaVacia() {
        return frente == null;
    }

    public int size() {
        return tamanio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Cola (frente -> final): [");
        Nodo<T> actual = frente;
        while (actual != null) {
            sb.append(actual.dato);
            if (actual.siguiente != null) {
                sb.append(", ");
            }
            actual = actual.siguiente;
        }
        sb.append("]");
        return sb.toString();
    }
}