package co.edu.uniquindio.estructuras.ejercicios;

import co.edu.uniquindio.estructuras.estructuras.ArbolABB;
import co.edu.uniquindio.estructuras.model.Equipo;

/**
 * Punto 4 — Operaciones sobre el Árbol Binario de Búsqueda.
 *
 * Método principal:
 *   contarEquiposEnRango(nodo, min, max)
 *     Cuenta cuántos nodos del ABB tienen un valor en el rango [min, max].
 *     Aprovecha la propiedad del ABB para podar ramas y no recorrer
 *     subárboles que nunca podrán estar dentro del rango.
 *
 * Ejemplo con el árbol del Main:
 *   Nodos en [15, 45]: 16, 20, 22, 41, 43, 44  → 6 nodos
 *   (el 13, 12, 6 quedan por debajo de 15; el 47, 50, 52, 60 quedan por encima de 45)
 */
public class Punto4ABB {

    /**
     * Cuenta los nodos del ABB cuyo valor está en el rango [min, max] (inclusivo).
     * Usa la propiedad del ABB para podar subárboles fuera de rango.
     *
     * Se declara con raw type en la firma para coincidir con el uso en Main
     * (ArbolABB sin parámetro de tipo), pero internamente opera sobre Comparable.
     *
     * @param nodo Nodo raíz del subárbol a evaluar (puede ser null).
     * @param min  Límite inferior del rango (inclusivo).
     * @param max  Límite superior del rango (inclusivo).
     * @return Cantidad de nodos cuyo dato está en [min, max].
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static int contarEquiposEnRango(ArbolABB.Nodo nodo, int min, int max) {
        if (nodo == null) return 0;

        // El dato del nodo es Comparable (Integer en la prueba del Main)
        int valor = (int) nodo.dato;

        if (valor < min) {
            // Todo el subárbol izquierdo también está por debajo de min → solo explorar derecha
            return contarEquiposEnRango(nodo.derecho, min, max);
        }

        if (valor > max) {
            // Todo el subárbol derecho también está por encima de max → solo explorar izquierda
            return contarEquiposEnRango(nodo.izquierdo, min, max);
        }

        // valor está en [min, max]: contar este nodo + ambos subárboles
        return 1
                + contarEquiposEnRango(nodo.izquierdo, min, max)
                + contarEquiposEnRango(nodo.derecho, min, max);
    }

    // Método de demostración (no usado por Main, útil para pruebas)
    
    public static void ejecutar() {
        System.out.println("\n--- PRUEBA PUNTO 4: CONSULTA DE EQUIPOS EN RANGO (ABB) ---");

        ArbolABB arbol = new ArbolABB();

        // Secuencia de inserción según el enunciado del parcial
        int[] codigos = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 43, 44};
        System.out.print("Insertando equipos en el ABB: ");
        for (int cod : codigos) {
            arbol.insertar(cod);
            System.out.print(cod + " ");
        }
        System.out.println();

        // Rango de prueba
        int limiteInf = 15;
        int limiteSup = 45;

        int resultado = contarEquiposEnRango(arbol.getRaiz(), limiteInf, limiteSup);

        System.out.println("Equipos en el rango [" + limiteInf + ", " + limiteSup + "]: " + resultado);
    }
}
