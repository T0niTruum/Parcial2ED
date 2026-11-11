package co.edu.uniquindio.estructuras;

import co.edu.uniquindio.estructuras.ejercicios.Punto2Historial;
import co.edu.uniquindio.estructuras.ejercicios.Punto4ABB;

import java.util.Scanner;

/**
 * Menú principal para ejecutar las pruebas de los puntos 2 y 4.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer opcion;

        do {
            System.out.println("\n===== PARCIAL 2 - ESTRUCTURAS DE DATOS =====");
            System.out.println("1. Punto 2: Historial (Datos de prueba predeterminados)");
            System.out.println("2. Punto 2: Historial (Ingresar datos manualmente)");
            System.out.println("3. Punto 4: ABB Equipos (Datos de prueba predeterminados)");
            System.out.println("4. Punto 4: ABB Equipos (Ingresar datos manualmente)");
            System.out.println("0. Salir");
            opcion = leerOpcion(scanner);

            if (opcion == null) {
                System.out.println("\nFin de la entrada. ¡Hasta luego!");
                break;
            }
            switch (opcion) {
                case 1 -> Punto2Historial.ejecutarDatosQuemados();
                case 2 -> Punto2Historial.ejecutarDatosDinamicos(scanner);
                case 3 -> Punto4ABB.ejecutarDatosQuemados();
                case 4 -> Punto4ABB.ejecutarDatosDinamicos(scanner);
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }

    /**
     * Lee una opción del menú y vuelve a solicitarla mientras no sea un entero.
     *
     * @param scanner lector compartido por el menú y los ejercicios
     * @return opción ingresada o {@code null} si se terminó la entrada
     */
    private static Integer leerOpcion(Scanner scanner) {
        while (true) {
            System.out.print("Seleccione una opción: ");
            if (!scanner.hasNextLine()) {
                return null;
            }

            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Ingrese un número entero.");
            }
        }
    }
}
