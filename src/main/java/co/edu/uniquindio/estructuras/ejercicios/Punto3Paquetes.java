package co.edu.uniquindio.estructuras.ejercicios;
import co.edu.uniquindio.estructuras.estructuras.Cola;
import co.edu.uniquindio.estructuras.model.Paquete;

public class Punto3Paquetes {

    // Selecciona los paquetes para la ruta y los retira de la cola de pendientes
    public static Cola<Paquete> seleccionarPaquetes(Cola<Paquete> pendientes, int capacidad) {
        Cola<Paquete> seleccionados = new Cola<>();

        // Si no hay capacidad, no se selecciona nada y pendientes queda igual
        if (capacidad <= 0) {
            return seleccionados;
        }

        System.out.println("Evaluando paquetes URGENTES:");
        int restante = procesarTipo(pendientes, seleccionados, "URGENTE", capacidad, pendientes.size());

        System.out.println("Evaluando paquetes NORMALES:");
        procesarTipo(pendientes, seleccionados, "NORMAL", restante, pendientes.size());

        return seleccionados;
    }

    // Recorre n paquetes de la cola (recursivamente) y retorna la capacidad que queda.
    // Cada paquete que se saca del frente o se selecciona, o vuelve al final de pendientes.
    private static int procesarTipo(Cola<Paquete> pendientes, Cola<Paquete> seleccionados,
                                    String tipo, int capacidad, int n) {
        // Caso base: ya se revisaron todos los paquetes
        if (n == 0) {
            return capacidad;
        }

        Paquete p = pendientes.desencolar();

        if (p.getTipo().equals(tipo)) {
            if (p.getPeso() <= capacidad) {
                System.out.println("  " + p + " -> SELECCIONADO (capacidad restante: "
                        + (capacidad - p.getPeso()) + " kg)");
                seleccionados.encolar(p);
                return procesarTipo(pendientes, seleccionados, tipo, capacidad - p.getPeso(), n - 1);
            }
            System.out.println("  " + p + " -> PENDIENTE (no cabe, capacidad restante: "
                    + capacidad + " kg)");
        }

        // No fue seleccionado: vuelve al final de la cola de pendientes
        pendientes.encolar(p);
        return procesarTipo(pendientes, seleccionados, tipo, capacidad, n - 1);
    }

    // Suma los pesos de una cola (recursivo) dejándola en su orden original
    private static int sumarPesos(Cola<Paquete> cola, int n) {
        if (n == 0) {
            return 0;
        }
        Paquete p = cola.desencolar();
        cola.encolar(p);
        return p.getPeso() + sumarPesos(cola, n - 1);
    }

    public static void ejecutar() {
        System.out.println("\n PUNTO 3: SELECCION DE PAQUETES ");

        Cola<Paquete> pendientes = new Cola<>();
        pendientes.encolar(new Paquete("P1", "NORMAL", 4));
        pendientes.encolar(new Paquete("P2", "URGENTE", 7));
        pendientes.encolar(new Paquete("P3", "NORMAL", 3));
        pendientes.encolar(new Paquete("P4", "URGENTE", 5));
        pendientes.encolar(new Paquete("P5", "NORMAL", 6));
        pendientes.encolar(new Paquete("P6", "URGENTE", 4));
        pendientes.encolar(new Paquete("P7", "NORMAL", 2));

        int capacidad = 14;
        System.out.println("Cola inicial: " + pendientes);
        System.out.println("Capacidad disponible: " + capacidad + " kg\n");

        Cola<Paquete> seleccionados = seleccionarPaquetes(pendientes, capacidad);

        int usado = sumarPesos(seleccionados, seleccionados.size());

        System.out.println("\nCola retornada: " + seleccionados);
        System.out.println("Cola de pendientes: " + pendientes);
        System.out.println("Capacidad que quedó disponible: " + (capacidad - usado) + " kg");
    }

    // Para probar este punto solo, sin tocar Main
    public static void main(String[] args) {
        ejecutar();
    }
}