package co.edu.uniquindio.estructuras.ejercicios;

import co.edu.uniquindio.estructuras.estructuras.Pila;

import java.util.Scanner;

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
        if (pila == null) {
            throw new IllegalArgumentException("La pila no puede ser null.");
        }
        if (inicio < 1 || fin < inicio) {
            throw new IllegalArgumentException(
                    "Rango inválido: inicio=" + inicio + ", fin=" + fin);
        }

        int total = pila.size();
        if (fin > total) {
            throw new IllegalArgumentException(
                    "fin=" + fin + " supera el tamaño de la pila (" + total + ").");
        }

        Pila<T> auxiliar = new Pila<>();
        Pila<T> segmento = new Pila<>();

        // La copia preserva el orden y deja la pila original restaurada.
        Pila<T> copia = clonar(pila, auxiliar);

        int posicion = 1;
        Pila<T> segmentoInverso = new Pila<>();

        while (!copia.estaVacia()) {
            T elemento = copia.pop();
            if (posicion >= inicio) {
                segmentoInverso.push(elemento);
            }
            posicion++;
            if (posicion > fin) {
                break;
            }
        }

        while (!segmentoInverso.estaVacia()) {
            segmento.push(segmentoInverso.pop());
        }

        return segmento;
    }

    /**
     * Crea una copia independiente de la pila preservando su orden.
     */
    private static <T> Pila<T> clonar(Pila<T> original, Pila<T> auxiliar) {
        Pila<T> temp = new Pila<>();
        while (!original.estaVacia()) {
            temp.push(original.pop());
        }

        while (!temp.estaVacia()) {
            T elemento = temp.pop();
            original.push(elemento);
            auxiliar.push(elemento);
        }
        return auxiliar;
    }

    /**
     * Ejecuta el caso predeterminado del parcial y muestra que la pila fuente
     * conserva todos sus elementos y su orden.
     */
    public static void ejecutarDatosQuemados() {
        System.out.println("\n--- PRUEBA PUNTO 2: HISTORIAL DE LA PILA ---");

        Pila<String> historial = new Pila<>();
        historial.push("A1");
        historial.push("A2");
        historial.push("A3");
        historial.push("A4");
        historial.push("A5");
        historial.push("A6");

        String estadoOriginal = historial.toString();
        System.out.println("Pila original: " + estadoOriginal);

        int inicio = 2;
        int fin = 4;
        System.out.println("Obteniendo segmento de la posición " + inicio + " a la " + fin + "...");

        Pila<String> segmento = obtenerSegmento(historial, inicio, fin);

        System.out.println("Segmento obtenido: " + segmento);
        System.out.println("Resultado esperado: A5, A4, A3 (cima a fondo).");
        System.out.println("Pila original después de la consulta: " + historial);
        System.out.println("¿La pila original quedó intacta? "
                + estadoOriginal.equals(historial.toString()));
    }

    /**
     * Solicita al usuario los elementos de una pila y las posiciones del
     * segmento que desea consultar.
     *
     * @param scanner lector de consola compartido con el menú principal
     */
    public static void ejecutarDatosDinamicos(Scanner scanner) {
        System.out.println("\n--- PUNTO 2: INGRESO MANUAL DEL HISTORIAL ---");
        Integer cantidad = leerEntero(scanner, "¿Cuántos elementos desea ingresar? ", 0);
        if (cantidad == null) {
            return;
        }
        if (cantidad == 0) {
            System.out.println("La pila está vacía; no hay segmento que consultar.");
            return;
        }

        Pila<String> historial = new Pila<>();
        for (int i = 1; i <= cantidad; i++) {
            String elemento = leerLinea(scanner, "Ingrese el elemento " + i + ": ");
            if (elemento == null) {
                return;
            }
            historial.push(elemento);
        }

        System.out.println("Pila creada (cima a fondo): " + historial);
        Integer inicio = leerEntero(scanner, "Posición inicial (desde 1, contando desde la cima): ", 1);
        if (inicio == null) {
            return;
        }
        Integer fin = leerEntero(scanner, "Posición final (desde la cima): ", 1);
        if (fin == null) {
            return;
        }

        String estadoOriginal = historial.toString();
        try {
            Pila<String> segmento = obtenerSegmento(historial, inicio, fin);
            System.out.println("Segmento obtenido (cima a fondo): " + segmento);
            System.out.println("Pila original después de la consulta: " + historial);
            System.out.println("¿La pila original quedó intacta? "
                    + estadoOriginal.equals(historial.toString()));
        } catch (IllegalArgumentException e) {
            System.out.println("No se pudo obtener el segmento: " + e.getMessage());
        }
    }

    private static Integer leerEntero(Scanner scanner, String mensaje, int minimo) {
        while (true) {
            String entrada = leerLinea(scanner, mensaje);
            if (entrada == null) {
                return null;
            }
            try {
                int valor = Integer.parseInt(entrada.trim());
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("Ingrese un entero mayor o igual que " + minimo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }
    }

    private static String leerLinea(Scanner scanner, String mensaje) {
        System.out.print(mensaje);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }
}
