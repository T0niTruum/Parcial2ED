package main.java.co.edu.uniquindio.estructuras;

import co.edu.uniquindio.estructuras.ejercicios.Punto2Historial;
import co.edu.uniquindio.estructuras.ejercicios.Punto4ABB;

import java.util.Scanner;

/**
 * Clase principal — Menú / Ejecutor de pruebas del parcial 2.
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n===== PARCIAL 2 - ESTRUCTURAS DE DATOS =====");
            System.out.println("1. Punto 2 - Historial de acciones (Pila)");
            System.out.println("2. Punto 4 - Árbol Binario de Búsqueda (Equipos)");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> Punto2Historial.ejecutar();
                case 2 -> Punto4ABB.ejecutar();
                case 0 -> System.out.println("¡Hasta luego!");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}

