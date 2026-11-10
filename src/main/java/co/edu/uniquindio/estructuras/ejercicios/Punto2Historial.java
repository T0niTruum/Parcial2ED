package co.edu.uniquindio.estructuras.ejercicios;

import co.edu.uniquindio.estructuras.estructuras.Pila;

/**
 * Punto 2 — Operaciones sobre una Pila propia.
 *
 * Método principal:
 *   obtenerSegmento(pila, inicio, fin)
 *     Extrae los elementos ubicados entre las posiciones [inicio, fin]
 *     contadas desde el tope (tope = posición 1).
 *     - La pila ORIGINAL no se modifica.
 *     - Retorna una nueva Pila cuyo TOPE es el elemento en la posición
 *       más cercana al tope original (posición "inicio").
 *
 * Ejemplo:
 *   Pila (tope→fondo): A6, A5, A4, A3, A2, A1
 *   obtenerSegmento(pila, 2, 4)  →  Pila (tope→fondo): A5, A4, A3
 */
public class Punto2Historial {

    /**
     * Retorna una nueva Pila con los elementos en las posiciones [inicio, fin]
     * contadas desde el tope (tope = posición 1). La pila original queda intacta.
     *
     * @param pila   Pila fuente.
     * @param inicio Posición desde el tope donde comienza el segmento (inclusivo, base 1).
     * @param fin    Posición desde el tope donde termina el segmento (inclusivo, base 1).
     * @param <T>    Tipo de los elementos.
     * @return Nueva Pila con los elementos del segmento solicitado.
     * @throws IllegalArgumentException si inicio < 1, fin < inicio o fin > tamaño de la pila.
     */
    public static <T> Pila<T> obtenerSegmento(Pila<T> pila, int inicio, int fin) {
        if (inicio < 1 || fin < inicio) {
            throw new IllegalArgumentException(
                    "Rango inválido: inicio=" + inicio + ", fin=" + fin);
        }

        int total = pila.size();
        if (fin > total) {
            throw new IllegalArgumentException(
                    "fin=" + fin + " supera el tamaño de la pila (" + total + ").");
        }

        // ── Paso 1: volcar la pila original en una pila auxiliar
        //    para poder recorrerla sin destruirla.
        //    Se usa una segunda pila temporal para restaurar al final.
        Pila<T> auxiliar = new Pila<>();
        Pila<T> segmento = new Pila<>();

        // Volcamos todos los elementos a 'auxiliar' (invierte el orden)
        Pila<T> copia = clonar(pila);

        // ── Paso 2: recorrer desde el tope contando posiciones
        int posicion = 1;
        // Usamos una pila intermedia para recoger el segmento en orden inverso
        Pila<T> segmentoInverso = new Pila<>();

        while (!copia.estaVacia()) {
            T elemento = copia.pop();
            if (posicion >= inicio && posicion <= fin) {
                // Los elementos salen del tope al fondo: A6(pos1), A5(pos2)...
                // Al apilar en segmentoInverso quedan: A3(tope) → A5(fondo)
                segmentoInverso.push(elemento);
            }
            posicion++;
            if (posicion > fin) break; // ya no hay más que recolectar
        }

        // ── Paso 3: invertir segmentoInverso para que el tope quede
        //    en la posición más cercana al tope original (A5 en el ejemplo)
        while (!segmentoInverso.estaVacia()) {
            segmento.push(segmentoInverso.pop());
        }

        return segmento;
        // La pila original nunca fue tocada; sólo se trabajó sobre 'copia'.
    }

    /**
     * Crea una copia independiente de la pila preservando el orden original.
     */
    private static <T> Pila<T> clonar(Pila<T> original) {
        // Primera pasada: invertir en auxiliar
        Pila<T> aux = new Pila<>();
        Pila<T> aux2 = new Pila<>();

        // Necesitamos una copia sin tocar 'original'.
        // Estrategia: pasar todos a aux (queda invertido), luego de aux a aux2 (orden original).
        // Para eso necesitamos poder recorrer la original sin modificarla —
        // pero pop() la modifica. Usamos un truco de doble inversión con vaciado y re-llenado:

        // ── vaciamos 'original' a 'aux' (invierte)
        Pila<T> temp = new Pila<>();
        while (!original.estaVacia()) {
            temp.push(original.pop());
        }
        // temp tiene los elementos con el fondo original arriba.
        // Ahora restituimos original Y llenamos aux2 (copia).
        while (!temp.estaVacia()) {
            T e = temp.pop();
            original.push(e); // restaura
            aux2.push(e);     // copia en mismo orden
        }
        // original queda restaurada, aux2 es una copia con el mismo tope
        return aux2;
    }

    // Método de demostración
    
    public static void ejecutar() {
        System.out.println("\n--- PRUEBA PUNTO 2: HISTORIAL DE LA PILA ---");

        Pila<String> historial = new Pila<>();
        // Se cargan de A1 (fondo) a A6 (cima) según el enunciado del parcial
        historial.push("A1");
        historial.push("A2");
        historial.push("A3");
        historial.push("A4");
        historial.push("A5");
        historial.push("A6");

        System.out.println("Pila original creada con 6 elementos (A6 en la cima).");

        int inicio = 2;
        int fin = 4;
        System.out.println("Obteniendo segmento de la posición " + inicio + " a la " + fin + "...");

        Pila<String> segmento = obtenerSegmento(historial, inicio, fin);

        System.out.println("Pila segmento retornada (tamaño: " + segmento.size() + "):");
        System.out.println("Cima del segmento: " + segmento.peek()); // Esperado: A5

        System.out.println("Verificando pila original (debe conservar sus 6 elementos intactos):");
        System.out.println("Cima de la pila original: " + historial.peek()); // Esperado: A6
        System.out.println("Tamaño actual de la pila original: " + historial.size());
    }
}

