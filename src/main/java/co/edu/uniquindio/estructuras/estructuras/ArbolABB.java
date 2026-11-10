package co.edu.uniquindio.estructuras.estructuras;

/**
 * Implementación propia de un Árbol Binario de Búsqueda (ABB) genérico.
 * El tipo T debe implementar Comparable para poder ordenarse.
 * No utiliza ninguna clase de java.util.
 *
 * @param <T> Tipo de dato almacenado (debe implementar Comparable<T>).
 */
public class ArbolABB<T extends Comparable<T>> {

    /**
     * Nodo del árbol — público para permitir que código externo
     * (como Punto4ABB) reciba y navegue el nodo raíz.
     */
    public static class Nodo<T> {
        public T dato;
        public Nodo<T> izquierdo;
        public Nodo<T> derecho;

        Nodo(T dato) {
            this.dato = dato;
            this.izquierdo = null;
            this.derecho = null;
        }
    }

    private Nodo<T> raiz;

    public ArbolABB() {
        this.raiz = null;
    }

    // ─────────────────────────────────────────
    // Insertar
    // ─────────────────────────────────────────

    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) {
            return new Nodo<>(dato);
        }
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, dato);
        } else if (cmp > 0) {
            nodo.derecho = insertarRec(nodo.derecho, dato);
        }
        // Si cmp == 0 el elemento ya existe; no se duplica
        return nodo;
    }

    // ─────────────────────────────────────────
    // Buscar
    // ─────────────────────────────────────────

    public T buscar(T dato) {
        return buscarRec(raiz, dato);
    }

    private T buscarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp == 0) return nodo.dato;
        if (cmp < 0) return buscarRec(nodo.izquierdo, dato);
        return buscarRec(nodo.derecho, dato);
    }

    public boolean contiene(T dato) {
        return buscar(dato) != null;
    }

    // ─────────────────────────────────────────
    // Eliminar
    // ─────────────────────────────────────────

    public void eliminar(T dato) {
        raiz = eliminarRec(raiz, dato);
    }

    private Nodo<T> eliminarRec(Nodo<T> nodo, T dato) {
        if (nodo == null) return null;
        int cmp = dato.compareTo(nodo.dato);
        if (cmp < 0) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, dato);
        } else if (cmp > 0) {
            nodo.derecho = eliminarRec(nodo.derecho, dato);
        } else {
            // Nodo encontrado
            if (nodo.izquierdo == null) return nodo.derecho;
            if (nodo.derecho == null)   return nodo.izquierdo;
            // Nodo con dos hijos: reemplazar con el sucesor in-orden (mínimo del subárbol derecho)
            Nodo<T> sucesor = minimoNodo(nodo.derecho);
            nodo.dato = sucesor.dato;
            nodo.derecho = eliminarRec(nodo.derecho, sucesor.dato);
        }
        return nodo;
    }

    private Nodo<T> minimoNodo(Nodo<T> nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // ─────────────────────────────────────────
    // Recorridos
    // ─────────────────────────────────────────

    /** Recorrido In-Orden (izquierda, raíz, derecha) — produce salida ordenada. */
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(Nodo<T> nodo) {
        if (nodo == null) return;
        inOrdenRec(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRec(nodo.derecho);
    }

    /** Recorrido Pre-Orden (raíz, izquierda, derecha). */
    public void preOrden() {
        preOrdenRec(raiz);
        System.out.println();
    }

    private void preOrdenRec(Nodo<T> nodo) {
        if (nodo == null) return;
        System.out.print(nodo.dato + " ");
        preOrdenRec(nodo.izquierdo);
        preOrdenRec(nodo.derecho);
    }

    /** Recorrido Post-Orden (izquierda, derecha, raíz). */
    public void postOrden() {
        postOrdenRec(raiz);
        System.out.println();
    }

    private void postOrdenRec(Nodo<T> nodo) {
        if (nodo == null) return;
        postOrdenRec(nodo.izquierdo);
        postOrdenRec(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

    // ─────────────────────────────────────────
    // Utilidades
    // ─────────────────────────────────────────

    public boolean estaVacio() {
        return raiz == null;
    }

    /**
     * Retorna el nodo raíz del árbol.
     * Permite que código externo (ej. Punto4ABB) navegue el árbol
     * sin exponer la estructura interna a través de colecciones.
     */
    public Nodo<T> getRaiz() {
        return raiz;
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(Nodo<T> nodo) {
        if (nodo == null) return 0;
        return 1 + Math.max(alturaRec(nodo.izquierdo), alturaRec(nodo.derecho));
    }
}
