package co.edu.uniquindio.estructuras.ejercicios;

import co.edu.uniquindio.estructuras.estructuras.ArbolABB;

import java.util.Scanner;

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
     * @param nodo Nodo raíz del subárbol a evaluar (puede ser null).
     * @param min  Límite inferior del rango (inclusivo).
     * @param max  Límite superior del rango (inclusivo).
     * @return Cantidad de nodos cuyo dato está en [min, max].
     * @throws IllegalArgumentException si el límite inferior supera al superior
     */
    public static int contarEquiposEnRango(ArbolABB.Nodo<Integer> nodo, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("El límite inferior no puede superar al superior.");
        }
        if (nodo == null) return 0;

        int valor = nodo.dato;

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

    /**
     * Ejecuta el caso predeterminado del parcial: inserta los códigos dados
     * y cuenta los equipos en el rango inclusivo [15, 45].
     */
    public static void ejecutarDatosQuemados() {
        System.out.println("\n--- PRUEBA PUNTO 4: CONSULTA DE EQUIPOS EN RANGO (ABB) ---");

        ArbolABB<Integer> arbol = new ArbolABB<>();

        int[] codigos = {41, 20, 52, 16, 6, 13, 12, 60, 22, 47, 50, 43, 44};
        System.out.print("Insertando equipos en el ABB: ");
        for (int cod : codigos) {
            arbol.insertar(cod);
            System.out.print(cod + " ");
        }
        System.out.println();

        int limiteInf = 15;
        int limiteSup = 45;

        int resultado = contarEquiposEnRango(arbol.getRaiz(), limiteInf, limiteSup);

        System.out.println("Códigos ordenados del ABB: ");
        arbol.inOrden();
        System.out.println("Equipos en el rango [" + limiteInf + ", " + limiteSup + "]: " + resultado);
        System.out.println("Resultado esperado: 6.");
    }

    /**
     * Solicita códigos de equipos y límites de rango, y consulta el ABB propio.
     *
     * @param scanner lector de consola compartido con el menú principal
     */
    public static void ejecutarDatosDinamicos(Scanner scanner) {
        System.out.println("\n--- PUNTO 4: INGRESO MANUAL DE EQUIPOS ---");
        Integer cantidad = leerEntero(scanner, "¿Cuántos códigos de equipo desea ingresar? ", 0);
        if (cantidad == null) {
            return;
        }

        ArbolABB<Integer> arbol = new ArbolABB<>();
        for (int i = 1; i <= cantidad; i++) {
            Integer codigo = leerEntero(scanner, "Ingrese el código " + i + ": ", Integer.MIN_VALUE);
            if (codigo == null) {
                return;
            }
            arbol.insertar(codigo);
        }

        System.out.print("Códigos ordenados del ABB: ");
        arbol.inOrden();
        Integer limiteInferior = leerEntero(scanner, "Ingrese el límite inferior: ", Integer.MIN_VALUE);
        if (limiteInferior == null) {
            return;
        }
        Integer limiteSuperior = leerEntero(scanner, "Ingrese el límite superior: ", Integer.MIN_VALUE);
        if (limiteSuperior == null) {
            return;
        }

        if (limiteInferior > limiteSuperior) {
            System.out.println("Rango inválido: el límite inferior no puede superar al superior.");
            return;
        }

        int resultado = contarEquiposEnRango(arbol.getRaiz(), limiteInferior, limiteSuperior);
        System.out.println("Cantidad de equipos en el rango [" + limiteInferior + ", "
                + limiteSuperior + "]: " + resultado);
    }

    private static Integer leerEntero(Scanner scanner, String mensaje, int minimo) {
        while (true) {
            System.out.print(mensaje);
            if (!scanner.hasNextLine()) {
                return null;
            }
            String entrada = scanner.nextLine().trim();
            try {
                int valor = Integer.parseInt(entrada);
                if (valor >= minimo) {
                    return valor;
                }
                System.out.println("Ingrese un entero mayor o igual que " + minimo + ".");
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }
    }
}
